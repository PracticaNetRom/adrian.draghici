package ro.netrom.summercamp.summercamp2017.data;

import java.security.SecureRandom;

import ro.netrom.summercamp.summercamp2017.services.MailerService;

public class ConfirmationCode {
	private final String verificationCode;

	private final String email;

	public ConfirmationCode(String email) {
		this.email = email;
		this.verificationCode = generateCode();
		System.out.println("verificationCode: " + verificationCode);
	}

	private String generateCode() {
		String code = "";
		char[] pattern = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'q', 'w', 'e', 'r', 't', 'y',
				'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', 'Q',
				'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C',
				'V', 'B', 'N', 'M' };
		SecureRandom rand = new SecureRandom();
		for (int i = 0; i < 20; i++) {
			code += pattern[rand.nextInt(pattern.length)];
		}
		return code;

	}

	public String getEmail() {
		return email;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public boolean validateCode(String code) {
		return code.contentEquals(verificationCode);
	}

	public boolean sendCodeToUser(int requestType, int announcementId) {
		String subject = "", content = "", link = "";
		switch (requestType) {
		case 0:
			subject += "Activare";
			content = "activa anuntul dumneavoastra cu numarul: #" + announcementId + " in Ziarul Online";
			link = "<a href=\"http:ady.ro/activate.html?announcementId=" + announcementId + "&confirmationCode="
					+ verificationCode + "\">http:ady.ro/activate.html?announcementId=" + announcementId
					+ "&confirmationCode=" + verificationCode + "</a>";
			break;
		case 1:
			subject += "Dezactivare";
			content = "dezactiva anuntul dumneavoastra cu numarul: #" + announcementId + " din Ziarul Online";
			link = "<a href=\"http:ady.ro/dezactivate.html?Id=" + announcementId + "&ownerEmail=" + email
					+ "&confirmationCode=" + verificationCode + "\">http:ady.ro/dezactivate.html?Id=" + announcementId
					+ "&ownerEmail=" + email + "&confirmationCode=" + verificationCode + "</a>";
			break;
		default:
			break;
		}
		System.out.println("Code for " + email + ": " + verificationCode);
		return MailerService.sendEmail(new String[] { email }, subject + " anunt in Ziarul Online by Ady", "Pentru a "
				+ content + " va rugam sa utilizati link-ul primit cu acest e-mail.<br/>Linkul de confirmare este: " + link
				+ "<br/>Daca nu sunteti dumneavoastra cel care a facut aceasta solicitare va rugam sa ignorati acest e-mail.<br/><br/>Va multumim.<br/>Ziarul Online System<br/><br/><br/>Acest mesaj a fost generat automat de catre aplicatia Ziarul Online. Nu raspundeti la acest e-mail.");
	}
}
