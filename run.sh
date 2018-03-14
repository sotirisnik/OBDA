#!/bin/sh -e

mkdir -p output
chmod 777 output

sudo docker-compose up -d mipmap_db i2b2_db_harmonized

sudo docker-compose up wait_dbs
#sudo docker-compose run --rm i2b2_setup
sudo docker-compose run --rm i2b2_setup_harmonized
sudo docker-compose up mipmap_etl
