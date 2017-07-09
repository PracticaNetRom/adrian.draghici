package ro.netrom.summercamp.summercamp2017.data;

public class SmtpInfo {

	private final String smtpServer;
	private final String smtpPort;
	private final String username;
	private final String password;
	private final Boolean smtpUserAuthentication;
	private final Boolean smtpStartTLSEnable;

	public SmtpInfo(){//hardcoded SMTP credentials
		this.smtpServer = "mail.ady-web.tk";
		this.smtpPort = "465";
		this.username = "test1@ady-web.tk";
		this.password = "*Smtp*10Test1";
		this.smtpUserAuthentication = true;
		this.smtpStartTLSEnable = true;
	}
	
	public SmtpInfo(String smtpServer, String smtpPort, String username, String password,
			Boolean smtpUserAuthentication, Boolean smtpStartTLSEnable) {
		this.smtpServer = smtpServer;
		this.smtpPort = smtpPort;
		this.username = username;
		this.password = password;
		this.smtpUserAuthentication = smtpUserAuthentication;
		this.smtpStartTLSEnable = smtpStartTLSEnable;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public Boolean getSmtpUserAuthentication() {
		return smtpUserAuthentication;
	}

	public Boolean getSmtpStartTLSEnable() {
		return smtpStartTLSEnable;
	}
}
