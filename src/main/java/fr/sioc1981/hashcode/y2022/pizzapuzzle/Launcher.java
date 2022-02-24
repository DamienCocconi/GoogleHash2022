package fr.sioc1981.hashcode.y2022.pizzapuzzle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Launcher {
	
	public static class Contributors {
		private HashMap<String, > likes;
		private HashMap<String> dislikes;
	}
	
	public static class Result {
		int score;
		HashSet<String> ingredientsList;
	}
	
	private static ArrayList<Client> clientList;
	
	private static HashSet<String> ingredientsList;
	
	public static void main(String[] args) throws Exception {
		
		ArrayList<String> filenames = new ArrayList<>();
		filenames.add("a_an_example");
		filenames.add("b_better_start_small");
		filenames.add("c_collaboration");
		filenames.add("d_dense_schedule");
		filenames.add("e_exceptional_skills");
		filenames.add("f_find_great_mentors");
		
		
		filenames.forEach(fileName -> {
			try {
				System.out.println("Filename: " + fileName);
				loadInput(new File("in", fileName+".in.txt"));
				process(clientList);
				writeOutput(ingredientsList, fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	private static void loadInput(File file) throws FileNotFoundException {
		try (final Scanner scanner = new Scanner(file)) {
			int maxClient = scanner.nextInt();
			clientList = new ArrayList<>(maxClient);
			Client client = null;
			String line;
			Pattern pattern = Pattern.compile("\\s* \\s*");
			while (scanner.hasNext()) {
//				System.out.println(scanner.nextInt());
				scanner.nextInt();
				line = scanner.nextLine();
				if (client == null ) {
					client = new Client();
					client.likes = pattern.splitAsStream(line.trim()).collect(Collectors.toCollection(HashSet::new));
//					System.out.println(client.likes);
				} else {
					client.dislikes = pattern.splitAsStream(line.trim()).collect(Collectors.toCollection(HashSet::new));
					clientList.add(client);
					client = null;
				}
			}
		}
	}
	
	private static HashSet<String> process(ArrayList<Client> clientList) throws Exception {
		ingredientsList = new HashSet();
		clientList.forEach(client -> ingredientsList.addAll(client.likes));

		return ingredientsList;
	}

	private static void writeOutput(HashSet<String> ingredientsList, String fileName) throws Exception {
		System.out.println(ingredientsList.size());
		FileWriter fwriter = new FileWriter(new File("out", fileName + ".out.txt"));
		try (BufferedWriter bwriter = new BufferedWriter(fwriter)) {
			bwriter.write(Integer.toString(ingredientsList.size()));
			bwriter.write(' ');
			bwriter.write(ingredientsList.stream().collect(Collectors.joining(" ")));
		}
	}
	
}
