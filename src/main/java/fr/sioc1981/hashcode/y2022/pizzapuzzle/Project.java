package fr.sioc1981.hashcode.y2022.pizzapuzzle;

import java.util.ArrayList;

public class Project {

	String name;

	int maxScore;

	int score;

	int duration;

	int bestBefore;

	int endDay = 0;

	ArrayList<Skill> skills = new ArrayList<>();

	ArrayList<Contributor> contributors = new ArrayList<>();

	@Override
	public String toString() {
		return "Project [name=" + name + ", maxScore=" + maxScore + ", score=" + score + ", duration=" + duration
				+ ", bestBefore=" + bestBefore + ", skills=" + skills + ", contributors=" + contributors + "]";
	}

}
