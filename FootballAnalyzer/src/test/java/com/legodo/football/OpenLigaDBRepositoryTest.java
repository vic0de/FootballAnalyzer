package com.legodo.football;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenLigaDBRepositoryTest {
	
	
	
	OpenLigaDBRepository respo = new OpenLigaDBRepository();
	
	
	
	@Test
	public void getAllMatches() {
		
		
		
		String s = respo.getAllMatches();	
		assertEquals("sdsd", s);
		
	}

}
