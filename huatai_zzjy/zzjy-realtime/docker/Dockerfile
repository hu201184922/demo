FROM 192.168.88.144:5000/java8-jre
VOLUME /tmp
ADD service-jpa-1.0-SNAPSHOT.jar /tmp/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-XX:PermSize=64M","-XX:MaxPermSize=128m","-jar","/tmp/app.jar"]