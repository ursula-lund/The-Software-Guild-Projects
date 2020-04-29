/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ursul
 */
public class rockPaperScissors {

    public static void main(String[] args) {

       
       
        do {
        int userWin = 0;
        int compWin = 0;
        int tie = 0;
        int rounds = getRounds();
        boolean goodRounds = rounds >= 0 && rounds <= 10;
        
        if (!goodRounds) {
            System.out.println("Error: Please choose value between 1-10.");
            return;
        }
         
          
        
         
        //boolean endRounds = false;
        int play = 0;
        for (play = 0; play < rounds; play++) {
            int userPick = userTurn();
            int comAnswer = getComAnswer();

           

            String[] showPick = new String[]{"You choose Rock!!", "You choose Paper!!", "You choose Scissors!!"};
            System.out.println(showPick[userPick -1]);
            

            String[] showComp = new String[]{"Computer chooses Rock.", "Computer chooses Paper.", "Computer chooses Scissors."};
            System.out.println(showComp[comAnswer-1]);
            
          

            if (userPick == 1 && comAnswer == 3 || userPick == 2 && comAnswer == 1 || userPick == 3 && comAnswer == 2) {
                userWin++;
                System.out.println("You Win!!");
            } else if (userPick == 1 && comAnswer == 2 || userPick == 2 && comAnswer == 3 || userPick == 3 && comAnswer == 1) {
                compWin++;
                System.out.println("Computer Win.");
            } else {
                System.out.println("Tie.");
                tie++;

            }

        }

        if (userWin > compWin && userWin > tie) {
            System.out.println(" ");
            System.out.println("You're the Overall Winner!");
        } else if (userWin < compWin && userWin < tie) {
            System.out.println(" ");
            System.out.println("Computer Wins Overall.");
        } else {
            System.out.println(" ");
            System.out.println("No Overall Winners, You Tied!!");
        }

        System.out.println(" ");
        System.out.println("Number of times you won: " + userWin);
        System.out.println("Number of times computer won: " + compWin);
        System.out.println("Number of ties: " + tie);
        System.out.println(" ");
        
        
        
        }
        
        while (playAgain() == 1);
           
    }
    public static int getRounds() {
        System.out.println("How many rounds should we play? (1-10): ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        int rounds = Integer.parseInt(input);
        return rounds;

    }

    
    public static int userTurn() {
        System.out.println(" ");
        System.out.println("Rock, Paper, or Scissors? (1, 2, or 3?): ");
        Scanner sc = new Scanner(System.in);
        String userTurn = sc.nextLine();
        int userPick = Integer.parseInt(userTurn);
        return userPick;

    }

    public static int getComAnswer() {
        Random numbers = new Random();
        int comAnswer = numbers.nextInt(3) + 1;
        return comAnswer;
    }

    public static int playAgain() {

        System.out.println("Would you like to play again? yes or no: 1 = yes, 2 = no ");
        Scanner sc = new Scanner(System.in);
        String returnAgain = sc.nextLine();
        int yesNo = Integer.parseInt(returnAgain);
        
        if (yesNo == 2) {
            System.out.println("Thanks for Playing!");
    }
        return yesNo;
    }
   
}
