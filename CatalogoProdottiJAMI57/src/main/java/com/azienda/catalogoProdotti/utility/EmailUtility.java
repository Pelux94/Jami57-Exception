package com.azienda.catalogoProdotti.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.azienda.catalogoProdotti.model.Item;

public class EmailUtility {
	
	public static void sendEmail(String username, Item item) throws IOException, MessagingException {
		Properties props = new Properties();
		InputStream is = EmailUtility.class.getClassLoader().getResourceAsStream("email.properties");
		props.load(is);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
			}
		});
		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(props.getProperty("from")));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
		msg.setSubject("Thank you for your purchase!");
		msg.setText("We hope you will have a nice good morning with our new mug.");
		Transport.send(msg);


	}
	
//	public static void main(String[] args) {
//		try {
//			sendEmail("peluso94@hotmail.it", new Item("tazza", null, null));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
