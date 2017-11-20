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

}
