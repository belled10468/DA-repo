package com.javabrains.ChengYuan.IO;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileInput {
	public static String readFile(String address) {
		String result = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(address))) {

			String line = null;
			while ((line = reader.readLine()) != null) {
				result += line;
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return result;
	}
}
