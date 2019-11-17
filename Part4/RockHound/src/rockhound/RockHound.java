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
package rockhound;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;

import java.util.ArrayList;

public class RockHound extends Critter{
	public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
		// it will move the rock
            if (!(a instanceof Critter)) {
                a.removeSelfFromGrid();
		}
        }
    }
}
