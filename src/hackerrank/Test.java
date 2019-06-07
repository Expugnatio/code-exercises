package hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		File input = new File("test_cases/input02.txt");
		
		List<String> list = new ArrayList<>();
		//Scanner scanner = new Scanner(System.in);
		Scanner scanner = null;
		try {
			scanner = new Scanner(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int cont = 0;
		while (scanner.hasNextLine()) {
			if(cont == 0) {
				cont++;
				scanner.nextLine();
				continue;
			}
			list.add(scanner.nextLine());
		}
		scanner.close();
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
		
		tagContentExtractor(list);
		//currencyFormatter();
	}
	
	public static void tagContentExtractor(List<String> stringList) {
		
		List<String> results = new ArrayList<>();
		
		for (String str : stringList) {
			results.addAll(contentIdentifier(new StringBuilder(str)));	
		}
		
		for (String result : results) {
			System.out.println(""+result);
		}
	}
	
	public static List<String> contentIdentifier(StringBuilder sb) {
		
		List<String> validContent = new ArrayList<>();
		String startTag = "", nestedStartTag = "";
		String endTag = "";
		String currentContent = "";
		
		int startTagIndex;
		int	endTagIndex;
		
		// The algorithm consists on going from the wrapper tags to the nested ones
		while(sb.length() > 0) {
			
			startTag = findTag(sb.toString()); 
			
			// Checks if the line contains any Tag
			if(startTag.isEmpty()) {
				break;
			}else if(startTag.indexOf("</") == 0 || startTag.length() <= 2) { // Deletes orphan End Tag OR invalid tag (<>)
				sb.delete(0, sb.indexOf(startTag)+startTag.length());
				continue;
			}else {
				endTag = startTag.replaceFirst("<", "</");
			}
			
			startTagIndex = sb.indexOf(startTag);
			endTagIndex = sb.lastIndexOf(endTag);
			
			// Deletes orphan Start Tag 
			if(startTagIndex >= 0 && endTagIndex < 0) {
				sb.delete(0, sb.indexOf(startTag)+startTag.length());
				continue;
			}
			
			if(startTagIndex >= 0 && endTagIndex > 0 ) {
				currentContent = sb.substring(startTagIndex + startTag.length() , endTagIndex).trim();
				
				nestedStartTag = findTag(currentContent);
				
				if(nestedStartTag.isEmpty()) {
					if (!currentContent.isEmpty()) {
						validContent.add(currentContent);
					}
					sb.delete(startTagIndex, endTagIndex + endTag.length());
				}else{
					sb.replace(startTagIndex, endTagIndex + endTag.length(), currentContent);
				}
			}
		}
		
		if(validContent.isEmpty()) {
			validContent.add("None");
		}
		
		return validContent;
	}
	
	public static String findTag(String str) {
		int sTagIdx = str.indexOf("<"), eTagIdx = str.indexOf(">");
		if(sTagIdx >= 0 && eTagIdx >0) {
			return str.substring(str.indexOf("<"), str.indexOf(">")+1);
		}
		return "";
	}
	
	public static void currencyFormatter() {
		double n = 12324.134D;
		
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		System.out.println("US: " + nf.format(n));

		nf = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
		System.out.println("India: " + nf.format(n));

		nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
		System.out.println("US: " + nf.format(n));
		
        nf = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        System.out.println("France: " + nf.format(n));
	}


}
