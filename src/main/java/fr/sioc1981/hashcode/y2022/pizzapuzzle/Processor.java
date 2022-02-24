package fr.sioc1981.hashcode.y2022.pizzapuzzle;

public class Processor {
	
	static int currentDay = 0;
	
	public static void process() {
		
			
			//parcourir les projets dispo
			for(int i = 0; i < Launcher.AVAILABLE_PROJECTS.size(); i++) {
				Project project = Launcher.AVAILABLE_PROJECTS.get(i);
				boolean ready = true;
				
				//trouver des contributeurs disponibles qui correspondent aux skills demand�s
				for(Skill requiredSkill : project.skills) {
					Contributor contrib = null;
					
					for(Contributor contributor : Launcher.CONTRIBUTORS) {
						if(!contributor.available || project.contributors.contains(contributor))
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
						ready = true;
					}else {
						ready = false;
						project.contributors.clear();
						break;
					}
				}
				
				if (ready) {
					project.contributors.forEach(c -> c.available = false);
					Launcher.PROJECTS_RELEASED.add(project);
					//launcher.AVAILABLE_PROJECTS.remove(project)
				}else {
					project.contributors.forEach(c -> c.available = false);
					//nothing to do for now
				}
			}
			
			Launcher.AVAILABLE_PROJECTS.removeAll(Launcher.PROJECTS_RELEASED);

	}

}
