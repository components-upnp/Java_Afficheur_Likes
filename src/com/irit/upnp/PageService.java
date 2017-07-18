package com.irit.upnp;

import com.irit.xml.LecteurXmlPage;
import org.fourthline.cling.binding.annotations.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

/**
 * Created by mkostiuk on 17/07/2017.
 */
@UpnpService(
        serviceId = @UpnpServiceId("PageService"),
        serviceType = @UpnpServiceType(value = "PageService", version = 1)
)
public class PageService {

    private final PropertyChangeSupport propertyChangeSupport;

    public PageService() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    @UpnpStateVariable(name = "Page")
    private String page = "";

    @UpnpAction(name = "SetPage")
    public void setPage(@UpnpInputArgument(name = "Page") String p) throws IOException, SAXException, ParserConfigurationException {
        String oldValue = page;
        page = p;

        LecteurXmlPage lec = new LecteurXmlPage(page);

        getPropertyChangeSupport().firePropertyChange("page", oldValue, lec.getNumPage());
    }
}
