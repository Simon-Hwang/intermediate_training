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
package circlebug; //package name

import info.gridworld.actor.Bug;

public class CircleBug extends Bug {
	private int sideLength;
	private int steps;
	public CircleBug(int radius) {
		sideLength = radius;
		steps = 0;
	}
	public void act() {
		if(steps >= 0 && steps < sideLength && canMove()) { // less, not no more than -> because step is started by zero
			move();
			++steps;
		}else if (steps == sideLength) { // turn twice to get the tright dirction
			turn();
			steps = 0;
		}
	}
}
