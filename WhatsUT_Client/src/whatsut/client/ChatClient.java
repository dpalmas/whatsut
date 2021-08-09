package whatsut.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient extends UnicastRemoteObject implements ChatClientInt
{

    private String name;
    private ChatUI ui;

    public ChatClient(String s) throws RemoteException
    {
        name = s;
    }

    public void tell(String st) throws RemoteException
    {
        System.out.println(st);
        ui.writeMsg(st);
    }

    public String getName() throws RemoteException
    {
        return name;
    }

    public void setGUI(ChatUI chatUI)
    {
        ui = chatUI;
    }
}
