package ro.netrom.summercamp.summercamp2017.data;

import java.security.SecureRandom;

public class ConfirmationCode {
	private final String verificationCode;

	private final String email;

	public ConfirmationCode(String email) {
		this.email = email;
		this.verificationCode = generateCode();
		System.out.println("verificationCode: "+verificationCode);
	}

	private String generateCode() {
		String code = "";
		char[] pattern = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r', 't', 'y',
				'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', 'Q',
				'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C',
				'V', 'B', 'N', 'M' };
		SecureRandom rand = new SecureRandom();
		for (int i = 0; i < 6; i++) {
			code += pattern[rand.nextInt(pattern.length)];
		}
		return code;

	}

	public String getEmail() {
		return email;
	}

	public boolean validateCode(String code) {
		return code.contentEquals(verificationCode);
	}
}
