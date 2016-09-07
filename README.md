# Tetris


## Functional requirements

### Must haves:

- [ ] The game board shall have a grid of 10 cells wide and 24 cells high
- [ ] The topmost 4 rows are visually hidden
- [ ]	A tetromino shall be placed at the top (center) of the grid when the game starts
- [ ]	A tetromino shall be placed at the top (center) of the grid when the previous tetromino has landed on top of the other tetrominoes or on the bottom
- [ ]	The tetromino can be rotated by 90 degrees to both sides by the player
- [ ]	The game will consist of 5 different tetrominoes, each containing 4 blocks
- [ ]	A row of blocks shall be cleared when it is filled with 10 blocks
- [ ]	All blocks will move down 1 row when 1 row of 10 blocks has been cleared
- [ ]	The tetromino is not allowed to be moved outside of the board
- [ ]	The tetromino is not allowed to be rotated outside of the board
- [ ]	The tetromino is not allowed to overlap with other blocks already fixed on the grid
- [ ]	The tetromino will move down one block every X milliseconds
- [ ]	If a tetromino falls down onto the bottom of the grid, the tetromino will be fixed
- [ ]	if a tetromino falls down onto a fixed block the tetromino will be fixed
- [ ]	The player will lose the game if a new tetromino is fixed outside of the board

### Should haves:

- [ ] A new game can be started by the player
- [ ] A new game will start when the previous game has ended
- [ ] Each tetromino shall have a different colour
- [ ] There shall be a preview pane showing the next Tetromino to be placed in the grid

### Could haves:

- [ ] The score of the player will be 0 when the game starts
- [ ] The final score will be shown when the game has ended
- [ ] The score shall increase for each row cleared by the player
- [ ] The game will follow the original Nintento scoring system:
  - 40 points will be given if 1 row of blocks is cleared
  - 100 points will be given if 2 rows of blocks are cleared at the same time
  - 300 points will be given if 3 rows of blocks are cleared at the same time
  - 1200 point will be given if 4 rows of blocks are cleared at the same time
- [ ] The game should have different levels where the tetrimos fall down with a different speed
- [ ] The game should have a maximum score, when reached, restarting the game on the next level
- [ ] The game has a high score system where the best 10 players can leave their names

### Would/won’t haves

- [ ] The game will keep track of the 10 highest scores with the names of the corresponding players
- [ ] The player shall be able to choose a soundtrack from a list of presets
- [ ] The player shall be able to choose a theme from a list of presets
- [ ] The game has sound effects
- [ ] The player shall be able to choose at which level to start the game
- [ ] The player shall be able to pause the game

### Non Functional requirements

- The game shall be playable on: Windows 7 or higher, Mac OS X 10.8 or higher and Linux
- The game shall be developed in Java
- A first build shall be delivered the 16th of September
- A fully working version shall be delivered the 4th of November
- Every week there will be a deliverable, working with the Scrum Methodology
