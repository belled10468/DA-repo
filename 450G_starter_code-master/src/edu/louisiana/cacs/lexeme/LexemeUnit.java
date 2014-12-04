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
public class LexemeUnit {
    String name;
    int token;

    public LexemeUnit(){
        
    }
    
    public LexemeUnit(String name, int token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
