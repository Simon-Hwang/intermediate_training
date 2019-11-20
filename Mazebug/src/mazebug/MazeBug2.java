package mazebug;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;


/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug2 extends Bug {
	private Location next;
	private Stack<Location> path = new Stack<Location>();
	private boolean exit = false;
	private int dirIndex = 0;
	private int[] dirs = {1,1,1,1};
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug2() {
		setColor(Color.GREEN);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if(exit){
			return;
		}else {
			move();
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if(gr == null){
			return null;
		}
		ArrayList<Location> locs = new ArrayList<Location>();
		int[] sortDirs = {0,1,2,3};
		for(int i = 0; i < 3; ++i) { //sorting
			for(int j = i+ 1; j < 4; ++j) {
				if(dirs[i] > dirs[j]) {
					int tmp = sortDirs[i];
					sortDirs[i] = sortDirs[j];
					sortDirs[j] = tmp; //sorting tehe index according to the value
				}
			}
		}
		for(int i = dirIndex; i < 4; i++){ // there are 4 options 
			Location adjLoc = loc.getAdjacentLocation(getDirection() + sortDirs[i] * 90); //getDirection must return 0->the bug would not turn
			if(gr.isValid(adjLoc)){
				Actor actor = gr.get(adjLoc);
				if(actor instanceof Rock && actor.getColor().equals(Color.RED)){
					locs.clear();
					locs.add(adjLoc);
					return locs; //return only one Location
				}else if(actor == null){ //can move
					locs.add(adjLoc);
				}
			}
		}
		return locs;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Grid<Actor> gr = getGrid();
		if(gr == null){
			return false;
		}
		ArrayList<Location> locs = getValid(getLocation());
		if(locs.size() != 0){
			next = locs.get(0); //still get the first location-> locs get in dirs order
			return true;
		}
		return false;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if(gr == null) {
			return;
		}
		Location curLoc = getLocation();
		if(canMove()) { //move to next Location directly and leave a flower
			path.push(next);
			dirs[curLoc.getDirectionToward(next)/90]++; //add
			dirIndex = 0; // move->set 0
			Actor actor = gr.get(curLoc);
			if(actor instanceof Rock) { // it means it meets the red Rock
				exit = true; // the program is to end
				actor.removeSelfFromGrid(); // remove the rock before the bug move
			}
			moveTo(next);
		}else { //can't move -> go back
			path.pop(); // pop the curLoc first
			Location lastLoc = path.peek();//the last location
			moveTo(lastLoc);//return
			int locIndex = lastLoc.getDirectionToward(curLoc) / 90; 
			dirs[locIndex]--;//decrese
			int count = 0;
			for(int i = 0; i < 4; i++) {
				if(dirs[i] > dirs[locIndex]) {
					++count;
				}else if(dirs[i] == dirs[locIndex]) { //->consider the equal situation
					if(i < locIndex) {
						++count;
					}
				}
			}
			dirIndex = count+ 1; // If go NORTH that step, when go back, dirIndex = 1->start from EAST
		}
	}
}
