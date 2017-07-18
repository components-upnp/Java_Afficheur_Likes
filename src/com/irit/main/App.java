package com.irit.main;

import com.irit.upnp.AfficheurServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new Thread(new AfficheurServer()).run();
    }
}
