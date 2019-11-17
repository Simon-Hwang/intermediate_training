/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

 package sparseboundedgrid;
 
 public class OccupantInCol
{ 
	// set the node
    private Object occupant;
    private int col;
    public OccupantInCol(Object object, int c){
		this.occupant = object;
		this.col = c;
	}
	public void setObject(Object object){
		this.occupant = object;
	}
	public Object getObject(){
		return this.occupant;
	}
	public void setCol(int c){
		this.col = c;
	}
	public int getCol(){
		return col;
	}
}