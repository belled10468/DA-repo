/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.louisiana.cacs.lexeme;

import java.util.ArrayList;

/**
 *
 * @author ChengYuan
 */
public class Expression {
    String LHS;
    String RHS;

    public Expression(String LHS, String RHS) {
        this.LHS = LHS;
        this.RHS = RHS;
    }

    public String parse(ArrayList stack, ParseInfo parseInfo){
        
        //Clear stack
        String targetStr = RHS;
        while(!targetStr.isEmpty()){
            LexemeUnit lu =(LexemeUnit)stack.get(stack.size()- 2);
            //Delete the two elements(stateNum and Lexeme), and move to next one
            if((lu.getName().equals(String.valueOf(targetStr.subSequence(targetStr.length() - lu.getName().length(), targetStr.length()))))){
                stack.remove(stack.size() -1);
                stack.remove(stack.size() -1);
                targetStr = targetStr.subSequence(0, targetStr.length() - lu.getName().length()).toString();
            }else{
                System.out.println("UNGRAMMATICAL from reduce");
                System.exit(1);
            }
        }
        parseInfo.setLengthOfRHS(String.valueOf(RHS.length()));
        stack.add(new LexemeUnit(LHS, 11));
        return LHS;
    }
    @Override
    public String toString() {
        return "Expression{" + "LHS=" + LHS + ", RHS=" + RHS + '}';
    }

    public String getLHS() {
        return LHS;
    }

    public void setLHS(String LHS) {
        this.LHS = LHS;
    }

    public String getRHS() {
        return RHS;
    }

    public void setRHS(String RHS) {
        this.RHS = RHS;
    }
    
}
