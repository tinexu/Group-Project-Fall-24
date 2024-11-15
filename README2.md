# Group-Project-Fall-24 <br />
_**L30-Team 1: Oscar Katz, Tommy Wei, Srinidhi Saravanan, Christine Xu**_

**Compiling and Running Our Project** <br />
For Phase 2, our project will still be outputting to the terminal; however a server and multiple clients (threads) will be implemented through a client class. Both the Server and Client classes will have to be running and compiled in order for our program to compile correctly. 

**Submission Details** <br />
- For Brightspace: Nothing to be submitted yet.
- For Vocareum: Christine Xu

**Newly Added Interfaces** <br />
- ClientInterface.java: The interface that the Client class will implement.
- ServerInterface.java: The interface that the Server class will implement.
- Runnable: Not an interface that we created, but the Server class will implement it so that our program can be thread-safe.

**Newly Added Classes** <br />
- **Client.java:** <br />
This class will store everything that a client does that interacts with our server/program. The server will process all of the data stored in the database and the data that is given to the server by the client via this class. Client.java serves more to provide the bridge between the server and client by allowing the client to interact with a visual interface. 
- **Server.java:** <br />
This class will contain everything that goes into server processing for our program. For instance, some functionalities might be searching through the ArrayList instance variable of users in order to make sure that login credentials are correct, and that new users are not making a new username with an existing one. 
