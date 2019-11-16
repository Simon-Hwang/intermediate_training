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
 * @author Cay Horstmann
 */
package spiralbug;// the package name

import info.gridworld.actor.Bug;

public class SpiralBug extends Bug {
	private int sideLength;
	private int steps;
	public SpiralBug(int initLength) { // set the initlize length of the spiral
		sideLength = initLength;
		steps = 0;
	}
	public void act() {
		if(steps >= 0 && steps < sideLength && canMove()) { // update the data 
			steps++;
			move();
		}else if(steps < 0){
			steps++;
			turn();
		}else { // can not move, then turn 45 degree to the right
			turn();
			if(steps == sideLength) {
				sideLength += 1; // spiral move
			}
			steps = -1;
		}
	}
}
