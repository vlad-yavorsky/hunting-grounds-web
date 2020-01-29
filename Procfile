release: ./mvnw -f hg-core liquibase:update
release: ./dbdata/initdb.sh
web: java $JAVA_OPTS -jar hg-web/target/*.jar
