package codewars;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CuatroEnLinea {

	public static void main(String[] args) {
		/*System.out.println(""+whoIsWinner(Arrays.asList("A_Red","B_Yellow","A_Red",
				"B_Yellow","A_Red","B_Yellow","G_Red","B_Yellow")));*/
		
		/*System.out.println(""+whoIsWinner(Arrays.asList("A_Red","A_Yellow","A_Red",
				"B_Yellow","B_Red",
				"C_Red","C_Yellow","C_Red",
				"D_Yellow","D_Red","D_Red",
				//"B_Red",
				"D_Red")));
		*/
		
		List<String> myList = Arrays.asList(
				"G1Yellow",
				"C1Red",
				"E1Yellow",
				"B1Red",
				"A1Yellow",
				"E2Red",
				"G2Yellow",
				"B2Red",
				"A2Yellow",
				"C2Red",
				"F1Yellow",
				"F2Red",
				"D1Yellow",
				"A3Red",
				"A4Yellow",
				"A5Red",
				"A6Yellow",
				"B3Red",
				"D2Yellow",
				"G3Red",
				"F3Yellow",
				"F4Red",
				"D3Yellow",
				"G4Red",
				"D4Yellow",
				"D5Red",
				"G5Yellow",
				"C3Red",
				"F5Yellow",
				"E3Red",
				"F6Yellow",
				"D6Red",
				"G6Yellow",
				"E4Red",
				"E5Yellow",
				"C4Red",
				"C5Yellow",
				"B4Red",
				"C6Yellow",
				"B5Red",
				"B6Yellow",
				"E6Red"
        );// I think should  be Yellow, but the platform solution is Red
            
		System.out.println(whoIsWinner(myList));
	}
	
	public static String whoIsWinner(List<String> piecesPositionList) {
		List<List<String>> board = new ArrayList<>(7);
		
		HashMap<String, Integer> boundX = new HashMap<>();
		
		for (int i = 0; i < 7; i++) { 
			boundX.put(""+(char)('A'+i), i);
			board.add(new ArrayList<>(6));
		}
		
		String[] movement;
		int[] movementIndexes = new int[2];
		
		String result = "";
		for (String play : piecesPositionList) {
			System.out.println(""+play);
		}
		for (String play : piecesPositionList) {
			movement = play.split("_").length > 1 ? play.split("_") : play.split("\\d");
			movementIndexes[0] = boundX.get(movement[0]);
			board.get(movementIndexes[0]).add(movement[1]);
			movementIndexes[1] = board.get(movementIndexes[0]).size()-1;
			
			printMatrix(board);
			
			if(checkVertical(board, movementIndexes, movement[1]) >= 4 
					|| checkHorizontal(board, movementIndexes, movement[1]) >= 4
					|| checkCross(board, movementIndexes, movement[1]) >= 4) {
					
				result = board.get(movementIndexes[0]).get(movementIndexes[1]);
			}
			
			if( !result.isEmpty() ) {
				return result;
			}
			System.out.println("/********************************");
		}
		
		System.out.println("Solution Is: "+result);
		
        return "Draw";
    }
	
	public static int checkCross(List<List<String>> board, int[] position, String piece) {
		int contDiagonalPieces = 1; //Counting the initial piece
		int contTransversalPieces = 1; //Counting the initial piece
		
		List<String> column;
		
		boolean checkUpRight = true, checkDownRight= true, checkUpLeft = true, checkDownLeft = true;
	
		for (int j = 1; j < 4; j++) {
			
			
			if ( position[0] - j >= 0 ) {
				column = board.get(position[0] - j);
				
				//check Up Left
				if(checkUpLeft && (position[1] + j) < column.size()) {
					if(column.get(position[1] + j).equalsIgnoreCase(piece)) {
						contDiagonalPieces++;
					}else {
						checkUpLeft = false;
					}
				}else {
					checkUpLeft = false;
				}
				
				//check Down Left
				if(checkDownLeft && (position[1] - j) >= 0 && column.size() > position[1] - j) { 
					if(column.get(position[1] - j).equalsIgnoreCase(piece)) {
						contTransversalPieces++;						
					}else {
						checkDownLeft = false;
					}
				}else {
					checkDownLeft = false;
				}
			}
			
			
			if ( position[0] + j < board.size() ) {
				column = board.get(position[0] + j);
				
				//check Down Right
				if(checkDownRight && (position[1] - j) >= 0 && column.size() > position[1] - j) {
					if(column.get(position[1] - j).equalsIgnoreCase(piece)) {						
						contDiagonalPieces++;
					}else {
						checkDownRight = false;
					}
				}else {
					checkDownRight = false;
				}
				
				//check Up Right
				if(checkUpRight &&  (position[1] + j) < column.size()) { 
					if(column.get(position[1] + j).equalsIgnoreCase(piece)) {
						contDiagonalPieces++;						
					}else {
						checkUpRight = false;
					}
				}else {
					checkUpRight = false;
				}
			}
			if(contDiagonalPieces >= 4 || contTransversalPieces >= 4) {
				break;
			}
		}
		System.out.println("CD:"+contDiagonalPieces+"::CT:"+contTransversalPieces);
		
		return contDiagonalPieces >= 4 ? contDiagonalPieces : contTransversalPieces;
	}
	
	public static int checkHorizontal(List<List<String>> board, int[] position, String piece) {
		int contPieces = 0;
		int startPosition = (position[0] - 3) >= 0 ? 
				(position[0] - 3) : 0 ;
		int endPosition = (position[0] + 3) < board.size() ? 
				(position[0] + 3) : board.size() ;
		
		for (int i = startPosition; i < endPosition; i++) { // Look for pieces before and after the current move
			
			if(position[1] < board.get(i).size() && 
					board.get(i).get(position[1]).equalsIgnoreCase(piece)) {
				contPieces++;
			} else {
				contPieces = 0;
			}
			
			if(contPieces >= 4 || (endPosition - i < 4 && contPieces == 0)) {
				break;
			}
		}
		System.out.println("H:"+contPieces);
		return contPieces;
	}
	
	public static int checkVertical(List<List<String>> board, int[] position, String piece) {
		List<String> col = board.get(position[0]);
		int contPieces = 0;
		int endPosition = (position[1] - 3) >= 0 ? 
				(position[1] - 3) : 0;
		
		for (int i = position[1]; i >= endPosition; i--) { //Look for pieces below
			
			if(col.get(i).equalsIgnoreCase(piece)) {
				contPieces++;
			} else {
				contPieces = 0;
			}
			
			if(i + endPosition < 4 && contPieces == 0) {
				break;
			}
		}
		System.out.println("V:"+contPieces);
		return contPieces;
	}
	
	public static void printMatrix(List<List<String>> matrix) {
		StringBuilder sb = new StringBuilder();
		for (List<String> col : matrix) {
			
			for (String element : col) {
				sb.append(element+"\t");
			}
			sb.append(System.getProperty("line.separator"));
		}
		System.out.println(sb.toString());
	}
}
