package com.fairyland.jdp.framework.mail;

//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @desc 邮件发送对象 提供 sendTextMail sendHtmlMail 两种方式的邮件发送格式
 * 
 */

public class MailSender {

//	/**
//	 * 邮件发送器，包含邮件服务器信息等
//	 */
//	private org.springframework.mail.MailSender mailSender;
//
//	/**
//	 * 系统缺省发送人地址
//	 */
//	private String systemDefaultFrom;
//
//	/**
//	 * 发送文本邮件
//	 * 
//	 * @param recipient
//	 *            待发送的邮件收件人
//	 * @param subject
//	 *            待发送的邮件主题
//	 * @param content
//	 *            待发送內容
//	 * @throws MessagingException
//	 */
//	public boolean sendTextMail(String recipient, String subject, String content)
//			throws MessagingException {
//		return sendTextMail(new String[] { recipient }, subject, content);
//	}
//
//	/**
//	 * 发送文本邮件
//	 * 
//	 * @param recipients
//	 *            待发送的邮件收件人
//	 * @param subject
//	 *            待发送的邮件主题
//	 * @param content
//	 *            待发送內容
//	 * @throws MessagingException
//	 */
//	public boolean sendTextMail(String[] recipients, String subject,
//			String content) throws MessagingException {
//		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//		simpleMailMessage.setFrom(systemDefaultFrom);
//		simpleMailMessage.setSubject(subject); // 设置邮件主题
//		simpleMailMessage.setTo(recipients); // 设定收件人
//		simpleMailMessage.setText(content); // 设置邮件主题内容
//		mailSender.send(simpleMailMessage); // 发送邮件
//		return true;
//	}
//
//	/**
//	 * 以HTML格式发送邮件
//	 * 
//	 * @param recipient
//	 *            待发送的邮件收件人
//	 * @param subject
//	 *            待发送的邮件主题
//	 * @param content
//	 *            待发送內容
//	 * @throws MessagingException
//	 */
//	public boolean sendHtmlMail(String recipient, String subject, String content)
//			throws MessagingException {
//
//		return sendHtmlMail(new String[] { recipient }, subject, content);
//	}
//
//	/**
//	 * 以HTML格式发送邮件
//	 * 
//	 * @param recipients
//	 *            待发送的邮件收件人
//	 * @param subject
//	 *            待发送的邮件主题
//	 * @param content
//	 *            待发送內容
//	 * @throws MessagingException
//	 */
//	public boolean sendHtmlMail(String[] recipients, String subject,
//			String content) throws MessagingException {
//		// 建立邮件消息,发送简单邮件和html邮件的区别
//		if (!(this.mailSender instanceof JavaMailSenderImpl)) {
//			throw new RuntimeException("cannot send html-mail");
//		}
//		JavaMailSenderImpl mailSender = (JavaMailSenderImpl) this.mailSender;
//		MimeMessage mailMessage = mailSender.createMimeMessage();
//		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);
//
//		// 设置收件人，寄件人
//		messageHelper.setTo(recipients);
//		messageHelper.setFrom(systemDefaultFrom);
//		messageHelper.setSubject(subject);
//		// true 表示启动HTML格式的邮件
//		messageHelper.setText(content, true);
//
//		// 发送邮件
//		mailSender.send(mailMessage);
//
//		return true;
//	}
//
//	public org.springframework.mail.MailSender getMailSender() {
//		return mailSender;
//	}
//
//	public void setMailSender(org.springframework.mail.MailSender mailSender) {
//		this.mailSender = mailSender;
//	}
//
//	public String getSystemDefaultFrom() {
//		return systemDefaultFrom;
//	}
//
//	public void setSystemDefaultFrom(String systemDefaultFrom) {
//		this.systemDefaultFrom = systemDefaultFrom;
//	}

}
