release: ./mvnw -f hg-core liquibase:update
release: bash ./dbdata/initdb.sh
web: java $JAVA_OPTS -jar hg-web/target/*.jar
