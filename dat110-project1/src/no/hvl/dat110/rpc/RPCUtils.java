package no.hvl.dat110.rpc;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded = new byte[str.length()+1];

		encoded[0] = rpcid;
		byte[] sb = str.getBytes();

		for (int i = 0; i < str.length(); i++) {
			encoded[i+1] = sb[i];
		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		return new String(data, 1, data.length-1, StandardCharsets.UTF_8);
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = new byte[1];

		encoded[0] = rpcid;

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO: unmarshall void type
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		// Integer in java = 4 bytes, + 1 byte for rpcid
		byte[] encoded = new byte[5];

		encoded[0] = rpcid;

		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.putInt(x);
		byte[] integer = bb.array();

		for (int i = 0; i < integer.length; i++) {
			encoded[i+1] = integer[i];
		}

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		// converts byte array to int
		ByteBuffer bb = ByteBuffer.wrap(data, 1, data.length-1);
		return bb.getInt();

	}
}
