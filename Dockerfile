FROM azul/zulu-openjdk:11.0.10
RUN apt update

#COPY target/ms-customer-0.0.1.jar ms-customer-0.0.1.jar
RUN mkdir -p /opt/arquitectura/logs
VOLUME /opt/arquitectura/logs
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app


#ENV SPRING_PROFILE local
ENV SERVER_PORT 7878
ENV EUREKA_URI "http://localhost:8763/eureka"
ENV CONFIG_URI "http://localhost:8887"

ENTRYPOINT ["java", \
            "-cp", \
            "app:app/lib/*", \
            "arquitectura.software.msteacher.MsTeacherApplication"]
