# Draughts game

Your task is implement draughts game (https://en.wikipedia.org/wiki/Draughts) using provided mock of classes. Implementation should follow all OOP and SOLID principles. Implementation should follow TDD principles.

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

