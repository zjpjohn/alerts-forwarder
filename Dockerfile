FROM openjdk:8-jdk-slim
VOLUME /tmp
EXPOSE 9093
ENV APP_HOME /home/cmss4tianjin
RUN mkdir $APP_HOME
ADD target/alerts_forwarder-0.0.1.jar $APP_HOME/alerts_forwarder.jar
ADD target/jmx_prometheus_javaagent-0.1.0.jar $APP_HOME/jmx_prometheus_javaagent.jar
ADD target/jmx_exporter.yml $APP_HOME/jmx_exporter.yml
WORKDIR $APP_HOME
RUN bash -c 'touch alerts_forwarder.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-javaagent:jmx_prometheus_javaagent.jar=1234:jmx_exporter.yml","-jar","alerts_forwarder.jar"]
LABEL maintainer "CMSS IT DEPARTMENT"