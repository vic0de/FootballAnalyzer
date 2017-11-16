package com.legodo.football;

import java.util.List;

import org.slf4j.Logger;

import com.legodo.football.util.LoggingFactory;
import com.legodo.football.util.RestClient;


class OpenLigaDBRepository implements Repository{
	

	private static final Logger LOG = LoggingFactory.make();

	private  RestClient restClient;
	  

	public OpenLigaDBRepository() {
		super();
		this.restClient = new RestClient("https://www.openligadb.de");
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
