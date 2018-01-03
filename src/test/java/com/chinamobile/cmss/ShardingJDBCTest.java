package com.chinamobile.cmss;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.chinamobile.cmss.domain.WWWAlert;
import com.chinamobile.cmss.service.WWWAlertService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJDBCTest {
	
	@Autowired
	private WWWAlertService wwwAlertService;
	
	//@Autowired
	private RestTemplate restTemplate = new RestTemplate();
	
	@Before
	public void testClear() {
		wwwAlertService.clear("alerts");
	}
	
	@Test
	public void insert() throws RestClientException, URISyntaxException {
		WWWAlert wwwAlert = null;
		
		for(int i = 0;i < 5000; i ++) {
			wwwAlert = new WWWAlert();
			long id = restTemplate.getForEntity(new URI("http://127.0.0.1:10086/api/id/64bit"), long.class).getBody().longValue();
			System.out.println("-------------------------------------"+ id +"------------------------------------");
			wwwAlert.setId(id);
			wwwAlert.setLocateInfo("metrics"+i);
			wwwAlertService.insert(wwwAlert);
		}
	}
}
