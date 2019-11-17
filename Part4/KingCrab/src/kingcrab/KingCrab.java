/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */
 
package kingcrab;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import java.awt.Color;
import java.util.ArrayList;

public class KingCrab extends CrabCritter{
	public void processActors(ArrayList<Actor> actors) // make the actors far away from one step or remove it
    {
		Grid<Actor> grid = getGrid();
		Location loc = getLocation();
        for (Actor a : actors)
        {
			int direction = loc.getDirectionToward(a.getLocation());//get the actor move direction
			Location des = a.getLocation().getAdjacentLocation(direction); // get the actor move location
            if (!(a instanceof Rock) && !(a instanceof KingCrab)){
				if(grid.isValid(des) && grid.get(des) == null){ // if the actor can move->move
					a.moveTo(des);
				}else{// can't -> remove
					a.removeSelfFromGrid();
				}
			}
        }
    }
}
