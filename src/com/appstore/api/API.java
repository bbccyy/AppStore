package com.appstore.api;

import com.appstore.api.dao.AppDAO;

public class API {
	
	private AppDAO appDao;

	public AppDAO getAppDao() {
		return appDao;
	}

	public void setAppDao(AppDAO appDao) {
		this.appDao = appDao;
	}
	
}
