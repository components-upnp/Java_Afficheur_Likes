package com.irit.upnp;

import com.irit.display.Fenetre;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.UpnpServiceImpl;
import org.fourthline.cling.binding.annotations.AnnotationLocalServiceBinder;
import org.fourthline.cling.model.DefaultServiceManager;
import org.fourthline.cling.model.ValidationException;
import org.fourthline.cling.model.meta.*;
import org.fourthline.cling.model.types.DeviceType;
import org.fourthline.cling.model.types.UDADeviceType;
import org.fourthline.cling.model.types.UDN;

/**
 * Created by mkostiuk on 17/07/2017.
 */
public class AfficheurServer implements Runnable {

    private LocalService<PageService> pageService;
    private LocalService<ReceiveLikeService> receiveLikeService;

    @Override
    public void run() {
        final UpnpService upnpService = new UpnpServiceImpl();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                upnpService.shutdown();
            }
        });

        // Add the bound local device to the registry
        try {
            upnpService.getRegistry().addDevice(
                    createDevice()
            );
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    private LocalDevice createDevice() throws ValidationException {
        DeviceIdentity identity =
                new DeviceIdentity(
                        UDN.uniqueSystemIdentifier("AfficheurLikes")
                );

        DeviceType type =
                new UDADeviceType("Diapo", 1);

        DeviceDetails details =
                new DeviceDetails(
                        "AfficheurLiker",					// Friendly Name
                        new ManufacturerDetails(
                                "UPS-IRIT",								// Manufacturer
                                ""),								// Manufacturer URL
                        new ModelDetails(
                                "AfficheurLike",						// Model Name
                                "Composant qui affiche les likes de la page courante",	// Model Description
                                "v1" 								// Model Number
                        )
                );

        pageService =
                new AnnotationLocalServiceBinder().read(PageService.class);
        pageService.setManager(
                new DefaultServiceManager<>(pageService, PageService.class)
        );
        receiveLikeService =
                new AnnotationLocalServiceBinder().read(ReceiveLikeService.class);
        receiveLikeService.setManager(
                new DefaultServiceManager<>(receiveLikeService,ReceiveLikeService.class)
        );

        new Fenetre(pageService,receiveLikeService).setVisible(true);

        return new LocalDevice(
                identity, type, details,
                new LocalService[] {pageService,receiveLikeService}
        );
    }
}
