package com.legodo.football.analyse;

public class Party {

	private String team1Id;
	private String team2Id;

	public Party(String team1Id, String team2Id) {
		this.team1Id = team1Id;
		this.team2Id = team2Id;
	}

	public String getTeam1Id() {
		return team1Id;
	}

	public String getTeam2Id() {
		return team2Id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((team1Id == null) ? 0 : team1Id.hashCode());
		result = prime * result + ((team2Id == null) ? 0 : team2Id.hashCode());
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
		Party other = (Party) obj;
		if (team1Id == null) {
			if (other.team1Id != null)
				return false;
		} else if (!team1Id.equals(other.team1Id))
			return false;
		if (team2Id == null) {
			if (other.team2Id != null)
				return false;
		} else if (!team2Id.equals(other.team2Id))
			return false;
		return true;
	}

}
