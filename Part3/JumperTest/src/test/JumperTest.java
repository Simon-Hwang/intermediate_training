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
 
import static org.junit.Assert.*;
import org.junit.Test;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import java.awt.Color;

public class JumperTest{
	@Test
	public void test1(){ // test when bug at grid[0,0]-> it would turn 45 
		ActorWorld world = new ActorWorld();
		Jumper jumper = new Jumper();
		world.add(new Location(0, 0), jumper);
		jumper.act();
		assertEquals(0, jumper.getLocation().getRow());
		assertEquals(0, jumper.getLocation().getCol());
		assertEquals(45, jumper.getDirection());
	}
	
	@Test
	public void test2(){// test when bug at grid[1,0]-> it would move to[0,0] 
		ActorWorld world = new ActorWorld();
		Jumper jumper = new Jumper();
		world.add(new Location(1, 0), jumper);
		jumper.act();
		assertEquals(0, jumper.getLocation().getRow());
		assertEquals(0, jumper.getLocation().getCol());
	}
	
	@Test
	public void test3(){ // test when the bug at grid[2,0] and there are rock in grid[0,0]->it would turn 45
		ActorWorld world = new ActorWorld();
		Jumper jumper = new Jumper();
		Rock rock = new Rock();
		world.add(new Location(2, 0), jumper);
		world.add(new Location(0, 0), rock);
		jumper.act();
		assertEquals(2, jumper.getLocation().getRow());
		assertEquals(0, jumper.getLocation().getCol());
		assertEquals(45, jumper.getDirection());
	}
	
	@Test
	public void test4(){// test when the bug at grid[2,0] and there are flower in grid[0,0]-> it will take place of the flower
		ActorWorld world = new ActorWorld();
		Jumper jumper = new Jumper();
		Flower flower = new Flower();
		world.add(new Location(2, 0), jumper);
		world.add(new Location(0, 0), flower);
		jumper.act();
		assertEquals(0, jumper.getLocation().getRow());
		assertEquals(0, jumper.getLocation().getCol());
	}
	
	@Test
	public void test5(){ // test when there are two jumper at the same line->if can, both jump two cells
		ActorWorld world = new ActorWorld();
		Jumper jumper1 = new Jumper();
		Jumper jumper2 = new Jumper();
		world.add(new Location(2, 0), jumper1);
		world.add(new Location(4, 0), jumper2);
		jumper1.act();
		jumper2.act();
		assertEquals(0, jumper1.getLocation().getRow());
		assertEquals(0, jumper1.getLocation().getCol());
		assertEquals(2, jumper2.getLocation().getRow());
		assertEquals(0, jumper2.getLocation().getCol());
	}
	
	@Test 
	public void test6(){ // test when there are two jumpers and the former can't move->the other can't move too
		ActorWorld world = new ActorWorld();
		Jumper jumper1 = new Jumper();
		Jumper jumper2 = new Jumper();
		world.add(new Location(0, 0), jumper1);
		world.add(new Location(2, 0), jumper2);
		jumper1.act();
		jumper2.act();
		assertEquals(0, jumper1.getLocation().getRow());
		assertEquals(0, jumper1.getLocation().getCol());
		assertEquals(45, jumper1.getDirection());
		assertEquals(2, jumper2.getLocation().getRow());
		assertEquals(0, jumper2.getLocation().getCol());
		assertEquals(45, jumper2.getDirection());
		
	}
}
