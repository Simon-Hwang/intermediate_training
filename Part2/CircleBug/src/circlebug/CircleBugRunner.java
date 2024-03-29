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

package circlebug;//package name

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.util.Random;

public final class CircleBugRunner {
	private CircleBugRunner(){} // constructor
	public static void main(String args[]) {
		Random random = new Random();
		CircleBug circle = new CircleBug(random.nextInt(9) + 1); // produce a random interger number [1,9]
		ActorWorld world = new ActorWorld();
	    world.add(new Location(random.nextInt(10),random.nextInt(10)), circle);
	    world.show();
	}
}
