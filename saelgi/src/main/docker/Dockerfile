FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/saelgi-1.0.0.jar saelgi.jar
RUN sh -c 'touch /saelgi.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","saelgi.jar"]