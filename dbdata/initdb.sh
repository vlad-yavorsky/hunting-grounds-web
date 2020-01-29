#!/bin/bash

echo "1. Inserting Roles and Users"
psql --dbname="$PGDATABASE" --echo-errors --file="$DBDATA_PATH/0_roles.sql"
echo "2. Inserting Regions"
psql --dbname="$PGDATABASE" --echo-errors --file="$DBDATA_PATH/1_regions.sql"
echo "3. Inserting Addresses"
psql --dbname="$PGDATABASE" --echo-errors --file="$DBDATA_PATH/2_addresses.sql"
echo "4. Inserting Grounds"
sh "$DBDATA_PATH/3_grounds.sh"
