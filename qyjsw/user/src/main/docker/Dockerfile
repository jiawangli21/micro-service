FROM java:8
VOLUME /usr/local/docker/target
ADD user-1.0-SNAPSHOT.jar /user.jar
EXPOSE 8088
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user.jar"]