package data;

import java.util.ArrayList;

public class Project {
	
	int score;
	
	int duration;
	
	int bestBefore;
	
	ArrayList<Skill> skills;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getBestBefore() {
		return bestBefore;
	}

	public void setBestBefore(int bestBefore) {
		this.bestBefore = bestBefore;
	}

	public ArrayList<Skill> getSkills() {
		return skills;
	}

	public void setSkills(ArrayList<Skill> skills) {
		this.skills = skills;
	}

}
