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
public class ParseInfo {

    String stack = "";
    String inputTokens = "";
    String actionLookUp = "";
    String actionValue = "";
    String valueOfLHS = "";
    String lengthOfRHS = "";
    String tempStack = "";
    String gotoLookUp = "";
    String gotoValue = "";
    String stackAction = "";
    String parseTreeStack = "";

    @Override
    public String toString() {

        return String.format("%-25s \t %-20s \t %-10s \t %-7s \t %s \t %s \t %-20s \t %-10s \t %-2s \t %-10s \t %s \t \n", 
                stack,
                inputTokens,
                actionLookUp,
                actionValue,
                valueOfLHS,
                lengthOfRHS,
                tempStack,
                gotoLookUp,
                gotoValue,
                stackAction,
                parseTreeStack);

    }

    public String getArrayListString(ArrayList a) {
        String result = "";
        for (Object o : a) {
            result += o.toString();
        }
        return result;
    }

    public String getGotoValue() {
        return gotoValue;
    }

    public void setGotoValue(String gotoValue) {
        this.gotoValue = gotoValue;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getInputTokens() {
        return inputTokens;
    }

    public void setInputTokens(String inputTokens) {
        this.inputTokens = inputTokens;
    }

    public String getActionLookUp() {
        return actionLookUp;
    }

    public void setActionLookUp(String actionLookUp) {
        this.actionLookUp = actionLookUp;
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    public String getValueOfLHS() {
        return valueOfLHS;
    }

    public void setValueOfLHS(String valueOfLHS) {
        this.valueOfLHS = valueOfLHS;
    }

    public String getLengthOfRHS() {
        return lengthOfRHS;
    }

    public void setLengthOfRHS(String lengthOfRHS) {
        this.lengthOfRHS = lengthOfRHS;
    }

    public String getTempStack() {
        return tempStack;
    }

    public void setTempStack(String tempStack) {
        this.tempStack = tempStack;
    }

    public String getGotoLookUp() {
        return gotoLookUp;
    }

    public void setGotoLookUp(String gotoLookUp) {
        this.gotoLookUp = gotoLookUp;
    }

    public String getStackAction() {
        return stackAction;
    }

    public void setStackAction(String stackAction) {
        this.stackAction = "push " + stackAction;
    }

    public String getParseTreeStack() {
        return parseTreeStack;
    }

    public void setParseTreeStack(String parseTreeStack) {
        this.parseTreeStack = parseTreeStack;
    }

}
