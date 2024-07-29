import { Client } from "@stomp/stompjs";

// Create a new STOMP client
const client = new Client({
  brokerURL: "ws://localhost:8080/subscribe",
  debug: (str) => {
    console.log(new Date(), str);
  },
  onConnect: () => {
    console.log("Connected to the broker");

    // Subscribe to the /topic/greetings topic
    client.subscribe("/topic/greetings/test", (message) => {
      console.log("Received message: " + message.body);
    });

    // Send a message to the /hello endpoint
    const helloMessage = { name: "World" };
    client.publish({
      destination: "/app/hello",
      body: JSON.stringify(helloMessage),
    });
  },
  onStompError: (frame) => {
    console.error("Broker reported error: " + frame.headers["message"]);
    console.error("Additional details: " + frame.body);
  },
  onWebSocketError: (error) => {
    console.error("WebSocket error: ", error);
  },
  onWebSocketClose: (event) => {
    console.log("WebSocket closed: ", event);
  },
});

// Activate the client
client.activate();
