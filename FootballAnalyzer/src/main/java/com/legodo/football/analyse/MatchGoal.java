package com.legodo.football.analyse;

import java.util.List;

public class MatchGoal {
	int minute;
	int matchday;
	Score team1score;
	Score team2score;
	
	MatchGoal(int minute, int matchday, Party party, int scoreTeam1, int scoreTeam2){
		this(minute, matchday, party.getTeam1Id(), party.getTeam2Id(), scoreTeam1, scoreTeam2);
	}
	
	MatchGoal(int minute, int matchday, String team1Id, String team2Id, int scoreTeam1, int scoreTeam2){
		this.minute = minute;
		this.matchday = matchday;
		this.team1score = new Score(team1Id, scoreTeam1, scoreTeam2);
		this.team2score = new Score(team2Id, scoreTeam2, scoreTeam1);
	}
	
	MatchGoal(int minute, int matchday, Party party, MatchGoal matchGoalOfLastMinute) {
		this(minute, matchday, party.getTeam1Id(), party.getTeam2Id(), matchGoalOfLastMinute.team1score.goal, matchGoalOfLastMinute.team1score.counterGoal);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matchday;
		result = prime * result + minute;
		result = prime * result + ((team1score == null) ? 0 : team1score.hashCode());
		result = prime * result + ((team2score == null) ? 0 : team2score.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchGoal other = (MatchGoal) obj;
		if (matchday != other.matchday)
			return false;
		if (minute != other.minute)
			return false;
		if (team1score == null) {
			if (other.team1score != null)
				return false;
		} else if (!team1score.teamId.equals(other.team1score.teamId))
			return false;
		if (team2score == null) {
			if (other.team2score != null)
				return false;
		} else if (!team2score.teamId.equals(other.team2score.teamId))
			return false;
		return true;
	}
	
}
