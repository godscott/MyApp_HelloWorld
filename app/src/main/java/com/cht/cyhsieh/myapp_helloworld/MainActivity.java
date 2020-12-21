package com.cht.cyhsieh.myapp_helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCenter.start(getApplication(), "7339c9da-874a-4b9c-a728-3b97d1f867f5",
                Analytics.class, Crashes.class);

//        startActivity(new Intent(MainActivity.this, TranscoderActivity.class));

//        Intent intent = new Intent();
//        intent.putExtra("videopath", "")

        try {
            MCrypt mCrypt = new MCrypt();
            MCryptGCM mCryptGCM = new MCryptGCM();
            String text = "HelloWorld TEST AABBCC";
            Log.d(TAG, String.format("ori text = %s", text));



//            byte[] encodeBytes = mCryptGCM.encrypt(text.getBytes());
//            String hexEncode = MCryptGCM.bytesToHex(encodeBytes);
//            Log.d(TAG, String.format("encode GCM text = %s", hexEncode));
//
//            byte[] encodeCBC = mCrypt.encrypt(text);
//            String hexCBCEncode = MCrypt.bytesToHex(encodeCBC);
//            Log.d(TAG, String.format("encode CBC text = %s", hexCBCEncode));
//
//            String decryptedText = mCryptGCM.decrypt(encodeBytes);
//            Log.d(TAG, String.format("decode text = %s", decryptedText));
//
//            String decryptedCBCText = mCryptGCM.decrypt2(encodeCBC);
//            Log.d(TAG, String.format("decode text CBC = %s", decryptedCBCText));

            String encodeStr_test = mCryptGCM.encrypt3_test(text.getBytes(), "1234567890123456");
            Log.d(TAG, String.format("encodeStr_test = %s", encodeStr_test));

//            String input_encodeStr = "+cqMtHDtdXnmijl9akXIgevuclGG+oDmp6NYbA==";
            String input_encodeStr = encodeStr_test;
            String decryptedText = MCryptGCM.decrypt_my(input_encodeStr, "1234567890123456", 16);
            Log.d(TAG, String.format("decryptedText = %s", decryptedText));
            Log.d(TAG, String.format("==========================="));

            String encodeStr_php = mCryptGCM.encrypt3_php(text.getBytes(), "secretKey1234567");
            Log.d(TAG, String.format("encodeStr_php = %s", encodeStr_php));

            String input_encodeFromPHP = "APajdauPp5LTNghSGWC8Qr342uW+4sVA1iooCJmO0+RLx6OtPNE/wAIM+E7HGEc=";
//            decryptedText = MCryptGCM.decrypt3_php(input_encodeFromPHP, "secretKey12345678998765432112345");
            decryptedText = MCryptGCM.decrypt3_php(input_encodeFromPHP, "secretKey1234567");
            Log.d(TAG, String.format("decryptedText(PHP) = %s", decryptedText));

            Log.d(TAG, String.format("==========================="));

            String encodeStr_php_part4 = mCryptGCM.encrypt3_php(text.getBytes(), "secretKey1234567secretKey1234567");
            Log.d(TAG, String.format("encodeStr_php_part4 = %s", encodeStr_php_part4));

            String input_encodeFromPHP_part4 = "/A3mI0QPmQAwcUACCdRLhfgmhsQcdjJDi6k=";
//            decryptedText = MCryptGCM.decrypt3_php(input_encodeFromPHP, "secretKey12345678998765432112345");
            decryptedText = MCryptGCM.decrypt3_php(input_encodeFromPHP_part4, "secretKey1234567secretKey1234567");
            Log.d(TAG, String.format("decryptedText(PHP)_part4 = %s", decryptedText));
//
////            byte[] encodeBytes = mCryptGCM.encrypt3(text.getBytes(), "0123456789abcdef");
//            byte[] encodeBytes = mCryptGCM.encrypt3_php(text.getBytes(), "secretKey12345678998765432112345");
//            Log.d(TAG, String.format("encode GCM text = %s", Base64.encodeToString(encodeBytes, Base64.DEFAULT)));
//            String hexEncode = MCryptGCM.bytesToHex(encodeBytes);
//            Log.d(TAG, String.format("encode GCM text = %s", hexEncode));
//
//            String decryptedTextPHP = mCryptGCM.decrypt3_php(Base64.decode(Base64.encodeToString(encodeBytes, Base64.DEFAULT), Base64.DEFAULT), "secretKey12345678998765432112345");
//            Log.d(TAG, String.format("decode PHP text = %s", decryptedTextPHP));
//
////            String decryptedText = mCryptGCM.decrypt3(encodeBytes, "0123456789abcdef");
//            String tag = "alextag";
//            String iv = "sssssss1";
//            String base64Tag = Base64.encodeToString(tag.getBytes(), Base64.DEFAULT);
////            String encodeStr = Base64.encodeToString("sssssss1".getBytes(), Base64.DEFAULT)+"+B3NJN37EhDNPw==" + base64Tag;
//            String encodeStr = "6uZ6RZzGj61Q8g==";
////            String encodeStr = "+B3NJN37EhDNPw==" + base64Tag;
////            String decryptedText = mCryptGCM.decrypt3(encodeStr.getBytes(), "secretKey12345678998765432112345");
//            Log.d(TAG, String.format("base64Tag = %s, encodeStr = %s, base64iv = %s", base64Tag, encodeStr, new String(Base64.decode(encodeStr, Base64.DEFAULT))));
////            String decryptedText = mCryptGCM.decrypt3(encodeStr.getBytes(), "secretKey12345678998765432112345");
////            String decryptedText = mCryptGCM.decrypt3(Base64.decode(encodeStr, Base64.DEFAULT), "secretKey12345678998765432112345");
////            String decryptedText = mCryptGCM.decrypt4(MCryptGCM.hexToBytes("eae67a459cc68fad50f2"), "1234567890123456");
//
//            final byte[] cipherText = Base64.decode("+cCX+H7IfyvzgWwDX6Y6Mw6z", Base64.DEFAULT);//MCryptGCM.hexToBytes("eae67a459cc68fad50f2");//
////            final byte[] ivBytes = Base64.decode("P8kU/Gf27LU9qgmLpA8gpQ==", Base64.DEFAULT);
//            final byte[] ivBytes = Base64.decode("c3Nzc3Nzc3Nzc3Nzc3NzMQ==", Base64.DEFAULT);
////            final byte[] tagBytes = Base64.decode("1+T/3BX0UaGaVMcUxCs4/A==", Base64.DEFAULT);
//            final byte[] tagBytes = Base64.decode("UFMGX0Uq4/tLSRxEZ9sBZQ==", Base64.DEFAULT);
//            final byte[] encryptedBytes2 = new byte[cipherText.length + ivBytes.length+ tagBytes.length];
//            System.arraycopy(ivBytes, 0, encryptedBytes2, 0, ivBytes.length);
//            System.arraycopy(cipherText, 0, encryptedBytes2, ivBytes.length, cipherText.length);
//            System.arraycopy(tagBytes, 0, encryptedBytes2, ivBytes.length+cipherText.length, tagBytes.length);
//
//            Log.d(TAG, String.format("cipherText Hex = %s", MCryptGCM.bytesToHex(cipherText)));
//            Log.d(TAG, String.format("ivBytes Hex = %s", MCryptGCM.bytesToHex(ivBytes)));
//            Log.d(TAG, String.format("tagBytes Hex = %s", MCryptGCM.bytesToHex(tagBytes)));
//            Log.d(TAG, String.format("encryptedBytes2 Hex = %s", MCryptGCM.bytesToHex(encryptedBytes2)));
////            String decryptedText = MCryptGCM.decrypt("P8kU/Gf27LU9qgmLpA8gpfHmdVuW5cC7XeKVIgmb3rgGzyjG0YHX5P/cFfRRoZpUxxTEKzj8", "1234567890123456", 16);
////            String decryptedText = MCryptGCM.decrypt_my(Base64.encodeToString(encryptedBytes2, Base64.DEFAULT), "1234567890123456", 16);
////            String decryptedText = MCryptGCM.decrypt(Base64.encodeToString(MCryptGCM.hexToBytes("f1e6755b96e5c0bb5de29522099bdeb806cf28c6d181d7e4ffdc15f451a19a54c714c42b38fc"), Base64.DEFAULT), "1234567890123456", 16);
//
//            String decryptedText = MCryptGCM.decrypt_my("+cqMtHDtdXnmijl9akXIgevuclGG+oDmp6NYbA==", "1234567890123456", 16);
//            Log.d(TAG, String.format("decode22 text = %s", decryptedText));
        }
        catch (Exception e) {
            Log.d(TAG, String.format("Exception => %s", e.getMessage()));
            e.printStackTrace();
        }

    }


}
