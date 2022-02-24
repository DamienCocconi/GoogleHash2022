package data;

import java.util.ArrayList;

public class Contributor {
	
	String name;

	ArrayList<Skill> skills;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}

}
