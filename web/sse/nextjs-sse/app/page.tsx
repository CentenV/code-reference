"use client";
import { Suspense, useEffect, useState } from "react";

export default function Home() {
  const [status, updateStatus] = useState<string>("");

  function activeConnection(event: React.MouseEvent) {
    event.preventDefault();
    const source = new EventSource("/api/stream");
    source.addEventListener("my-sse", event => {
      console.log(event.data);
      let payload = JSON.parse(event.data);
      updateStatus(payload.data.message);
      if (payload.data.status == "terminated") { source.close(); }
    });
  }

  return (
    <main>
      <button onClick={activeConnection}>Start</button>
      <pre>{status}</pre>
    </main>
  );
}