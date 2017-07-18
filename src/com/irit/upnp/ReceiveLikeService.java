package com.irit.upnp;

import com.irit.xml.LecteurXml;
import org.fourthline.cling.binding.annotations.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

/**
 * Created by mkostiuk on 17/07/2017.
 */
@UpnpService(
        serviceType = @UpnpServiceType(value = "ReceiveLikeService", version = 1),
        serviceId = @UpnpServiceId("ReceiveLikeService")
)
public class ReceiveLikeService {

    private final PropertyChangeSupport propertyChangeSupport;

    public ReceiveLikeService() {
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }

    @UpnpStateVariable(name = "LikedPage")
    private String likedPage = "";

    @UpnpAction(name = "SetLikes")
    public void setPageLike(@UpnpInputArgument(name = "LikedPage") String l) throws IOException, SAXException, ParserConfigurationException {
        likedPage = l;

        LecteurXml lec = new LecteurXml(likedPage);

        getPropertyChangeSupport().firePropertyChange("likeReveived", "", lec.getNumPage());
    }
}
