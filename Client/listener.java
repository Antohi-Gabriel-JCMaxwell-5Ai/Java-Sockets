/*
 * Listener.java e' una classe "runnable" che serve ad agire quando il Client riceve testo dal Server.
 * In questo caso il client riceve il testo e lo scrive su terminale, 
 * nel caso in cui riceva "Bye." chiude il socket e termina il client
 */
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Prof. Matteo Palitto
 */
class listener implements Runnable {
  private Socket client;

    //Constructor
    listener(Socket client) {
        this.client = client;
        System.out.println("In ascolto con: " + client);
    }

    public void run(){
        
        // connetti al socket per poter leggere i dati che arrivano al Client
        // (client <-- server)
        BufferedReader in = null;
        try{
          in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
          System.out.println("IO Error!!!");
          System.exit(-1);
        }

        //scrivi su terminale il testo ricevuto
        String testoDaServer = "";
        try {
            while ((testoDaServer = in.readLine()) != null) {
                System.out.println(testoDaServer);
                //nel caso il testo ricevuto dal Server contiene "Bye." termina il Client
                if (testoDaServer.contains("Bye.")) {
                    client.close();
                    System.exit(0);
                    break;
                }
            }
        } catch (IOException e) { 
            try {
                System.out.println("Connessione terminata dal Server");
                client.close();
                System.exit(-1);
            } catch (IOException ex) {
                System.out.println("Error nella chiusura del Socket");
                System.exit(-1);
            }
        }
    }
}

