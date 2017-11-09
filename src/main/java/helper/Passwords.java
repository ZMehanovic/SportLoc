package helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class Passwords {

	private static final Random RANDOM = new SecureRandom();

	private Passwords() {
	}

	/**
	 * Returns a random salt to be used to hash a password.
	 *
	 * @return a 16 bytes random salt
	 */
	public static String getNextSalt() {
		String result = null;
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		try {
			result = new String(salt, "UTF-16");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static String getSecurePassword(String passwordToHash, String salt) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes("UTF-16"));
			byte[] bytes;
			bytes = md.digest(passwordToHash.getBytes("UTF-16"));

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	public static boolean checkPasswordHash(String password, String salt, String hash) {
		return hash.equals(getSecurePassword(password, salt));
	}

	/**
	 * Generates a random password of a given length, using letters and digits.
	 *
	 * @param length
	 *            the length of the password
	 *
	 * @return a random password
	 */
	public static String generateRandomPassword(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int c = RANDOM.nextInt(62);
			if (c <= 9) {
				sb.append(String.valueOf(c));
			} else if (c < 36) {
				sb.append((char) ('a' + c - 10));
			} else {
				sb.append((char) ('A' + c - 36));
			}
		}
		return sb.toString();
	}
}