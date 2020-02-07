# Hunting Grounds

Database of Ukrainian Hunting Grounds

Requirements:
* Java 13
* PostgreSQL 12

Installing:

Before liquibase update command execution or application run set next environment variables:
* PORT - Application port. Default: 8080
* JDBC_DATABASE_URL - Database name. Default: jdbc:postgresql://localhost:5432/hg
* JDBC_DATABASE_USERNAME - Database user name. Default: postgres
* JDBC_DATABASE_PASSWORD - Database user password. Default: postgres
* GOOGLE_MAPS_API_KEY - Api Key for an interactive map. Provider: Google

For dbdata scripts execution (psql command) set next environment variables:
* DBDATA_PATH - path to /dbdata folder
* PGHOST - Postgres database host. Default: localhost
* PGDATABASE - Postgres database name. Default: hg
* PGUSER - Postgres database user name. Default: postgres
* PGPASSWORD - Postgres database user password. Default: postgres
