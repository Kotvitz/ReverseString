package com.example.reversestring;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReverseStringApp {

	public static List<String> reverseStringList(List<String> strList) {
		List<String> revStrList = new ArrayList<>();
		String revStr = new String();

		for (int i = 0; i < strList.size(); i++) {
			String str = strList.get(i);
			for (int j = str.length() - 1; j >= 0; --j) {
				revStr += str.charAt(j);
			}
			revStrList.add(revStr);
			revStr = "";
		}

		return revStrList;
	}

	public static List<String> getStringsFromFile(String filePath) {
		List<String> strList = new ArrayList<>();

		try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = buffer.readLine()) != null) {
				strList.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return strList;
	}

	public static void saveReverseStringListToFile(List<String> strList, String filePath) throws IOException {
		List<String> revStrList = reverseStringList(strList);

		FileWriter writer = new FileWriter(filePath);
		for (String revStr : revStrList) {
			writer.write(revStr + "\n");
		}
		writer.close();
	}

	public static void main(String[] args) {
		String sourceFilePath = args[0];
		String targetFilePath = args[1];
		List<String> strList = getStringsFromFile(sourceFilePath);
		if (strList.isEmpty()) {
			return;
		}
		try {
			saveReverseStringListToFile(strList, targetFilePath);
			System.out.println("Reversed strings were successfully saved to target file.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
