# Database of Ukrainian Hunting Grounds

Requirements:

* Java 11
* PostgreSQL 12

Installing:

Set next environment variables before application start:

* PORT - Application port. Default: 8080
* JDBC_DATABASE_URL - Database name. Default: jdbc:postgresql://localhost:5432/hg
* JDBC_DATABASE_USERNAME - Database username. Default: postgres
* JDBC_DATABASE_PASSWORD - Database password. Default: postgres
* GOOGLE_MAPS_API_KEY - Api Key for an interactive map. Provider: Google

## Csv Loader

To upload grounds into the database you can use several requests.

### First method

Using these requests you can send csv files in request body:

`POST {host}/api/batch/create` - responsible for creation new grounds

`POST {host}/api/batch/update` - responsible for updating existing grounds by alias

```
curl --location --request POST '{host}/api/batch/create' \
--form 'file=@"/C:/{project_root}/dbdata/create-1.csv"' \
--form 'file=@"/C:/{project_root}/dbdata/create-2.csv"'

curl --location --request POST '{host}/api/batch/update' \
--form 'file=@"/C:/{project_root}/dbdata/update-1.csv"' \
--form 'file=@"/C:/{project_root}/dbdata/update-2.csv"'
```

### Second method

These requests will take csv files for processing from directory `"{project_root}/dbdata/to_process/"`

`POST {host}/api/batch/createJob` - responsible for creation new grounds

`POST {host}/api/batch/updateJob` - responsible for updating existing grounds by alias

Filename pattern should be next:

`create.*.csv` - is taken by `createJob`

`update.*.csv` - is taken by `updateJob`


```
curl --location --request POST '{host}/api/batch/createJob'

curl --location --request POST '{host}/api/batch/updateJob'
```

### Csv file structure

First line is header. Second and others - grounds data.

Required header fields: `alias, name, countryId, regionId`

Other available header fields: `area, kmlPath, description, city, street, zipCode, latitude, longitude, info, subRegionId`

Example:
```
name,alias,area,kmlPath,description,city,street,zipCode,latitude,longitude,info,countryId,regionId,subRegionId
"Hunting Ground Name",hunting-ground-alias,50000,/path_to_kml_file.kml,Hunting ground description,City,Street and Building number,15000,50.123456,30.123456,Additional address info,1,2,3
"Hunting Ground Name",hunting-ground-alias,50000,/path_to_kml_file.kml,Hunting ground description,City,Street and Building number,15000,50.123456,30.123456,Additional address info,1,2,3
```

Successfully processed csv files will be moved to the directory `"{project_root}/dbdata/processed/"`

Failed files will stay in folders `"{project_root}/dbdata/to_process/"` and `"{project_root}/dbdata/web/"`
