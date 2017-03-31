package com.k2data.k2app.controller;


import com.k2data.k2app.ResponseConstant;
import com.k2data.k2app.constant.ResponseCode;
import com.k2data.k2app.exception.K2Exception;
import com.k2data.k2app.exception.K2ResponseException;
import com.k2data.k2app.response.CommonResultResponse;
import com.k2data.k2app.response.ResponseHelper;
import com.k2data.k2app.service.MailService;
import com.k2data.k2app.validate.MailSimpleK2;
import com.k2data.k2app.validate.MailTemplateK2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(description = "邮件接口")
@RestController
@RequestMapping("/v1/mail")
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }
    /**
     * <p>send mail</p>
     * @param mailSimpleK2
     */
    @ApiOperation(value = "发送纯文本的简单邮件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mailSimpleK2", value = "接收邮件配置", required = true, dataType = "MailSimpleK2", paramType = "body")
    })
    @PostMapping(value = "/simple/send")
    public ResponseEntity<CommonResultResponse> sendSimple(@Valid @RequestBody MailSimpleK2 mailSimpleK2) throws Exception{
        try{
            mailService.initSender(mailSimpleK2);
            return ResponseHelper.success(mailService.sendSimpleMail(mailSimpleK2));
        }catch (Exception e){
            throw new K2ResponseException(ResponseCode.getCode(ResponseCode.TYPE_SERVER_ERROR,ResponseCode.K2ALPHA_MAIL, ResponseConstant.SEND_ERROR),"the simple mail send failed!",e);
        }
    }

    /**
     * <p>send mail</p>
     *
     * <p>html模板放于${@code }/resources/templates</p>下面, ex. ${@code template.vm}
     *
     * @param mailTemplateK2
     */
    @ApiOperation(value = "发送带模板的html邮件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mailTemplateK2", value = "mailTemplateK2对象", required = true, dataType = "MailTemplateK2", paramType = "body")
    })
    @PostMapping(value = "/template/send")
    public ResponseEntity<CommonResultResponse> send(@Valid @RequestBody MailTemplateK2 mailTemplateK2) throws Exception {
        try{
            mailService.initSender(mailTemplateK2);
            return ResponseHelper.success(mailService.sendHtmlMail(mailTemplateK2));
        }catch (K2Exception e){
            throw new K2ResponseException(ResponseCode.getCode(ResponseCode.TYPE_CLIENT_ERROR,ResponseCode.K2ALPHA_MAIL,ResponseConstant.SEND_ERROR),"the template mail send failed",e);
        }
    }




    /**
     * <p>send mail</p>
     * @param sendTo 接收者邮箱
     * @param subject 主题
     * @param content 邮件内容
     *//*
    @ApiOperation(value = "发送纯文本的简单邮件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sendTo", value = "接收者邮箱", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "subject", value = "主题", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "邮件内容", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/simple/send")
    public ResponseEntity<CommonResultResponse> sendSimple(@NotNull  @RequestParam @Pattern(regexp = "^([a-z0-9A-Z]+[-|_|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$",message = "邮箱格式错误") String sendTo,
                                                           @NotNull  @RequestParam String subject,
                                                           @NotNull  @RequestParam String content) throws Exception {
        try{
            return ResponseHelper.success(mailService.sendSimpleMail(sendTo,subject,content));
        }catch (K2Exception e){
            throw new K2ResponseException(ResponseCode.CLIENT_ERROR, ResponseMessage.CLIENT_ERROR);
        }
    }

    *//**
     * <p>send mail</p>
     *
     * <p>html模板放于${@code }/resources/templates</p>下面, ex. ${@code template.vm}
     *
     * @param sendTo 接收者邮箱
     * @param subject 主题
     * @param templateName 模板名称
     *//*
    @ApiOperation(value = "发送带模板的html邮件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sendTo", value = "接收者邮箱", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "subject", value = "主题", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "templateName", value = "位于下面的html模板的名称", required = true, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "templateParams", value = "模板需要的参数", required = true, dataType = "Map", paramType = "body")
    })
    @PostMapping(value = "/template/send")
    public ResponseEntity<CommonResultResponse> send(@NotNull @Valid @Pattern(regexp = "^([a-z0-9A-Z]+[-|_|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$",message = "邮箱格式错误") @RequestBody String sendTo,
                                                     @NotNull  @RequestBody String subject,
                                                     @NotNull  @RequestBody String templateName,
                                                     @NotNull  @RequestBody Map templateParams) throws Exception {
        try{
            return ResponseHelper.success(mailService.sendHtmlMail(sendTo,subject,templateName,templateParams));
        }catch (K2Exception e){
            throw new K2ResponseException(ResponseCode.CLIENT_ERROR, ResponseMessage.CLIENT_ERROR);
        }
    }*/
}
