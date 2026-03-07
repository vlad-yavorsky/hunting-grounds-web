# Database of Ukrainian Hunting Grounds

Application for managing and storing information about Ukrainian hunting grounds.

---

# Requirements

* **Java 21**
* **PostgreSQL 15**

---

# Installation

Before starting the application, configure the following environment variables.

| Variable                 | Description                                                 | Default                               |
| ------------------------ | ----------------------------------------------------------- | ------------------------------------- |
| `PORT`                   | Application port                                            | `8080`                                |
| `JDBC_DATABASE_URL`      | PostgreSQL connection URL                                   | `jdbc:postgresql://localhost:5432/hg` |
| `JDBC_DATABASE_USERNAME` | Database username                                           | `postgres`                            |
| `JDBC_DATABASE_PASSWORD` | Database password                                           | `postgres`                            |
| `GOOGLE_MAPS_API_KEY`    | Google Maps API key used for displaying the interactive map | —                                     |

# Default Users

| Login   | Password |
| ------- | -------- |
| `admin` | `admin`  |
| `user`  | `user`   |

---

# Website Routes

The application provides both a **public map interface** and an **administrator panel**.

| Path                          | Description                                                         |
| ----------------------------- | ------------------------------------------------------------------- |
| `/`                           | Public page displaying an interactive map with hunting grounds      |
| `/administrator`              | Administrator dashboard                                             |
| `/administrator/login`        | Administrator login page                                            |
| `/administrator/grounds`      | Admin panel page with a list of hunting grounds and CRUD operations |
| `/administrator/batch/upload` | Page for uploading CSV files for batch processing                   |

---

# CSV Loader

The application supports **batch importing hunting grounds from CSV files**.

Supported operations:

* `CREATE`
* `UPDATE`
* `DELETE`

There are **three ways to import CSV files**.

---

# 1. Upload CSV via API

Send one or multiple CSV files in the request body.

```bash
curl -X POST '{host}/api/batch' \
-F 'file=@/path/to/batch_1.csv' \
-F 'file=@/path/to/batch_2.csv'
```

---

# 2. Process CSV Files From Directory

The application can automatically process CSV files placed in the following directory:

```
{project_root}/batch/to_process/
```

Trigger processing with:

```bash
curl -X POST '{host}/api/batchJob'
```

**Filename pattern**

```
*.csv
```

## 3. Upload CSV via Web Interface

CSV files can also be uploaded through the **administrator web interface**.

1. Log in to the administrator panel:

```
/administrator/login
```

2. Navigate to the batch upload page:

```
/administrator/batch/upload
```

3. Upload CSV files using the provided form and start processing.


---

# CSV File Format

The first row must contain headers. All subsequent rows contain hunting ground data.

| Field         | Type                                | Required              |
| ------------- | ----------------------------------- | --------------------- |
| `operation`   | Enum (`CREATE`, `UPDATE`, `DELETE`) | yes                   |
| `alias`       | String                              | yes                   |
| `name`        | String                              | required for `CREATE` |
| `area`        | BigDecimal                          | no                    |
| `kmlPath`     | String                              | no                    |
| `description` | String                              | no                    |
| `city`        | String                              | no                    |
| `street`      | String                              | no                    |
| `zipCode`     | String                              | no                    |
| `latitude`    | BigDecimal                          | no                    |
| `longitude`   | BigDecimal                          | no                    |
| `info`        | String                              | no                    |
| `countryId`   | Long                                | required for `CREATE` |
| `regionId`    | Long                                | required for `CREATE` |
| `subRegionId` | Long                                | no                    |

---

# Example CSV

```csv
operation,alias,name,area,kmlPath,description,city,street,zipCode,latitude,longitude,info,countryId,regionId,subRegionId
CREATE,hunting-ground-alias,"Hunting Ground Name",50000,/path_to_kml_file.kml,Hunting ground description,City,Street and Building number,15000,50.123456,30.123456,Additional address info,1,2,3
UPDATE,hunting-ground-alias,"Hunting Ground Name",50000,/path_to_kml_file.kml,Hunting ground description,City,Street and Building number,15000,50.123456,30.123456,Additional address info,1,2,3
DELETE,hunting-ground-alias,,,,,,,,,,,,,
```

---

# File Processing

After processing:

**Successfully processed files are moved to**

```
{project_root}/batch/processed/
```

**Failed files remain in**

```
{project_root}/batch/to_process/
{project_root}/batch/web/
```

These files can be corrected and reprocessed.
