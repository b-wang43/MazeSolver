package mazeSolver;

import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * PathFinder.java
 * creates path through maze using recursive search, instantiates visualiser maze
 * ICS4UE
 * @author Blair Wang
 * @version 1.0, 14 November 2021
 */
public class PathFinder {
	final int[][] MOVEMENTS = {{1,0}, {-1,0}, {0, 1}, {0, -1}};
	final int PATH_TIMER_SLEEP = 20;
	final int BACK_TIMER_SLEEP = 5;
	private static String[][] map;
	private static Stack<Integer> xPos = new Stack<>();
	private static Stack<Integer> yPos = new Stack<>();
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private int maxX;
	private int maxY;
	private boolean finished = false;
	
	/**
	 * PathFinder
	 * Constructor of pathfinder object
	 * @param x: integer - maximum x coordinate of maze
	 * @param y: integer - maximum y coordinate of maze
	 * @param arr: String array of maze
	 */
	public PathFinder(int x, int y, String[][] arr) {
		map = new String[y][x];
		setMap(arr);
		setMaxX(x - 1);
		setMaxY(y - 1);
		setStartEndCoords();
		Visualizer visual = new Visualizer(this);
		findPath(this.getStartX(), this.getStartY());
	}
	
	/**
	 * findPath
	 * searches for a path from start to end points in the maze
	 * @param x: current x position on array
	 * @param y: current y position on array
	 * @return boolean: whether the recursive method should continue searching along this path
	 */
	public void findPath(int x, int y) {
		try {
			TimeUnit.MILLISECONDS.sleep(PATH_TIMER_SLEEP);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		xPos.push(x);
		yPos.push(y);
		if(map[y][x].equals(Constants.END)) {
			setFinished(true);
			return;
		}
		
		for(int i = 0; i < MOVEMENTS.length; i++) {
			int newX = x + MOVEMENTS[i][1];
			int newY = y + MOVEMENTS[i][0];
			if(newX >= 0 && newY >= 0 && newX <= maxX  && newY <= maxY && (map[newY][newX].equals(Constants.END) || map[newY][newX].equals(Constants.SPACE))) {
				if(map[newY][newX].equals(Constants.SPACE)) {
					map[newY][newX] = Constants.PATH;
				}
				findPath(newX, newY);
				if(finished) {
					return;
				}
			}
		}
		int checkX = xPos.pop();
		int checkY = yPos.pop();
		map[checkY][checkX] = Constants.CHECKED;
		try {
			TimeUnit.MILLISECONDS.sleep(BACK_TIMER_SLEEP);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * setStartEndCoords
	 * sets a random valid position for start and end points in the maze
	 */
	public void setStartEndCoords() {
		do{
			setStartX((int)(Math.random() * maxX));
			setStartY((int)(Math.random() * maxY));
		}
		while(!map[startY][startX].equals(Constants.SPACE));
		map[startY][startX] = Constants.START;
	
		do{
			setEndX((int)(Math.random() * maxX));
			setEndY((int)(Math.random() * maxY));
		}
		while(!map[endY][endX].equals(Constants.SPACE) || map[endY][endX].equals(Constants.START));
		map[endY][endX] = Constants.END;
	}
	
	
	/**
	 * setMaxX
	 * set maximum of x position
	 * @param max: maximum x value
	 */
	public void setMaxX(int max) {
		maxX = max;
	}
	
	/**
	 * setMaxY
	 * set maximum of y position
	 * @param max: maximum y value
	 */
	public void setMaxY(int max) {
		maxY = max;
	}
	
	/**
	 * setMap
	 * setting each element from an array as the maze
	 * @param arr: array to set maze as
	 */
	public void setMap(String[][] arr) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				map[i][j] = arr[i][j];
			}
		}
	}
	
	/**
	 * getMap
	 * returns maze array
	 * @return: maze array
	 */
	public String[][] getMap(){
		return PathFinder.map;
	}
	
	/**
	 * setStartX
	 * sets the starting x coordinate
	 * @param start: starting x coordinate
	 */
	public void setStartX(int start) {
		this.startX = start;
	}
	
	/**
	 * setStartY
	 * sets the starting y coordinate
	 * @param start: starting x coordinate
	 */
	public void setStartY(int start) {
		this.startY = start;
	}
	
	/**
	 * getStartY
	 * returns the starting y coordinate
	 * @return: starting y coordinate
	 */
	public int getStartY() {
		return this.startY;
	}
	
	/**
	 * getStartX
	 * returns the starting x coordinate
	 * @return: starting x coordinate
	 */
	public int getStartX() {
		return this.startX;
	}
	
	/**
	 * getMaxY
	 * returns the maximum y coordinate
	 * @return: maximum y coordinate
	 */
	public int getMaxY() {
		return this.maxY;
	}
	
	/**
	 * getMaxX
	 * returns the maximum x coordinate
	 * @return: maximum x coordinate
	 */
	public int getMaxX() {
		return this.maxX;
	}
	
	/**
	 * setEndX
	 * sets the ending x coordinate
	 *  @param end: ending x coordinate
	 */
	public void setEndX(int end) {
		endX = end;
	}
	
	/**
	 * setEndY
	 * sets the ending y coordinate
	 * @param end: ending y coordinate
	 */
	public void setEndY(int end) {
		endY = end;
	}
	
	/**
	 * getEndY
	 * returns the ending y coordinate
	 * @return: ending y coordinate
	 */
	public int getEndY() {
		return this.maxY;
	}
	
	/**
	 * getEndX
	 * returns the ending x coordinate
	 * @return: ending x coordinate
	 */
	public int getEndX() {
		return this.maxX;
	}
	
	/**
	 * setFinished
	 * set if the maze has been completed
	 * @param bool: whether the search has completed or not
	 */
	public void setFinished(boolean bool) {
		finished = bool;
	}
	
	/**
	 * getFinished
	 * returns if the maze has been completed
	 * @return: if maze is done
	 */
	public boolean getFinisehd() {
		return this.finished;
	}
}
