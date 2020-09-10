package springmvc.listeners;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

// @WebListener
public class ContextListener  extends ContextLoaderListener {

    public ContextListener(WebApplicationContext context) {
        super(context);
    }

    @Override
    public void contextInitialized(ServletContextEvent e) {
        super.contextInitialized(e);
    }



    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        super.contextDestroyed(arg0);
    }
}

