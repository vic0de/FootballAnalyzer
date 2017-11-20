package com.legodo.football;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.legodo.football.util.LoggingFactory;




@Component
public class OpenLigaDBClientFallback implements OpenLigaDBClient {

	private final Logger LOG = LoggingFactory.make();
	
	@Autowired
	OpenLigaDBRepository repo;
	

	@Override
	public String getAllMatches(String season, String league) {
		LOG.info("invoke fallback. league=" + league + ", season=" + season);
		String json = repo.getChachedJson(new RepositoryFilter(season, league).getKey());
		if(json == null) {
			return "";
		}
		return json;
	} 
}