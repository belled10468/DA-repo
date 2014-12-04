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
public class ParseTreeStack {

    ArrayList treeStack = new ArrayList();

    public ParseTreeStack() {
    }

    public ParseTreeStack(ActionLookupPair alp) {
        this.treeStack.add(alp);
    }

    public ParseTreeStack(ActionLookupPair alp, ArrayList treeStack) {
        this.treeStack.add(alp);

        this.treeStack.add(treeStack);
    }

    public String getParseTreeStack() {
        return getParseTreeStackInner(treeStack);
    }

    private String getParseTreeStackInner(ArrayList ts) {
        String result = "";

        for (Object o : ts) {
            if (o instanceof ActionLookupPair) {

                result += ((ActionLookupPair) o).getToken().getName() + " ";

            } else if (o instanceof ArrayList) {

                result += "[" + getParseTreeStackInner((ArrayList) o) + "] ";
            }
        }

        return result;
    }

    public void insert(Object o) {
        this.treeStack.add(0, o);
    }

    public ArrayList getTreeStack() {
        return treeStack;
    }

    public void setTreeStack(ArrayList treeStack) {
        this.treeStack = treeStack;
    }

}
