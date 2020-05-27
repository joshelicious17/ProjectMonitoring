package workflowsystem.web;

import javax.mail.*;
import javax.mail.internet.*;

import workflowsystem.util.WfConstCommon;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class SendMail {

	public void sendMail(String recipients[], String subject, String message,
			String from) throws MessagingException {
		boolean debug = false;

		/* Set the host SMPT address */

		Properties props = new Properties();
		props.put("mail.smtp.host",WfConstCommon.DEFAULT_SMTP);
//		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "25");

		/* create some properties and get the default Session */

//		Session session = Session.getDefaultInstance(props, null);
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(WfConstCommon.DEFAULT_SENDER,WfConstCommon.DEFAULT_PW); 		// username and the password
            }
		});
		session.setDebug(debug);

		// create a message
		MimeMessage msg = new MimeMessage(session);

		/* Set the Message Content Type */
		msg.setHeader("Content-Type", "text/plain; charset=UTF-8");

		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// Optional : You can also set your custom headers in the Email if you
		// Want
		// msg.addHeader("MyHeaderName", "myHeaderValue");

		/* replace keyword from "localhost" to server's ip address */
		try {
			// Get IP Address
			String ipAddr = InetAddress.getLocalHost().getHostAddress();
			if(ipAddr != null && !ipAddr.isEmpty())
			{
				/* update link path */
				message = message.replace("localhost", ipAddr);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		/* Setting the Subject and Content Type */
		msg.setSubject(subject,"utf-8");
		msg.setContent(message, "text/html; charset=utf-8");

		/* send message */
		Transport.send(msg);
	}
}
