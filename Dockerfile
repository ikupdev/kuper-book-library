FROM openjdk:11
MAINTAINER ikupdev
ARG JAR_FILE=target/service-library.jar
COPY ${JAR_FILE} /opt/app/library-platform/service-library.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/library-platform/service-library.jar", "--spring.config.additional-location=/opt/conf/", "-Dfile.encoding=UTF-8"]