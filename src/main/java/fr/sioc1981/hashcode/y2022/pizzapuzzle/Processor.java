package fr.sioc1981.hashcode.y2022.pizzapuzzle;

import java.util.Optional;

public class Processor {

	static int currentDay = 0;

	public static void process() {

		currentDay = 0;
		int maxDays = 0;

		// calculer maxDays
		for (Project project : Launcher.AVAILABLE_PROJECTS) {
			int maxDaysForProject = project.bestBefore + project.score;
			if (maxDaysForProject > maxDays) {
				maxDays = maxDaysForProject;
			}
		}

		System.out.println("maxDays = " + maxDays);

		maxDays = 100;

		while (currentDay < maxDays) {

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
			
			Launcher.AVAILABLE_PROJECTS.stream().sorted((a,b) -> a.score - b.score ).forEach( project -> {
				
				boolean ready = true;

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
					project.endDay = currentDay + project.duration;
					// launcher.AVAILABLE_PROJECTS.remove(project)
				} else {
					project.contributors.forEach(c -> c.available = false);
					// nothing to do for now
				}
				
			});

			

			Launcher.AVAILABLE_PROJECTS.removeAll(Launcher.PROJECTS_RELEASED);

			currentDay++;

		}

	}

}
