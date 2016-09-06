package com.appstore.webapi.service;

import java.util.List;

import com.appstore.entity.App;

public interface AppService {
	public App readApp(String appId);
	public List<App> readTopApps(int topN);
	public List<App> readRecomApps(List<String> appIds);
}
