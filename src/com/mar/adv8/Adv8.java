package com.mar.adv8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Adv8 {

    private static String[][] instructions = new String[650][3];

    public static void main(String[] args) {

        try {
            File f = new File("resources/input8.txt");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;

            int i = 0;
            int count = 0;
            int jmpCount = 0;
            int nopCount = 0;
            //System.out.println(Integer.parseInt("+30") + Integer.parseInt("-7"));
            while ((line = br.readLine()) != null) {
                instructions[i][0] = line.split(" ")[0];
                instructions[i][1] = line.split(" ")[1];
                instructions[i][2] = "N";
                if (instructions[i][0].equals("nop")) {
                    nopCount++;
                }
                if (instructions[i][0].equals("jmp")) {
                    jmpCount++;
                }
                i++;
            }
            System.out.println("pocet prvku v poli=" + count);


            for (int j = 0; j < 641; j++) {
                if (!instructions[j][0].equals("acc")) {
                    switchInstructions(j);

                    i = 1;
                    int accumulator = 0;
                    while (instructions[i - 1][2].equals("N")) {
                        //printInstructions(i-1);
                        instructions[i - 1][2] = "Y";
                        switch (instructions[i - 1][0]) {
                            case "acc" -> {
                                accumulator += Integer.parseInt(instructions[i - 1][1]);
                                i++;
                            }
                            case "jmp" -> i += Integer.parseInt(instructions[i - 1][1]);
                            case "nop" -> i++;
                        }


                    }
                    System.out.println(i);
                    if (i == 642) {

                        System.out.println("+++++++++++++++++++++++++++++++++++++accumulator=" + accumulator);
                    }
                    for (int k = 0; k < 641; k++) {
                        instructions[k][2] = "N";
                    }
                    switchInstructions(j);
                    System.out.println();
                }
            }

            if (i == 640) {
                System.out.println("spravne");
            }

            System.out.println("jmpCount=" + jmpCount);
            System.out.println("nopCount=" + nopCount);
            br.close();
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
            System.out.println("error mar");
        }
    }

    private static void switchInstructions(int j) {
        System.out.print("instr[" + j + "]: " + instructions[j][0] + " " + instructions[j][1] + " zmenena na ");
        if (instructions[j][0].equals("nop")) {
            instructions[j][0] = "jmp";
        } else {
            instructions[j][0] = "nop";
        }
        System.out.println(instructions[j][0] + " " + instructions[j][1]);
    }

    private static void printInstructions(int j) {
        System.out.println("line " + (j + 1) + ": " + instructions[j][0] + " " + instructions[j][1]);
    }
}

