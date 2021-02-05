package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		if (payload.length > 127)
			throw new IllegalArgumentException("Payload kan ikkje vere større enn 127 bytes.");
		this.payload = payload;
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = new byte[MessageConfig.SEGMENTSIZE];

		encoded[0] = (byte) payload.length;

		for (int i = 0; i < payload.length; i++) {
			encoded[i+1] = payload[i];
		}

		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		int receivedSize = received[0];
		payload = new byte[receivedSize];

		for (int i = 0; i < receivedSize; i++) {
			payload[i] = received[i+1];
		}
		
	}
}
