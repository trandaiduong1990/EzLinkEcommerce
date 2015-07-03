package com.wirecard.ezecom.controller;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class Util {

	public Util() {
		// TODO Auto-generated constructor stub
	}

	public static String encrypt3DES(String clearKey, String clearData)
			throws Exception {

		byte[] keyData = clearKey.getBytes("UTF8");

		SecretKey key = new SecretKeySpec(keyData, "DESede");
		Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
		IvParameterSpec ivSpec = new IvParameterSpec(new byte[8]);
		cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

		byte[] stringBytes = clearData.getBytes("UTF8");
		byte[] cipherText = cipher.doFinal(stringBytes);

		BASE64Encoder encoder = new BASE64Encoder();
		String base64 = encoder.encode(cipherText);// string base 64

		String strHex = hexString(base64.getBytes());// string to hex

		return strHex;
	}

	public static String hexString(byte[] b) {
		StringBuffer d = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			char hi = Character.forDigit((b[i] >> 4) & 0x0F, 16);
			char lo = Character.forDigit(b[i] & 0x0F, 16);
			d.append(Character.toUpperCase(hi));
			d.append(Character.toUpperCase(lo));
		}
		return d.toString();
	}
}
