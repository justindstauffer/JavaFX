# JavaFX
Final project for OOP class

This JavaFX project consists of three different scenes. Start game, Playing game, and End game. 

The starting screen asks for the player to enter their name and click start. If no name is input a message will appear requesting name input before moving on.

The starting screen also has a report button. This button issues a file to C:/testfile/ with the top ten players and their scores. 

After entering in a name and clicking start game, the user is taken to the next scene where they will be asked a series of ten questions. 

The questions are two objects being added together. These objects can be either int, double, or strings.

Answers that arecorrect add 1 point to the users scores.

After the ten questions have been answered the user is taken to the end game scene where all ten questions are listed out with their respective scores.

After reading the scores there is a button for the user to start a new round or they can exit the program. 

------------------------------------------------------------------------------------------------------------------------------------
# Project requirements

Use a generic method to add the objects together for the questions.

Take .txt file as input to get questions and answers

Connect to SQLite database and save user's name and score into a table.

