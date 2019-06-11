package geekforgeeks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Test {
	
	public static void main(String[] args) {
		
		BiFunction<LinkedList<String>, Integer, String> shiftString = (chain, shifts) -> {
			
			shifts = (chain.size()-1) - shifts;
			for (int i = 0; i <= shifts; i++) {
				chain.addFirst(chain.getLast());
				chain.removeLast();
			}
			return chain.stream().collect(Collectors.joining(" "));
		};
		
		BiFunction<List<String>, Integer, String> shiftStringArray = (chain, shifts) -> {
			
			List<String> sub = chain.subList(shifts, chain.size() );
			List<String> tmp = new ArrayList<>(sub);
			tmp.addAll(chain.subList(0, shifts));
			StringBuilder sb = new StringBuilder();
			tmp.forEach(n -> sb.append(n+" "));
			return sb.toString().trim();
		};
		
		BiFunction<String[], Integer, String> shiftStringVector = (chain, shifts) -> {
			StringBuilder sb = new StringBuilder();
			String[] tmp = new String[chain.length];
			for (int i = shifts, j = 0; j < tmp.length; i++,j++) {
				if(i >= tmp.length) {
					i=0;
				}
				tmp[j] = chain[i];
				sb.append(tmp[j]+" ");
			}
			return sb.toString().trim();
		};
		
		
		LinkedList<String> chainOfNumbers = null;
		List<String> chainOfNumbers2 = null;
		
		Scanner scanner = null;
		File input = new File("test_cases/shift_testcases.txt");
		//Scanner scanner = new Scanner(System.in);
		try {
			scanner = new Scanner(input);
			//scanner = new Scanner(System.in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int cont = 0;
		int testCases = 0;
		while (scanner.hasNextLine()) {
			if(cont == 0) {
				cont++;
				testCases = Integer.parseInt(scanner.nextLine());
				continue;
			}else {
				String[] testData = scanner.nextLine().split("\\s+");
				String strOfNumbers = scanner.nextLine();
				List<String> listOfNumbers = Arrays.asList(strOfNumbers.split("\\s+"));
				chainOfNumbers = new LinkedList<>(listOfNumbers);
				chainOfNumbers2 = new ArrayList<>(listOfNumbers);
				System.out.println("Linked: "+ shiftString.apply(chainOfNumbers, Integer.parseInt(testData[1])) );
				System.out.println("Array: " + shiftStringArray.apply(chainOfNumbers2, Integer.parseInt(testData[1])) );
				System.out.println("Vector: " + shiftStringVector.apply(strOfNumbers.split("\\s+"), Integer.parseInt(testData[1])) );
			}
		}
		scanner.close();
	}
}
