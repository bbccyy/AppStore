package com.appstore.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appstore.entity.App;
import com.appstore.webapi.WebApi;

@Controller
public class AppController {
	
	@Resource(name = "webapi")  //use @Resource let spring knows this instance should be injected later, then no need to create get/set method
	private WebApi api;
	
	// retrieve top 10 Apps
	@RequestMapping(value ="/app/", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<App>> getTopApps(){
		int topN = 10;
		List<App> apps = this.api.getAppService().readTopApps(topN);
		if(apps == null){
			System.out.println("No apps found in DB!");
			return new ResponseEntity<List<App>>(HttpStatus.NOT_FOUND);
		}
		System.out.println("==> Retrieve top "+topN+" Apps <==");
		return new ResponseEntity<List<App>>(apps, new HttpHeaders(), HttpStatus.OK);
	}
	
	// get a list of recommended Apps
	@RequestMapping(value = "/app/getRecom/similarapp/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<App>> getRecomApps(@RequestBody List<String> appIds){ //jackson parse json into java list! Request post msg that stored in http body!
		List<App> apps = this.api.getAppService().readRecomApps(appIds);
		if(apps == null || apps.isEmpty()){
			System.out.println("AppController 65: no recommandation apps found!");
			return new ResponseEntity<List<App>>(HttpStatus.NOT_FOUND);
		}
		System.out.println("==> Retrieve "+apps.size()+" Recom Apps <==");
		return new ResponseEntity<List<App>>(apps, new HttpHeaders(), HttpStatus.OK);
		// ResponseEntity object will first be passed to Jackson to convert into json file, then transfer to client side!
	}
	
}
