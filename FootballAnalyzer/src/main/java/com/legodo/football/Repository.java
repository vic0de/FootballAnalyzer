package com.legodo.football;

import java.util.List;

public interface Repository {
	

	 List<IdentifiableDTO> getSeasons();
	
	 List<IdentifiableDTO> getLeagues();
	 
	 List<Integer> getMatchDays(); 
	
	List<ResultDTO> getResult(RepositoryFilter filter);

}
