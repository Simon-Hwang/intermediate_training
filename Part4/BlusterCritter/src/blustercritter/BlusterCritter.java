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
 
package blustercritter;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.awt.Color;
import java.util.ArrayList;

public class BlusterCritter extends Critter{
	private static final double FACTOR = 0.05; //darkern coefficient
	private int c;
	public BlusterCritter(int cSize)){
		this.c = cSize;
	}
	public ArrayList<Actor> getActors(){ 
		ArrayList<Actor> actors = new ArrayList<Actor>();// get Critter Actor
		Location loc = getLocation();
		int row = loc.getRow(), col = loc.getCol();
		for(int i = row - 2; i <= row += 2; ++i){
			for(int j = col - 2; j <= col += 2; ++j){ // get actor from 5*5 rectangle
				if(i == row && j == row){
					continue;
				}
				Actor a = getGrid().get(new Location(i, j));
				if (a != NULL && a instanceof Critter){ // record the number of critter, then compare to c
					actors.add(a);
				}
			}
        }
        return actors;
	}

	public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
		Color c = getColor();
        if (n >= 0){ // if the actors size is zero, the kid will get darken
			int red = (int) (c.getRed() * (1 - FACTOR));
			int green = (int) (c.getGreen() * (1 - FACTOR));
			int blue = (int) (c.getBlue() * (1 - FACTOR));
			setColor(new Color(red, green, blue));
			return;
		}else{//get brighter
			int red = (int) (c.getRed() * (1 + FACTOR));
			int green = (int) (c.getGreen() * (1 + FACTOR));
			int blue = (int) (c.getBlue() * (1 + FACTOR));
			setColor(new Color(red, green, blue));
			return;
		}
    }
}
