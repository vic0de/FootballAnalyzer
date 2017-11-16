package com.legodo.football;

import java.util.Arrays;
import java.util.List;


class OpenLigaDBRepository implements Repository{
	

	private  RestClient restClient;
	  
	  
	  
	  
	  

	public OpenLigaDBRepository() {
		super();
		try {
			this.restClient = new RestClient("https://www.openligadb.de");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<IdentifiableDTO> getSeasons() {
		return Arrays.asList(new IdentifiableDTO("2017", "2017/2018"), new IdentifiableDTO("2016", "2016/2017"), new IdentifiableDTO("2015", "2015/2016"));
	}

	@Override
	public List<IdentifiableDTO> getLeagues() {
		return Arrays.asList(new IdentifiableDTO("b1", "1.Bundesliga"), new IdentifiableDTO("b2", "2.Bundesliga"), new IdentifiableDTO("b3", "3.Bundesliga"));
	}

	@Override
	public List<Integer> getMatchDays() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResultDTO> getResult(RepositoryFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	String getAllMatches(RepositoryFilter filter){
		String url = String.format("https://www.openligadb.de/api/getmatchdata/%s/%s" +  filter.leagueId + filter.seasonId);
		return restClient.get("/api/getmatchdata/bl1/2016/8");
	}
}
