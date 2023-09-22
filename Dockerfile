FROM openjdk:17-oraclelinux8 as builder
RUN jlink \
    --module-path "$JAVA_HOME/jmods" \
    --add-modules java.compiler,java.sql,java.naming,java.management,java.instrument,java.rmi,java.desktop,jdk.internal.vm.compiler.management,java.xml.crypto,java.scripting,java.security.jgss,jdk.httpserver,java.net.http,jdk.naming.dns,jdk.crypto.cryptoki,jdk.unsupported \
    --verbose \
    --strip-debug \
    --compress 2 \
    --no-header-files \
    --no-man-pages \
    --output /opt/jre-minimal

FROM bitnami/minideb:bullseye
COPY --from=builder /opt/jre-minimal /opt/jre-minimal
ENV JAVA_HOME=/opt/jre-minimal
ENV PATH="$PATH:$JAVA_HOME/bin"
RUN mkdir "/app"
COPY target/SimpleSpringBootRestAPI-0.0.1-SNAPSHOT.jar /app/
CMD java -jar /app/SimpleSpringBootRestAPI-0.0.1-SNAPSHOT.jar
EXPOSE 8183