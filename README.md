# 🚀 WebSocket - Spring Boot Real-Time Communication

A simple **WebSocket-based real-time communication application** built using **Java, Spring Boot, and Gradle**.

This project demonstrates how to establish full-duplex communication between a client and server using the WebSocket protocol.

---

## 📌 Tech Stack

- Java 17+
- Spring Boot
- Spring WebSocket
- Gradle

---

## 🏗️ Project Structure

```
websocket/
├── src/
│   ├── main/
│   │   ├── java/                # Application source code
│   │   └── resources/           # application.properties, configs
│   └── test/                    # Unit tests
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
└── README.md
```

---

## ⚙️ How It Works

1. Client initiates an HTTP request.
2. Server upgrades the connection to WebSocket.
3. A persistent TCP connection is established.
4. Messages flow bidirectionally without HTTP polling.

---

## 🛠️ Getting Started

### ✅ Prerequisites

- Java 17 or higher
- Git
- Gradle (optional — wrapper included)

---

## 📥 Clone the Repository

```bash
git clone https://github.com/techrajdeep/websocket.git
cd websocket
```

---

## ▶️ Running the Application

### Using Gradle Wrapper (Recommended)

```bash
./gradlew clean build
./gradlew bootRun
```

### Using Installed Gradle

```bash
gradle clean build
gradle bootRun
```

---

## 🌐 Application URL

By default, the application runs at:

```
http://localhost:8080
```

---

## 🧪 Testing the WebSocket

Open your browser console and run:

```javascript
const socket = new WebSocket("ws://localhost:8080/your-endpoint");

socket.onopen = function() {
    console.log("Connected to server");
    socket.send("Hello Server!");
};

socket.onmessage = function(event) {
    console.log("Message from server:", event.data);
};

socket.onclose = function() {
    console.log("Connection closed");
};
```

> Replace `/your-endpoint` with your actual WebSocket endpoint.

---

## 📦 Build Executable Jar

```bash
./gradlew clean build
```

The jar will be generated inside:

```
build/libs/
```

Run it using:

```bash
java -jar build/libs/<your-app-name>.jar
```

---

## 🔍 Example Use Cases

- Real-time chat application
- Stock/crypto price streaming
- Notification broadcasting
- Live dashboards
- Event-driven microservices

---

## 🤝 Contributing

Contributions are welcome.

1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Push to your branch
5. Open a Pull Request

---

## 📜 License

This project is licensed under the **MIT License**.

You are free to:
- Use
- Modify
- Distribute
- Private use

With the following condition:
- Include the original copyright and license notice in any copy of the software.

See the `LICENSE` file for full details.

---

## 👨‍💻 Author

**Rajdeep Sahoo**  
Senior Backend Engineer  
Java | Distributed Systems | Data Engineering  

---

⭐ If you found this project useful, consider giving it a star.
