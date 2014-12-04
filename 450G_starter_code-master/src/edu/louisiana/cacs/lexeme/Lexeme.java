package edu.louisiana.cacs.lexeme;

import java.util.ArrayList;

public class Lexeme {
	private ArrayList lexeme;

	public ArrayList getLexeme() {
		return lexeme;
	}

	public void setLexeme(ArrayList lexeme) {
		this.lexeme = lexeme;
	}

	public Lexeme() {
		lexeme = new ArrayList();
	}

	public void addCharInLexeme(char c) {
		lexeme.add(c);
	}

	@Override
	public String toString() {
		return lexeme.toString();
	}
}
