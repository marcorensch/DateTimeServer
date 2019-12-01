FROM maven:3-jdk-8
EXPOSE 6060
COPY . /usr/src/server
WORKDIR /usr/src/server
RUN mvn clean install
CMD ["java", "-cp", "target/*", "ch.ibw.clientServer.server.javaReply.DateTimeServer", "6060"]