// Server side operations - Rest API
import express, { response } from "express";
import cors from "cors";
import { checkCredential } from "./accounts";
import { createHash } from "crypto";
import { Database, OPEN_CREATE, OPEN_READWRITE, sqlite3 } from "sqlite3";
import process from "process";
import { unlinkSync, existsSync } from "fs";

const APP = express();
const PORT = 8000;
const SERVER_SIDE_SESSION_STORE_DB_LOCATION = "./session_store.db";

// Verifying that no database file exists before app starts
if (existsSync(SERVER_SIDE_SESSION_STORE_DB_LOCATION)) {
    console.log(`There exists ${SERVER_SIDE_SESSION_STORE_DB_LOCATION}. Deleting old db file`);
    unlinkSync(SERVER_SIDE_SESSION_STORE_DB_LOCATION);
}

// Init ExpressJS
APP.use(express.json());
APP.use(cors({ origin: "http://localhost:5173", credentials: true }));

APP.listen(PORT, () => {
    console.log(`Rest API Server on ${PORT}`);
});

// Message for every page
APP.get("/message", (request, response) => {
    const DATE = new Date(Date.now());
    response.send({ message: `Welcome, it is currently ${DATE.toLocaleTimeString()} on ${DATE.toLocaleDateString()}` });
});
// Sample message to test authentication validity
APP.get("/test", (request, response) => {
    console.log("called");
    if (request.headers.cookie != undefined) { 
        console.log(request.headers.cookie); 
        response.status(200).send({ "message": "You are currently authenticated" });
    }
    else {
        response.status(401).send({ "message": "No cookie provided" });
    }
});

// Server-side session management
const DB = new Database(SERVER_SIDE_SESSION_STORE_DB_LOCATION, OPEN_READWRITE | OPEN_CREATE, (error) => { (error) ? console.error(error) : console.log(`SQLite database ${SERVER_SIDE_SESSION_STORE_DB_LOCATION} created`) });
DB.serialize(() => DB.run("CREATE TABLE sessions(uuid TEXT NOT NULL, sessionId TEXT NOT NULL, expiration INTEGER NOT NULL, PRIMARY KEY(uuid, sessionId));"));
APP.post("/sss/auth", async (request, response) => {
    // Convert the request body to a format to be put through system
    const inputtedCredentials = await request.body as { username: string, password: string };
    console.log("Requested auth: " + JSON.stringify(inputtedCredentials));
    // Check credential
    const credCheck = checkCredential(inputtedCredentials);
    if (credCheck != null) {
        // SERVER-SIDE SESSION MANAGEMENT
        // Generate a token and store it in the database (SQLite)
        const CURRENT_TIME = Date.now();
        const sessionId = createHash("sha256").update(`${credCheck.uuid}${credCheck.username}${CURRENT_TIME}`).digest("hex");
        const expiration = CURRENT_TIME + 15000;
        // Storing the session id, expiration, and delegating a task to delete it after some time
        // !!! SQLite for the application is not feasible for real world application since production deployments tend to have multiple NodeJS instances. Use standalone databases instead (i.e. PostgreSQL, MySQL, etc.) !!!
        DB.run(`INSERT INTO sessions(uuid, sessionId, expiration) VALUES ('${credCheck.uuid}', '${sessionId}', ${expiration})`);
        console.log(`Authenticated ${credCheck} at ${CURRENT_TIME}, expires ${expiration}`);
        // Return empty data with the session id as a cookie back to the user
        response.status(200).header({ "Set-Cookie": `sessionId=${sessionId}` }).send();
    }
    else {
        response.status(401).send({});
    }
});
// Debug route
APP.get("/sss/debug", async (request, response) => {
    DB.all("SELECT * FROM sessions;", (err, rows) => {
        if (err) {
            console.error("Error in getting all the server-side sessions at /sss/debug");
            response.status(500).send({});
        }
        
        response.status(200).send(rows);
    });
});

// JSON Web Token
APP.post("/jwt", async (request, response) => {
    // Convert the request body to a format to be put through system
    const inputtedCredentials = await request.body as { username: string, password: string };
    console.log("Requested auth: " + JSON.stringify(inputtedCredentials));
    // Check credential
    const credCheck = checkCredential(inputtedCredentials);
    if (credCheck != null) {
        // SERVER-SIDE SESSION MANAGEMENT
        // Generate a token and store it in the database (SQLite)
        const sessionId = createHash("sha256").update(`${credCheck.uuid}${credCheck.username}${Date.now()}`).digest("hex");

        // Return empty data with the session id as a cookie back to the user
        response.status(200).header({ "Set-Cookie": `sessionId=${sessionId}` }).send();
    }
    else {
        response.status(401).send({});
    }
});


// Application cleanup
const serverShutdown = (code: any) => {
    console.log(`Exiting on code ${code}\nRemoving ${SERVER_SIDE_SESSION_STORE_DB_LOCATION}`);
    DB.close();
    unlinkSync(SERVER_SIDE_SESSION_STORE_DB_LOCATION);
}

process.on("SIGTERM", serverShutdown);
process.on("SIGINT", serverShutdown);
process.on("exit", serverShutdown);