/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.doggenetics;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ursul
 */
public class dogGenetics {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("What is your dog's name? ");
        String dogName = sc.nextLine();
        System.out.println("Well then, I have this highly reliable "
                + "report on " + dogName + "'s prestigious background right here.");
        
        System.out.println(dogName + " is: ");
        
        Random rng = new Random();
        int ranOne = rng.nextInt(100);
        int ranTwo = rng.nextInt(100);
        int ranThree = rng.nextInt(100);
        int ranFour = rng.nextInt(100);
        int ranFive = rng.nextInt(100);

        int ranTotal = ranOne + ranTwo + ranThree + ranFour + ranFive;

        int stBern = 100 * ranOne / ranTotal;
        int chiHua = 100 * ranTwo / ranTotal;
        int redNose = 100 * ranThree / ranTotal;
        int comCur = 100 * ranFour / ranTotal;
        int kinDob = 100 * ranFive / ranTotal;

        System.out.println(stBern + "% St. Bernard.");
        System.out.println(chiHua + "% Chihuahua.");
        System.out.println(redNose + "% Dramatic RedNosed Asaian Pug");
        System.out.println(comCur + "% Common Cur.");
        System.out.println(kinDob + "% King Doberman");
        System.out.println("Wow, that's quite the dog!");
    }

}
