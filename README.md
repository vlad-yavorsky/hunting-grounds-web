# Hunting Grounds

Database of Ukrainian Hunting Grounds

Requirements:
* Java 13
* PostgreSQL 12

Installing:

Before database initialization set the next environment variables:
* JDBC_DATABASE_USERNAME, PGUSER - Database name. Default: postgres
* JDBC_DATABASE_PASSWORD, PGPASSWORD - Database name. Default: postgres
* PGDATABASE - Database name. Default: hg
* JDBC_DATABASE_URL - Database name. Default: jdbc:postgresql://localhost:5432/hg
* DBDATA_PATH - path to /dbdata folder
* GOOGLE_MAPS_API_KEY - Api Key for front end maps. Provider: Google
* PORT - Application port. Default: 8080
