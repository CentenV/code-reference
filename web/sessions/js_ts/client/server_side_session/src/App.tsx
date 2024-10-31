import { useEffect, useRef, useState } from 'react';

const SERVER_URL = "http://localhost:8000";

function ServerSideSessionApp() {
  const [message, updateMessage] = useState<string>("");
  const [serverError, updateServerError] = useState<boolean | null>(null);  // Connection error checking
  const [loginError, updateLoginError] = useState<boolean>(false);
  const [cookieData, updateCookieData] = useState<string>(document.cookie);
  const [customMessage, updateCustomMessage] = useState<string | null>(null);

  // Form data
  const usernameText = useRef("");
  const passwordText = useRef("");
  // Get form data and submit to Rest API
  async function onSubmit(event: React.FormEvent) {
    event.preventDefault();
    // Auth and create session
    const payload = { username: usernameText.current, password: passwordText.current };
    try {
      const req = await fetch(`${SERVER_URL}/sss/auth`, { method: "POST", headers: { "Content-Type": "application/json" }, credentials: "include", body: JSON.stringify(payload) });
      console.log(req);
      if (req.status == 401) {
        updateLoginError(true);
      }
      if (req.status == 200) {
        updateLoginError(false);
        updateCookieData(document.cookie);
      }
    }
    catch (e) {
      console.error("Authentication error occurred: " + e);
    }
  }

  // 
  async function getCustomMessage(event: React.MouseEvent) { 
    event.preventDefault(); 
    const req = await fetch("/sss/debug", { method: "GET" });
    console.log(await req.text());
    // updateCustomMessage(data.message);
  }

  // Fetching a message from the server to verify that the server is up and connected
  useEffect(() => {
    async function fetchMessage() {
      try { 
        const req = await fetch(`${SERVER_URL}/message`, { method: "GET" });
        const resObj = await req.json();
        updateMessage(resObj.message);
        updateServerError(false);
      }
      catch (error) {
        updateServerError(true);
      }
    }

    fetchMessage();
  });

  // Verifying the cookie is still valid
  useEffect(() => {

  }, []);

  return (
    (serverError == null ? (<div>Loading...</div>) : 
      ((!serverError) ? 
      (
        <div id="content">
          <h1>{message}</h1>
          <form onSubmit={onSubmit} id="form-area">
            <input className="input-box" type="text" onChange={(e) => { e.preventDefault(); usernameText.current = e.target.value }} name="username" placeholder="Username" required />
            <input className="input-box" type="password" onChange={(e) => { e.preventDefault(); passwordText.current = e.target.value }} name="password" placeholder="Password" required />
            <input id="signin-button" type="submit" value="Sign In" />
          </form>
          {(!loginError) ? <div></div> : <div>Invalid username and/or password</div> }
          {(cookieData == "") ? <div>Currently not logged in</div> : <div>Cookie: {document.cookie}</div> }
          <button onClick={getCustomMessage}>Get custom message</button>
          {(customMessage != null) && <div>{customMessage}</div>}
        </div>
      )
      : 
      (
        <>
          <div>Cannot connect to server located at {SERVER_URL}</div>
          <div>Is the backend server up and on the right port?</div>
        </>
      ))
    )
  );
}

export default ServerSideSessionApp
