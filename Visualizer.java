package mazeSolver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Visualizer.java
 * Visualizer for pathFinder pathfinder
 * ICS4UE
 * @author Blair Wang
 * @version 1.0, 14 November 2021
 */
public class Visualizer extends JFrame{
    PathFinder pathFinder;
    MazePanel panel;
    
    final int MAX_X = (int)getToolkit().getScreenSize().getWidth();
    final int MAX_Y = (int)getToolkit().getScreenSize().getHeight();    
    final int GRIDSIZE = MAX_Y/120;
	final int TILE_SIZE = 15;

	/**
	 * Visualizer
	 * Constructor for visualizer object
	 * @param pathFinder: pathfinder object to display
	 */
    Visualizer (PathFinder pathFinder) {
        this.pathFinder = pathFinder;
        this.panel = new MazePanel();
        this.getContentPane().add(BorderLayout.CENTER, panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(MAX_X/2, MAX_Y/2);
        this.setVisible(true);
        
    }
    
    /**
     * MazePanel
     * Panel to display path on
     * ICS4UE
     * @author Blair Wang
     * @version 1.0, 14 November 2021
     */
    private class MazePanel extends JPanel {
    	/**
    	 * paintComponent
    	 * paints maze onto the panel
    	 */
        public void paintComponent(Graphics g)  {
        	for(int i = 0; i < pathFinder.getMap().length; i++) {
        		for(int j = 0; j < pathFinder.getMap()[i].length; j++) {
        			if(pathFinder.getMap()[i][j].equals(Constants.WALL)) {
        				g.setColor(Color.BLACK);
        			}else if(pathFinder.getMap()[i][j].equals(Constants.SPACE)) {
        				g.setColor(Color.WHITE);
        			}else if(pathFinder.getMap()[i][j].equals(Constants.START)) {
        				g.setColor(Color.GREEN);
        			}else if(pathFinder.getMap()[i][j].equals(Constants.CHECKED)) {
        				g.setColor(Color.GRAY);
        			}else if(pathFinder.getMap()[i][j].equals(Constants.PATH)) {
        				g.setColor(Color.BLUE);
        			}else{
        				g.setColor(Color.RED);
        			}
        			g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        		}
        	}
        	this.repaint();
        }
    }
}