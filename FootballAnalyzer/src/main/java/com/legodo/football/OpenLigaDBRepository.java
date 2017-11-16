package com.legodo.football;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IdentifiableDTO> getLeagues() {
		// TODO Auto-generated method stub
		return null;
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
	
	String getAllMatches(){
		return restClient.get("/api/getmatchdata/bl1/2016/8");
	}
}
