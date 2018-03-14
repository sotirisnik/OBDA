#!/bin/sh -e

mkdir -p output
chmod 777 output

echo "running mipmap_db and i2b2_db_harmonized"
docker-compose up -d mipmap_db i2b2_db_harmonized

echo "running wait_dbs"
docker-compose up wait_dbs
#sudo docker-compose run --rm i2b2_setup
echo "removing i2b2_setup_harmonized container"
docker-compose run --rm i2b2_setup_harmonized
echo "running mipmap_etl"
docker-compose up mipmap_etl
