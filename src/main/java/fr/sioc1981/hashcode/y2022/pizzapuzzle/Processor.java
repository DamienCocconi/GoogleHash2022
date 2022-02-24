package fr.sioc1981.hashcode.y2022.pizzapuzzle;

public class Processor {
	
	int currentDay = 0;
	
	public void process(Launcher launcher) {
		
		while(true) {
			
			//parcourir les projets dispo
			for(int i = 0; i < launcher.AVAILABLE_PROJECTS.size(); i++) {
				Project project = launcher.AVAILABLE_PROJECTS.get(i);
				
				//trouver des contributeurs disponibles qui correspondent aux skills demandés
				for(Skill requiredSkill : project.skills) {
					boolean contributorFound = false;
					for(Contributor contributor : launcher.CONTRIBUTORS) {
						Skill contributorSkill = contributor.skills.get(requiredSkill.name);
						if(contributorSkill != null && contributorSkill.level >= requiredSkill.level )
							//attribuer le contributeur
							
							project.contributors.add(contributor);
					}
				}
			}

			currentDay++;
		}
	}

}
