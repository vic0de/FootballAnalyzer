package com.legodo.football;


public class RepositoryFilter {
	
	String seasonId;
	
	String leagueId;
		
	int minute;
	
	
	RepositoryFilter(String seasonId, String leagueId){
		this.seasonId = seasonId;
		this.leagueId = leagueId;
	}
	
	String getKey(){
		return seasonId  + "_" + leagueId;
	}

}
