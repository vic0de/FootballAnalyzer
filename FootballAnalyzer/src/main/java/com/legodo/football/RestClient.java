package com.legodo.football;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestClient {

	  private final String server;
	  private final RestTemplate rest;
	  private final HttpHeaders headers;
	  private HttpStatus status;
	  private String keyStoreFile = "C:\\Users\\vi_sc.LEGODO\\git\\FootballAnalyzer\\FootballAnalyzer\\src\\main\\resources\\clientkeystore.jks";
	  private String keyStorePassword = "football";

	  public RestClient(String serverUrl) throws Exception {
		  
		this.server = serverUrl;
		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
	    keyStore.load(new FileInputStream(new File(keyStoreFile)), keyStorePassword.toCharArray());

	    SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
	            new SSLContextBuilder()
	                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
	                    .loadKeyMaterial(keyStore, keyStorePassword.toCharArray())
	                    .build(),
	            NoopHostnameVerifier.INSTANCE);

	    HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();

	    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
	    
	    rest = new RestTemplate(requestFactory);
		
		
		
	    this.headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json");
	    headers.add("Accept", "*/*");
	  }

	  public String get(String uri) {
	    HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
	    ResponseEntity<String> responseEntity = rest.exchange(server + uri, HttpMethod.GET, requestEntity, String.class);
	    this.setStatus(responseEntity.getStatusCode());
	    return responseEntity.getBody();
	  }

	  public HttpStatus getStatus() {
	    return status;
	  }

	  public void setStatus(HttpStatus status) {
	    this.status = status;
	  } 
	}