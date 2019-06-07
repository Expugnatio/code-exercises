package geekforgeeks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {
	
	public static void main(String[] args) {
		
		BiFunction<LinkedList<String>, Integer, String> shiftString = (chain, shifts) -> {
			for (int i = 0; i <= shifts; i++) {
				chain.addFirst(chain.getLast());
				chain.removeLast();
			}
			return chain.stream().collect(Collectors.joining(" "));
		};
		
		File input = new File("test_cases/shift_testcases.txt");
		
		LinkedList<String> chainOfNumbers = null;
		//Scanner scanner = new Scanner(System.in);
		Scanner scanner = null;
		try {
			scanner = new Scanner(input);
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
				chainOfNumbers = new LinkedList<>( Arrays.asList(scanner.nextLine().split("\\s+")) );
				System.out.println( shiftString.apply(chainOfNumbers, Integer.parseInt(testData[1])) );
			}
		}
		scanner.close();
		
		
		
		
		
		System.out.println(shiftString.apply(chainOfNumbers, 2));
		
		/*
		list.add("Nayeem loves counseling</h1><h2>Is Valid</h2>");
		list.add("<h1>Nayeem loves counseling</h1>");
		list.add("<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>");
		list.add("<h1><h1>Sanjay has no watch</h1></h1><Amee>safat codes like a ninja</amee><par>So wait for a while</par>");
		list.add("<Amee>safat codes like a ninja</amee>");
		list.add("<SA premium>Imtiaz has a secret crush</SA premium>");
		
		list.add("<>hello</>");
		list.add("<>hello</><h>dim</h>");
		list.add("<>hello</><h>dim</h>>>>>");*/
		
		//currencyFormatter();
	}
	
	public String shiftChain(LinkedList<String> chain, int shifts) {
		for (int i = 0; i <= shifts; i++) {
			chain.addFirst(chain.getLast());
			chain.removeLast();
		}
		return chain.stream().collect(Collectors.joining(" "));
	}
	
}
