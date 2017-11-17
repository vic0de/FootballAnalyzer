package com.legodo.football;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class LeagueService {
		
	
	@HystrixCommand(fallbackMethod = "defaulAllLeagues")
	public List<IdentifiableDTO> allLeagues(){
		return new OpenLigaDBRepository().getLeagues();
	}
	
	public List<IdentifiableDTO> defaulAllLeagues(){
		return new OpenLigaDBRepository().getLeagues();
	}

}
