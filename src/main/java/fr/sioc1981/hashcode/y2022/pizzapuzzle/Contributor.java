package fr.sioc1981.hashcode.y2022.pizzapuzzle;

import java.util.ArrayList;

public class Contributor {
	
	String name;
	
	ArrayList<Skill> skills = new ArrayList<>();
	
	boolean available = true;

	@Override
	public String toString() {
		return "Contributor [name=" + name + ", skills=" + skills + ", available=" + available + "]";
	}
	
}
