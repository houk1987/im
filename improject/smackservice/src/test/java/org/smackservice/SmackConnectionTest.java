package org.smackservice;


import junit.framework.Assert;
import org.jivesoftware.smack.XMPPException;
import org.junit.BeforeClass;
import org.junit.Test;

public class SmackConnectionTest {

    private static SmackConnection connection;

    @BeforeClass
    public static void beforeClass() {
        connection = SmackConnection.getInstance();
        try {
            connection.connect();
        } catch (XMPPException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isConnected() {
        Assert.assertEquals(true, connection.isConnected());
    }

    @Test
    public void getHostNameTest() {
        Assert.assertEquals("192.168.1.105", connection.getHost());
    }
}