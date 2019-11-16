1.  What is the role of the instance variable sideLength?
    -  ```sideLength``` determine how many steps the ```bug``` can take in one direction
2.  What is the role of the instance variable steps?
    -  ```steps``` recorded the ```bug``` taking a few steps in one direction
3.  Why is the turn method called twice when steps becomes equal to sideLength?
    -  if called once only, the ```bug``` will go along the hypotenuse
4.  Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?
    -  Because the ```move()``` function extends from ```Bug```
   ```java
     // from @Bug.java
     /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
   ```
5.  After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?
    -  Yes. The square pattern has been determined when the constructed function is called
   ```java
   //@ Bug.java
   // setColor function will only be called in the constructor from 
     public Bug()
      {
          setColor(Color.RED);
      }
  
      /**
       * Constructs a bug of a given color.
       * @param bugColor the color for this bug
       */
      public Bug(Color bugColor)
      {
          setColor(bugColor);
      }
   ```
6.  Can the path a BoxBug travels ever change? Why or why not?
    -  Yes. If the bug can not move, it will rotate until it can move, and then it traverl path change.
    ```java
    // from @ Bug.java
     public void act()
    {
        if (canMove())
            move();
        else
            turn();
    }
    ```
7.  When will the value of steps be zero? 
    -  If the ```bug``` steps property was constructed to be zero, and the ```bug``` will not move at this situation.

------
Study the code for the `BoxBugRunner` class. Summarize the steps you would use to add another `BoxBug` actor to the grid.
- Firstly, create a new ```BoxBug``` object like ```BoxBug alice_2 = new BoxBug(6);```
- Then, add the new one to ```ActorWolrd``` object like ```world.add(alice_2)```  in a random position or in a given location like ```world.add(new Location(2,2), alice_2)``` in a given position.