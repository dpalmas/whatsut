package whatsut.server;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * @author davi on 5/25/21
 * @project WhatsUT_Server
 */

public class ChatServer extends UnicastRemoteObject implements ChatServerInt {

    private Vector v = new Vector();

    public ChatServer() throws RemoteException {
    }

    public boolean login(ChatClientInt cci) throws RemoteException {
        System.out.println(cci.getName() + "  se conectou...");
        cci.tell("Você se conectou com sucesso.");
        publish(cci.getName() + " acabou de conectar.");
        v.add(cci);
        return true;
    }

    public void publish(String s) throws RemoteException {
        System.out.println(s);
        for (int i = 0; i < v.size(); i++) {
            try {
                ChatClientInt tmp = (ChatClientInt) v.get(i);
                tmp.tell(s);
            } catch (Exception e) {
            }
        }
    }

    public Vector getConnected() throws RemoteException {
        return v;
    }
}