package com.appstore.webapi.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.appstore.api.API;
import com.appstore.entity.App;
import com.appstore.webapi.service.AppService;

public class AppServiceImpl implements AppService{
	
	private API api;
	
	@Override
	@Transactional
	public App readApp(String appId) {
		return this.api.getAppDao().readApp(appId);
	}

	@Override
	@Transactional
	public List<App> readTopApps(int topN) {
		return this.api.getAppDao().readTopApps(topN);
	}

	@Override
	@Transactional
	public List<App> readRecomApps(List<String> appIds) {
		return this.api.getAppDao().readRecomApps(appIds);
	}

	public API getApi() {
		return api;
	}

	public void setApi(API api) {
		this.api = api;
	}

}
