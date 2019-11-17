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
import java.util.LinkedList;
import java.awt.Color;

/**
 * <code>AbstractGrid</code> contains the methods that are common to grid
 * implementations. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */ 

public class SparseBoundedGrid<E> extends AbstractGrid<E>{
	private int cols, rows;
	private ArrayList<LinkedList<OccupantInCol>> occupantArray;
	public SparseBoundedGrid(int rows, int cols){
		if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
		this.rows = rows;
		this.cols = cols;
		occupantArray = new ArrayList<LinkedList<OccupantInCol>>();
		for(int j = 0; j < rows; j++)
		{
			occupantArray.add(new LinkedList<OccupantInCol>());
		} 	
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

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
		//just like the @BoundedGrid.java
        ArrayList<Location> theLocations = new ArrayList<Location>();
        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for(int c = 0; c < occupantArray.get(r).size(); c++){
				OccupantInCol occupant = occupantArray.get(r).get(c);//get the occupant object
				theLocations.add(new Location(r, occupant.getCol()));
			}
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
		//locate row
		LinkedList<OccupantInCol> list = occupantArray.get(loc.getRow());
		if(list.size() != 0){
			for(int c = 0; c < list.size(); ++c){
				// locate col
				if(list.get(c).getCol() == loc.getCol()){
					return (E)list.get(c).getObject();
				}
			}
		}
        return null; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = remove(loc);
        occupantArray.get(loc.getRow()).add(new OccupantInCol(obj, loc.getCol()));
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        if(r != null){
			LinkedList<OccupantInCol> list = occupantArray.get(loc.getRow());
			if(list.size() != 0){
				for(int c = 0; c < list.size(); c++){
					if(list.get(c).getCol() == loc.getCol()){
						list.remove(c);
					}
				}
			}
		}
        return r;
    }
	
}