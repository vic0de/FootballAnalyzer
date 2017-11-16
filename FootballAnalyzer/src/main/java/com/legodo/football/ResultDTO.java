package com.legodo.football;

public class ResultDTO extends IdentifiableDTO {
	
	
	public ResultDTO(String id, String name) {
		super(id, name);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int rank;
	
	private int gamesCount;
	
	private int pointsCount;
	
	private int winsCount;
	
	private int losesCount;
	
	private int tiedsCount;
	
	private int goalsCount;
	
	private int diffCount;
	
	private String logoUri;
	
	

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getGamesCount() {
		return gamesCount;
	}

	public void setGamesCount(int gamesCount) {
		this.gamesCount = gamesCount;
	}

	public int getPointsCount() {
		return pointsCount;
	}

	public void setPointsCount(int pointsCount) {
		this.pointsCount = pointsCount;
	}

	public int getWinsCount() {
		return winsCount;
	}

	public void setWinsCount(int winsCount) {
		this.winsCount = winsCount;
	}

	public int getLosesCount() {
		return losesCount;
	}

	public void setLosesCount(int losesCount) {
		this.losesCount = losesCount;
	}
	
	public int getGoalsCount() {
		return goalsCount;
	}

	public void setGoalsCount(int goalsCount) {
		this.goalsCount = goalsCount;
	}

	public int getDiffCount() {
		return diffCount;
	}

	public void setDiffCount(int diffCount) {
		this.diffCount = diffCount;
	}

	public String getLogoUri() {
		return logoUri;
	}

	public void setLogoUri(String logoUri) {
		this.logoUri = logoUri;
	}

	public int getTiedsCount() {
		return tiedsCount;
	}

	public void setTiedsCount(int tiedsCount) {
		this.tiedsCount = tiedsCount;
	}
	
}
