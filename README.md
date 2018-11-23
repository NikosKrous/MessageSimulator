# MessageSimulator
A message simulator console application, where you can send and receive messages with other users. Technologies used: Java, SQL, MYSQL database.

The first log-in can be possible by entering for username and password  the word "admin" and has all the rights for the application.
A logged-in admin can create, delete and update users and messages as well. Each role assigned to created users has different rights. By logging-in, a menu will appear in the console stating what actions the user is able to do. Messages can be sent, recieved and edited as well.

A file named with the assined role_id is created by the time a user sends a message. Inside the file is stated every message that the user sent and recieved, showing the ids of the users and also the time that message was sent.

The MySQL database is included in the folder, showing the created tables and fields that were needed for the creationg of this console application.
