

<div align="center">
        <h1>Dragons' Dice Duel</h1>
        <h3>A Simple Turn-Based Game Using gRPC with Java</h3>

![House of the Dragon - Greens vs Blacks](dragons-dice-duel.jpg)
</div>

# Dragons' Dice Duel: The Dance of the Dragons

Dragons' Dice Duel is a simple turn-based game implementation inspired by the tumultuous conflict known as the Dance of the Dragons from the [House of the Dragon](https://www.hbo.com/house-of-the-dragon) series. This project serves as a practical example of using gRPC (Google Remote Procedure Call) in Java, demonstrating how to handle communication between game clients and the server.

## Scenario: The Dance of the Dragons

In the turbulent times of the Dance of the Dragons, House Targaryen is torn asunder by bitter familial feuds and political intrigue. The realm stands divided between two powerful factions:
- **[The Greens](https://awoiaf.westeros.org/index.php/Greens)**: Loyal to Aegon II Targaryen's claim to the throne.
- **[The Blacks](https://awoiaf.westeros.org/index.php/Blacks)**: Supporting Rhaenyra Targaryen's right to rule.

Players take on the roles of these factions in a dice-rolling game, where each roll represents a strategic maneuver or diplomatic victory aimed at gaining support from influential lords and allies across Westeros.

## Gameplay Mechanics

- **Turn-based Play**: Players take turns rolling a die using gRPC to communicate with the game server.
- **Point System**: Rolling a 6 earns the player 1 point and an extra turn, symbolizing successful alliances or strategic victories.
- **Winning Condition**: The first player to reach [131](https://awoiaf.westeros.org/index.php/131_AC) points wins the game, securing their faction's claim to the Iron Throne.

### Technologies Used

- **gRPC**: Facilitates efficient communication between the game server and clients.
- **Java**: Language used for both client and server logic.

### Prerequisites
- Java 21 or higher
- Maven or Gradle for dependency management
- gRPC and Protobuf tools installed


## Getting Started

To get started with **Dragons' Dice Duel**, follow these steps to set up and run the server and client. This guide assumes you have Java 11 or higher, Maven or Gradle for dependency management, and the gRPC and Protobuf tools installed.

### 1\. Clone the Repository
First, clone the repository to your local machine:
```bash
git clone <repository-url> cd dragons-dice-duel
```

### 2\. Build the Project
Ensure you have Maven installed. Then, build the project using the following command:
```bash
mvn clean install
```

### 3\. Running the Server
The server listens for incoming gRPC requests and handles game logic. To start the server:
```bash
mvn exec:java -Dexec.mainClass="io.github.mojtabaj.data.DragonsDiceDuelServer"
```
By default, the server will run on port **6565**. Ensure no other application is using this port.

### 4\. Running the Client

The client interacts with the server, sending game requests and receiving responses. To run the client:

1.  **Ensure the Server is Running**: The client needs to connect to an actively running server.
2.  **Execute the Client**:
```bash
mvn exec:java -Dexec.mainClass="io.github.mojtabaj.data.DragonsDiceDuelClient"
```
The client will connect to the server, send join requests, and simulate dice rolls.


### 5\. Modifying Client Configuration

If you need to change the server address or port, update the `DragonsDiceDuelClient` class. Modify the `ManagedChannelBuilder` configuration to match your server settings:
```java
ManagedChannel channel = ManagedChannelBuilder
        .forAddress("localhost", 6565)
        .usePlaintext()
        .build();
```
Replace `"localhost"` and `6565` with the appropriate server address and port if necessary.


## gRPC Protocol
### Protobuf Definitions

The `.proto` file defines the data structures and service contract used in this project.

*   **`Faction`**: Enum representing the two factions in the game.
*   **`PlayerData`**: Message containing details about a player.
*   **`GameData`**: Message containing game state information and the winner.
*   **`DiceData`**: Message representing a dice roll and the player who rolled it.
*   **`GameRequest`**: Message used to send requests to the server (join game or roll dice).
*   **`GameResponse`**: Message used to send responses from the server (player joined, turn info, dice roll result, game result).

### gRPC Services

*   **`DragonsDiceDuel` Service**:
    *   **`PlayGame`**: A bidirectional streaming RPC that allows clients to send `GameRequest` messages and receive `GameResponse` messages.

