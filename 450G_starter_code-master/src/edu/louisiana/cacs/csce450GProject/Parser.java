package edu.louisiana.cacs.csce450GProject;

import edu.louisiana.cacs.IO.FileInput;
import edu.louisiana.cacs.analyzer.WordAnalyzer;
import edu.louisiana.cacs.lexeme.ActionLookupPair;
import edu.louisiana.cacs.lexeme.Expression;
import edu.louisiana.cacs.lexeme.LexemeUnit;
import edu.louisiana.cacs.lexeme.ParseInfo;
import edu.louisiana.cacs.lexeme.ParseTreeStack;
import java.util.ArrayList;

public class Parser {
    /*
     * YOUR CODE GOES HERE
     * 
     * You must implement two methods
     * 1. parse
     * 2. printParseTree
     
     * Print the intermediate states of the parsing process,
     * including the intermediate states of the parse tree,make
     * as specified in the class handout.
     * If the input is legal according to the grammar,
     * print ACCEPT, else UNGRAMMATICAL.
     * If the parse is successful, print the final parse tree.

     * You can modify the input and output of these function but not the name
     */

    public static final int INT_LIT = 10;
    public static final int IDENT = 11;
    public static final int ASSIGN_OP = 20;
    public static final int ADD_OP = 21;
    public static final int SUB_OP = 22;
    public static final int MULT_OP = 23;
    public static final int DIV_OP = 24;
    public static final int LEFT_PAREN = 25;
    public static final int RIGHT_PAREN = 26;

    public ArrayList<LexemeUnit> analyzedEquation = new ArrayList<LexemeUnit>();
    public ArrayList<Expression> expressionList = new ArrayList<Expression>();
    public ArrayList stack = new ArrayList();
    public ArrayList tempStack = new ArrayList();
    public ParseTreeStack mainTreeStack = new ParseTreeStack();
    public String[][] actionTable;
    public String[][] gotoTable;
    public ArrayList<ParseInfo> parseInfoList = new ArrayList<ParseInfo>();

    public Parser(String fileName) {
        System.out.println("File to parse : " + fileName);
        WordAnalyzer wa = new WordAnalyzer();

        String equation = "id+id*id";

        try {
         wa.lex(FileInput.readFile(fileName.trim()));
         } catch (Exception e) {
         System.out.println("File address error! show the default file");
         equation = FileInput.readFile("./file.txt");
         }
        this.analyzedEquation = wa.lex(equation);
        this.analyzedEquation.add(new LexemeUnit("$", 0));
        initialize();
    }

    private void initialize() {
        setExpressionList();
        setActionTable();
        setGotoTable();
        stack.add("0");
    }

    /*
     * Dummy code
     */
    public void printParseTree() {
       // System.out.println("Hello World from " + getClass().getName());

        int currentLevel = 0;
        for (char c : mainTreeStack.getParseTreeStack().toCharArray()) {
            
            switch (c) {
                case '[':
                   currentLevel++;
                    break;
                case ']':
                    currentLevel--;
                    break;
                case ' ':
                    break;
                case 'E':
                    printNode("E", currentLevel);
                    break;
                case 'T':
                    printNode("T", currentLevel);
                    break;
                case 'F':
                    printNode("F", currentLevel);
                    break;
                case 'i':
                    printNode("id", currentLevel + 1 );
                    break;
                case 'd':
                    break;
                default:
                    printNode(String.valueOf(c), currentLevel + 1);
                    break;
                    
            }
            
        }
        System.out.println("$");

    }
    public void printNode(String str,int currentLevel){
        for(int i = 1; i < currentLevel; i++){
            System.out.print("\t");
        }
        System.out.println(str);
    }

    /*
     * Dummy code
     */
    public void parse() {
        String actionValue = "";
        do {
            ParseInfo parseInfo = new ParseInfo();
            ActionLookupPair actionLookupUnit = new ActionLookupPair();

            parseInfo.setStack(getArrayListString(stack));
            parseInfo.setInputTokens(getArrayListString(analyzedEquation));

            String LastActionValue = actionValue;

            actionValue = actionLookUp(actionLookupUnit, parseInfo);

            parseInfo.setActionValue(actionValue);

            switch ((actionValue.toCharArray())[0]) {
                case 'S':
                    shift(Integer.valueOf(String.valueOf(actionValue.toCharArray()[1])), actionLookupUnit, parseInfo);
                    break;
                case 'R':
                    reduce(Integer.valueOf(String.valueOf(actionValue.toCharArray()[1])), actionLookupUnit, parseInfo);
                    break;
                default:
                    break;
            }

            if (!actionValue.equals("accept") && !actionValue.equals("UNGRAMMATICAL")) {

                LexemeUnit lu = (LexemeUnit) stack.get(stack.size() - 2);
                String stateNum = stack.get(stack.size() - 1).toString();
                ActionLookupPair currentActionPair = new ActionLookupPair(Integer.valueOf(stateNum), lu);
                parseInfo.setStackAction(lu.getName() + stateNum);

                if (lu.getName().equals("id")) {
                    mainTreeStack.insert(currentActionPair);
                } else if (lu.getName().equals("F")) {
                    ArrayList currentTreeStack = mainTreeStack.getTreeStack();

                    for (Object o : currentTreeStack.toArray()) {
                        if (o instanceof ActionLookupPair) {
                            ArrayList FStack = new ArrayList();
                            FStack.add(currentActionPair);
                            FStack.add(o);
                            currentTreeStack.add(currentTreeStack.indexOf(o), FStack);
                            currentTreeStack.remove(o);
                        }
                    }
                } else if (actionLookupUnit.getToken().getToken() != 0) {
                    if (lu.getName().equals("T")) {
                        ArrayList currentTreeStack = mainTreeStack.getTreeStack();
                        for (Object o : currentTreeStack.toArray()) {
                            ArrayList arr = (ArrayList) o;
                            if ((arr).size() == 2) {
                                ActionLookupPair a = (ActionLookupPair) arr.get(0);
                                if (a.getToken().getName().equals("F")) {
                                    ArrayList TStack = new ArrayList();
                                    TStack.add(arr);
                                    TStack.add(0, currentActionPair);
                                    currentTreeStack.add(currentTreeStack.indexOf(arr), TStack);
                                    currentTreeStack.remove(arr);
                                }
                            }
                        }
                    } else if (lu.getName().equals("E")) {
                        ArrayList currentTreeStack = mainTreeStack.getTreeStack();
                        for (Object o : currentTreeStack) {
                            ArrayList arr = (ArrayList) o;
                            if ((arr).size() == 2) {
                                ActionLookupPair a = (ActionLookupPair) arr.get(0);
                                if (a.getToken().getName().equals("T")) {
                                    ArrayList EStack = new ArrayList();
                                    EStack.add(arr);
                                    EStack.add(0, currentActionPair);
                                    currentTreeStack.add(currentTreeStack.indexOf(arr), EStack);
                                    currentTreeStack.remove(arr);
                                }
                            }
                        }
                    }
                } else {

                    ArrayList newCombineStack = new ArrayList();
                    ArrayList currentTreeStack = mainTreeStack.getTreeStack();
                    newCombineStack.add(currentActionPair);
                    newCombineStack.add(currentTreeStack.get(1));
                    newCombineStack.add(new ActionLookupPair((Integer) tempStack.get(tempStack.size() - 1), (LexemeUnit) tempStack.get(tempStack.size() - 2)));
                    newCombineStack.add(currentTreeStack.get(0));
                    tempStack = new ArrayList(tempStack.subList(0, tempStack.size() - 4));
                    currentTreeStack = new ArrayList(currentTreeStack.subList(2, currentTreeStack.size()));
                    currentTreeStack.add(0, newCombineStack);

                    mainTreeStack.setTreeStack(currentTreeStack);

                }

            }
            parseInfo.setParseTreeStack(mainTreeStack.getParseTreeStack());
            setTempStack(actionValue, LastActionValue, parseInfo);
            System.out.println(parseInfo);
            parseInfoList.add(parseInfo);
        } while (!actionValue.equals("accept") && !actionValue.equals("UNGRAMMATICAL"));

        printParseTree();

    }

    public String actionLookUp(ActionLookupPair actionLookupUnit, ParseInfo parseInfo) {

        Integer stateNum = Integer.valueOf(stack.get(stack.size() - 1).toString());

        LexemeUnit lu = analyzedEquation.get(0);

        actionLookupUnit.setStateNum(stateNum);

        actionLookupUnit.setToken(lu);

        parseInfo.setActionLookUp(actionLookupUnit.toString());

        String actionValue = "";

        switch (lu.getToken()) {
            case 11://id
                actionValue = actionTable[stateNum][0];
                break;
            case 21://+
                actionValue = actionTable[stateNum][1];
                break;
            case 23://*
                actionValue = actionTable[stateNum][2];
                break;
            case 25://(
                actionValue = actionTable[stateNum][3];
                break;
            case 26://)
                actionValue = actionTable[stateNum][4];
                break;
            case 0: //$
                actionValue = actionTable[stateNum][5];
                break;
            default:
                break;
        }
        return (actionValue.isEmpty() ? "UNGRAMMATICAL" : actionValue);
    }

    public void shift(int stateNum, ActionLookupPair actionLookupUnit, ParseInfo parseInfo) {
        analyzedEquation.remove(0);
        stack.add(actionLookupUnit.getToken());
        stack.add(stateNum);
        //parseInfo.setStackAction(actionLookupUnit.getToken().getName() + stateNum);
    }

    public void reduce(int stateNum, ActionLookupPair actionLookupUnit, ParseInfo parseInfo) {

        String parseResult = expressionList.get(stateNum - 1).parse(stack, parseInfo);
        LexemeUnit lu = (LexemeUnit) stack.get(stack.size() - 1);

        int x = Integer.valueOf(stack.get(stack.size() - 2).toString());

        int y = 0;
        switch (lu.getName().toCharArray()[0]) {
            case 'F':
                y = 2;
                break;
            case 'T':
                y = 1;
                break;
            case 'E':
                y = 0;
                break;
            default:
                System.out.println("UNGRAMMATICAL from reduce prat2");
                System.exit(1);
                break;
        }
        String gotoStateNum = gotoTable[x][y];
        stack.add(gotoStateNum);
        //parseInfo.setStackAction(((LexemeUnit)stack.get(stack.size() - 2)).getName() + gotoStateNum);
        parseInfo.setValueOfLHS(lu.getName());
        parseInfo.setGotoLookUp("[" + x + ", " + lu.getName() + "]");
        parseInfo.setGotoValue(gotoStateNum);
    }

    public String getArrayListString(ArrayList a) {
        String result = "";
        for (Object o : a) {
            result += o.toString();
        }
        return result;
    }

    public void setTempStack(String actionValue, String lastActionValue, ParseInfo parseInfo) {
        if (lastActionValue.startsWith("S") && actionValue.startsWith("R")) {
            tempStack = new ArrayList(stack.subList(0, stack.size() - 2));
            parseInfo.setTempStack(getArrayListString(tempStack));
        } else if (lastActionValue.startsWith("R") && actionValue.startsWith("S")) {
            tempStack = null;
        } else if (lastActionValue.startsWith("R") && actionValue.startsWith("R")) {
            parseInfo.setTempStack(getArrayListString(tempStack));
        }
    }

    private void setExpressionList() {
        /*
         1. E → E + T
         2. E → T
         3. T → T * F
         4. T → F
         5. F → (E)
         6. F → id
         */
        expressionList.add(new Expression("E", "E+T"));
        expressionList.add(new Expression("E", "T"));
        expressionList.add(new Expression("T", "T*F"));
        expressionList.add(new Expression("T", "F"));
        expressionList.add(new Expression("F", "(E)"));
        expressionList.add(new Expression("F", "id"));

    }

    private void setActionTable() {
        actionTable = new String[12][];
        actionTable[0] = new String[]{"S5", "", "", "S4", "", ""};
        actionTable[1] = new String[]{"", "S6", "", "", "", "accept"};
        actionTable[2] = new String[]{"", "R2", "S7", "", "R2", "R2"};
        actionTable[3] = new String[]{"", "R4", "R4", "", "R4", "R4"};
        actionTable[4] = new String[]{"S5", "", "", "S4", "", ""};
        actionTable[5] = new String[]{"", "R6", "R6", "", "R6", "R6"};
        actionTable[6] = new String[]{"S5", "", "", "S4", "", ""};
        actionTable[7] = new String[]{"S5", "", "", "S4", "", ""};
        actionTable[8] = new String[]{"", "S6", "", "S11", "", ""};
        actionTable[9] = new String[]{"", "R1", "S7", "", "R1", "R1"};
        actionTable[10] = new String[]{"", "R3", "R3", "", "R3", "R3"};
        actionTable[11] = new String[]{"", "R5", "R5", "", "R5", "R5"};

    }

    void setGotoTable() {
        gotoTable = new String[12][];
        gotoTable[0] = new String[]{"1", "2", "3"};
        gotoTable[1] = new String[]{"", "", ""};
        gotoTable[2] = new String[]{"", "", ""};
        gotoTable[3] = new String[]{"", "", ""};
        gotoTable[4] = new String[]{"8", "2", "3"};
        gotoTable[5] = new String[]{"", "", ""};
        gotoTable[6] = new String[]{"", "9", "3"};
        gotoTable[7] = new String[]{"", "", "10"};
        gotoTable[8] = new String[]{"", "", ""};
        gotoTable[9] = new String[]{"", "", ""};
        gotoTable[10] = new String[]{"", "", ""};
        gotoTable[11] = new String[]{"", "", ""};

    }

}
