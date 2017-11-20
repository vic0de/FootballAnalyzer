package com.legodo.football.analyse;

import java.util.function.IntConsumer;

public class Score implements IntConsumer{
	
	String teamId;
	int goal = 0;
	int counterGoal = 0;
	int win = 0;
	int draw = 0;
	int loss = 0;
	int point = 0;
	
	Score(){
		
	}
	
	Score(String teamId, int goal, int counterGoal) {
		super();
		this.teamId = teamId;
		this.goal = goal;
		this.counterGoal = counterGoal;
	}

	
	int getDiff() {
		return goal - counterGoal;
	}
	
	public void accept(int value) {
		return;
	}
	
	public Score combine(Score other) {
		goal += other.goal;
		counterGoal += other.counterGoal;
		win += (other.goal > other.counterGoal ? 1 : 0);
		draw += (other.goal == other.counterGoal ? 1 : 0);
		loss += other.goal < other.counterGoal ? 1 : 0;
		if(other.goal > other.counterGoal) {
			point += 3;
		}else if(other.goal == other.counterGoal) {
			point += 1;
		}
		return this;
	}

	public String getTeamId() {
		return teamId;
	}
	
	
}
