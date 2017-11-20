package com.legodo.football;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.legodo.football.openligadb.json.MatchResult;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenLigaDBRepositoryTest {
	
	
	@Autowired
	OpenLigaDBRepository respo;
	
	
	
	@Test
	public void getAllMatches() {
		
		
		RepositoryFilter filter = new RepositoryFilter("","");
		filter.leagueId = "b1";
		filter.seasonId = "2016";
		String  all = respo.getAllMatches(filter);	
		all = respo.getAllMatches(filter);	
		assertEquals("sdsd", all);
		
	}

}
