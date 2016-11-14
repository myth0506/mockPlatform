package com.mockCommon.util.yangguang;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * <p>Title: </p> 
 * 
 * <p>Copyright: Copyright (c) 2012</p> 
 * 
 * <p>Company: www.netease.com</p>
 * 
 * @author 	Zjx
 * @date 	2015年3月23日 下午3:44:15
 * @version 1.0.0
 */
public class RSASigner
{
	public static PublicKey getPublicKey(String key)
	{
		try
		{
			byte[] keyBytes = (Base64.decodeBase64(key));
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}

	public static PrivateKey getPrivateKey(String key)
	{
		try
		{
			byte[] keyBytes = (Base64.decodeBase64(key));
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
			return privateKey;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

	}

	public static String sign(byte[] data, PrivateKey priKey)
	{
		try
		{
			Signature signature = Signature.getInstance("MD5WithRSA");
			signature.initSign(priKey);
			signature.update(data);
			return Base64.encodeBase64URLSafeString(signature.sign());
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public static boolean verify(byte[] data, String sign, PublicKey pubKey)
	{
		try
		{
			Signature signature = Signature.getInstance("MD5WithRSA");
			signature.initVerify(pubKey);
			signature.update(data);
			return signature.verify(Base64.decodeBase64(sign));
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
