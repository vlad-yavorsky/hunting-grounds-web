# Database of Ukrainian Hunting Grounds

**Requirements:**

* Java 11
* PostgreSQL 12

**Installing:**

**Set next environment variables before application start:**

* PORT - Application port. Default: 8080
* JDBC_DATABASE_URL - Database name. Default: jdbc:postgresql://localhost:5432/hg
* JDBC_DATABASE_USERNAME - Database username. Default: postgres
* JDBC_DATABASE_PASSWORD - Database password. Default: postgres
* GOOGLE_MAPS_API_KEY - Api Key for an interactive map. Provider: Google

## Csv Loader

To import new grounds (also modify or delete) you can use 2 requests

**First request can send csv files in request body**

```
curl -X POST '{host}/api/batch' \
-F 'file=@/path_to_batch_1.csv' \
-F 'file=@/path_to_batch_2.csv'
```

**Second request will take csv files for processing from directory `{project_root}/batch/to_process/`**

```
curl -X POST '{host}/api/batchJob'
```

Filename pattern should be next: `.*.csv`

### Csv file structure

First line is header. Second and others - grounds data.

Header Field | Data Type | Required
--- | --- | ---
operation | Enum<br />Values: CREATE, UPDATE, DELETE | yes
alias | String | yes
name | String | yes - for CREATE operation<br/>no - for other operations
area | BigDecimal | no
kmlPath | String | no
description | String | no
city | String | no
street | String | no
zipCode | String | no
latitude | BigDecimal | no
longitude | BigDecimal | no
info | String | no
countryId | Long | yes - for CREATE operation<br/>no - for other operations
regionId | Long | yes - for CREATE operation<br/>no - for other operations
subRegionId | Long | no

Example:
```
operation,alias,name,area,kmlPath,description,city,street,zipCode,latitude,longitude,info,countryId,regionId,subRegionId
CREATE,hunting-ground-alias,"Hunting Ground Name",50000,/path_to_kml_file.kml,Hunting ground description,City,Street and Building number,15000,50.123456,30.123456,Additional address info,1,2,3
UPDATE,hunting-ground-alias,"Hunting Ground Name",50000,/path_to_kml_file.kml,Hunting ground description,City,Street and Building number,15000,50.123456,30.123456,Additional address info,1,2,3
DELETE,hunting-ground-alias,,,,,,,,,,,,,
```

Successfully processed csv files will be moved to the directory `{project_root}/batch/processed/`

Failed files will stay in folders `{project_root}/batch/to_process/` and `{project_root}/batch/web/`
