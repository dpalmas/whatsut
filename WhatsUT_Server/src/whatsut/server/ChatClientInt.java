package whatsut.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author davi on 5/25/21
 * @project WhatsUT_Server
 */

public interface ChatClientInt extends Remote {
    public void tell(String name) throws RemoteException;

    public String getName() throws RemoteException;
}
