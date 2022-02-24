package fr.sioc1981.hashcode.y2022.pizzapuzzle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Launcher {
	
	
	public static List<Contributor> CONTRIBUTORS;
	
	public static List<Project> AVAILABLE_PROJECTS;
	
	public static List<Project> PROJECTS_RELEASED;
	
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
				process();
				writeOutput(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	private static void loadInput(File file) throws FileNotFoundException {
		CONTRIBUTORS = new ArrayList<>();
		AVAILABLE_PROJECTS = new ArrayList<>();
		PROJECTS_RELEASED = new ArrayList<>();
		try (final Scanner scanner = new Scanner(file)) {
			int maxContrib = scanner.nextInt();
			int maxProject = scanner.nextInt();
			scanner.nextLine();
			
			for (int i = 0; i < maxContrib; i++) {
				Contributor contributor = new Contributor();
				contributor.name = scanner.next(); 
				int nbSkill = scanner.nextInt();
				scanner.nextLine();
				for (int j = 0; j < nbSkill; j++) {
					Skill skill = new Skill();
					skill.name = scanner.next(); 
					skill.level = scanner.nextInt();
					contributor.skills.put(skill.name, skill);
					scanner.nextLine();
				}
				CONTRIBUTORS.add(contributor);
			}
//			System.out.println(CONTRIBUTORS);

			for (int i = 0; i < maxProject; i++) {
				Project project = new Project();
				project.name = scanner.next(); 
				project.duration = scanner.nextInt();
				project.maxScore = scanner.nextInt();
				project.bestBefore = scanner.nextInt();
				int nbRoles = scanner.nextInt();
				scanner.nextLine();
				for (int j = 0; j < nbRoles; j++) {
					Skill skill = new Skill();
					skill.name = scanner.next(); 
					skill.level = scanner.nextInt();
					project.skills.add(skill);
					scanner.nextLine();
				}
				AVAILABLE_PROJECTS.add(project);
			}
//			System.out.println(AVAILABLE_PROJECTS);
		}
	}
	
	private static void process() throws Exception {
		Processor.process();
	}

	private static void writeOutput(String fileName) throws Exception {
		System.out.println(PROJECTS_RELEASED);
		FileWriter fwriter = new FileWriter(new File("out", fileName + ".out.txt"));
		try (BufferedWriter bwriter = new BufferedWriter(fwriter)) {
			bwriter.write(Integer.toString(PROJECTS_RELEASED.size()));
			for (Project project : PROJECTS_RELEASED) {
				bwriter.write('\n');
				bwriter.write(project.name);
				bwriter.write(' ');
				bwriter.write(""+project.contributors.size());
				bwriter.write('\n');
				bwriter.write(project.contributors.stream().map(c -> c.name).collect(Collectors.joining(" ")));
			}
		}
	}
	
}
