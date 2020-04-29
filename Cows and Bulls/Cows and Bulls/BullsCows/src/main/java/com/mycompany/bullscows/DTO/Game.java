/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullscows.DTO;

/**
 *
 * @author ursul
 */
public class Game {

    private Integer GameId;
    private Integer answerNumber;
    private boolean isOver;
    

    public Integer getGameId() {
        return GameId;
    }

    public void setGameId(Integer GameId) {
        this.GameId = GameId;
    }

    public Integer getanswerNumber() {
        return answerNumber;
    }

    public void setanswerNumber(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    public boolean getIsOver() {
        return isOver;
    }

    public void setIsOver(boolean isOver) {
        this.isOver = isOver;
    }

    public void hideAnswerNum() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
