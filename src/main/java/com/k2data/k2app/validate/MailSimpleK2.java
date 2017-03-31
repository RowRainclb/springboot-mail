package com.k2data.k2app.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 简单邮件
 * Created by clb on 17-3-20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailSimpleK2 extends MailSenderK2{
	/**
	 * 邮件内容
	 */
	@NotNull
	String content;
}
