package core.listener;

import static core.util.HibernateUtil.getSessionFactory;
import static core.util.HibernateUtil.shutdown;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//import static core.util.HibernateUtil.*;

@WebListener
public class HibernateListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		getSessionFactory();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		shutdown();	
	}

}
