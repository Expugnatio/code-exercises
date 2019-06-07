package codewars;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class Basics {

	public static void main(String[] args) {
		System.out.println(lcs("anothertest", "notatest"));
		//System.out.println(""+dblLinear(100));
		//System.out.println(isPangram("The quick brown fox jumps over the lazy dog"));
		//System.out.println(spinWords("Hey fellow warriors"));
		//sumDigPow(90, 100);
		//System.out.println(getXO("zpzpzpsppspxxOooOxX"));
		//System.out.println(highAndLow("1 2 3 4 -5 6 -7"));
		//System.out.println(SongDecoder("RWUBWUBWUBLWUB"));
		//System.out.println(createPhoneNumber(new int[]{1,2,3,4,5,6,7,8,9,0}) );
		/*int[] perfectPower = perfectPower(81);
		System.out.println("Base: "+ perfectPower[0] + " :: Expo: "+perfectPower[1]);*/
		//System.out.println(findShort("it turns out random test cases are easier than writing out basic ones"));
		//System.out.println(seriesSum(72));
	}
	
	public static String lcs(String x, String y) {//Longest Common Subsequence
		
		if(x.isEmpty() || y.isEmpty()) {
			return "";
		}
		System.out.println("X: "+x);
		System.out.println("Y: "+y);
		String[] xArray = x.split("");
		String[] yArray = y.split("");
		StringBuilder sb = new StringBuilder();
		for (String xLetter : xArray) {
			for (String yLetter : yArray) {
				if(xLetter.equals(yLetter)) {					
					sb.append(xLetter);
				}
			}
		}
		return sb.toString();
    }
	
	
	public static int dblLinear (int n) {
		List<Integer> array = new ArrayList<>();
		
		array.add(1);
		
		int y = 0, nextY;
		int z = 0, nextZ;
		
		for (int i = 0; i < n; i++) {
			
			nextY = 2 * array.get(y) +1;
			nextZ = 3 * array.get(z) +1;
			
			if (nextY <= nextZ) {
				array.add(nextY);
				y++;
				
				z += nextY == nextZ ? 1 : 0;
			} else {
				array.add(nextZ);
				z++;
			}
		}
		
		return array.get(n);
		
		/* Alt Solution ...
	    if (n == 0) return 1;
        SortedSet<Integer> u = new TreeSet<>();
        u.add(1);
        for(int i=0; i<n; i++) {
            int x = u.first();
            u.add(x*2+1);
            u.add(x*3+1);
            u.remove(x);
        }
        return u.first();
	    */
    }
	
	public static boolean isPangram(String sentence){
		sentence = sentence.toLowerCase();
		String abc = "abcdefghijklmnopqrstuvwxyz";
		int cont = 0;
		for (String letter : abc.split("")) {
			cont+= sentence.contains(letter) ? 1 : 0;
		}
		return cont == abc.length();
	}
	
	public static String spinWords(String sentence) {
		StringBuilder sb = new StringBuilder();
		for (String s : sentence.split(" ")) {
			sb.append(s.length() < 5 ? s : new StringBuilder(s).reverse()).append(" ");
		}
		return sb.toString().trim();
	}
	
	public static List<Long> sumDigPow(long a, long b) {
		List<Long> list = new ArrayList<>();
		long acum;
		String[] dividedNum;
		for (long i = a; i < b; i++) {
			acum = 0;
			dividedNum = (""+i).split("");
			for (int j = 0; j < dividedNum.length; j++) {
				acum += Math.pow(Integer.parseInt(dividedNum[j]), j+1);
			}
			if(acum == i) {
				list.add(i);
			}
		}
		return list;
    }
	
	public static boolean getXO (String str) {
		int cont = 0;
		for (String s : str.split("")) {
			cont+= s.equalsIgnoreCase("x") ? 1 : 0;
			cont+= s.equalsIgnoreCase("o") ? -1 : 0;
		}
		return cont == 0;
	}
	
	public static String highAndLow(String numbers) {
	    List<Integer> intNumbers = new ArrayList<>();
	    for (String s : numbers.split(" ")) {intNumbers.add(Integer.parseInt(s));}
	    intNumbers.sort((a,b) -> {return a-b;});
		return intNumbers.get(intNumbers.size()-1)+" "+intNumbers.get(0);
	  }
	
	public static String SongDecoder (String song){
		return song.replace("WUB"," ").trim().replaceAll(" +", " ");
	}
	
	public static String createPhoneNumber(int[] numbers) {
		StringBuilder sb = new StringBuilder();
		for (int i : numbers) {sb.append(i);}
		return String.format("(%3s) %3s-%4s", sb.substring(0,3), sb.substring(3,6), sb.substring(6,10));
	}
	
	public static int[] perfectPower(int number) {
		Double n = (double) number;
		Double base = 1D;
		Double pow = 1D;
		Double result = 1D;
		
		for (int i = 2; i < number; i++) {
			if(number % i == 0) {
				System.out.println("Divisor: "+i);
				base = (double) i;
				pow = Math.log(number) / Math.log(i);
				result = Math.pow(base, pow);
				System.out.println(""+ base +"::"+pow+"::"+result+"::"+n);
				if(result.equals(n)) {
					break;
				}
			}
		}
		
		return new int[]{base.intValue(), pow.intValue()};
	}	
	
	public static int findShort(String s) {
		List<String> list = Arrays.asList(s.split(" "));
		list.sort( (str1, str2) -> {return str1.length() - str2.length();} ); 
		return list.get(0).length();
    }
	
	public static String seriesSum(int n) {
		double numerador = 1;
		double denominador = 4;
		int aumento = 3;
		
		double frac = 0;
		Double result = 1D;
		
		
		for (int i = 1; i < n; i++) {
			frac = (numerador / denominador);
			result += frac;
			denominador += aumento;
		}
		result = ((double) Math.round(result * 100)) / 100;
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(result).toString();
	}
	
	public static void printMatrix(List<List<String>> matrix) {
		StringBuilder sb = new StringBuilder();
		for (List<String> col : matrix) {
			
			for (String element : col) {
				sb.append(element+"   ");
			}
			sb.append(System.getProperty("line.separator"));
		}
		System.out.println(sb.toString());
	}
}
