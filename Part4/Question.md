## Set7
1.  What methods are implemented in Critter?
- ```act、getActors、processActors、getMoveLocations、selectMoveLocation、makeMove```
```java
//from @Critter.java
public void act(){……}
public ArrayList<Actor> getActors(){……}
public void processActors(ArrayList<Actor> actors){……}
public ArrayList<Location> getMoveLocations(){……}
public Location selectMoveLocation(ArrayList<Location> locs){……}
public void makeMove(Location loc){……}
```

2.  What are the five basic actions common to all critters when they act?
- ```getActors、processActors、getMoveLocations、selectMoveLocation、makeMove```
```java
// from@Critter.java
public void act()
    {
        if (getGrid() == null)
            return;
        ArrayList<Actor> actors = getActors();
        processActors(actors);
        ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
    }
```

3.  Should subclasses of Critter override the getActors method? Explain.
- Yes, Different types of critters may select move locations in different ways, may have different ways of selecting among them, and may vary the actions they take when they make the move. Just like the ```CrabCritter``` below, the crab will get move towards three directions.
```java
//from @ CrabCritter.java
public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        return actors;
    }
```

4.  Describe the way that a critter could process actors.
- Just like the code below, by getting the ```ArrayList<Actor> actors```, it can make changes of the actor by calling the actor’s function.
```java
//from @Critter.java
 public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
    }
```

5.  What three methods must be invoked to make a critter move? Explain each of these methods.
 -  Firstly, use ```getMoveLocations()``` to get all locations the ```Critter``` may move and store as ```ArrayList<Location>```
 - Secondly, use ```selectMoveLocation``` to choose a location from the ```ArrayList<Location>``` above
 - Lastly, use ```makeMove``` to move to the specific location.
```java
//from @Critter.java
public void act(){
      ………
      ArrayList<Location> moveLocs = getMoveLocations();
        Location loc = selectMoveLocation(moveLocs);
        makeMove(loc);
 }
```

6.  Why is there no Critter constructor?
- Because it extends from ```Actor```, it can use ```Actor``` constructor instead.
```java
//from @Critter.java
public class Critter extends Actor{……}
```


## Set8
1.  Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?
- Although ```ChameleonCritter``` doesn’t override act, it overrides ```processActor``` and ```makeMove``` function. These will change the behavior of the ```act``` function
```java
// from @ChameleonCritter.java
    public void processActors(ArrayList<Actor> actors) {……}
    public void makeMove(Location loc){……}
```

2.  Why does the makeMove method of ChameleonCritter call super.makeMove?
- Because the ```ChameleonCritter``` would move like ```Critter``` after the ```setDirection```， it uses ```super.makeMove``` to call the ```Critter.makeMove()```. 
```java
//from @ChamleonCritter.java
public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
```

3.  How would you make the ChameleonCritter drop flowers in its old location when it moves?
- By adding the following sentences like the ```Bug.java``` does after the ```super.makeMove``` in the ```makeMove``` function.
```java
Location locTmp = getLocation();
super.makeMove(loc);
Flower flower = new Flower(getColor());
flower.pushSelfInGrid(getGrid(), locTmp);
```

4.  Why doesn’t ChameleonCritter override the getActors method?
- Because it doesn’t need to do that,  it can use the ```getActors``` inherit from ```Criter``` directly to get all actors.
5.  Which class contains the getLocation method?
- ```Actor```
```java
//from @Actor.java
public Location getLocation()
    {
        return location;
    }
```

6.  How can a Critter access its own grid?
- By calling the ```getGrid()``` function extends from ```Actor```
```java
//from @Actor.java
public Grid<Actor> getGrid()
    {
        return grid;
    }
```


## Set 9
1.  Why doesn’t CrabCritter override the processActors method?
- Because it doesn’t need to do that, the ```CrabCritter``` get all location gotton from ```getActor``` just like the ```CrabCritter``` does.
2.  Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.
- No, it just eats actors which is ahead of it or in the left/right location of it according to the ```getActors()``` process while ```getActor``` in ```Critter``` finds all neighboring actors.
```java
//from @CrabCritter.java
public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        return actors;
    }
```

3.  Why is the getLocationsInDirections method used in CrabCritter?
- Because ```CrabCritter``` needs ```getLocationsInDirections``` to get specific location of specfic direction like```Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT```
```java
//from @ CrabCritter.java
public ArrayList<Actor> getActors(){
  /……/
  int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
  for (Location loc : getLocationsInDirections(dirs))
        {
           /……/
        }
  /……/
}
```

4.  If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?
- ```(4,4) (4,3) (4,5)```
5.  What are the similarities and differences between the movements of a CrabCritter and a Critter?
- Similarities:
    - Both ```CrabCritter``` and ```Critter``` will randomly choose a locatoin from the ```ArrayList<Location>``` gotten from ```getMoveLocations```, and if the location is empty, they both call ```moveTo``` to move towards to that location.
- Differencts:
    - ```CrabCritter``` will choose location from no more than 3 locations while ```Critter``` choose from no more than 8 locations. If the location randomly chosen is not ```null```, the ```Critter``` will stay at its own location without doing anything while the ```CrabCritter``` will call ```setDirection``` function to turn left or right.
  ```java
  //from @Critter.java
  public void makeMove(Location loc)
    {
        if (loc == null)
            removeSelfFromGrid();
        else
            moveTo(loc);
    }
    
  // from @CrabCritter.java
  public void makeMove(Location loc)
    {
        if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = Location.LEFT;
            else
                angle = Location.RIGHT;
            setDirection(getDirection() + angle);
        }
        else
            super.makeMove(loc);
    }
  ```
6.  How does a CrabCritter determine when it turns instead of moving?
- When the ```loc``` randomly chosen from ```ArrayList<Location>``` is equals to its ```location```
```java
public void makeMove(Location loc)
    {
        if (loc.equals(getLocation()))
        {
            /*turns*/
        }
    /……/
    }
```

7.  Why don’t the CrabCritter objects eat each other?
- Because the ```processActors``` function extends from ```Critter``` decides the ```CrabCritter``` will only obeject actors which is not ```Rock``` or ```Critter``` while the ```CrabCritter``` belongs to ```Critter```.
```java
//from @ Critter.java
 public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
    }
```
