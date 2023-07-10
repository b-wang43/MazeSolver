package mazeSolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Main.java
 * Driver code of pathfinder algorithm (reads maze, creates pathfinder instance)
 * ICS4UE
 * @author Blair Wang
 * @version 1.0, 14 November 2021
 */
public class Main {
	public static void main(String[] args) throws IOException {
		String input = JOptionPane.showInputDialog("Enter the file name (With .txt):" + '\n' + "(Download and use text.txt from this folder)");
		File test = new File(input);
		Scanner in = new Scanner(test);
		ArrayList<String> held = new ArrayList<>();
		int horizontal = 0;
		while(in.hasNextLine()) {
			String holder = in.nextLine();
			held.add(holder);
			horizontal = holder.length();
		}
		in.close();
		
		String[][] maze = new String[held.size()][horizontal];
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length; j++) {
				maze[i][j] = Character.toString(held.get(i).charAt(j));
			}
		}
		
		new PathFinder(horizontal, held.size(), maze);
	}
}
