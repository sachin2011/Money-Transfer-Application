package com.transfermoneyapp;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import com.transfermoneyapp.dao.DBFactory;
import com.transfermoneyapp.service.AccountManagementService;
import com.transfermoneyapp.service.MoneyTransferService;

/**
 * @author sachin.sharma
 * Main class to launch the application on Jetty server at port 8080
 */
public class MoneyTransferApplication {

	private static Logger log = Logger.getLogger(MoneyTransferApplication.class);

	public static void main(String[] args) throws Exception {
		Server jettyServer = new Server(8080);
		try {
			DBFactory dbFactory = DBFactory.getDBFactory();
			// Creating table and data in in-memory H2 database using scripts present in
			// dummyData.sql file
			dbFactory.populateDummyData();
			ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
			servletContext.setContextPath("/");
			jettyServer.setHandler(servletContext);
			ServletHolder servletHolder = servletContext.addServlet(ServletContainer.class, "/*");
			servletHolder.setInitParameter("jersey.config.server.provider.classnames",
					AccountManagementService.class.getCanonicalName() + ","
							+ MoneyTransferService.class.getCanonicalName());
			log.debug("Staring Jetty Server");
			jettyServer.start();
			jettyServer.join();
		} finally {
			log.debug("Destroying jetty server");
			jettyServer.destroy();
		}
	}
}
