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
		this.teamId = teamId;
		this.goal = goal;
		this.counterGoal = counterGoal;
		if(goal > counterGoal) {
			point = 3;
			win = 1;
		}else if(goal == counterGoal) {
			point = 1;
			draw = 1;
		}else {
			loss = 1;
		}
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
		win += other.win;
		draw += other.draw;
		loss += other.loss;
		point += other.point;
		return this;
	}

	public String getTeamId() {
		return teamId;
	}
	
	
}
