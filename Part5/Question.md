## **Set 10**
1.  Where is the isValid method specified? Which classes provide an implementation of this method?
- The ```isValid``` method is specfied in ```Grid.java``` and both ```BoundedGrid.java``` and ```UnboundedGrid.java``` provide an implementation of this method.
```java
// from @ Grid.java
boolean isValid(Location loc);
// from @UnboundedGrid.java
public boolean isValid(Location loc)
    {
        return true;
    }
// from @BoundedGrid.java
public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
```

2.  Which AbstractGrid methods call the isValid method? Why don’t the other methods need to call it?
- The ```getValidAdjacentLocations``` method in ```AbstractGrid``` call the ```isValid``` method. Because the other methods can get the result from ```getValidAdjacentLocations``` without calling ```isValid``` method directly.
```java
//from @AbtractGrid.java
public ArrayList<Location> getValidAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();

        int d = Location.NORTH;
        for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
        {
            Location neighborLoc = loc.getAdjacentLocation(d);
            if (isValid(neighborLoc))
                locs.add(neighborLoc);
            d = d + Location.HALF_RIGHT;
        }
        return locs;
    }
```

3.  Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?
- ```getOccupiedAdjacentLocations``` and ```get``` method are called. ```AbstractGrid.java``` provide the implementation of ```getOccupiedAdjacentLocations``` and both ```BoundGrid.java``` and ```UnboundedGrid.java``` provide the implementation of ```get``` method.
```java
// from @AbstractGrid.java
public ArrayList<E> getNeighbors(Location loc)
    {
        ArrayList<E> neighbors = new ArrayList<E>();
        for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
            neighbors.add(get(neighborLoc));
        return neighbors;
    }
public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc))
        {
            if (get(neighborLoc) != null)
                locs.add(neighborLoc);
        }
        return locs;
    }   

// from @BoundedGrid.java
public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

//from @ UnboundedGrid.java
public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.get(loc);
    }
```

4.  Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?
- Because the ```getEmptyAdjacentLocations``` must use ```get``` to decide whether the current location is empty or occupied. If using objects of type E, it is hard to decide each location info.
```java
// from @AbstractGrid.java
 public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc))
        {
            if (get(neighborLoc) == null)
                locs.add(neighborLoc);
        }
        return locs;
    }
```

5.  What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?
- The ```getValidAdjacentLocations``` will be unable to get all 8 direction places of valid location and only be able to get valid location from 4 direction as ```NORTH、SOUTH、EAST、WEST``` 
```java
// from @AbstractGrid.java
public ArrayList<Location> getValidAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();

        int d = Location.NORTH;
        for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
        {
            Location neighborLoc = loc.getAdjacentLocation(d);
            if (isValid(neighborLoc))
                locs.add(neighborLoc);
            d = d + Location.HALF_RIGHT;
        }
        return locs;
    }
```

------
## **Set 11**

1.  What ensures that a grid has at least one valid location?
- In the constructor of ```BoundGrid```, it limits that the ```rows、cols``` must no less than zero, it ensures that a grid has at least one valid location.
```java
//from @BoundGrid.java
public BoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new Object[rows][cols];
    }
```

2.  How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?
- By returning ```occupantArray[0].length```. Because when the object of ```BoundedGrid``` is constructed, it was constructed to be ```occupantArray = new Object[rows][cols];```
```java
//from @BoundedGrid.java
 public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return occupantArray[0].length;
    }
```

3.  What are the requirements for a Location to be valid in a BoundedGrid?
- The Location’s value of row should no less than zero and less than grid’s number of rows and  the Location’s value of col should no less than zero and less than grid’s number of cols.
```java
// from @BoundedGrid.java
public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
```

4.  What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?
- Return type of ```ArrayList<Location>```. The time complexity is ```O(r*c)```;
```java
// from @ BounededGrid.java
public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }
```

5.  What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?
- Return type of ```E``` while E means the type of actor the method find. The time complexity of this method is ```O(1)```
```java
// from @ BounededGrid.java
 public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }
```

6.  What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?
- If the location is unvalid or the inpur object is ```null```, then it may cause an exception. The time complexity is ```O(1)```.
```java
//from @BoundedGrid.java
public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }
```

7.  What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?
- Return type of ```E``` while E is the type of actor the method find. If removed an item from an empty location, it would throw an exception as ```IllegalArgumentException```The time complexity is```O(1)```
```java
//from @BoundedGrid.java
public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
```

8.  Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.
- Yes. According to the time complexity above, we can make the decision that this is an efficient implementation.
-----
## **Set 12**
1.  Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?
- The ```Location``` class should implement ```hashCode()``` and ```equals()``` method. If a TreeMap was used, the ```Location``` class should implement ```compareTo``` method. Yes, it does satisfy all of these requirements.
```java
//from @Location.java
public boolean equals(Object other)
    {
        if (!(other instanceof Location))
            return false;

        Location otherLoc = (Location) other;
        return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
    }

    /**
     * Generates a hash code.
     * @return a hash code for this location
     */
    public int hashCode()
    {
        return getRow() * 3737 + getCol();
    }
   public int compareTo(Object other)
    {
        Location otherLoc = (Location) other;
        if (getRow() < otherLoc.getRow())
            return -1;
        if (getRow() > otherLoc.getRow())
            return 1;
        if (getCol() < otherLoc.getCol())
            return -1;
        if (getCol() > otherLoc.getCol())
            return 1;
        return 0;
    }
```
 
2.  Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?
- Because ```null``` is a legal items and ```isValid``` always return ```true``` in ```UnboundedGrid.java```, it should judge whether the items is ```null``` to decide what to do next while these method in ```BoundedGrid``` do not return ```true``` when the actors in the specific location is ```null```
```java
//from @UnbounedGrid.java
public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.get(loc);
    }

    public E put(Location loc, E obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
        return occupantMap.put(loc, obj);
    }
    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.remove(loc);
    }
```

3.  What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?
- They are all ```O(1)```. The time complexity above would all be ```O(log n)``` instead.
4.  How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?
- The method behavior may be different. If used ```HashMap```, these method could search the specific location or actor directly while if used ```TreeMap```, these method should search the tree firstly to locate these factors. And as the ```Set12.1``` suggests ,the ```HashMap``` use ```hashCode``` and ```equals``` to access its process while ```TreeMap``` uses ```compareTo```. 
5.  Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?
- Yes, it can. If used ```map``` instead, the time complexity would sharken from```O(n* n)``` to ```O(n)```, while it may cause more memory cost  to store data than two-dimentsional array does.