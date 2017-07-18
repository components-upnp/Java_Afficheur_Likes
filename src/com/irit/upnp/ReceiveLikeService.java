package com.irit.upnp;

import com.irit.xml.LecteurXmlLikes;
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

    @UpnpStateVariable(name = "Likes")
    private String likes = "";

    @UpnpAction(name = "SetLikes")
    public void setPageLike(@UpnpInputArgument(name = "Likes") String l) throws IOException, SAXException, ParserConfigurationException {
        String oldValue = likes;
        likes = l;

        LecteurXmlLikes lec = new LecteurXmlLikes(likes);

        getPropertyChangeSupport().firePropertyChange("likeReveived", oldValue, lec.getLikes());
    }
}
