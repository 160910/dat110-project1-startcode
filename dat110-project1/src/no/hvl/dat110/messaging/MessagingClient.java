package no.hvl.dat110.messaging;

import java.io.IOException;

import java.net.Socket;

import no.hvl.dat110.TODO;

public class MessagingClient {

	private String server;
	private int port;

	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}

	// connect to messaging server
	public Connection connect() {

		Connection connection = null;

		try {
			Socket clientSocket = new Socket(MessageConfig.MESSAGINGHOST, MessageConfig.MESSAGINGPORT);
			connection = new Connection(clientSocket);

		} catch (IOException e) {
			System.out.println("TCP client: " + e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

		return connection;
	}
}
