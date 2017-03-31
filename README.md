默认以协议smtp发送
1 开启smtp服务器 开启之后，会显示一个授权码。此授权码作为发件方的密码，qq邮箱可能作为安全考虑，防止邮箱密码泄露。

163邮箱:
 端口: 25
 host: smtp.163.com

qq邮箱:
 端口: 465|587
 host: smtp.qq.com

 企业邮箱
 端口:465
 host:smtp.exmail.qq.com

 qq邮箱发送时,真正运行程序时，还是会爆 535 认证失败。
 1 因为JDK1.8中jre\lib\security中两个 jar 包替换的缘故。将下载后的local_policy.jar和US_export_policy.jar替换到JDK1.8的jre\lib\security文件夹即可。
 2 检查密码,账号,host

