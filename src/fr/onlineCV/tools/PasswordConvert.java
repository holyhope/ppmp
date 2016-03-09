package fr.onlineCV.tools;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PasswordConvert implements AttributeConverter<String, String> {

	public static String hashPassword(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes(), 0, password.length());

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return new BigInteger(1, md.digest()).toString(16);
	}

	@Override
	public String convertToDatabaseColumn(String attribute) {
		return hashPassword(attribute);

	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		return dbData;
	}

}
