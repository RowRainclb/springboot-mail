package com.k2data.k2app.validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 带模板的html邮件
 * Created by clb on 17-3-20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailTemplateK2 extends MailSenderK2{
	/**
	 * 模板名称
	 */
	@NotNull
	String templateName;
	/**
	 * 模板参数
	 */
	Map<String,Object> templateParams;


}
