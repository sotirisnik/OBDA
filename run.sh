#!/bin/sh -e

mkdir -p output
chmod 777 output

echo 'setup and mipmap_db i2b2_db_harmonized'
docker-compose up -d mipmap_db i2b2_db_harmonized #i2b2_db 

echo 'setup and mipmap_db i2b2_db_harmonized'
docker-compose up wait_dbs
#docker-compose run --rm i2b2_setup
echo 'remove i2b2_setup_harmonized'
docker-compose run --rm i2b2_setup_harmonized
docker-compose up mipmap_etl
