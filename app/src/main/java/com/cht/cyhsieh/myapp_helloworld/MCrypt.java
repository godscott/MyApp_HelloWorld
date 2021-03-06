/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cht.cyhsieh.myapp_helloworld;

import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author chuanhan
 */
public class MCrypt
{
		private String iv = "fedcba9876543210";//Dummy iv (CHANGE IT!)
		private IvParameterSpec ivspec;
		private SecretKeySpec keyspec;
		private Cipher cipher;
		
		private String SecretKey = "0123456789abcdef";//Dummy secretKey (CHANGE IT!)
		
		public MCrypt()
		{
			ivspec = new IvParameterSpec(iv.getBytes());

			keyspec = new SecretKeySpec(SecretKey.getBytes(), "AES");
			
			try {
				cipher = Cipher.getInstance("AES/CBC/NoPadding");
//				cipher = Cipher.getInstance("AES/CCM/NoPadding");
//				cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public byte[] encrypt(String text) throws Exception
		{
			if(text == null || text.length() == 0)
				throw new Exception("Empty string");
			
			byte[] encrypted = null;

			try {
				cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);

				
//				Log.d("MCrypt", Integer.toString(padString(text).getBytes().length));
				encrypted = cipher.doFinal(padString(text).getBytes());
			} catch (Exception e){			
				throw new Exception("[encrypt] " + e.getMessage());
			}
			
			return encrypted;
		}
		
		public byte[] decrypt(String code) throws Exception
		{
			if(code == null || code.length() == 0)
				throw new Exception("Empty string");
			
			byte[] decrypted = null;

			try {
				cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
				
				decrypted = cipher.doFinal(hexToBytes(code));
			} catch (Exception e)
			{
				throw new Exception("[decrypt] " + e.getMessage());
			}
			return decrypted;
		}

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
