/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.louisiana.cacs.lexeme;

/**
 *
 * @author ChengYuan
 */
public class ActionLookupPair {
    int stateNum;
    LexemeUnit token;

    public ActionLookupPair() {
    }

    public ActionLookupPair(int stateNum, LexemeUnit token) {
        this.stateNum = stateNum;
        this.token = token;
    }

    public int getStateNum() {
        return stateNum;
    }

    public void setStateNum(int stateNum) {
        this.stateNum = stateNum;
    }

    @Override
    public String toString() {
        return "[" + stateNum + ", " + token.getName() +"]";
    }
    
    

    public LexemeUnit getToken() {
        return token;
    }

    public void setToken(LexemeUnit token) {
        this.token = token;
    }
    
}
