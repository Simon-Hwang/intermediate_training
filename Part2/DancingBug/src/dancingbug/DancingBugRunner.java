  
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

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.util.Random;

public final class DancingBugRunner {
	private DancingBugRunner(){}
	public static void main(String args[]) {
		Random random = new Random();
		int array[] = new int[random.nextInt(10) + 1]; // produce a array length to be [1, 10]
		for(int i = 0; i < array.length; ++i) {
			array[i] = random.nextInt(10); // assign to be [0, 10)
		}
		DancingBug dancing = new DancingBug(array); // build the gridworld
		ActorWorld world = new ActorWorld();
	    world.add(new Location(random.nextInt(10),random.nextInt(10)), dancing);
	    world.show();
	}
}
