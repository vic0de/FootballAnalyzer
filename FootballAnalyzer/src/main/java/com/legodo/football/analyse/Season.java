package com.legodo.football.analyse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Season {
	
	Map<String, Team> teams = new ConcurrentHashMap<>(18);;
	
	List<MatchGoal> matchGoals = new ArrayList<>();
	
	Map<Integer, Set<Party>> matchday = new HashMap<>();
	
	int maxMatchDay() {
		if(matchday.isEmpty()) {
			return 0;
		}else if(matchday.size() == 1) {
			return 1;
		}else {
			return matchday.keySet().stream().max((matchday1, matchday2) -> matchday1 - matchday2).get();
		}
	}

}
