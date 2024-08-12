# Server Sent Events (SSE) in NextJS
NextJS implementation of server sent events

Server-Sent Events (SSE) is a technology that allows a server to push updates to the client over a single, long-lived HTTP connection. It is a part of the HTML5 standard and provides a way for servers to send real-time updates to clients without the need for the client to repeatedly poll the server for new data.
[Mozilla - Using server-sent events](https://developer.mozilla.org/en-US/docs/Web/API/Server-sent_events/Using_server-sent_events)

### Caveats
- There is no mechanism for halting the `longRunningFunction` server function call in `/api/stream`. Any termination of the SSE subscription would result in the function's continuation until completion
- Building the application with `npm run build` and running in production `npm run start` will cache the output of the API. Building and running the application in production mode is NOT RECOMMENDED. ONLY RUN IN DEVELOPMENT MODE with `npm run dev`
- The application build process will be stuck if the server sent event GET route is not terminated properly. Running `npm run build` in a server sent event route without calling the method `writer.close()` after the miscellaneous logic has completed will result in an application consistently in the build command

### Running
Install all necessary packages with 

```npm install```

Run application (development mode)

```npm run dev```