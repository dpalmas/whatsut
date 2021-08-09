package whatsut.client;

import java.rmi.*;
import java.util.*;

/**
 * @author davi on 5/25/21
 * @project WhatsUT_Client
 */

public interface ChatServerInt extends Remote 
{
    public boolean login(ChatClientInt a) throws RemoteException;

    public void publish(String s) throws RemoteException;

    public Vector getConnected() throws RemoteException;
}
