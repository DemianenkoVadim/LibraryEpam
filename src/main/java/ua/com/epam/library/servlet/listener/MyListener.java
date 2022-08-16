package ua.com.epam.library.servlet.listener;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

@WebListener
public class MyListener implements ServletRequestAttributeListener {

    Logger log = (Logger) LogManager.getLogger(MyListener.class.getName());

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        log.info("Attribute [{} = {}] added to request", srae.getName(), srae.getValue());
    }
}
