/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author ursul
 */
public class ConsoleIo implements UserIo {

    final private Scanner console = new Scanner(System.in);

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
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
    public String editString(String prompt, String oldString) {

        String newString = oldString;
        String userInput = readString(prompt);
        if (!userInput.isEmpty()) {
            newString = userInput;
        }
        return newString;

    }

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

    @Override
    public int editInt(String prompt, int oldValue) {
        int toReturn = Integer.MIN_VALUE;
        boolean isValid = false;
        while (!isValid) {
            String userInput = readString(prompt);
            if (userInput.isEmpty()) {
                toReturn = oldValue;
                isValid = true;
            } else {
                try {
                    toReturn = Integer.parseInt(userInput);
                    isValid = true;
                } catch (NumberFormatException e) {
                }
            }
        }
        return toReturn;

    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        boolean invalidInput = true;
        BigDecimal num = BigDecimal.ZERO;
        while (invalidInput) {
            try {
                String stringValue = this.readString(prompt);
                num = new BigDecimal(stringValue);
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        boolean invalidInput = true;
        LocalDate dateTime = null;
        while (invalidInput) {
            try {
                String enteredDate = this.readString(prompt);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                dateTime = LocalDate.parse(enteredDate, formatter);
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("input error. Pleae try again.");
            }
        }
        return dateTime;

    }

    @Override
    public BigDecimal editBigDecimal(String prompt, BigDecimal oldValue) {

        BigDecimal toReturn = BigDecimal.ZERO;
        boolean isValid = false;
        while (!isValid) {
            String userInput = readString(prompt);
            if (userInput.isEmpty()) {
                toReturn = oldValue;
                isValid = true;
            } else {
                try {
                    toReturn = new BigDecimal(userInput);
                    isValid = true;
                } catch (NumberFormatException e) {
                }
            }
        }
        return toReturn;

    }
}
