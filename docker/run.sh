#!/bin/sh

dockerize -template /opt/map.xml.tmpl:/opt/map.xml
dockerize -template /opt/mapHarmonize.xml.tmpl:/opt/mapHarmonize.xml
dockerize -template /opt/mipmap-db.properties.tmpl:/opt/mipmap-db.properties
dockerize -template /opt/i2b2-db.properties.tmpl:/opt/i2b2-db.properties
dockerize -template /opt/i2b2-db-harmonized.properties.tmpl:/opt/i2b2-db-harmonized.properties


cd /opt

echo 'Unpivoting Medical_report.csv'
java -jar MIPMapReduced.jar -unpivot /opt/source/Medical_report.csv /opt/mipmap-db.properties "attribute" /opt/selectedMR.txt -u /opt/unpivotMR.txt

echo 'Unpivoting Episode.csv'
java -jar MIPMapReduced.jar -unpivot /opt/source/Episode.csv /opt/mipmap-db.properties "attribute" /opt/selectedEpisode.txt -u /opt/unpivotEpisode.txt

echo 'Unpivoting Imaging.csv'
java -jar MIPMapReduced.jar -unpivot /opt/source/Imaging.csv /opt/mipmap-db.properties "attribute" /opt/selectedImaging.txt -u /opt/unpivotImaging.txt

echo 'generating patient_num and encounter_num'
java -jar MIPMapReduced.jar -generate_id patientmapping.properties

java -jar MIPMapReduced.jar -generate_id encountermapping.properties

echo 'Running execution' 

java -jar MIPMapReduced.jar /opt/map.xml /opt/mipmap-db.properties -db /opt/i2b2-db.properties

#echo 'Done with the map_test'

#java -jar MIPMapReduced.jar /opt/mapHarmonize_test.xml /opt/mipmap-db.properties -db /opt/i2b2-db-harmonized.properties

#echo 'Done with the mapHarmonize_test'

#java -jar MIPMapReduced.jar -runsql /opt/pivot_i2b2_cde_SF_MinDate_NEW2.sql /opt/i2b2-db-harmonized.properties

#echo 'Done pivoting... Made the flat csv... All good...'
	
