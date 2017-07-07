# Draughts game

Your task is implement draughts game (https://en.wikipedia.org/wiki/Draughts) using provided mock of classes. Implementation should follow all OOP and SOLID principles. Implementation should follow TDD principles.

Documentation
<ol> 
  <li>Oracle OOP principles - https://docs.oracle.com/javase/tutorial/java/concepts/index.html. ~ 30 minutes</li>
  <li>Object Oriented Analysis & Design Tutorial https://www.tutorialspoint.com/object_oriented_analysis_design ~ 30 minutes</li>
  <li>Object-oriented programming concepts and principles https://www.ibm.com/developerworks/library/j-perry-object-oriented-programming-concepts-and-principles/index.html ~ 1 hour</li>
  <li>The SOLID Principles in Real Life https://dzone.com/articles/the-solid-principles-in-real-life ~ 20 minutes</li>
  <li>SOLID (object-oriented design) https://en.wikipedia.org/wiki/SOLID_(object-oriented_design) ~ 40 minutes </li>
  <li>Agile Software Development, Principles, Patterns, and Practices https://www.amazon.com/Software-Development-Principles-Patterns-Practices/dp/0135974445 ~ 2 hours</li>
</ol>

You supplied with following structures: 
<ol>
  <li>Figure – interface, abstraction for figure on the board.</li>
  <li>GameRule – interface, abstraction for concrete game rules.</li>
  <li>Board - class, abstraction for chess board, holds figures on plate with size X * X.</li>
  <li>Color – enum, abstraction for figure color.</li>
  <li>Game – class, abstraction for game, hold all related game play information.</li>
  <li>Player – class, abstraction for player participated in the game.</li>
</ol>

Implementation should be divided into two parts.

In first part, you should implement the following

<ol>
  <li>Create both Man and King data structures. Classes should satisfy tests provided KingTest, ManTest.</li>
  <li>Create rules for russian draughts game. (For simplification, you can consider each player can beat, move only once per turn). Implementation should satisfy DraughtsRulesTest</li>
</ol>
In second part, you should implement the following

<ol>
  <li>Implement operation Undo, Redo for last user move. Add tests for operations.</li>
  <li>Write implementation for GameStateDao and store state of the game into json and xml file. Add possibility for GameRule object to 
recover from last state saved state.  Add tests for DAOs.</li>
</ol>

All implementation code should be property formatted and follow code conventions. Code will be checked by Checkstyle maven plugin with 
Google code profile.

PS: Optionally you can implement rules and figures for chess game.

Good luck! 

