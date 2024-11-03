# Group-Project-Fall-24 <br />
_**L30-Team 1: Oscar Katz, Tommy Wei, Srinidhi Saravanan, Christine Xu**_

**Compling and Running our Project** <br />
For Phase 1, compiling our project will involve using the command line "javac class_name.java" for each class that wants to be ran separately, but will most likely only employ the main runner class, SocialMediaPlatformRunner, or the main method in SocialMediaPlatform. As there is no GUI/visual component yet, the running of our project will be through verifying the success of our methods and a RunLocalTest class. 

**Submission Details** <br />
For Brightspace: <br />
For Vocareum: <br />

**Existing Interfaces:**
- UserInterface.java: The interface that the User class will implement. <br />
- SocialMediaPlatformInterface.java: The interface that the SocialMediaPlatform class will implement. <br />
- PostInterface.java: The interface that the Post class will implement. <br />
- MessagingInterface.java: The interface that the Messaging class will implement. <br />
- FeedInterface.java: The interface that the Feed class will implement. <br />

**Non-Existing Interfaces (the classes that do not require interfaces, but still exist as classes):** <br />
- InvalidExceptionInterface does not exist because: The inheritance from the Exception superclass allows the Custom Exception that we created to be provided with all the necessary functionality. Custom Exceptions are generally also only used for a specific purpose, and therefore do not employ the true purpose of an interface, which is set defining behaviors that can be implemented across multiple classes. <br />
- VisualPlatformInterface does not exist because: The implementation of a GUI is highly specific so an interface would not necessarily be needed, or even add much to the implementation of the class. The structure and behavior of GUI's function similar to inheritance, and therefore do not require interfaces to be implemented. However, the future creation of an interface may prove useful, and therefore may be written later on. <br />

- **User.java:** <br />
This class stores all the functionalities of the User objects of our platform, beginning with how a new User of the platform is created (via username and password). The class also includes functionalities over how the User will add, block, or remove friends. Furthermore, the class includes processing methods, such as a .equals() method for when searching through lists, and a toString() method for displaying the User's information to a file, first, and eventually the GUI interface. The tests that were performed on this class were written either in tests for other classes, or just simply to make sure the accessor, mutator, and functional methods were working. For instance, the method, setUsername() was used in conjunction with a test for the SocialMediaPlatform.java class when testing to make sure the input that the user would eventually type in from a input dialog in our GUI could be modified to represent an acutal username present on existing social media platforms. In another example, the methods that edit the blocked and friends ArrayList fields were simply tested to make sure that our program could create a list of people (for different reasons) for each User that is created for our platform. This class is essentially the information storage center for all the Users created on our platform. It will interact with our messaging class to monitor what friends a User can message via the blocked and friends ArrayLists; it will interact with our Post and Feed class to monitor whose posts will show up on a specific User's feed; it will also interact with our overarching SocialMediaPlatform class to provide the methods necessary in reading and writing Users to our database, as well as maintaining the data stored in it (e.g. the SocialMediaPlatform class will contain an ArrayList of Users for displaying and processing).
- **SocialMediaPlatform.java:** <br />
This class is the running component for our platform and currently stores the methods that read and write to files in our database when we process data for testing. There are fundamental methods that read an input file and write to an output file, as well as a method that exists as a mediator between reading and writing (i.e. this method takes the username that a User inputs and edits it to represent an actual username on a social media platform). The class also has methods that display Users to the screen, something that will be present when the GUI is implemented. Overall, this class is responsible for getting the input that an individual puts in and pushes out what our whole program processes back to that individual. The tests performed on this class for Phase 1 of this project were focused on making sure that our program can read in input to the backend's database as well as write out what our code processed back to human visibility. For instance, the first test for our reading and writing methods employed an input file with pre-written inputs and expected outputs, and made sure that what was read in matched the input file, as well as made sure that what was written to an output file matched the input file, precisely. The second test employed a method that processed the read input and modified the text a bit (e.g the '@' character was added to each username to represent an actual username that existing social media platforms use, since a user using our program will not intentionally type that character when creating an account. This character will also serve as a useful placemarker for String methods, such as substring). Futhermore, we tested the method that adds users to an instance variable ArrayList, _users_, by, again, creating input and expected output. This function is extremely important, because it serves as the base for the method, in the same class, that displays users to the GUI screen. Furthermore, the test that was committed to the class, and the method that will eventually take care of the multiple "client" threads that will run our program, is present inside the file. This test outputs to the terminal instead of text files and creates a more GUI-like interface, due to the fact that it is more user interactive and simulates what a GUI would be fundamentally doing. It synchronizes threads as different "people" and runs a series of prompts and processes (by Scanner) to simulate logging in or creating an account. Another additional test, something that would be better used when implementing our GUI, would be to test our method that displays users to the screen (i.e. for a purpose such as, a list of users that are present on the platform that the first-person user can friend/follow). This class uses other classes, especially the User class, in order to generate what is exactly displayed to our eventual GUI. For example, this class appends new Users to the platform's list of users in order to perform a number of reading and writing processes, and it is currently tied to our Post class in order to store all of the posts on the platform, for potential future use.
- **Post.java:** <br />
This class will provide the base and fundamentals for all posts that are created and stored on our platform. This includes functionalities such as storing likes and dislikes on the post for display purposes, mainly. This class will also hold functionalities for comments on posts, including what comments are present on the post, how many comments there are for display, and who is able to comment on an individual's post. Upvotes and downvotes are also stored in relation to each post in this class. In terms of tracking what posts are hidden by their respective owners, a boolean variable is contained inside this class to process that function. Since this class is display heavy, for Phase 1, we had to test with pre-determined input and expected output in order to make sure the methods we wrote were returning correct values and modifying their respective variables accurately. For instance, for the method removeComment, we employed a pre-written ArrayList filled with comments and called the method in order to make sure that the User calling the method owns the post or was the commentator, and that the expected comment was removed from the comments ArrayList defined and initialized inside the Post class. In terms of relations to other class, firstly, this class is related to User class in more than one way. It first uses User.java in order to store who created the post, who commented on the post, and an ArrayList of a particular User's friends and their features (blocked, hidden, etc.) - more to be implemented when we will be working on the GUI aspect of our project. Post.java is also closely intertwined with the Feed class (to be discussed later in this document), since social media feeds are created from posts. For instance, if a particular User wants a certain friend to be hidden, that boolean variable would reflect that change, and therefore inherently change that User's social media feed to remove that certain friend from their scrolling page.
- **Comments.java:** <br />
This class has all the functionalities for each comment on a post on our platform. This will include methods that control how many likes and dislikes a comment has (its upvotes and downvotes), as well as associate each comment with its respective User. There are also a plethora of accessor methods in this class in order to return values that will be used for our GUI display (e.g. methods that return the amount of upvotes and downvotes to display like counts, a method that returns the User that commented that specific comment to be used in other classes (to be explained further), and a method that returns the actual text representation of a comment for more display purposes). The tests that will be present for this class will involve making sure that the constructor initializes the correct values, as this is essential for the success of the Post class, and additional tests that modify the instance variables to make sure that they are being changed correctly. The Comments class, as mentioned above, is closely related to the Post class, as comments will be eventually displayed on a post and comment objects will be stored in a list of comments in the Post class. This is for the organization of our comments, so that we can store them in relation to their respective posts and tie groups of comments into one, efficient list. The Comments class is also related to the User class, in that our platform needs to make sure that we can process what User commented what comment. This mainly serves the method that removes comments from a post, as only the User that created the post and the User that commented the comment can remove a specific comment. Although, we do not necessarily see this class as being directly affecting our visual platform, it has essential functionalities for the Post class, which will be definitely interacting with our GUI.
- **Messaging.java:** <br />
This class contains all of the functionalities of direct messaging for each User that employs our platform. _(insert what this class is doing via its methods and the tests that should be ran to check functionality)._ This class is closely related to User class, inherently, because direct messaging is particular for each User and dependant on a certain individual's blocked and friends lists. Based on who a User chooses to be friends with, this class will remove and add messaging privileges to certain Users via their username. 
- **Feed.java:** <br />
This class holds all the functionalities implemented in our scrolling feed for each User in our platform's database. When we are more focused on implementing our visual interface, this class will be more of a necessary component, because it serves as the base for something similar to a scrolling social media page, filled with a User's friend's posts. When we code our GUI, we plan to use this class to monitor whose posts will appear on a certain User's social media page, as well as provide as a bridge between the data in our database (specifically image paths) and the GUI. For Phase 1, our testing for this class is making sure that our program is adding the correct image paths to the ArrayList instance variable and that the image paths are able to be displayed as their respective image. To do this, we created an additional (and early GUI test) that reads each image path in the pre-written file and displays the picture to a simple GUI implementation. This class is closely related to the Post class, as the posts that are "scheduled" to be on the feed will be created and set through Post. Our Feed class also has relationships with our running class, SocialMediaPlatform, as all of the Users of our platform have a feed associated with them, a factor that is stored in our SocialMediaPlatform class in an ArrayList with the same index as that specific user in the users ArrayList. An additional functionality of this class would be the implementation of the Random class, so that the posts of each friend of a User are not displayed in a strict ascending or descending index order.
- **InvalidException.java:** <br />
This Custom Exception subclass of Exception will be used in the creation of a new User and thrown when an individual enters a username or password that does match our criteria. Currently, we have tested it against purposely invalid input and printed an exception message to the terminal. Eventually, in the implementation of our GUI, the message will be printed to the screen on a separate dialog or right below the login credentials during creation. This exception class will be likely used with every class, but mainly the User and SocialMediaPlatform runner class in order to catch the creation of invalid credentials. However, this class is not limited to any one class, and will be used and thrown where we see fit.
