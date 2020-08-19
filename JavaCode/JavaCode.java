package pl_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

public class JavaCode {
	public static void main(String[] args) {
		// print something if there is less than two files
		if (args.length <= 1) {
			System.out.println(
					"Please write your files name as argument in command line!\nTwo files as argument expected");
			System.exit(0);
		}

		try {
			// assign the name or the address of files in variables
			String arg0 = args[0];
			String arg1 = args[1];
			// call the second method
			main2(arg0, arg1);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("There is something Wrong with your files!");
			System.exit(0);
		}

	}

	// accept two string as arguments
	public static void main2(String arg0, String arg1) {
		File file1 = new File(arg0);
		File file2 = new File(arg1);
		// for efficiency and to increase reading speed we use Buffered reader
		// we do not need to close our file explicitly because BufferedReader implements
		// auto closable interface
		try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
				BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
			// to store every single line per loop
			String line;
			// to store line after splitting
			String arr[];
			// to store total of grades
			int total;

			// load first file into two dimensional hash
			HashMap<String, HashMap<Integer, String>> hash2df1 = new HashMap<String, HashMap<Integer, String>>();
			while ((line = br1.readLine()) != null) {
				// split the line by space and assign it to an array
				arr = line.split(" ");
				// calculate the total grade and assign it to total variable
				total = Integer.parseInt(arr[1]) + Integer.parseInt(arr[2]) + Integer.parseInt(arr[3]);
				// initialize the hash, name as a key and new hash as value
				hash2df1.put(arr[0], new HashMap<Integer, String>());
				// get or access the new hash and put total as key
				hash2df1.get(arr[0]).put(total, arr[1] + ", " + arr[2] + ", " + arr[3]);
			}

			// load second file into two dimensional hash
			HashMap<String, HashMap<Integer, String>> hash2df2 = new HashMap<String, HashMap<Integer, String>>();
			while ((line = br2.readLine()) != null) {
				arr = line.split(" ");
				total = Integer.parseInt(arr[1]) + Integer.parseInt(arr[2]) + Integer.parseInt(arr[3]);
				hash2df2.put(arr[0], new HashMap<Integer, String>());
				hash2df2.get(arr[0]).put(total, arr[1] + ", " + arr[2] + ", " + arr[3]);
			}

			// find the intersection of two file
			ArrayList<String> arraylist = new ArrayList<String>();
			if (hash2df1.size() < hash2df2.size()) {

				for (String i : hash2df1.keySet()) {
					if (hash2df2.containsKey(i)) {
						arraylist.add(i);

					}
				}
			} else {
				for (String i : hash2df2.keySet()) {
					if (hash2df1.containsKey(i)) {
						arraylist.add(i);
					}
				}
			}
			// sort and print
			Collections.sort(arraylist);
			for (String i : arraylist) {
				System.out
						.println(i + ": " + arg0 + " --> " + hash2df1.get(i) + ", " + arg1 + " --> " + hash2df2.get(i));
			}

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!!");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
