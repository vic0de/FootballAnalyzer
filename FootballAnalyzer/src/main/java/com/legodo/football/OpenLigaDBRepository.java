package com.legodo.football;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.legodo.football.util.LoggingFactory;


@Component
class OpenLigaDBRepository implements Repository{
	

	private static final Logger LOG = LoggingFactory.make();
	
	private static final  Map<String, String> JSON_DATA_CACHE = new HashMap<>();

	@Autowired
	private OpenLigaDBClient client;
	  

	public OpenLigaDBRepository() {
		super();
	}

	@Override
	public List<IdentifiableDTO> getSeasons() {
		return Arrays.asList(new IdentifiableDTO("2017", "2017/2018"), new IdentifiableDTO("2016", "2016/2017"), new IdentifiableDTO("2015", "2015/2016"));
	}

	@Override
	public List<IdentifiableDTO> getLeagues() {
		return Arrays.asList(new IdentifiableDTO("bl1", "1.Bundesliga"), new IdentifiableDTO("bl2", "2.Bundesliga"), new IdentifiableDTO("bl3", "3.Bundesliga"));
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
//		String url = String.format("https://www.openligadb.de/api/getmatchdata/%s/%s" +  filter.leagueId + filter.seasonId);
		if(filter != null) {
			String json =  client.getAllMatches(filter.seasonId, filter.leagueId);
			LOG.info(json);
			if(!JSON_DATA_CACHE.containsKey(filter.getKey())) {
				JSON_DATA_CACHE.put(filter.getKey(), json);
			}
			return json;
		}
		return "";
	}
	
	String getChachedJson(String key){
		return JSON_DATA_CACHE.get(key);
	}
}
