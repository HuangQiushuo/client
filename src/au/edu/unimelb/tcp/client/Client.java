package au.edu.unimelb.tcp.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.json.simple.parser.ParseException;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Client {

	public static void main(String[] args) throws IOException, ParseException {
		//Socket socket = null;
		SSLSocket sslsocket = null;
		String userId = null;
		String password = null;
		String identity = null;
		boolean debug = false;
		try {
			
			//Location of the Java keystore file containing the collection of 
			//certificates trusted by this application (trust store).
			//store the certificates from the servers
			System.setProperty("javax.net.ssl.trustStore", "mykeystore/tclient.keystore");
			System.setProperty("javax.net.debug","all");
			
			//load command line args
			ComLineValues values = new ComLineValues();
			CmdLineParser parser = new CmdLineParser(values);
			try {
				parser.parseArgument(args);
				String hostname = values.getHost();
				// add two statements
				userId = values.getUserId();
				password = values.getPassword();
				identity = values.getIdeneity();
				int port = values.getPort();
				debug = values.isDebug();
				//socket = new Socket(hostname, port);
				//Create SSL socket and connect it to the remote server 
				SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
				sslsocket = (SSLSocket) sslsocketfactory.createSocket(hostname, port);
			} catch (CmdLineException e) {
				e.printStackTrace();
			}
			
			// put username and password into the client state
			State state = new State(userId, password, identity, "");
			
			// start sending thread
			MessageSendThread messageSendThread = new MessageSendThread(sslsocket, state, debug);
			Thread sendThread = new Thread(messageSendThread);
			sendThread.start();
			
			// start receiving thread
			Thread receiveThread = new Thread(new MessageReceiveThread(sslsocket, state, messageSendThread, debug));
			receiveThread.start();
			
		} catch (UnknownHostException e) {
			System.out.println("Unknown host");
		} catch (IOException e) {
			System.out.println("Communication Error: " + e.getMessage());
		}
	}
}
