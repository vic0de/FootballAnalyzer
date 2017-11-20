package com.legodo.football;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "OpenLigaDb", url="https://www.openligadb.de", fallback = OpenLigaDBClientFallback.class)
public interface OpenLigaDBClient {
 
    @RequestMapping(method = RequestMethod.GET, value = "/api/getmatchdata/{league}/{season}", produces = "application/json; charset=UTF-8")
    String getAllMatches(@PathVariable("season") String season, @PathVariable("league") String league);
 }
