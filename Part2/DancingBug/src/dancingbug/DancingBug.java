  
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

package dancingbug;

import info.gridworld.actor.Bug;

public class DancingBug extends Bug{
	private int steps;
	private int arr[];
	public DancingBug(int array[]) {
		arr = array.clone(); //initilize the array
		steps = 0;
	}
	public void act() {
		turnDirection(arr[steps]);
		if(canMove()) {
			move();
			steps++;
			steps %= arr.length; // process as a circle
		}else{
			turn();//it may forwards to the wall
		}
	}
	private void turnDirection(int count) {
		for(int i = 0; i < count; ++i) {
			turn();
		}
	}
}
