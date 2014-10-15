package com.javabrains.ChengYuan;

import java.util.Scanner;

import com.javabrains.ChengYuan.IO.FileInput;
import com.javabrains.ChengYuan.analyzer.WordAnalyzer;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordAnalyzer wa = new WordAnalyzer();
		System.out.println("Please choose input method\n 1: keyboard 2:file else: exit\n");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		if(choice == 1){
			System.out.println("Please input file address");
			String fileAddress = sc.next();
			wa.lex(FileInput.readFile(fileAddress));
		}else{
			System.out.println("Please input equation ex: (sum + 47) / total");
			String equation = sc.next();
			wa.lex(equation);
		}
	}

}
