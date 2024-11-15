# Group-Project-Fall-24 <br />
_**L30-Team 1: Oscar Katz, Tommy Wei, Srinidhi Saravanan, Christine Xu**_

**Compiling and Running Our Project** <br />
For Phase 2, our project will still be outputting to the terminal; however a server and multiple clients (threads) will be implemented through a client class. Both the Server and Client classes will have to be running and compiled in order for our program to compile correctly. 

**Submission Details** <br />
- For Brightspace:
- For Vocareum:

**Newly Added Interfaces** <br />
- ClientInterface.java: The interface that the Client class will implement.
- ServerInterface.java: The interface that the Server class will implement.
- Runnable: Not an interface that we created, but the Server class will implement it so that our program can be thread-safe.

**Newly Added Classes** <br />
- **Client.java:** <br />
This class will store everything that a client does that interacts with our server/program. 
- **Server.java:** <br />
