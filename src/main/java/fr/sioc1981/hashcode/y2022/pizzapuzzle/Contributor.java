package fr.sioc1981.hashcode.y2022.pizzapuzzle;

import java.util.HashMap;

public class Contributor {
	
	String name;

	boolean available = true;
	
	HashMap<String, Skill> skills = new HashMap<>();

	@Override
	public String toString() {
		return "Contributor [name=" + name + ", available=" + available + ", skills=" + skills + "]";
	}

}
