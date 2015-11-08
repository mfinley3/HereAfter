Project:  HereAfter a TRPG
Project Manager: Ben Whitely

Course:  335
Instructor:  R. Mercer
Iteration 1 Due Date:  11/23/14
Iteration 2 Due Date:  Last Day of Class
Description:  A tactical role playing game set in a post-apocalyptic time period
              after a massive outbreak infected millions and turned them into monsters.

Overview of the Project: To learn the key concepts in working with a small team to develop software while practicing our java skills. The project lasted around 6 weeks, starting from coming up with an idea and developing a plan to the game's final state. 

Authors:

Brian Seaman 
Mike Finley
Chioke Aarhus
Katelyn Hudspeth

**NOTES**
Currently there is no easy way to simply run the game from a .jar executable. It is recommended to clone the repository and then import the project into an IDE such as Eclipse and then follow
the next steps in Instructions. 

**Instructions**
To first get the game running open up the class TRPGUI.java located in the 'view' package using eclipse(or your preferred IDE) and run this java file (Click 'no' when the game asks if you would like to start in testing mode) and once everything has loaded a window will pop up.
In this new window there will be three options on the right hand side: 'New Game' 'Continue' and 'Quit'. You will want to click on any part of the words 'New Game'.
After you have clicked on 'New Game' the options will change on the right hand size to the difficulty selection. You will want to click on the word 'Easy' for now (other difficulties are very unbalanced). 
Once you have clicked on 'Easy' you will be taken to a new screen that requires you to enter a username for yourself and then enter a number representing how 
many of each unit type you want. There are five units to choose from and you can choose a max of five units. Choosing to have one of each unit by entering a 1
in each box is recommended for now.

Once you have entered a five into the soldier box you may now click on the button at the bottom 'Make team'. This will take you to the map and spawn five soldier 
units for you to control. Along with clicking the 'Make team' button the window will resize itself so you are able to see a bigger portion of the map. At this point you 
may resize the screen to your liking so you are able to see both a good amount of the map and the eclipse console at the same time as both will have important
information (Console is no longer needed as all information is shown in the game). At this point the game is set up and you are almost ready to play but you should read Information for Game Play first.

**Information for Game Play**
Some key info you'll want to know: 
The console will be showing information about what units are being attacked along with where units are moving to.  
There is a tab near the top right hand side of the map, above the how to play button, this tab will display key information about the current players team. 
(meaning if it is player ones turn their units info will be shown and if it is player twos turn their units info will be show)
The map will highlight the currently selected unit with a blue or red outline depending on the team.
The map will also gray out units that have already moved for that turn.
When a new turn starts the players who turn it is will have their units become un-grayed and the other player who is waiting for their turn will have their units grayed. 
The blue team (player 1) starts in the top right hand corner of the map and the red team  (player 2) starts a little bit Northwest of the bottom left hand corner of the map. 
The blue team’s goal is to kill all of the zombies or capture one square of the tower they guard before the zombies can kill them.
The red team or the zombie’s goal is to kill all of the blue teams units without dying or giving up the tower they spawn by. 
To learn the basic commands and how to get units to execute them it is essential you click on the 'How to play' button on the right hand side of the game map.
Turns will automatically end once all units on a team have moved no need to click the end turn button unless you want to end the turn early. 
Each unit currently can either attack, move or wait currently. They cannot do more than one thing in a turn so choose how to approach enemies carefully.
Would also recommend looking at the unit information to know the limits and powers of the two units that will be used for now.
The name of the user whose turn it is will appear in the top left hand corner of the window once the game has started and will change after a turn has ended.

At this point it is recommended you have the game fully started and head to the "help" tab at the top to learn more about game play.


**Unit Information**
Attack: How much damage a unit can do.
Defense: How much protection a unit has from attacks.
Health: Remaining unit’s health
Movement: How far a unit is able to move on the map.

**Space Information**
There are a few types of spaces each affects movement in some way.
Wasteland - no movement affects
Water - move hindrance of 5: may not actually hinder movement yet. 
Path - increases movement by 1: may not actually affect movement yet.
Mountain - move hindrance of 4: should be working
Tower - this is what the blue team wants to capture
Bridge - no movement affects
Walls - cannot walk on them

*It is recommended to click on the how to play button in game and read the instructions in the pop up window, it is possible to leave this window up throughout the game*
