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
 
package sparseboundedgrid;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
/**
 * This class runs a world with additional grid choices.
 */
public final class SparseGridRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}