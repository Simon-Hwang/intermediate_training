## set1
1.  Does the bug always move to a new location? Explain.
    - No, the bug will only move to a new location if and only if the grid is exist and is not occupied by a rock
2.  In which direction does the bug move?
    - The direction which the bug’s head forwards to.
3.  What does the bug do if it does not move?
    - Turning 45 degrees to the right.
4.  What does a bug leave behind when it moves?
    - A flower which color is the same as the bug.
5.  What happens when the bug is at an edge of the grid? (Consider whether the bug is facing the edge as well as whether the bug is facing some other direction when answering this question.)
    -  At this situation, if the bug is told to act, it will turn 45 degrees to the right, or if told to move, it will remove from the gridworld.
6.  What happens when a bug has a rock in the location immediately in front of it?
    -  Turning 45 degrees to the right.
7.  Does a flower move?
    -  No
8.  What behavior does a flower have?
    -  The flower will get darker as time goes by until a bug go through it again, and then it will be red one more time.
9.  Does a rock move or have any other behavior?
    -  No
10.  Can more than one actor (bug, flower, rock) be in the same location in the grid at the same time?
      -  No. There is no more than 1 actor in the same location at the same time.
    
## By clicking on a cell containing a bug, flower, or rock, do the following.
1.  Test the setDirection method with the following inputs and complete the table, giving the compass direction each input represents.

    | Degrees | Compass Direction |
    | --- | --- |
    | 0 | North |
    | 45 |  NorthEast|
    | 90 |  East |
    | 135 | SouthEast  |
    | 180 |  South |
    | 225 | SouthWest  |
    | 270 | West  |
    | 315 | NorthWest  |
    | 360 |  North |

2.  Move a bug to a different location using the moveTo method. In which directions can you move it? How far can you move it? What happens if you try to move the bug outside the grid?
    -  Can be moved to any valid grid if there was empty. If moving a bug to a location outside of the grid, some errors like```IllegalArgumentException```would be thrown out.
3.  Change the color of a bug, a flower, and a rock. Which method did you use?
    -  ```void.setColor(java.awt.Color())```;
4.  Move a rock on top of a bug and then move the rock again. What happened to the bug?
    -  The bug will disappear.