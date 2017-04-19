package com.k2data.k2app.service;

import com.k2data.k2app.validate.MailSenderK2;
import com.k2data.k2app.validate.MailSimpleK2;
import com.k2data.k2app.validate.MailTemplateK2;
import lombok.extern.log4j.Log4j2;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

/**
 * Created by clb on 17-3-21.
 */
@Log4j2
@Service
public class MailService {
	/**
	 * 模板引擎
	 */
	@Autowired
	VelocityEngine velocityEngine;

	@Autowired
	private RestTemplate restTemplate;

//	@Autowired
	private JavaMailSenderImpl sender;

	public void initSender(MailSenderK2 mailSenderK2){
		//创建邮件发送服务器
		sender = new JavaMailSenderImpl();
		sender.setHost(mailSenderK2.getHost());
		sender.setUsername(mailSenderK2.getSendFrom());
		sender.setPassword(mailSenderK2.getPwd());
		sender.setDefaultEncoding("utf-8");
//		sender.setPort(mailSenderK2.getPort());
		//加认证机制
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", true);
		javaMailProperties.put("mail.smtp.starttls.enable", true);
		javaMailProperties.put("mail.smtp.timeout", 5000);
		sender.setJavaMailProperties(javaMailProperties);

//		restTemplate.delete("http://K2ALPHA-DEVICE/v1/dd");
	}


	private String from="";

	/**
	 * 发送纯文本的简单邮件
	 * @param mail	邮件
	 */
	public int sendSimpleMail(MailSimpleK2 mail){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mail.getSendFrom());
		message.setTo(mail.getSendTO());
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		sender.send(message);
		log.info("邮件发送成功! 地址: {}", mail.getSendTO());
		return 1;
	}


	/**
	 * 发送html格式的邮件
	 * @param mailTemplateK2
	 */
	public String sendHtmlMail(MailTemplateK2 mailTemplateK2){
		String info = "";
		MimeMessage message = sender.createMimeMessage();
		try {
			String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, mailTemplateK2.getTemplateName(), "UTF-8", mailTemplateK2.getTemplateParams());
			//true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailTemplateK2.getSendFrom());
			helper.setTo(mailTemplateK2.getSendTO());
			helper.setSubject(mailTemplateK2.getSubject());
			helper.setText(content, true);
			sender.send(message);
			info = "html邮件已经发送";
			log.info("html邮件已经发送");
		} catch (MessagingException e) {
			info = "发送html邮件时发生异常";
			log.error("发送html邮件时发生异常", e);
		}
		return info;
	}
	/**
	 * 发送带附件的邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 */
	public void sendAttachmentsMail(String to, String subject, String content, String filePath){
		MimeMessage message = sender.createMimeMessage();

		try {
			//true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);

			sender.send(message);
			log.info("带附件的邮件已经发送。");
		} catch (MessagingException e) {
			log.error("发送带附件的邮件时发生异常！", e);
		}
	}

	/**
	 * 发送嵌入静态资源（一般是图片）的邮件
	 * @param to
	 * @param subject
	 * @param content 邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" >
	 * @param rscPath 静态资源路径和文件名
	 * @param rscId 静态资源id
	 */
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId){
		MimeMessage message = sender.createMimeMessage();

		try {
			//true表示需要创建一个multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);

			FileSystemResource res = new FileSystemResource(new File(rscPath));
			helper.addInline(rscId, res);

			sender.send(message);
			log.info("嵌入静态资源的邮件已经发送。");
		} catch (MessagingException e) {
			log.error("发送嵌入静态资源的邮件时发生异常！", e);
		}
	}

}