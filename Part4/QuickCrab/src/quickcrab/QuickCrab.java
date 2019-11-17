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
 
package quickcrab;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.awt.Color;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter{
	public ArrayList<Location> getMoveLocations() // vary from CrabCritter, choose two steps firstly, if can't choose one step far
    {
        ArrayList<Location> locs = new ArrayList<Location>();
		Location left = getLocationsInDirections(Location.LEFT), right = getLocationsInDirections(Location.RIGHT);
        Grid<Actor> grid = getGrid();
		if(grid.isValid(left)){
			if(grid.get(left.getAdjacentLocation(getDirection() + Location.LEFT)) == null){
				locs.add(left.getAdjacentLocation(getDirection() + Location.LEFT));
			}
		}
		if(grid.isValid(right)){
			if(grid.get(right.getAdjacentLocation(getDirection() + Location.RIGHT)) == null){
				locs.add(right.getAdjacentLocation(getDirection() + Location.RIGHT));
			}
		}
		if(locs.size() != 0){
			return locs;
		}
		int[] dirs =
            { Location.LEFT, Location.RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
            if (getGrid().get(loc) == null)
                locs.add(loc);

        return locs;
    }
}