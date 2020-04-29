/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.healthyhearts;

import java.util.Scanner;

/**
 *
 * @author ursul
 */
public class healthyHearts {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What is your age?");
        String input = sc.nextLine();
        double yourAge = Double.parseDouble(input);
        
        double maxHeart = 220 - yourAge;
        double lowZone = maxHeart * 0.5;
        double highZone = maxHeart * 0.85;
        
        System.out.println("Your age is " + yourAge);
        System.out.println("Your maximum heart rate should be: " + maxHeart);
        System.out.println("Your target heart rate zone should be " + lowZone + "-" + highZone + " beats per minute.");
    }
    
}
