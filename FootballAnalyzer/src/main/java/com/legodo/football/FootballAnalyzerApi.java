package com.legodo.football;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.legodo.football.analyse.Analyzer;
import com.legodo.football.openligadb.json.Match;
import com.legodo.football.util.LoggingFactory;


@RestController
@RequestMapping(value = "/api")
public class FootballAnalyzerApi {
	
	private static final Logger LOG = LoggingFactory.make();
	

	
	@Autowired
	OpenLigaDBRepository repo;

	
	
	@RequestMapping(value = "/league/list", method = RequestMethod.GET)
	@ResponseBody
	public String leagues() throws IOException {
		LOG.info("GET all leagues.");
		return new Gson().toJson(repo.getLeagues());	
	}
	
	@RequestMapping(value = "/season/list", method = RequestMethod.GET)
	@ResponseBody
	public String seasons(@RequestParam(value = "leagueid", required = true, defaultValue = "") String leagueid) throws IOException {
		LOG.info("GET all seasons.");
		return new Gson().toJson(repo.getSeasons());		
	}
	
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	@ResponseBody
	public String result(@RequestParam(value = "leagueid", required = true, defaultValue = "") String leagueid,
						 @RequestParam(value = "seasonid", required = true, defaultValue = "") String seasonid,
						 @RequestParam(value = "min", 	 required = true, defaultValue = "") String min) throws IOException {
		LOG.info("GET result. leagueid=" + leagueid + ", seasonid=" + seasonid + ", min=" + min);

		RepositoryFilter filter = new RepositoryFilter();
		filter.leagueId = leagueid;
		filter.seasonId = seasonid;
		String json = repo.getAllMatches(filter);
		LOG.info(json);
		Gson gson = new Gson();
		List<Match> matches = gson.fromJson(json, new TypeToken<List<Match>>(){}.getType());
		Analyzer analyzer = new Analyzer();
		return gson.toJson(getResults(matches,min));		
	}
	
	
	private List<ResultDTO> getResults(List<Match> matches, String min){
		Analyzer analyzer = new Analyzer();
		return analyzer.analyze(Integer.valueOf(min), matches);
		
		
//		List<ResultDTO> results = new ArrayList<>();
//		for(int i=1; i < 19; i++ ) {
//			ResultDTO r = new ResultDTO("idr-" + i , "Team " + i + " " + min);
//			r.setRank(i);
//			r.setPointsCount(10 + i);
//			r.setWinsCount(i);
//			r.setLosesCount(i);
//			r.setTiedsCount(2);
//			r.setGoalsCount(3);
//			r.setDiffCount(2);
//			r.setLogoUri("https://www.openligadb.de/images/teamicons/1_FC_Koeln.gif");
//			results.add(r);
//		}
//		
//		return results;

	}
	
	
	
	
	
	

}
