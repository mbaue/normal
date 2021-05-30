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
            // rozparsovani vstupu do hashmap
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
            System.out.println("zaznamu " + itemCount + ", barev " + colors.size());
            System.out.println(colors);

            Set<String> bagColorsAllowed = new HashSet<>();
            Set<String> searchBags = new HashSet<>();
            searchBags.add("shiny gold");
            //Set<String> anotherBagColorsAllowed = new HashSet<>();

//            for (HashMap.Entry<String, List<String>> entry : colors.entrySet()) {
//                if (entry.getValue().contains("shiny gold")) {
//                    System.out.println(entry.getKey() + "++++++++++++++");
//                    bagColorsAllowed.add(entry.getKey());
//                }
//            }

            //System.out.println(bagColorsAllowed.size());
            //int prevSize = bagColorsAllowed.size();
            int round = 0;
            Set<String> bagsFromRound = new HashSet<>();
            do {
                round++;
                System.out.println(round + ". kolo --------------------------------------------------------------------");
                bagsFromRound.clear();
                for (String item : searchBags) {
                    System.out.println(item  + " searched-----------------------------------------");
                    for (HashMap.Entry<String, List<String>> entry : colors.entrySet()) {
                        if (entry.getValue().contains(item)) {
                            System.out.println(entry.getKey());
                            if (!bagColorsAllowed.contains(entry.getKey())) {
                                bagsFromRound.add(entry.getKey());
                            } else {
                                System.out.println(item + " already there +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            }
                        }
                    }
                }
                searchBags.clear();
                searchBags.addAll(bagsFromRound);
                bagColorsAllowed.addAll(bagsFromRound);
                System.out.println("found from this round " + bagsFromRound.size());
                System.out.println("total " + bagColorsAllowed.size());
            } while (bagsFromRound.size() > 0
                    //&& round <10
             );

            //System.out.println(anotherBagColorsAllowed.size());

            br.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("error mar");
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
