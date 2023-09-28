package servletContext;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import manager.FilesManager;

@WebListener
public class MyAppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        FilesManager manager = new FilesManager();

        sce.getServletContext().setAttribute("manager", manager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
