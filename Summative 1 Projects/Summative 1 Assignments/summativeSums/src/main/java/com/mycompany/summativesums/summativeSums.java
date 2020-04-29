/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.summativesums;

/**
 *
 * @author ursul
 */
public class summativeSums {

    public static void main(String[] args) {

        int[] arrayOne = {1, 90, -33, -55, 67, -16, 28, -55, 15};
        int sum = addArray(arrayOne);
       

        System.out.println("1st Array Sum is: " + sum);

        int[] arrayTwo = {999, -60, -77, 14, 160, 301};
        sum = addArray (arrayTwo);
      

        System.out.println("2nd Array Sum is: " + sum);

        int[] arrayThree = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99};
        sum = addArray(arrayThree);
    

        System.out.println("3rd Array Sum is: " + sum);
    }

    public static int addArray(int[] array) {

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }
   
}


