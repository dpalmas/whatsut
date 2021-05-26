package whatsut.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author davi on 5/25/21
 * @project WhatsUT_Server
 */

public interface ChatServerInt extends Remote {
    public boolean login(ChatClient cc) throws RemoteException;

    public void publish(String s) throws RemoteException;

    public Vector getConnected() throws RemoteException;
}
