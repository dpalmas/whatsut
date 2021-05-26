package whatsut.server;

import java.rmi.*;
import java.rmi.server.*;

/**
 * @author davi on 5/25/21
 * @project WhatsUT_Server
 */

public class StartServer {
    public static void main(String[] args) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            ChatServerInt csi = new ChatServer();
            Naming.rebind("rmi://192.168.1.108/admin", csi);
            System.out.println("[Sistema] Servidor de Chat esta pronto.");
        } catch (Exception e) {
            System.out.println("Falha no Servidor de Chat: " + e);
        }
    }
}