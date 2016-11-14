package com.mockCommon.util.yangguang;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;


public class KeyPairer {

	// 第三方公钥
	public final static String PARTNER_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCI+jUAvljXFKCdSBqB8LZqRTI43chTT6L+Bk0argfymTwQ/9k0my8iZThVrl1wnQPMcXXfZ9rF+OwyKCcXWBqUI5eTFlMSwwL+f7lrQ1w6GwWXyM1wJOBX1JPOC/VMGMMceIy5gMMlC5u30FgkVkV4HNaJrJOPNMe1h7g7fj3PlQIDAQAB";
	// 第三方私钥
	public final static String PARTNER_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIj6NQC+WNcUoJ1IGoHwtmpFMjjdyFNPov4GTRquB/KZPBD/2TSbLyJlOFWuXXCdA8xxdd9n2sX47DIoJxdYGpQjl5MWUxLDAv5/uWtDXDobBZfIzXAk4FfUk84L9UwYwxx4jLmAwyULm7fQWCRWRXgc1omsk480x7WHuDt+Pc+VAgMBAAECgYAzEJQR4uRbymTWPbskFgjrNUCz0nqMFHQ/Hzo/aAGuf1HVRIxFAFViDTojNw2+ncp8vQ+kaaM1iscDK9Tm7wF5DEC9ZQ2ZKGJAxANZRx7EYqVr8yuZpgHXq5pseZXWbuOun4fQkLRqIJB5r/aM5bvsh5HyCIjWPTbMaLkiFTJ6AQJBAM6LMj8W5ZZbkAO9ohNcQ+0L7m1GvhtZ5ShvopM2qJs0qZBqvznZezmerrQfSVHUFYzKVIo4EY83DVXMAUmoCwkCQQCpxrPvF/unwagXBAdxd1/zh9e0zc/cpHs/0W8o62aVl4conDFYIN/ARjDWpTCOqTA7O7ggO1CnRcjkPkMbuactAkA2bwj3B5nKXqc91SR55b8hIhvcQOCpZK+4UHOQSL926BIoNXngTSjkrqVsYzJ3lmV3jXtqUgyOqfuhuPo950PBAkBT+uhSoshnCRI+oE2WQPiDnHSFCTGC8RHOVajo0tihspy259w4vbowgAf0hS3pw6MKCdZgizawJ4Lh9DJ56nHZAkBjaeKixt3U47NpjjfuLt9ryaR3VSc4Ptv8ocqabAGaZNayhiDUcz1peoxU4dcv6ZnYImd7r2UWTKfKtITnc8bj";
	
	//阳光公钥
	public final static String YG_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCpi714LS6BTL2xVPy7tAGPd2muIebSHrKTLqQ2mmUXjaEfHftIH1-slYQbtfTzimX7LO5VJGZtBohOfhMY-YSGEiMFJAmmHefX0SX6dY80FpC_Wgf-l0FVn4NQ5HcIXYMONAF7HXnhSJfgJ4Rp1x7NP1-0mLnhuHWCv1zYtw9gjwIDAQAB";
	
	public static PublicKey getPublicKey(String key) {
		try {
			byte[] keyBytes = (Base64.decodeBase64(key));
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static PrivateKey getPrivateKey(String key) {
		try {
			byte[] keyBytes = (Base64.decodeBase64(key));
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
			return privateKey;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}