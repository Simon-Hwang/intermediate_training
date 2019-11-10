  
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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */
package ZBug;

import info.gridworld.actor.Bug;

public class ZBug extends Bug {
	private int sideLength;
	private int steps;
	private int times;
	public ZBug(int zLength) {
		sideLength = zLength;
		steps = 0;
		times = 0; // the bug turing times
	}
	public void act() {
		if(times > 2 || (!canMove() && steps < sideLength)) { //can not move but still attempts to move to form a 'Z' then stop
			return;
		}
		if(steps < sideLength) {
			move();
			++steps;
		}else {
			steps = 0;
			turnDirection(); // turn right according to where the bug is 
			times++;
		}
	}
	private void turnDirection() { // set the direction
		if(times == 0) {
			for(int i = 0; i < 3; ++i) {
				turn();
			}
		}else if(times == 1) {
			for(int i = 0; i < 5;++i) {
				turn();
			}
		}
	}
}
