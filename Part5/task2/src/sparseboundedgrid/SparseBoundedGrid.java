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

import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * <code>AbstractGrid</code> contains the methods that are common to grid
 * implementations. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */ 

public class SparseBoundedGrid<E> extends AbstractGrid<E>{
	private int cols, rows;
	private Map<Location, E> occupantMap;
	public SparseBoundedGrid(int rows, int cols){ //constructor
		if (rows <= 0){ 
            		throw new IllegalArgumentException("rows <= 0");
		}
		if (cols <= 0){
		    throw new IllegalArgumentException("cols <= 0");
		}
		this.rows = rows;
		this.cols = cols;
		occupantMap = new HashMap<Location, E>();	
	}
	public int getNumRows()
    {
        return rows;
    }

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return cols;
    }

    public boolean isValid(Location loc)//judege wheter the location is valid
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations() //get the occupied location and return it
    {
		// same as @UnboundedGrid.java
		ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet()){
            a.add(loc);
	}
        return a;
    }

    public E get(Location loc) //get the actor at the specfic location, if there are empty rerturn null
    {
        if (loc == null){
            throw new NullPointerException("loc == null"); // throw an error
	}
        return occupantMap.get(loc);
    }

    public E put(Location loc, E obj)//put an actor to a certain location
    {
        if (loc == null){
            throw new NullPointerException("loc == null");
	}
        if (obj == null){
            throw new NullPointerException("obj == null"); //throw error
	}
        return occupantMap.put(loc, obj);
    }

    public E remove(Location loc)
    {
        if (loc == null){
            throw new NullPointerException("loc == null");
	}
        return occupantMap.remove(loc);
    }
	
}
