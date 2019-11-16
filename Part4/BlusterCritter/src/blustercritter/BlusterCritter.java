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
	public BlusterCritter(int cSize){
		this.c = cSize;
	}
	public ArrayList<Actor> getActors(){ 
		ArrayList<Actor> actors = new ArrayList<Actor>();// get Critter Actor
		Location loc = getLocation();
		int row = loc.getRow(), col = loc.getCol();
		int rows = getGrid().getNumRows(), cols = getGrid().getNumCols();
		int sRow, eRow, sCol, eCol;
		if(row - 2 >= 0){ // decide the left edge of row
			sRow = row - 2;
		}else{
			if(row - 1 >= 0){
				sRow = row - 1;
			}else{
				sRow = row;	
			}
		}
		if(col - 2 >= 0){ // decide the upper edge of row
			sCol = col - 2;
		}else{
			if(col - 1 >= 0){
				sCol = col - 1;
			}else{
				sCol = col;	
			}
		}
		if(row + 2 < rows){ // decide the right edge of row
			eRow = row + 2;
		}else{
			if(row + 1 < rows){
				eRow = row + 1;
			}else{
				eRow = row;	
			}
		}
		if(col + 2 < cols){ // decide the lowwer edge of row
			eCol = col + 2;
		}else{
			if(col + 1 < cols){
				eCol = col + 1;
			}else{
				eCol = col;	
			}
		}
		for(int i = sRow; i <= eRow; ++i){
			for(int j = sCol; j <= eCol; ++j){ // get actor from 5*5 rectangle
				if(i == row && j == row){
					continue;
				}
				Actor a = getGrid().get(new Location(i, j));
				if (a != null && a instanceof Critter){ // record the number of critter, then compare to 
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
        if (n >= c){ // if the actors size is zero, the kid will get darken
			int red = (int) (color.getRed() * (1 - FACTOR));
			int green = (int) (color.getGreen() * (1 - FACTOR));
			int blue = (int) (color.getBlue() * (1 - FACTOR));
			red = red > 0 ? red : 0;
			green = green > 0? green:0;
			blue = blue > 0? blue :0;
			setColor(new Color(red, green, blue));
			return;
		}else{//get brighter
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
