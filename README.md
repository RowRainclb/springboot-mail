默认以协议smtp发送
1 qq邮箱可能作为安全考虑，防止邮箱密码泄露,需要开启smtp服务器,开启之后，会显示一个授权码。此授权码作为发件方的密码
2 不同类型邮箱的端口号和host服务器参考如下:

 1) 163邮箱:
 端口: 25
 host: smtp.163.com

 2) qq邮箱:
 端口: 465|587
 host: smtp.qq.com

 3) 企业邮箱
 端口:465
 host:smtp.exmail.qq.com



