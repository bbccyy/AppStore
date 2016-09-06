package com.appstore.api.dao;

import java.util.List;

import com.appstore.entity.App;

public interface AppDAO {
	public App readApp(String appId);
	public List<App> readTopApps(int topN);
	public List<App> readRecomApps(List<String> appIds);
}
