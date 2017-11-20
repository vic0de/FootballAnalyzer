package com.legodo.football.analyse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bouncycastle.asn1.x509.qualified.SemanticsInformation;

import com.legodo.football.ResultDTO;
import com.legodo.football.openligadb.json.Goal;
import com.legodo.football.openligadb.json.Match;
import com.legodo.football.openligadb.json.Team1;
import com.legodo.football.openligadb.json.Team2;

public class Analyzer {
	
	public List<ResultDTO> analyze(int minute, List<Match> matches){
		final Season season = new Season();
		matches.stream().filter(match -> match.getGoals().size() > 0).map(match -> converter2MatchGoalList(season, match)).forEach(matchgoals -> season.matchGoals.addAll(matchgoals));
		
		// matchgoals are sorted by minute
		season.matchGoals.sort((matchgoal1, matchgoal2)-> matchgoal1.minute - matchgoal2.minute);
		Map<Integer, List<MatchGoal>> matchGoalsByMinute = season.matchGoals.stream().collect(Collectors.groupingBy(matchGoal -> matchGoal.minute, Collectors.toList()));
		IntStream.range(1, 90).forEach(i -> completeMatchGoalByMinute(i, matchGoalsByMinute, season));
		return calculateTable(minute, matchGoalsByMinute, season); 
		
	}
	
	void completeMatchGoalByMinute(final int minute, final Map<Integer, List<MatchGoal>> matchGoalsByMinute, Season season){
		List<MatchGoal> matchGoals = matchGoalsByMinute.get(minute);
		final List<MatchGoal> list = new ArrayList<>(306);
		if(matchGoals == null) {
			if(minute==1) {
				list.addAll(initialAllMatchesInSeason(minute, season));
			}else {
				season.matchday.entrySet().forEach(entry -> {
					int matchday = entry.getKey();
					list.addAll(entry.getValue().stream()
							.map(party -> new MatchGoal(minute, matchday, party,
									getMatchGoalOfLastMinute(minute, matchGoalsByMinute, party)))
							.collect(Collectors.toList()));
				});
			}
		}else {
			if(minute==1) {
				list.addAll(initialAllMatchesInSeason(minute, season));
			}else {
				season.matchday.entrySet().forEach(entry -> {
					int matchday = entry.getKey();
					list.addAll(entry.getValue().stream()
							.map(party -> new MatchGoal(minute, matchday, party,
									getMatchGoalOfLastMinute(minute, matchGoalsByMinute, party)))
							.collect(Collectors.toList()));
				});
				
			}
			list.replaceAll(item -> matchGoals.stream().filter(matchgoal -> matchgoal.equals(item)).findFirst().orElse(item));
		}
		matchGoalsByMinute.put(minute, list);
	}

	private List<MatchGoal> initialAllMatchesInSeason(int minute, Season season) {
		List<MatchGoal> list = new ArrayList<>(306);
		season.matchday.entrySet().forEach(entry -> {
			int matchday = entry.getKey();
			list.addAll(entry.getValue().stream().map(party -> new MatchGoal(minute, matchday, party, 0, 0)).collect(Collectors.toList()));
		});
		return list;
	}
	
	private MatchGoal getMatchGoalOfLastMinute(final int minute, final Map<Integer, List<MatchGoal>> matchGoalsByMinute, final Party party) {
		return matchGoalsByMinute.get(minute-1).stream()
				.filter(matchGoal -> (matchGoal.team1score.teamId.equals(party.getTeam1Id())
						&& matchGoal.team2score.teamId.equals(party.getTeam2Id())))
				.findFirst().orElse(new MatchGoal(minute, 0, "", "", 0, 0));
	}
	
	List<ResultDTO> calculateTable(final int minute, final Map<Integer, List<MatchGoal>> matchGoalsByMinute, Season season) {
		List<Score> scores = new ArrayList<Score>();
		matchGoalsByMinute.get(minute).stream().forEach(matchGoal -> {
			scores.add(matchGoal.team1score);
			scores.add(matchGoal.team2score);
		});
		Map<String, List<Score>> scoresByTeam = scores.stream().collect(Collectors.groupingBy(score -> score.teamId, Collectors.toList()));
		List<Score> result = new ArrayList<>();
		scoresByTeam.entrySet().stream().forEach(entry -> {
			Score score = entry.getValue().stream().reduce(Score::combine).get();
			score.teamId = entry.getKey();
			result.add(score);
		});
		
		result.sort((score1, score2) -> score2.point - score1.point);
		
		final AtomicInteger i = new AtomicInteger(1);
		return result.stream().map(score -> convert2ResultDTO(i.getAndIncrement(), score, season)).collect(Collectors.toList());
	}
	
	private ResultDTO convert2ResultDTO(int rank, Score score, Season season){
		Team team = season.teams.get(score.teamId);
		return new ResultDTO(team.id, team.name, rank, 34, score.point, score.win, score.loss, score.draw, score.goal, score.getDiff(), team.iconUri);
	}
	
	List<MatchGoal> converter2MatchGoalList(Season season, Match match) {
		Team1 team1 = match.getTeam1();
		String team1Id = team1.getTeamId();
		Team2 team2 = match.getTeam2();
		String team2Id = team2.getTeamId();
		int matchday = match.getGroup().getGroupOrderID();
		season.teams.putIfAbsent(team1Id, new Team(team1.getTeamId(), team1.getTeamName(), team1.getTeamIconUrl()));
		season.teams.putIfAbsent(team2Id, new Team(team2.getTeamId(), team2.getTeamName(), team2.getTeamIconUrl()));
		Party party = new Party(team1Id, team2Id);
		season.matchday.putIfAbsent(matchday, new HashSet<Party>(Arrays.asList(party)));
		season.matchday.get(matchday).add(party);
		
		return match.getGoals().stream().filter(goal -> goal != null).map(goal -> convert(goal,matchday,team1Id, team2Id)).collect(Collectors.toList());
	}
	
	MatchGoal convert(Goal goal, int matchday, String team1Id, String team2Id) {
//		System.out.println("team1Id: " + team1Id + " team2Id: " + team2Id + " matchdazy" + matchday);
		int minute;
		if(goal.getMatchMinute() == null) {
			minute = 1;
		}else {
			minute = goal.getMatchMinute();
			if(minute > 90) {
				minute = 90;
			}
		}
		return new MatchGoal(minute, matchday, team1Id, team2Id, goal.getScoreTeam1(), goal.getScoreTeam2());
	}
	
	
	
}
