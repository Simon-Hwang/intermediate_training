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
 
package jumper;
 
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import java.util.Random;

public final class JumperRunner{
	private JumperRunner(){}
	public static void main(String[] args){
		Random random = new Random();
		Jumper jumper1 = new Jumper(); // build the gridworld
		Jumper jumper2 = new Jumper(); // build the gridworld
		ActorWorld world = new ActorWorld();
	    world.add(new Location(random.nextInt(10),random.nextInt(10)), jumper1);
		world.add(new Location(random.nextInt(10),random.nextInt(10)), jumper2);
	    world.show();
	}
}