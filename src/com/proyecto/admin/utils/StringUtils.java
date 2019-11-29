package com.proyecto.admin.utils;

import java.util.regex.Pattern;

import org.apache.activemq.artemis.utils.StringEscapeUtils;

public abstract class StringUtils {

	// Quality valideishon
	private static final Pattern validUser = Pattern.compile("[a-zA-Z]\\w{3,}");
	private static final Pattern validName = Pattern.compile("[a-zA-Z]{2,}");
	private static final Pattern validSurname = Pattern.compile("[a-zA-Z]{1,2}\'??[a-zA-Z]*?");
	private static final Pattern validEmail = Pattern.compile("[a-zA-Z]\\w{3,}@[a-zA-Z]{4,}\\.[a-zA-Z]{2,3}");
	private static final Pattern validPhone = Pattern.compile("9\\d{7}");
	private static final Pattern validCharacteristic = Pattern.compile("[a-zA-Z]([a-zA-Z]| |\\.|-){1,}");

	public static String sanitize(String s) {
		return StringEscapeUtils.escapeString(s);
	}

	public static boolean isUserValid(String s) {
		return validUser.matcher(s).matches();
	}

	public static boolean isNameValid(String s) {
		return validName.matcher(s).matches();
	}

	public static boolean isSurnameValid(String s) {
		return validSurname.matcher(s).matches();
	}

	public static boolean isEmailValid(String s) {
		return validEmail.matcher(s).matches();
	}

	public static boolean isValidPhone(String s) {
		return validPhone.matcher(s).matches();
	}

	public static boolean isValidCharacteristic(String s) {
		return validCharacteristic.matcher(s).matches();
	}

}
