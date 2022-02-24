package fr.sioc1981.hashcode.y2022.pizzapuzzle;

public class Processor {
	
	static int currentDay = 0;
	
	public static void process(Launcher launcher) {
		
			
			//parcourir les projets dispo
			for(int i = 0; i < launcher.AVAILABLE_PROJECTS.size(); i++) {
				Project project = launcher.AVAILABLE_PROJECTS.get(i);
				boolean ready = true;
				
				//trouver des contributeurs disponibles qui correspondent aux skills demandés
				for(Skill requiredSkill : project.skills) {
					Contributor contrib = null;
					
					for(Contributor contributor : launcher.CONTRIBUTORS) {
						if(!contributor.available)
							continue;
						
						Skill contributorSkill = contributor.skills.get(requiredSkill.name);
						if(contributorSkill != null && contributorSkill.level >= requiredSkill.level ) {
							//attribuer le contributeur
							contrib = contributor;
							break;
						}

					}
					if(contrib != null) {
						project.contributors.add(contrib);
					}else {
						ready = false;
						project.contributors.clear();
						break;
					}
				}
				
				if(ready) {
					project.contributors.forEach(c -> c.available = false);
					launcher.PROJECTS_RELEASED.add(project);
					//launcher.AVAILABLE_PROJECTS.remove(project)
				}else {
					//nothing to do for now
				}
			}
			
			launcher.AVAILABLE_PROJECTS.removeAll(launcher.PROJECTS_RELEASED);

	}

}
