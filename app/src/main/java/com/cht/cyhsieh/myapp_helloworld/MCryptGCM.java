/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cht.cyhsieh.myapp_helloworld;

import android.util.Base64;
import android.util.Log;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author chuanhan
 */
public class MCryptGCM
{
	public static final String TAG = MCryptGCM.class.getCanonicalName();

	public static final int AES_KEY_SIZE = 256;
	public static final int GCM_IV_LENGTH = 12;
	public static final int GCM_TAG_LENGTH = 16;

	private Cipher cipher;
	private SecretKeySpec keySpec;
	private GCMParameterSpec gcmParameterSpec;

	private String iv = "fedcba9876543210";//Dummy iv (CHANGE IT!)
		private IvParameterSpec ivspec;
		private SecretKeySpec keyspec;
//		private Cipher cipher;

		private String SecretKey = "0123456789abcdef";//Dummy secretKey (CHANGE IT!)

		public MCryptGCM() throws Exception
		{
//			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//			keyGenerator.init(AES_KEY_SIZE);
//
//			// Generate Key
//			javax.crypto.SecretKey key = keyGenerator.generateKey();
//			byte[] IV = new byte[GCM_IV_LENGTH];
//			SecureRandom random = new SecureRandom();
//			random.nextBytes(IV);

			// Get Cipher Instance
			cipher = Cipher.getInstance("AES/GCM/NoPadding");

			// Create SecretKeySpec
//			keySpec = new SecretKeySpec(key.getEncoded(), "AES");
			keySpec = new SecretKeySpec(SecretKey.getBytes(), "AES");

			// Create GCMParameterSpec
			gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv.getBytes());

			ivspec = new IvParameterSpec(iv.getBytes());
			Log.d("Test", String.format("gcmParameterSpec.getIV() = %s", bytesToHex(gcmParameterSpec.getIV())));
			Log.d("Test", String.format("ivspec.getIV() = %s", bytesToHex(ivspec.getIV())));

//
//			keyspec = new SecretKeySpec(SecretKey.getBytes(), "AES");
//
//			try {
//				cipher = Cipher.getInstance("AES/CBC/NoPadding");
////				cipher = Cipher.getInstance("AES/CCM/NoPadding");
////				cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//			} catch (NoSuchAlgorithmException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (NoSuchPaddingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}

//	public byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception
	public byte[] encrypt(byte[] plaintext) throws Exception
	{
//		// Get Cipher Instance
//		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//
//		// Create SecretKeySpec
//		SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
//
//		// Create GCMParameterSpec
//		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);

		// Initialize Cipher for ENCRYPT_MODE
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

		// Perform Encryption
		byte[] cipherText = cipher.doFinal(plaintext);

		return cipherText;
	}

//	public String decrypt(byte[] cipherText, SecretKey key, byte[] IV) throws Exception
	public String decrypt(byte[] cipherText) throws Exception
	{
//		// Get Cipher Instance
//		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//
//		// Create SecretKeySpec
//		SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
//
//		// Create GCMParameterSpec
//		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);

		// Initialize Cipher for DECRYPT_MODE
		cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

		// Perform Decryption
		byte[] decryptedText = cipher.doFinal(cipherText);

		return new String(decryptedText);
	}

	public String decrypt2(byte[] cipherText) throws Exception
	{
//		// Get Cipher Instance
//		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//
//		// Create SecretKeySpec
//		SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
//
//		// Create GCMParameterSpec
//		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);

		// Initialize Cipher for DECRYPT_MODE
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivspec);

		// Perform Decryption
		byte[] decryptedText = cipher.doFinal(cipherText);

		return new String(decryptedText);
	}

//    public byte[] encrypt3(byte[] plaintext, String key) throws Exception
//    {
//		// Get Cipher Instance
////		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
//		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//
//		// Create SecretKeySpec
//		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
//
//		// Create GCMParameterSpec
//		String iv = "fedcba9876543210";
//		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv.getBytes());
//
//        // Initialize Cipher for ENCRYPT_MODE
////        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
//		cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
//
//        // Perform Encryption
//        byte[] cipherText = cipher.doFinal(plaintext);
//
//        return cipherText;
//    }

//	public byte[] encrypt3_php(byte[] plaintext, String key) throws Exception
//	{
//		// Get Cipher Instance
////		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
//		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//
//		// Create SecretKeySpec
//		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
//
//		// Create GCMParameterSpec
//		String iv = "sssssss1";
//		byte[] ivBytes = iv.getBytes();
//		Log.d("GCM", String.format("iv base64 = %s", Base64.encodeToString(ivBytes, Base64.DEFAULT)));
//		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, ivBytes);
//
//		// Initialize Cipher for ENCRYPT_MODE
////        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
//		cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
//
//		// Perform Encryption
//		byte[] cipherText = cipher.doFinal(plaintext);
//		final byte[] encryptedBytes = new byte[cipherText.length + ivBytes.length];
//		System.arraycopy(ivBytes, 0, encryptedBytes, 0, ivBytes.length);
//		System.arraycopy(cipherText, 0, encryptedBytes, ivBytes.length, cipherText.length);
//
//		String tag = "alextag";
//		final byte[] encryptedBytes2 = new byte[cipherText.length + ivBytes.length+ tag.getBytes().length];
//		System.arraycopy(ivBytes, 0, encryptedBytes2, 0, ivBytes.length);
//		System.arraycopy(cipherText, 0, encryptedBytes2, ivBytes.length, cipherText.length);
//		System.arraycopy(tag.getBytes(), 0, encryptedBytes2, ivBytes.length+cipherText.length, tag.getBytes().length);
//		Log.d("GCM", String.format("encode GCM text01 = %s", Base64.encodeToString(cipherText, Base64.DEFAULT)));
//		Log.d("GCM", String.format("encode GCM text02 = %s", Base64.encodeToString(encryptedBytes, Base64.DEFAULT)));
//		Log.d("GCM", String.format("encode GCM text03 = %s", Base64.encodeToString(encryptedBytes2, Base64.DEFAULT)));
//		return encryptedBytes;
//	}

//	public String decrypt3_php(byte[] cipherText, String key) throws Exception
//	{
//		// Get Cipher Instance
////		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
//		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//
//		// Create SecretKeySpec
//		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
////		SecretKey keySpec = new SecretKeySpec(key.getBytes(), 0, key.getBytes().length, "AES");
//
//		// Create GCMParameterSpec
//		String iv = "sssssss1";
////		byte[] ivByte = Arrays.copyOfRange(iv.getBytes(), 0, GCM_IV_LENGTH);
////		Log.d("GCM", new String(ivByte));
//		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv.getBytes());
//
//		// Initialize Cipher for DECRYPT_MODE
////		cipher.init(Cipher.DECRYPT_MODE, keySpec);
//		cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);
//
//		// Perform Decryption
////		byte[] decryptedText = cipher.doFinal(cipherText);
////		Log.d("GCM", String.format("%s", new String(Base64.decode(cipherText, Base64.DEFAULT))));
//		byte[] decryptedText = cipher.doFinal(cipherText, iv.getBytes().length, cipherText.length - iv.getBytes().length);
//
//		return new String(decryptedText);
//	}

	static final String ALGO = "AES"; //$NON-NLS-1$
	static final String GCMALGO = "AES/GCM/NoPadding"; //$NON-NLS-1$
	static final String UNICODE_FORMAT = "UTF8"; //$NON-NLS-1$

	public static String decrypt_my(String data, String mainKey, int ivLength) throws Exception {
		Log.d(TAG, String.format("call decrypt_my"));
		final byte[] encryptedBytes = Base64.decode(data.getBytes(UNICODE_FORMAT), Base64.DEFAULT);
		Log.d(TAG, String.format("Hex = %s", bytesToHex(encryptedBytes)));
//		final byte[] initializationVector = new byte[ivLength];
//		System.arraycopy(encryptedBytes, 0, initializationVector, 0, ivLength);
//		byte [] ivBytes = Base64.decode("c3Nzc3Nzc3Nzc3Nzc3NzMQ==", Base64.DEFAULT);
		byte [] ivBytes = "sssssssssssssss1".getBytes();

//		final byte[] encryptedBytes2 = new byte[encryptedBytes.length - ivBytes.length];
//		System.arraycopy(encryptedBytes, ivBytes.length, encryptedBytes2, 0, encryptedBytes.length - ivBytes.length);
//		Log.d(TAG, String.format("encryptedBytes2 = %s", bytesToHex(encryptedBytes2)));

		SecretKeySpec secretKeySpec = new SecretKeySpec(mainKey.getBytes(UNICODE_FORMAT), ALGO);

		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, ivBytes);
		Cipher cipher = Cipher.getInstance(GCMALGO);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, gcmParameterSpec);
		return new String(cipher.doFinal(encryptedBytes, ivLength, encryptedBytes.length - ivLength),
				UNICODE_FORMAT);
//		return new String(cipher.doFinal(encryptedBytes2));
//		return new String(cipher.doFinal(encryptedBytes));
	}

	public String encrypt3_test(byte[] plaintext, String key) throws Exception
	{
		// byte[] initializationVector = generateInitializationVector(ivLength);
		int ivLength = 16;
//		byte[] initializationVector = new byte[ivLength];
//		Random random = new SecureRandom();
//		random.nextBytes(initializationVector);
		byte[] initializationVector = "sssssssssssssss1".getBytes();
		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(UNICODE_FORMAT), ALGO);
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, initializationVector);
		Cipher cipher = Cipher.getInstance(GCMALGO);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, gcmParameterSpec);
		final byte[] encryptedData = cipher.doFinal(plaintext);
		final byte[] encryptedBytes = new byte[encryptedData.length + ivLength];
		System.arraycopy(initializationVector, 0, encryptedBytes, 0, ivLength);
		System.arraycopy(encryptedData, 0, encryptedBytes, ivLength, encryptedData.length);
		System.out.println("data [String]        : " + new String(plaintext));
		System.out.println("data           length: " + plaintext.length
				+ " data: " + bytesToHex(plaintext));
		System.out.println("mainKey        length: " + key.getBytes(UNICODE_FORMAT).length
				+ " data: " + bytesToHex(key.getBytes(UNICODE_FORMAT)));
		System.out.println("initvector     length: " + initializationVector.length
				+ " data: " + bytesToHex(initializationVector));
		System.out.println("encryptedBytes length: " + encryptedBytes.length
				+ " data: " + bytesToHex(encryptedBytes));
		return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
	}

	public String encrypt3_php(byte[] plaintext, String key) throws Exception
	{
		// Get Cipher Instance
//		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
//		Cipher cipher = Cipher.getInstance(GCMALGO);
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

		// Create SecretKeySpec
//		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(UNICODE_FORMAT), ALGO);
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");

		int ivLength = 16;
//		byte[] initializationVector = "sssssssssssssss1".getBytes();
		// Create GCMParameterSpec
		String iv = "sssssss1";
		byte[] ivBytes = iv.getBytes();
		ivLength = ivBytes.length;
		Log.d("GCM", String.format("iv base64 = %s", Base64.encodeToString(ivBytes, Base64.DEFAULT)));
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, ivBytes);

		// Initialize Cipher for ENCRYPT_MODE
//        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
//		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, gcmParameterSpec);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);

		final byte[] encryptedData = cipher.doFinal(plaintext);
		final byte[] encryptedBytes = new byte[encryptedData.length + ivLength];
		System.arraycopy(ivBytes, 0, encryptedBytes, 0, ivLength);
		System.arraycopy(encryptedData, 0, encryptedBytes, ivLength, encryptedData.length);
		System.out.println("data [String]        : " + new String(plaintext));
		System.out.println("data           length: " + plaintext.length
				+ " data: " + bytesToHex(plaintext));
		System.out.println("mainKey        length: " + key.getBytes(UNICODE_FORMAT).length
				+ " data: " + bytesToHex(key.getBytes(UNICODE_FORMAT)));
		System.out.println("iv     length: " + ivBytes.length
				+ " data: " + bytesToHex(ivBytes));
		System.out.println("encryptedBytes length: " + encryptedBytes.length
				+ " data: " + bytesToHex(encryptedBytes));
		return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
	}

	public static String decrypt3_php(String data, String key) throws Exception {
		Log.d(TAG, String.format("call decrypt3_php"));

		// Get Cipher Instance
//		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
//		Cipher cipher = Cipher.getInstance(GCMALGO);
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

		// Create SecretKeySpec
//		SecretKeySpec secretKeySpec = new SecretKeySpec(mainKey.getBytes(UNICODE_FORMAT), ALGO);
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");

		// Create GCMParameterSpec
//		byte [] ivBytes = "sssssssssssssss1".getBytes();
//		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, ivBytes);
		String iv = "sssssss1";
		byte[] ivBytes = iv.getBytes();
		int ivLength = ivBytes.length;
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv.getBytes());

		// Initialize Cipher for DECRYPT_MODE
//		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, gcmParameterSpec);
		cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

		// Perform Decryption
//		final byte[] encryptedBytes = Base64.decode(data.getBytes(UNICODE_FORMAT), Base64.DEFAULT);
		final byte[] encryptedBytes = Base64.decode(data.getBytes(), Base64.DEFAULT);
		Log.d(TAG, String.format("Hex = %s", bytesToHex(encryptedBytes)));

//		byte[] decryptedText = cipher.doFinal(encryptedBytes, ivLength, encryptedBytes.length - ivLength);
		byte[] decryptedText = cipher.doFinal(encryptedBytes);

		return new String(decryptedText);
	}

//		public byte[] encrypt(String text) throws Exception
//		{
//			if(text == null || text.length() == 0)
//				throw new Exception("Empty string");
//
//			byte[] encrypted = null;
//
//			try {
//				cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
//
//
////				Log.d("MCrypt", Integer.toString(padString(text).getBytes().length));
//				encrypted = cipher.doFinal(padString(text).getBytes());
//			} catch (Exception e){
//				throw new Exception("[encrypt] " + e.getMessage());
//			}
//
//			return encrypted;
//		}
//
//		public byte[] decrypt(String code) throws Exception
//		{
//			if(code == null || code.length() == 0)
//				throw new Exception("Empty string");
//
//			byte[] decrypted = null;
//
//			try {
//				cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
//
//				decrypted = cipher.doFinal(hexToBytes(code));
//			} catch (Exception e)
//			{
//				throw new Exception("[decrypt] " + e.getMessage());
//			}
//			return decrypted;
//		}
//
//		//modify by cy at 106/08/28 for fix white box test issue(Heap Inspection)
//		public String getDCryptString() throws Exception
//		{
//			INgnConfigurationService mConfigurationService = NgnEngine.getInstance().getConfigurationService();
//			String ECrypt = mConfigurationService.getString(NgnConfigurationEntry.ACCOUNT_SETTING_PWD, "");
//			String DCrypt_Str;
//
//			if(ECrypt == null || ECrypt.length() == 0)
//				throw new Exception("Empty string");
//
//			byte[] decrypted = null;
//
//			try {
//				cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
//
//				decrypted = cipher.doFinal(hexToBytes(ECrypt));
//				DCrypt_Str = new String(decrypted);
//				DCrypt_Str = DCrypt_Str.trim();
//			} catch (Exception e)
//			{
//				throw new Exception("[decrypt] " + e.getMessage());
//			}
//
//			return DCrypt_Str;
//		}
//		//end by cy at 106/08/28 for fix white box test issue(Heap Inspection)

		public static String bytesToHex(byte[] data)
		{
			if (data==null)
			{
				return null;
			}

			int len = data.length;
			String str = "";
			for (int i=0; i<len; i++) {
				if ((data[i]&0xFF)<16)
					str = str + "0" + Integer.toHexString(data[i]&0xFF);
				else
					str = str + Integer.toHexString(data[i]&0xFF);
			}
			return str;
		}
		
			
		public static byte[] hexToBytes(String str) {
			if (str==null) {
				return null;
			} else if (str.length() < 2) {
				return null;
			} else {
				int len = str.length() / 2;
				byte[] buffer = new byte[len];
				for (int i=0; i<len; i++) {
					buffer[i] = (byte) Integer.parseInt(str.substring(i*2,i*2+2),16);
				}
				return buffer;
			}
		}
		
		

		private static String padString(String source)
		{
		  char paddingChar = ' ';
		  int size = 16;
		  int x = source.length() % size;
		  int padLength = size - x;

		  for (int i = 0; i < padLength; i++)
		  {
			  source += paddingChar;
		  }
//		  Log.d("MCrypt", String.format("length -> %d", source.length()));
		  return source;
		}
}
