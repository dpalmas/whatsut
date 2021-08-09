package whatsut.server;

import java.rmi.*;
import java.rmi.server.*;

public class StartServer 
{
    public static void main(String[] args) 
    {
        try 
        {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            ChatServerInt csi = new ChatServer();
            Naming.rebind("rmi://seu_ip_aqui/admin", csi);
            System.out.println("[Sistema] Servidor de Chat esta pronto.");
        } catch (Exception e) {
            System.out.println("Falha no Servidor de Chat: " + e);
        }
    }
}
