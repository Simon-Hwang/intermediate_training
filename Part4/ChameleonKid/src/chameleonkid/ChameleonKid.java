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
 
package chameleonkid;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.awt.Color;
import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritter{
	private static final double DARKENING_FACTOR = 0.05; //darkern coefficient
	public ArrayList<Actor> getActors(){ 
		ArrayList<Actor> actors = new ArrayList<Actor>();
		int[] dirs = {Location.AHEAD, Location.HALF_CIRCLE}; // get actor behind or in front of kid
		for (Location loc : getLocationsInDirections(dirs)) //get actors
        {
            Actor a = getGrid().get(loc);
            if (a != null){
                actors.add(a);
		}
        }
        return actors;
	}
	public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc)){
                locs.add(neighborLoc);
	    }
        }
        return locs;
    }    

	public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0){ // if the actors size is zero, the kid will get darken
			Color c = getColor();
			int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
			int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
			int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
			setColor(new Color(red, green, blue));
			return;
		}
        int r = (int) (Math.random() * n);
        Actor other = actors.get(r);
        setColor(other.getColor());
    }
}
