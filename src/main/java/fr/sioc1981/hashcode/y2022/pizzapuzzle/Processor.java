package fr.sioc1981.hashcode.y2022.pizzapuzzle;

import java.util.ArrayList;
import java.util.Optional;

public class Processor {

	static long currentDay = 0;

	public static void process() {

		currentDay = 0;
		long maxDays = 0;

//		// calculer maxDays
//		for (Project project : Launcher.AVAILABLE_PROJECTS) {
//			int maxDaysForProject = project.bestBefore + project.score;
//			if (maxDaysForProject > maxDays) {
//				maxDays = maxDaysForProject;
//			}
//		}
//
//		System.out.println("maxDays = " + maxDays);

		maxDays = Integer.MAX_VALUE;
		
		ArrayList<Project> projectToRemove = new ArrayList<Project>();

		while (currentDay < maxDays) {
			projectToRemove.clear();
			// DAY START
			for (Project project : Launcher.PROJECTS_RELEASED) {
				if (project.endDay == currentDay) {
					// release all contributors

					for (int i = 0; i < project.contributors.size(); i++) {
						Contributor con = project.contributors.get(i);
						Skill skillUsed = project.skills.get(i);

						if (con.skills.get(skillUsed.name).level <= skillUsed.level) {
							// LEVEL UP !
							con.skills.get(skillUsed.name).level++;
						}

						con.available = true;
					}

				}
			}
			
			for (Project project : Launcher.AVAILABLE_PROJECTS) {
				boolean ready = true;
				
				if (project.bestBefore + project.maxScore <= currentDay + project.duration ) {
					projectToRemove.add(project);
					continue;
				}

				// trouver des contributeurs disponibles qui correspondent aux skills demand�s
				for (Skill requiredSkill : project.skills) {
					Contributor contrib = null;

					for (Contributor contributor : Launcher.CONTRIBUTORS) {
						if (!contributor.available || project.contributors.contains(contributor))
							continue;

						Skill contributorSkill = contributor.skills.get(requiredSkill.name);
						if (contributorSkill != null && contributorSkill.level >= requiredSkill.level) {
							// attribuer le contributeur
							contrib = contributor;
							break;
						} else if (contributorSkill != null && contributorSkill.level + 1 == requiredSkill.level
								&& project.contributors.stream().filter(c -> {
									Skill skill = c.skills.get(requiredSkill.name);
									if (skill != null && skill.level >= requiredSkill.level) {
										return true;
									} else {
										return false;
									}
								}).count() > 0) {

							contrib = contributor;
							break;
						}
					}
					if (contrib != null) {
						project.contributors.add(contrib);
						ready = true;
					} else {
						ready = false;
						project.contributors.clear();
						break;
					}
				}

				if (ready) {
					project.contributors.forEach(c -> c.available = false);
					Launcher.PROJECTS_RELEASED.add(project);
					projectToRemove.add(project);
					project.endDay = (int) (currentDay + project.duration);
					// launcher.AVAILABLE_PROJECTS.remove(project)
				} else {
					project.contributors.forEach(c -> c.available = false);
					// nothing to do for now
				}
				
			}

			

			Launcher.AVAILABLE_PROJECTS.removeAll(projectToRemove);
			
			currentDay = Launcher.PROJECTS_RELEASED.stream().filter(project -> project.endDay > currentDay).mapToInt(project -> project.endDay).asLongStream().min().orElse(maxDays); 
//			currentDay++;

		}

	}

}
