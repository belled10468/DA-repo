package com.javabrains.ChengYuan.analyzer;

import com.javabrains.ChengYuan.lexeme.Lexeme;

public class WordAnalyzer {
	public static final int EOF = 0;
	public static final int INT_LIT = 10;
	public static final int IDENT = 11;
	public static final int ASSIGN_OP = 20;
	public static final int ADD_OP = 21;
	public static final int SUB_OP = 22;
	public static final int MULT_OP = 23;
	public static final int DIV_OP = 24;
	public static final int LEFT_PAREN = 25;
	public static final int RIGHT_PAREN = 26;
	
	
	public void lex(String targetString) {
		int nextToken = -1;
		int previousIndex = 0;
		int targetIndex = 0;
		Lexeme lexeme = new Lexeme();
		//Clean spaces
		targetString = targetString.replace(" ", "");
		char[] targetStringArray = targetString.toCharArray();

		while (targetIndex < targetStringArray.length) {
			char targetChar = targetStringArray[targetIndex];

			if (isAlpha(targetChar)) {

				lexeme.addCharInLexeme(targetChar);

				// Determine the next char is the Alpha or digit. If yes, then
				// continue
				if (targetIndex < targetStringArray.length - 1) {
					char nextChar = targetStringArray[targetIndex + 1];

					while (isAlpha(nextChar) || isDigit(nextChar)) {
						lexeme.addCharInLexeme(nextChar);

						targetIndex++;
						if (targetIndex < targetStringArray.length-1) {
							nextChar = targetStringArray[targetIndex + 1];
						} else {
							break;
						}
					}
				}
				nextToken = IDENT;
			} else if (isDigit(targetChar)) {
				lexeme.addCharInLexeme(targetChar);

				// Determine the next char is the digit. If yes, then
				// continue
				if (targetIndex < targetStringArray.length - 1) {
					char nextChar = targetStringArray[targetIndex + 1];

					while (isDigit(nextChar)) {
						lexeme.addCharInLexeme(nextChar);
						targetIndex++;
						if (targetIndex < targetStringArray.length - 1) {
							nextChar = targetStringArray[targetIndex + 1];
						} else {
							break;
						}
					}
				}
				nextToken = INT_LIT;
			} else {
				nextToken = noDigitAndAlphaCharAnalyzer(lexeme, targetChar);
			}

			System.out.print("Next token is " + nextToken
					+ ", Next lexeme is ");
			/*if(targetIndex == 0){
				System.out.print(lexeme.getLexeme().get(0));
			}else*/
			for(int i = previousIndex; i <= targetIndex; i ++){
				System.out.print(lexeme.getLexeme().get(i));	
			}
			System.out.println();
			
			
			previousIndex = ++targetIndex;
			
			if (nextToken == 0) {
				// break;
			}
		}
		System.out.print("Next token is " + "-1"
				+ ", Next lexeme is EOF");
	}

	private int noDigitAndAlphaCharAnalyzer(Lexeme lexeme, char targetChar) {
		int nextToken;
		switch (targetChar) {
		case '(':
			lexeme.addCharInLexeme(targetChar);
			nextToken = LEFT_PAREN;
			break;
		case ')':
			lexeme.addCharInLexeme(targetChar);
			nextToken = RIGHT_PAREN;
			break;
		case '+':
			lexeme.addCharInLexeme(targetChar);
			nextToken = ADD_OP;
			break;
		case '-':
			lexeme.addCharInLexeme(targetChar);
			nextToken = SUB_OP;
			break;
		case '*':
			lexeme.addCharInLexeme(targetChar);
			nextToken = MULT_OP;
			break;
		case '/':
			lexeme.addCharInLexeme(targetChar);
			nextToken = DIV_OP;
			break;
		default:
			// end the examination
			lexeme.addCharInLexeme(targetChar);
			nextToken = EOF;
			break;
		}
		return nextToken;
	}

	boolean isAlpha(char targetChar) {
		return (65 <= targetChar && targetChar <= 90)
				|| (97 <= targetChar && targetChar <= 122);
	}

	boolean isDigit(char targetChar) {
		return (48 <= targetChar && targetChar <= 57);
	}
}
