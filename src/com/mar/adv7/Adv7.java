package com.mar.adv7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Adv7 {

    public static void main(String[] args) {
        try {
            File f = new File("resources/input7.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;

            int itemCount = 0;
            HashMap<String, List<String>> colors = new HashMap<>();

            while ((line = br.readLine()) != null) {
                itemCount++;
                //System.out.println("line " + itemCount + ": " + line);

                String outerBag = line.split(" contain ")[0];
                String[] innerBags = line.split(" contain ")[1].split(",");
                //System.out.println("pred *****" + printarray(innerBags));
                for (int j = 0; j < innerBags.length; j++) {
                    innerBags[j] = innerBags[j]
                            .replace(".", "")
                            .replaceAll("bag[s]?", "")
                            .replaceAll("\\d+", "")
                            .trim();
                }
                //System.out.println("  po ***** " + printarray(innerBags));
                colors.put(outerBag.replace("bags", "").trim(), Arrays.stream(innerBags).toList());
                //System.out.println();
            }
            System.out.println("zaznamu "+itemCount+", barev " + colors.size());
            System.out.println(colors);

            Set<String> bagColorsAllowed = new HashSet<String>();
            Set<String> anotherBagColorsAllowed = new HashSet<String>();
            for (HashMap.Entry<String, List<String>> entry : colors.entrySet()) {
                if (entry.getValue().contains("shiny gold")) {
                    System.out.println(entry.getKey() + "++++++++++++++");
                    bagColorsAllowed.add(entry.getKey());
                }
            }
            System.out.println(bagColorsAllowed.size());
            int prevSize = bagColorsAllowed.size();
            for(String item : bagColorsAllowed) {
                for (HashMap.Entry<String, List<String>> entry : colors.entrySet()) {
                    if (entry.getValue().contains(item)) {
                        System.out.println(entry.getKey() + "+++2nd level+++++++++++");
                        anotherBagColorsAllowed.add(entry.getKey());
                    }
                }
            }

            System.out.println(anotherBagColorsAllowed.size());

            br.close();


        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }


    }


    private static String printarray(String[] str) {
        StringBuilder retstr = new StringBuilder();
        for (String s : str) {
            retstr.append(s).append(" + ");
        }
        return retstr.toString();
    }
}
