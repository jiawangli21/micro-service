FROM java:8
VOLUME /usr/local/docker/target
ADD configserver-1.0-SNAPSHOT.jar /configserver.jar
EXPOSE 8762
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/configserver.jar"]