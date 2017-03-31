FROM daocloud.io/ld00000/jdk-8:0.6.0

MAINTAINER <lidong@k2data.com.cn>

ENV K2_WORK_DIR /usr/local/k2alpha
ENV K2_JAR_NAME k2alpha-mail-send.jar

RUN mkdir -p $K2_WORK_DIR

COPY ./target/$K2_JAR_NAME $K2_WORK_DIR/$K2_JAR_NAME

WORKDIR $K2_WORK_DIR

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "k2alpha-mail-send.jar", "com.k2data.k2app.Application"]

EXPOSE 17878

CMD []
