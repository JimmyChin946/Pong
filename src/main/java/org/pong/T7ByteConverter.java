package org.pong;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Converts things to and from bytes in a universal way so that we can send
 * information through mqtt
 *
 * @author Jude Shin
 *
 */
class T7ByteConverter {
	private T7ByteConverter(){}
	
	public static byte[] toBytes(Object obj) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
			out.writeObject(obj);
		}
		return bos.toByteArray();
	}

	public static <T> T fromBytes(byte[] data, Class<T> cls) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		try (ObjectInputStream in = new ObjectInputStream(bis)) {
			return cls.cast(in.readObject());
		}
	}

	
	// public byte[] encodeChat(){}
	// public T7Chat decodeChat(){}

	// public byte[] encodeBall(){}
	// public T7Ball decodeBall(){}

	// public byte[] encodePlayer(){}
	// public T7Player decodePlayer(){}

	public byte[] encodeInt(int i){ return new byte[]{(byte)i}; }
	public int decodeInt(byte[] ba){ return (ba[0]) & 0xFF; }

	public byte[] encodeString(String s){ return s.getBytes(StandardCharsets.UTF_8); }
	public String decodeString(byte[] ba){ return new String(ba, StandardCharsets.UTF_8); }
}
