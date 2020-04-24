FROM java:8
VOLUME /usr/local/docker/target
ADD eurekaserver-1.0-SNAPSHOT.jar /eurekaserver.jar
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eurekaserver.jar"]