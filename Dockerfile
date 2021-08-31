FROM adoptopenjdk/openjdk8:ubi
ADD target/cotacoes-api-0.0.1-SNAPSHOT.jar cotacoes-api-0.0.1-SNAPSHOT.jar
ENV JAVA_OPTS="-Dspring.profiles.active=env"
ENTRYPOINT exec java -Duser.timezone=America/Sao_Paulo $JAVA_OPTS -jar cotacoes-api-0.0.1-SNAPSHOT.jar