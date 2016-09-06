package com.appstore.api.dao.impl;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.appstore.api.dao.AppDAO;
import com.appstore.entity.App;

public class AppDAOImpl implements AppDAO{
	
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	public App readApp(String appId) {
		App app = (App) this.getSession().get(App.class, appId);
		return app;
	}

	@Override
	public List<App> readTopApps(int topN) {
		//HQL -> Hibernate Query Language, not SQL
		Query query = this.getSession().createQuery("from App app order by app.score desc").setMaxResults(topN);
		List<App> apps = (List<App>)query.list();
		return apps;
	}

	@Override
	public List<App> readRecomApps(List<String> appIds) {
		List<App> apps = new ArrayList<>();
		if(appIds==null || appIds.isEmpty()) return apps;
		for(String appId : appIds){
			App app = this.readApp(appId);
			if(app != null)
				apps.add(app);
			else
				System.out.println("appImpl 104 returned app with id: " + appId + " is Null!");
		}
		return apps;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
