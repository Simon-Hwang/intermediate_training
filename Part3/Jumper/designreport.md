1. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?
- If the locaition two cells in front contains a flower, the jumper will jump to the location two cells in front of it, or if contains a rock or another jumper and the location in front of it is empty, the jumper will still called ```turn()``` instead of jump the location in front of it.
2. What will a jumper do if the location two cells in front of the jumper is out of the grid?
- It will decide if the location in front of it is valid and empty, and if it was, the jumper would jump one step or it would call ```turn()``` to turn 45 degrees to the right.
3. What will a jumper do if it is facing an edge of the grid?
- It will call ```turn()``` function to turn 45 degrees to the right.
4. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?
- It will call ```turn()``` function to turn 45 degrees to the right.
5. What will a jumper do if it encounters another jumper in its path?
- If the another jumper will jump out in the next ```step``` and jump quicker than the jumper jumps， the jumper will take place of its place or it would call ```turn()``` instead.
6. Are there any other tests the jumper needs to make?
- Some tests like what will happen if two jumpers jump into the same location next step.
- Actually, the answer of the above tests is, the jumper whose location is less than the other will jump into that position while the other jumper will call ```turn()```