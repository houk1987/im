package lister;

import com.comunication.connection.ConnectionListerImpl;
import com.comunication.connection.ConnectionListerImplHandle;

/**
 * Created by lenovo on 2014/11/11.
 */
public class ConnectionHandle implements ConnectionListerImplHandle {

    public ConnectionHandle() {
        ConnectionListerImpl.setConnectionListerImplHandle(this);
    }

    @Override
    public void repeatConnectionHandle() {
        System.exit(0);
    }
}
