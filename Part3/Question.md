## set3
Assume the following statements when answering the following questions.
```
Location loc1 = new Location(4, 3);
Location loc2 = new Location(3, 4);
```
1.  How would you access the row value for loc1?
    -  By getting the return value of ```loc1.getRow()```
    ```java
    // from @Location.java
    public int getRow()
    {
        return row;
    }
    ```
2.  What is the value of b after the following statement is executed? 
``` boolean b = loc1.equals(loc2);```
    -    false
    ```java
    // from @Location.java
    // according the following function 
      public boolean equals(Object other)
      {
          if (!(other instanceof Location))
              return false;
  
          Location otherLoc = (Location) other;
          return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
      }
    ```
3.  What is the value of loc3 after the following statement is executed?
```Location loc3 = loc2.getAdjacentLocation(Location.SOUTH); ```
    -   (4,4)
    ```java
    // from @Location.java
     public Location getAdjacentLocation(int direction)
    {
      /……/
      else if (adjustedDirection == SOUTH)
              dr = 1;
      /……/
      return new Location(getRow() + dr, getCol() + dc);
    }
    ```
4.  What is the value of dir after the following statement is executed? 
```int dir = loc1.getDirectionToward(new Location(6, 5));```
    -  135 
    ```java
    //from @Locationo.java
      public int getDirectionToward(Location target)
      {
          int dx = target.getCol() - getCol();
          int dy = target.getRow() - getRow();
          // y axis points opposite to mathematical orientation
          int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));
  
          // mathematical angle is counterclockwise from x-axis,
          // compass angle is clockwise from y-axis
          int compassAngle = RIGHT - angle;
          // prepare for truncating division by 45 degrees
          compassAngle += HALF_RIGHT / 2;
          // wrap negative angles
          if (compassAngle < 0)
              compassAngle += FULL_CIRCLE;
          // round to nearest multiple of 45
          return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
      }
    ```
5.  How does the getAdjacentLocation method know which adjacent location to return?
    -  By getting the value of the ```Location``` object and compare it to the parameter, and then return the nearset location from it to user.
    ```java
    // from @Location.java
      public Location getAdjacentLocation(int direction)
      {
          // reduce mod 360 and round to closest multiple of 45
          int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
          if (adjustedDirection < 0)
              adjustedDirection += FULL_CIRCLE;
  
          adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
          int dc = 0;
          int dr = 0;
          if (adjustedDirection == EAST)
              dc = 1;
          else if (adjustedDirection == SOUTHEAST)
          {
              dc = 1;
              dr = 1;
          }
          else if (adjustedDirection == SOUTH)
              dr = 1;
          else if (adjustedDirection == SOUTHWEST)
          {
              dc = -1;
              dr = 1;
          }
          else if (adjustedDirection == WEST)
              dc = -1;
          else if (adjustedDirection == NORTHWEST)
          {
              dc = -1;
              dr = -1;
          }
          else if (adjustedDirection == NORTH)
              dr = -1;
          else if (adjustedDirection == NORTHEAST)
          {
              dc = 1;
              dr = -1;
          }
          return new Location(getRow() + dr, getCol() + dc);
      }
    ```
-----
## set4
1.  How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?
    -  By applying the ```getOccupiedLocations()``` function of the ```Grid``` object to get list  ```ArrayList<Location>``` and then use ```size()``` function to get the count of the objects.
    - Using the equation ```getNumRows() * getNumCols() - getOccupiedLations.size()``` to get the count of the empty locations size.
    ```java
    // from @Grid.java
     /**
     * Gets the locations in this grid that contain objects.
     * @return an array list of all occupied locations in this grid
     */
    ArrayList<Location> getOccupiedLocations();

    /**
       * Returns the number of rows in this grid.
       * @return the number of rows, or -1 if this grid is unbounded
       */
      int getNumRows();
  
      /**
       * Returns the number of columns in this grid.
       * @return the number of columns, or -1 if this grid is unbounded
       */
      int getNumCols();
    ```
2.  How can you check if location (10,10) is in a grid?
    -  If ```isValid(new Location(10,10))``` return true, location(10,10) is valid or it is invalid
    ```java
    /**
     * Checks whether a location is valid in this grid. <br />
     * Precondition: <code>loc</code> is not <code>null</code>
     * @param loc the location to check
     * @return <code>true</code> if <code>loc</code> is valid in this grid,
     * <code>false</code> otherwise
     */
    boolean isValid(Location loc);
    ```
3.  Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?
    -  Because ```Grid``` is a interface class, so we can find the code in the implementation class like ```BoundedGrid``` or ```UnboundedGrid``` or ```AbstractGrid```
    ```java
    public abstract class AbstractGrid<E> implements Grid<E>
    public class BoundedGrid<E> extends AbstractGrid<E>
    public class UnboundedGrid<E> extends AbstractGrid<E>
    ```
4.  All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.
    -   At some case it would be better. If the location size was fixed size, it would be convenient to get or set value, or it would be harder than ```ArrayList``` to deal with the data.
## set5
1.  Name three properties of every actor.
    -  grid、Location、direction、color
    ```java
    // from @Actor.java
     private Grid<Actor> grid;
    private Location location;
    private int direction;
    private Color color;
    ```
2.  When an actor is constructed, what is its direction and color?
    -  color=blue && direction=North
    ```java
    public Actor()
    {
        color = Color.BLUE;
        direction = Location.NORTH;
        grid = null;
        location = null;
    }
    ```
3.  Why do you think that the Actor class was created as a class instead of an interface?
    -  ```Actor``` has both function and variation while interfce class has function only and it set to be a class.
    ```java
    public class Actor{
      /……/
    }
    ```
4.  Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?
    -  No, the actor can put itself to a grid if and only if there is empty. If it did that, it would cause error unless the actor is ```NULL```
    - No,  when the actor remove it from the grid, there is empty and if it tried to remove the same actor again, it would cause error unless the actor is ```NULL```
    ```java
     /**
       * Puts this actor into a grid. If there is another actor at the given
       * location, it is removed. <br />
       * Precondition: (1) This actor is not contained in a grid (2)
       * <code>loc</code> is valid in <code>gr</code>
       * @param gr the grid into which this actor should be placed
       * @param loc the location into which the actor should be placed
       */
        public void putSelfInGrid(Grid<Actor> gr, Location loc)
        {
            if (grid != null)
                throw new IllegalStateException(
                        "This actor is already contained in a grid.");
    
            Actor actor = gr.get(loc);
            if (actor != null)
                actor.removeSelfFromGrid();
            gr.put(loc, this);
            grid = gr;
            location = loc;
        }
    
      /**
       * Removes this actor from its grid. <br />
       * Precondition: This actor is contained in a grid
       */
      public void removeSelfFromGrid()
      {
          if (grid == null)
              throw new IllegalStateException(
                      "This actor is not contained in a grid.");
          if (grid.get(location) != this)
              throw new IllegalStateException(
                      "The grid contains a different actor at location "
                              + location + ".");
  
          grid.remove(location);
          grid = null;
          location = null;
      }
    ```
5.  How can an actor turn 90 degrees to the right?
    - By applying the following code ```actor.setDirection(actor.getDirection() + 90)```
    ```java
    public void setDirection(int newDirection)
    {
        direction = newDirection % Location.FULL_CIRCLE;
        if (direction < 0)
            direction += Location.FULL_CIRCLE;
    }
    ```
## set6
1.  Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?
    ```c
    if (!gr.isValid(next))
            return false;
    ```
  ```java
  // from @Bug.java
    public boolean canMove()
    {
      /……/
        if (!gr.isValid(next))
            return false;
      /……/
    }
  ```
2.  Which statement(s) in the canMove method determines that a bug will not walk into a rock?
    ```c
    Actor neighbor = gr.get(next);
    return (neighbor == null) || (neighbor instanceof Flower);
    ```
    ```java
    // from @Bug.java
      public boolean canMove()
      {
        /……/
        return (neighbor == null) || (neighbor instanceof Flower);
    }
    ```
3.  Which methods of the Grid interface are invoked by the canMove method and why?
      ```c
      boolean isValid(Location loc);
      E get(Location loc);
      ```
      -  ```canMove()``` have to get status of the grid to decide whether the actor can move, like ```isValid``` to decide whethere the actor would be outside the grid or ```get``` to get the actor next to the location.
      ```java
    // from @Bug.java
      public boolean canMove()
      {
        /……/
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
         /……/
    }
    ```
4.  Which method of the Location class is invoked by the canMove method and why?
    ```c
    public Location getLocation()
    public Location getAdjacentLocation(int direction)
    ```
    - The actor use the upper function to get next location info .
    ```java
    // from @Bug.java
      public boolean canMove()
      {
        /……/
       Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
         /……/
    }
    ```
5.  Which methods inherited from the Actor class are invoked in the canMove method?
      ```c
      public int getDirection()
      public Grid<Actor> getGrid()
      public Location getLocation()
      ```
6.  What happens in the move method when the location immediately in front of the bug is out of the grid?
      - It will call the ```removeSelfFromGrid``` function to remove the bug from the grid
     ```java
     public void move()
    {
        /……/
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
        /……/
    }
     ```
7.  Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?
    -  Yes, it needes and it can be replaced by the ```getLocation```, but it will cause extra cost.
8.  Why do you think the flowers that are dropped by a bug have the same color as the bug?
    -  Because the flower is constructed to be ```DEFAULT_COLOR```.
    ```java
    // from @ Flower.java
    public Flower()
    {
        setColor(DEFAULT_COLOR);
    }
    ```
9.  When a bug removes itself from the grid, will it place a flower into its previous location?
    -  Yes it wil.
     ```java
     // from @Bug.java
     public void move()
    {
        /……/
         Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
     ```
10.  Which statement(s) in the move method places the flower into the grid at the bug’s previous location?
    ```c
    Flower flower = new Flower(getColor());  
    flower.putSelfInGrid(gr, loc);
    ``` 
11.  If a bug needs to turn 180 degrees, how many times should it call the turn method?
     -  4 times



