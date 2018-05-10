package com.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	/**
	 * 
	 * 对文件全文生成MD5摘要
	 *
	 * 
	 * 
	 * @param file
	 * 
	 *            要加密的文件
	 * 
	 * @return MD5摘要码
	 * 
	 */

	static char hexdigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',

			'9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String getMD5(File file) {

		FileInputStream fis = null;

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			fis = new FileInputStream(file);

			byte[] buffer = new byte[2048];

			int length = -1;

			long s = System.currentTimeMillis();

			while ((length = fis.read(buffer)) != -1) {

				md.update(buffer, 0, length);

			}

			byte[] b = md.digest();

			return bytesToHexString(b);

			// 16位加密

			// return buf.toString().substring(8, 24);

		} catch (Exception ex) {

			ex.printStackTrace();

			return null;

		} finally {

			try {
				if(fis != null) {
					fis.close();
				}

			} catch (Exception ex) {

				ex.printStackTrace();

			}

		}

	}


	public static String hashKeyForDisk(String key) {
		String cacheKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			cacheKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			cacheKey = String.valueOf(key.hashCode());
		}
		return cacheKey;
	}

	private static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	public static void main(String arg[]) {

		System.out.println(getMD5(new File("f:/a.txt")));

	}

}
