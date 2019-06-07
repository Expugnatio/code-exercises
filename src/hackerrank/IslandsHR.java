package hackerrank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class IslandsHR {
	
	public static void main(String[] args) {
		
		List<List<Integer>> board = new ArrayList<>();
		
		List<Integer> row;
		Scanner scanner = new Scanner(System.in);
		int cont = 0;
		while (scanner.hasNextLine()) {
			if(cont == 0) {
				cont++;
				scanner.nextLine();
				continue;
			}
			String[] elements = scanner.nextLine().split("\\s");
			row = new ArrayList<>();
			for (String e : elements) {
				row.add(Integer.parseInt(e));
			}
			board.add(row);
		}
		scanner.close();
		
		System.out.println("Number of Islands: "+identifyIslands(board));
	}
	
	
	public static int identifyIslands(List<List<Integer>> board) {

		//Clone the matrix
		//List<List<Integer>> identifier = board.stream().map(ArrayList<Integer>::new).collect(Collectors.toList());
		
		int[] root = new int[2];
		int islandCont = 2;
		
		for (int y = 0; y < board.size(); y++) {
			
			List<Integer> row = board.get(y);
			
			for (int x = 0; x < row.size(); x++) {
				
				if(board.get(y).get(x) == 1) {
					root[0] = y;
					root[1] = x;
					exploreIsland(root, board, islandCont++);
				}
			}
			
		}
		
		Set<Integer> islands = new HashSet<>();
		
		for (List<Integer> col : board) {
			
			for (int island : col) {
				if(island != 0) {
					islands.add(island);
				}
			}
		}
		
		return islands.size();
	}
	
	public static void exploreIsland(int[] root, List<List<Integer>> original, int cont) {
				
		//System.out.println("Root: "+ root[0] + ";" + root[1]);
		
		List<int[]> possibleMovements = new ArrayList<>();
		
		if(checkUp(root, original)) {
			//System.out.println("Up:True");
			possibleMovements.add(new int[] {root[0] - 1, root[1]});
		}
		
		if(checkDown(root, original)) {
			//System.out.println("Down:True");
			possibleMovements.add(new int[] {root[0] + 1, root[1]});
		}
		
		if(checkLeft(root, original)) {
			//System.out.println("Left:True");
			possibleMovements.add(new int[] {root[0], root[1] - 1});
		}
		
		if(checkRight(root, original)) {
			//System.out.println("Right:True");
			possibleMovements.add(new int[] {root[0], root[1] + 1});
		}
		
		// Diagonals
		
		if(checkUpRight(root, original)) {
			//System.out.println("UpR:True");
			possibleMovements.add(new int[] {root[0] - 1, root[1] + 1});
		}
		
		if(checkUpLeft(root, original)) {
			//System.out.println("UpL:True");
			possibleMovements.add(new int[] {root[0] - 1, root[1] - 1});
		}
		
		if(checkDownRight(root, original)) {
			//System.out.println("DownR:True");
			possibleMovements.add(new int[] {root[0] + 1, root[1] + 1});
		}
		if(checkDownLeft(root, original)) {
			//System.out.println("DownL:True");
			possibleMovements.add(new int[] {root[0] + 1, root[1] - 1});
		}
		
		//System.out.println("Movements: ");
		
		for (int[] is : possibleMovements) {
			//System.out.println(is[0]+";"+is[1]);
		}
		
		//System.out.println("_______________");
		
		original.get(root[0]).set(root[1], cont);
		
		if(possibleMovements.isEmpty()) {
			return;
		}
		
		for (int[] movement : possibleMovements) {
			exploreIsland(movement, original, cont);
		}
	}
	
	public static boolean checkUp(int[] position, List<List<Integer>> original) {
		if(position[0] - 1 >= 0 && original.get(position[0]-1).get(position[1]) == 1) {
			return true;
		}
		return false;
	}
	
	public static boolean checkDown(int[] position, List<List<Integer>> original) {
		if(position[0] + 1 < original.size() && original.get(position[0]+1).get(position[1]) == 1) {
			return true;
		}
		return false;
	}
	
	public static boolean checkRight(int[] position, List<List<Integer>> original) {
		if(position[1] + 1 < original.get(0).size() && 
				original.get(position[0]).get(position[1] + 1) == 1) {
			return true;
		}
		return false;
	}
	
	public static boolean checkLeft(int[] position, List<List<Integer>> original) {
		if(position[1] - 1 >= 0 && original.get(position[0]).get(position[1] - 1) == 1) {
			return true;
		}
		return false;
	}
	
	//Diagonals
	
	public static boolean checkUpRight(int[] position, List<List<Integer>> original) {
		if(position[0] - 1 >= 0 && position[1] + 1 < original.get(0).size() &&
				original.get(position[0]-1).get(position[1]+1) == 1) {
			return true;
		}
		return false;
	}
	
	public static boolean checkUpLeft(int[] position, List<List<Integer>> original) {
		if(position[0] - 1 >= 0 && position[1] - 1 >= 0 &&
				original.get(position[0]-1).get(position[1]-1) == 1) {
			return true;
		}
		return false;
	}
	
	public static boolean checkDownRight(int[] position, List<List<Integer>> original) {
		if(position[0] + 1 < original.size() && position[1] + 1 < original.get(0).size() &&
				original.get(position[0]+1).get(position[1]+1) == 1) {
			return true;
		}
		return false;
	}
	
	public static boolean checkDownLeft(int[] position, List<List<Integer>> original) {
		if(position[0] + 1 < original.size() && position[1] - 1 >= 0 && 
				original.get(position[0]+1).get(position[1]-1) == 1) {
			return true;
		}
		return false;
	}

	public static void printMatrix(List<List<Integer>> matrix) {
		StringBuilder sb = new StringBuilder();
		for (List<Integer> col : matrix) {
			
			for (int element : col) {
				sb.append(element+"   ");
			}
			sb.append(System.getProperty("line.separator"));
		}
		System.out.println(sb.toString());
	}
}