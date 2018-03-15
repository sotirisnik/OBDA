FROM hbpmip/mipmap:latest
MAINTAINER Vasilis Vassalos <vassalos@aueb.gr>

ARG BUILD_DATE

#ARG VCS_REF
LABEL org.label-schema.build-date=$BUILD_DATE \
    org.label-schema.name="hbpmip/mipmap-demo-ehr-to-i2b2" \
    org.label-schema.description="MIPMap configured to transform EHR demo data to I2B2" \
    org.label-schema.url="https://github.com/HBPSP8Repo/MIPMap-demo-ehr-to-i2b2" \
    org.label-schema.vcs-type="git" \
    #org.label-schema.vcs-ref=$VCS_REF \
    org.label-schema.vcs-url="https://github.com/HBPSP8Repo/MIPMap-demo-ehr-to-i2b2" \
    org.label-schema.vendor="WIM AUEB" \
    org.label-schema.docker.dockerfile="Dockerfile" \
    org.label-schema.schema-version="1.0"

COPY docker/map.xml.tmpl docker/mipmap-db.properties.tmpl docker/i2b2-db.properties.tmpl docker/i2b2-db-harmonized.properties.tmpl docker/mapHarmonize.xml.tmpl docker/selectedMR.txt docker/unpivotMR.txt docker/selectedEpisode.txt docker/unpivotEpisode.txt docker/selectedImaging.txt docker/unpivotImaging.txt docker/pivot_i2b2_cde_SF_MinDate_NEW2.sql /opt/
COPY docker/run.sh /run.sh
COPY docker/encountermapping.properties docker/patientmapping.properties /opt/

#USER docker
CMD ["/run.sh"]
#CMD ["su", "-", "user", "-c", "/bin/bash"]
#CMD sudo sh run.sh
