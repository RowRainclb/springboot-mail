package com.k2data.k2app.service;

import com.k2data.k2app.validate.MailSimpleK2;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试mail
 * Created by clb on 17-3-21.
 */
@RunWith(SpringRunner.class)
@Rollback
@SpringBootTest
public class MailServiceTest {

	@Autowired
	private MailService mailService;
	@Autowired
	VelocityEngine velocityEngine;

	private String to = "clbzxj0720@163.com";

	@Test
	public void testSimpleMain() throws Exception {
		MailSimpleK2 mailSimpleK2 = new MailSimpleK2();
		mailSimpleK2.setHost("smtp.qq.com");
		mailSimpleK2.setPort(465);
		mailSimpleK2.setSendFrom("1029625587@qq.com");
		mailSimpleK2.setPwd("tlamonohqvzzbbba");
		mailSimpleK2.setSendTO("clbzxj0720@163.com");
		mailSimpleK2.setSubject("ti");
		mailSimpleK2.setContent("ti");
		mailService.initSender(mailSimpleK2);
		mailService.sendSimpleMail(mailSimpleK2);
	}

	public void sendAttachmentsMail() {
		mailService.sendAttachmentsMail(to, "主题：带附件的邮件", "有附件，请查收！", "/home/clb/shutdown.sh");
	}

	public void sendInlineResourceMail() {
		String rscId = "rscId001";
		mailService.sendInlineResourceMail(to,
				"主题：嵌入静态资源的邮件",
				"<html><body>这是有嵌入静态资源：<img src=\'cid:" + rscId + "\' ></body></html>",
				"/home/clb/图片/选区_010.png",
				rscId);
	}

}