package com.legodo.football;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.legodo.football.util.LoggingFactory;

@RestController
@RequestMapping(value = "/api")
public class FootballAnalyzerApi {
	
	private static final Logger LOG = LoggingFactory.make();

	
	
	@RequestMapping(value = "/league/list", method = RequestMethod.GET)
	@ResponseBody
	public String leagues() throws IOException {
		LOG.info("GET all leagues.");
		return new Gson().toJson(getLeagues());		
	}
	
	@RequestMapping(value = "/seasons/list", method = RequestMethod.GET)
	@ResponseBody
	public String seasons() throws IOException {
		LOG.info("GET all seasons.");
		return new Gson().toJson(getSeasons());		
	}
	
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	@ResponseBody
	public String result(@RequestParam(value = "league", required = true, defaultValue = "") String league,
						 @RequestParam(value = "season", required = true, defaultValue = "") String season,
						 @RequestParam(value = "min", 	 required = true, defaultValue = "") String min) throws IOException {
		LOG.info("GET result.");
		return new Gson().toJson(getResults());		
	}
	
	
	private List<IdentifiableDTO> getLeagues(){
		return Arrays.asList(new IdentifiableDTO("idl-1", "1. Liga"), new IdentifiableDTO("idl-2", "2. Liga"), new IdentifiableDTO("idl-3", "3. Liga"));	
	}
	
	private List<IdentifiableDTO> getSeasons(){
		return Arrays.asList(	new IdentifiableDTO("ids-1", "2013/2014"), 
								new IdentifiableDTO("ids-2", "2014/2015"), 
								new IdentifiableDTO("ids-3", "2015/2016"),
								new IdentifiableDTO("ids-4", "2016/2017"));	
	}
	
	
	private List<ResultDTO> getResults(){
		
		List<ResultDTO> results = new ArrayList<>();
		for(int i=1; i < 19; i++ ) {
			ResultDTO r = new ResultDTO("idr-" + i , "Team " + i);
			r.setRank(i);
			r.setGamesCount(i);
			r.setPointsCount(10 + i);
			r.setWinsCount(i);
			r.setLosesCount(i);
			r.setTiedsCount(2);
			r.setGoalsCount(3);
			r.setDiffCount(2);
			r.setLogoUri("https://www.openligadb.de/images/teamicons/1_FC_Koeln.gif");
			results.add(r);
		}
		
		return results;

	}
	
	
	
	
	
	

}
