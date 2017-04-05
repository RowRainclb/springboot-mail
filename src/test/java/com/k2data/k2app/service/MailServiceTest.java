package com.k2data.k2app.service; 

import com.k2data.k2app.validate.MailSimpleK2;
import com.k2data.k2app.validate.MailTemplateK2;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
* MailService Tester. 
* 
* @author <clb> 
* @since <pre>四月 5, 2017</pre> 
* @version 1.0 
*/ 
@RunWith(SpringRunner.class)
@Rollback
@Transactional
@SpringBootTest
public class MailServiceTest { 

    @Autowired
    private MailService mailService;
    @Autowired
    VelocityEngine velocityEngine;

    private String to = "xx@163.com";
    
    @Before
    public void before() throws Exception { 
    } 
    
    @After
    public void after() throws Exception { 
    } 
    
        /** 
    * 
    * Method: initSender(MailSenderK2 mailSenderK2) 
    * 
    */ 
    @Test
    public void testInitSender() throws Exception { 
    //TODO: Test goes here... 
    } 
    
        /** 
    * 
    * Method: sendSimpleMail(MailSimpleK2 mail) 
    * 
    */ 
    @Test
    public void testSendSimpleMail() throws Exception {
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
    
    /**
    * 
    * Method: sendHtmlMail(MailTemplateK2 mailTemplateK2) 
    * 
    */ 
    @Test
    public void testSendHtmlMail() throws Exception {
        MailTemplateK2 templateK2 = new MailTemplateK2();
        templateK2.setHost("smtp.qq.com");
        templateK2.setPort(465);
        templateK2.setSendFrom("1029625587@qq.com");
        templateK2.setPwd("tlamonohqvzzbbba");
        templateK2.setSendTO("clbzxj0720@163.com");
        templateK2.setSubject("top:xxx");
        templateK2.setTemplateName("mailTemplate.vm");
        Map map1 = new HashMap<>();
        map1.put("toUserName","这是客户名称");
        map1.put("message","这是内容");
        templateK2.setTemplateParams(map1);
        mailService.sendHtmlMail(templateK2);
    } 

    } 
