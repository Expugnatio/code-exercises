package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Result {	
	
	public static void main(String[] args) {
		
	}	

    public static int countMessages(List<String> keys, String message) {
        Map<String,String> pressMap = new HashMap<>();

        String[] chars = message.split("");
        StringBuilder sb = new StringBuilder();
        for (String character : chars) {
            for (int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                int press = key.indexOf(character);
                if (press >= 0) {
                    press += 1;
                    pressMap.put(""+(i + 2), ""+press);
                    for (int j = 0; j < press; j++) {
                        sb.append("" + (i + 2));
                    }
                }
            }
        }

        Double total = 1D;
        Double modulo = 1000000007D;
        int value;
        for(Entry<String, String> entry : pressMap.entrySet()){
        	value = Integer.parseInt(entry.getValue());
            total *= Math.pow(2, value - 1);
        }
        total = total % modulo;
        return total.intValue();
    }
}