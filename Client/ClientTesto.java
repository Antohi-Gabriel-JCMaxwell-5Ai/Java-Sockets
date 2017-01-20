/*
 * ClientTesto.java e' il programma per connettersi ad un Server usando i socket
 * ed inviare il testo ricevuto dalla linea di comando.
 * Utilizza una "Thread" per l'ascolto dei messaggi provenienti dal Server.
 */

/**
 *
 * @author Prof. Matteo Palitto :)
 */
import java.net.*;
import java.io.*;

public class ClientTesto {
    //VECTOR PER LE CHAT
    	public static Vector<String> NomeChat = new Vector<String>();
    
    public static void main(String[] args) {
	// verifica correttezza dei parametri
	if (args.length != 2) {
            System.out.println("Uso: java client-Testo <indirizzo IP Server> <Porta Server>");
            return;
        }
	    
	    String nickname = null;
        try{
            System.out.println("Inserisci il nickname");
            nickname = (new BufferedReader(new InputStreamReader(System.in))).readLine();
        } catch(IOException e) 
            { System.out.println("I/O Error");
                                 System.exit(-1); }
	  
	  //CODICE PER AGGIUNGERE UN UTENTE AD UNA CHAT
	  try{
	  System.out.println("Usa il commando <addChat> per entrare nella chat";
	  NomeChat=(new BufferedReader(new InputStreamReader(System.in))).readLine();
	  }
	  catch (IOException e){
	  System.out.println("Errore connessione sulla chat");
	  System.exit(-1);}
	  
	  
	  	 //CODICE CHE LEGGE IL COMMANDO addChat
	  
	   String line = "";
            int clientPort = NomeChat.getPort(); //il "nome" del mittente (client)
            nickname = in.readLine();
            ServerTestoMultiThreaded.nicks.add(nickname).NomeChat;
            while(line != null){
                try{
                    line = in.readLine();
                    if(line.equals("addChat")){
                        for(int i = 0; i < ServerTestoMultiThreaded.NomeChat.size(); i++){
                            System.out.println((String) ServerTestoMultiThreaded.NomeChat.get(i));
                           
                        }
                    }
                    else{   
                        //Manda lo stesso messaggio appena ricevuto con in aggiunta il "nome" del client
                        out.println(nickname + ">> " + line);
                        //scrivi messaggio ricevuto su terminale
                        System.out.println(nickname + ">> " + line);
                    }
                } catch (IOException e) {
                    System.out.println("lettura da socket fallito");
                    System.exit(-1);
                }
	  
	  //Finisce codice addChat
	String hostName = args[0];
	int portNumber = Integer.parseInt(args[1]);
	try {
            // prendi l'indirizzo IP del server dalla linea di comando
            InetAddress address = InetAddress.getByName(hostName);
			
            // creazione socket 
            Socket clientSocket = new Socket(address, portNumber);
		
            // visualizza istruzioni
            System.out.println("Client-Testo: usa Ctrl-C per terminare, ENTER per spedire la linea di testo.\n");
			
            // connessione concorrente al socket per ricevere i dati da Server
            listener l;
            try {
                l = new listener(clientSocket);
                Thread t = new Thread(l);
                t.start();
            } catch (Exception e) { System.out.println("Connessione NON riuscita con server: "); }
		
            // connessione al socket (in uscita client --> server)
            PrintWriter out =  new PrintWriter(clientSocket.getOutputStream(), true);
			
            // connessione allo StdIn per inserire il testo dalla linea di comando
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput;
            
            //leggi da linea di comando il testo da spedire al Server
            System.out.print(">"); //visualizza il prompt
            while ((userInput = stdIn.readLine()) != null) {
            	// scrittura del messaggio da spedire nel socket 
		out.println(userInput);
                System.out.println("Messaggio spedito al server: " + userInput);
                System.out.print(">"); //visualizza il prompt
            }
            // chiusura socket
            clientSocket.close();
            System.out.println("connessione terminata!");
	}
        catch (IOException e) { System.out.println("Connessione terminata dal server: "); e.printStackTrace(); }
    }
    
}
