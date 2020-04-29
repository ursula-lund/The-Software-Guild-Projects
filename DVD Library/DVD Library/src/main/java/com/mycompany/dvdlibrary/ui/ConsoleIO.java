/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrary.ui;

import static java.lang.System.console;
import java.util.Scanner;
/*

 *
 * @author ursul
 */
public class ConsoleIO implements UserIO {

    public ConsoleIO() {
    }
    
    final private Scanner console = new Scanner(System.in);

   @Override
    public void print(String msg) {
       System.out.println(msg);
    }

    @Override
    public int readInt(String prompt) {
       boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                String stringValue = this.readString(prompt);
                num = Integer.parseInt(stringValue); 
                invalidInput = false; 
            } catch (NumberFormatException e) { 
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int result;
        do {
            result = readInt(prompt);
        } while (result < min || result > max);

        return result;
    }

    

    @Override
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
    }
    
    
    @Override 
    public String editString(String prompt, String oldString) {
       
        String newString = oldString;
        String userInput = readString(prompt);
        if (!userInput.isEmpty()) {
            newString = userInput;
        }
        return newString;
        
    }
    
    //add other editInt
    @Override
    public int editInt(String prompt, int oldValue, int min, int max) {
        int toReturn = Integer.MIN_VALUE;
        boolean isValid = false;
        while (!isValid) {
            toReturn = editInt(prompt, oldValue);
            isValid = toReturn >= min && toReturn <= max;

        }
        return toReturn;
    }
    
    
    public int editInt(String prompt, int oldValue) {
       int toReturn =Integer.MIN_VALUE;
        boolean isValid = false;
        while(!isValid) {
            String userInput = readString(prompt);
            if (userInput.isEmpty()) {
                toReturn = oldValue;
                isValid = true;
            } else {
                try {
                    toReturn = Integer.parseInt(userInput);
                    isValid = true;
                } catch(NumberFormatException e) {}
            }
        }
        return toReturn;
        
    }

    

  
}
