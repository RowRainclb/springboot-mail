package com.k2data.k2app.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by clb on 17-3-22.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailSenderK2 {
	/**
	 * 发送者邮件
	 */
	@Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",message = "发送者邮箱格式错误")
	private String sendFrom;
	private int port;
	private String pwd;
	private String host;
	/**
	 * 接收者邮件
	 */
	@Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",message = "邮箱格式错误")
	String sendTO;
	/**
	 * 主题
	 */
	@NotNull
	String subject;
}
