package whatsut.client;

import java.rmi.*;

/**
 * @author davi on 5/25/21
 * @project WhatsUT_Client
 */

public interface ChatClientInt extends Remote 
{
    public void tell(String name) throws RemoteException;

    public String getName() throws RemoteException;
}
