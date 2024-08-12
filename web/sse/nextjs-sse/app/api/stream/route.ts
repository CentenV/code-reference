import { NextRequest, NextResponse } from "next/server";

function delay(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
function isObject(value: any) {
    return value !== null && typeof value === "object";
}

export function GET(request: NextRequest) {
    const responseStream = new TransformStream();
    const writer = responseStream.writable.getWriter();
    const encoder = new TextEncoder();

    function toDataString(data: string | Record<string, any>): string {
        if (isObject(data)) {
            return toDataString(JSON.stringify(data));
        }
        return data.split(/\r\n|\r|\n/).map((line: string) => `data: ${line}\n\n`).join("");
    }

    // Terminate
    function terminate() {
        writer.close();
    }
    
    // Closed subscription
    request.signal.onabort = () => {
        console.log("Connection aborted");
        terminate();
    }

    const EVENT_NAME = "my-sse";
    let broadcastData = { data: { message: "", status: "run" } };
    async function longRunningFunction() {
        // Message update 1
        broadcastData.data.message = broadcastData.data.message + "\n" + "Loading Code";
        writer.write(encoder.encode(`event: ${EVENT_NAME}\n`));
        writer.write(encoder.encode(toDataString(broadcastData)));
        await delay(1500);
        // Message update 2
        broadcastData.data.message = broadcastData.data.message + "\n" + "Executing Program";
        writer.write(encoder.encode(`event: ${EVENT_NAME}\n`));
        writer.write(encoder.encode(toDataString(broadcastData)));
        await delay(1500);
        // Message update 3
        broadcastData.data.message = broadcastData.data.message + "\n" + "Running Unit Tests";
        writer.write(encoder.encode(`event: ${EVENT_NAME}\n`));
        writer.write(encoder.encode(toDataString(broadcastData)));
        await delay(1500);
        // Message update 4
        broadcastData.data.message = broadcastData.data.message + "\n" + "Validating Code";
        writer.write(encoder.encode(`event: ${EVENT_NAME}\n`));
        writer.write(encoder.encode(toDataString(broadcastData)));
        await delay(1500);
        // Message update 5
        broadcastData.data.message = broadcastData.data.message + "\n" + "Done";
        broadcastData.data.status = "terminated";
        writer.write(encoder.encode(`event: ${EVENT_NAME}\n`));
        writer.write(encoder.encode(toDataString(broadcastData)));

        terminate();
    }
    
    let funcInstance = longRunningFunction();

    return new NextResponse(responseStream.readable, { 
        headers: {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "text/event-stream; charset=utf-8",
            Connection: "keep-alive",
            "Cache-Control": "no-cache, no-transform",
            "X-Accel-Buffering": "no",
            "Content-Encoding": "none",
        }
    });
}