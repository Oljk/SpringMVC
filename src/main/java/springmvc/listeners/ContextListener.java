package springmvc.listeners;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener  extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent e) {
        super.contextInitialized(e);
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        super.contextDestroyed(arg0);
    }
}

