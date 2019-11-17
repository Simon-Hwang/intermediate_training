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
import info.gridworld.grid.Location;
import java.util.ArrayList;

public class BlusterCritter extends Critter{
	private static final double FACTOR = 0.05; //darkern coefficient
	private int c;
	public BlusterCritter(int cSize){
		this.c = cSize;
	}
	public ArrayList<Actor> getActors(){ 
		ArrayList<Actor> actors = new ArrayList<Actor>();// get Critter Actor
		Location loc = getLocation();
		int row = loc.getRow(), col = loc.getCol();
		int rows = getGrid().getNumRows(), cols = getGrid().getNumCols();
		int sRow, eRow, sCol, eCol;
		sRow = (row - 2 >= 0) ? row -2 : (row - 1 >= 0) ? row - 1 : row;
		eRow = (row + 2 < rows) ? row + 2: (row + 1 < rows) ? row + 1 : row;
		sCol = (col - 2 >= 0) ? col -2 : (col - 1 >= 0) ? col - 1 : col;
		eCol = (col + 2 < cols) ? col + 2: (col + 1 < cols) ? col + 1 : col;
		// get actor from 5*5 rectangle
		for(int i = sRow; i <= eRow; ++i){
			for(int j = sCol; j <= eCol; ++j){ 
				if(i == row && j == row){
					continue;
				}
				Actor a = getGrid().get(new Location(i, j));
				// record the number of critter, then compare to 
				if (a instanceof Critter){ 
					actors.add(a);
				}
			}
        }
        return actors;
	}

	public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
		Color color = getColor();
	// if the actors size is zero, the kid will get darken
        if (n >= c){ 
			int red = (int) (color.getRed() * (1 - FACTOR));
			int green = (int) (color.getGreen() * (1 - FACTOR));
			int blue = (int) (color.getBlue() * (1 - FACTOR));
			red = red > 0 ? red : 0;
			green = green > 0? green:0;
			blue = blue > 0? blue :0;
			setColor(new Color(red, green, blue));
			return;
		}else{
			//get brighter
			int red = (int) (color.getRed() * (1 + FACTOR));
			int green = (int) (color.getGreen() * (1 + FACTOR));
			int blue = (int) (color.getBlue() * (1 + FACTOR));
			red = red < 255 ? red : 255;
			green = green < 255? green:255;
			blue = blue < 255? blue :255;
			setColor(new Color(red, green, blue));
			return;
		}
    }
}
