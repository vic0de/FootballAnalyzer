package com.legodo.football;


import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.legodo.football.util.LoggingFactory;




@Component
public class OpenLigaDBClientFallback implements OpenLigaDBClient {

	private final Logger LOG = LoggingFactory.make();

	@Override
	public String getAllMatches(String league, String season) {
		LOG.info("invoke fallback. league=" + league + ", season=" + season);
		return "{fallback}";
	} 
}