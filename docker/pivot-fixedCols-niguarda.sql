create or replace function pivotfunction() returns void as '
DECLARE concept text;
DECLARE valtype text;
DECLARE type_name text;
DECLARE query text;
DECLARE table_name text;
DECLARE char_val text;
DECLARE num_val numeric(18,5);
DECLARE usedid text;
DECLARE currentid text;
DECLARE sex text;
DECLARE year integer;
DECLARE startdate timestamp WITHOUT TIME ZONE;
DECLARE pid integer;
DECLARE subjectageyears integer;
DECLARE subjectagemonths integer;
DECLARE gender text;
BEGIN
query := '''';
usedid := '''';
table_name := ''new_table'';


query := ''CREATE TABLE '' || table_name || ''( pid integer, "rightinflatvent" text,"leftinflatvent" text,"rightlateralventricle" text,"leftlateralventricle" text,"3rdventricle" text,"4thventricle" text,"csfglobal" text,"rightcerebellumwhitematter" text,"leftcerebellumwhitematter" text,"rightcerebralwhitematter" text,"leftcerebralwhitematter" text,"opticchiasm" text,"cerebellarvermallobulesviiix" text,"cerebellarvermallobulesvivii" text,"cerebellarvermallobulesiv" text,"leftcerebellumexterior" text,"rightcerebellumexterior" text,"rightbasalforebrain" text,"leftbasalforebrain" text,"rightaccumbensarea" text,"leftaccumbensarea" text,"rightcaudate" text,"leftcaudate" text,"rightpallidum" text,"leftpallidum" text,"rightputamen" text,"leftputamen" text,"rightamygdala" text,"leftamygdala" text,"righthippocampus" text,"lefthippocampus" text,"rightthalamusproper" text,"leftthalamusproper" text,"rightacgganteriorcingulategyrus" text,"leftacgganteriorcingulategyrus" text,"rightententorhinalarea" text,"leftententorhinalarea" text,"rightmcggmiddlecingulategyrus" text,"leftmcggmiddlecingulategyrus" text,"rightpcggposteriorcingulategyrus" text,"leftpcggposteriorcingulategyrus" text,"rightphgparahippocampalgyrus" text,"leftphgparahippocampalgyrus" text,"rightfugfusiformgyrus" text,"leftfugfusiformgyrus" text,"rightitginferiortemporalgyrus" text,"leftitginferiortemporalgyrus" text,"rightmtgmiddletemporalgyrus" text,"leftmtgmiddletemporalgyrus" text,"rightppplanumpolare" text,"leftppplanumpolare" text,"rightptplanumtemporale" text,"leftptplanumtemporale" text,"rightstgsuperiortemporalgyrus" text,"leftstgsuperiortemporalgyrus" text,"righttmptemporalpole" text,"lefttmptemporalpole" text,"rightttgtransversetemporalgyrus" text,"leftttgtransversetemporalgyrus" text,"rightcalccalcarinecortex" text,"leftcalccalcarinecortex" text,"rightcuncuneus" text,"leftcuncuneus" text,"rightioginferioroccipitalgyrus" text,"leftioginferioroccipitalgyrus" text,"rightliglingualgyrus" text,"leftliglingualgyrus" text,"rightmogmiddleoccipitalgyrus" text,"leftmogmiddleoccipitalgyrus" text,"rightocpoccipitalpole" text,"leftocpoccipitalpole" text,"rightofugoccipitalfusiformgyrus" text,"leftofugoccipitalfusiformgyrus" text,"rightsogsuperioroccipitalgyrus" text,"leftsogsuperioroccipitalgyrus" text,"rightangangulargyrus" text,"leftangangulargyrus" text,"rightmpogpostcentralgyrusmedialsegment" text,"leftmpogpostcentralgyrusmedialsegment" text,"rightpcuprecuneus" text,"leftpcuprecuneus" text,"rightpogpostcentralgyrus" text,"leftpogpostcentralgyrus" text,"rightsmgsupramarginalgyrus" text,"leftsmgsupramarginalgyrus" text,"rightsplsuperiorparietallobule" text,"leftsplsuperiorparietallobule" text,"rightaorganteriororbitalgyrus" text,"leftaorganteriororbitalgyrus" text,"rightcocentraloperculum" text,"leftcocentraloperculum" text,"rightfofrontaloperculum" text,"leftfofrontaloperculum" text,"rightfrpfrontalpole" text,"leftfrpfrontalpole" text,"rightgregyrusrectus" text,"leftgregyrusrectus" text,"rightlorglateralorbitalgyrus" text,"leftlorglateralorbitalgyrus" text,"rightmfcmedialfrontalcortex" text,"leftmfcmedialfrontalcortex" text,"rightmfgmiddlefrontalgyrus" text,"leftmfgmiddlefrontalgyrus" text,"rightmorgmedialorbitalgyrus" text,"leftmorgmedialorbitalgyrus" text,"rightmprgprecentralgyrusmedialsegment" text,"leftmprgprecentralgyrusmedialsegment" text,"rightmsfgsuperiorfrontalgyrusmedialsegment" text,"leftmsfgsuperiorfrontalgyrusmedialsegment" text,"rightopifgopercularpartoftheinferiorfrontalgyrus" text,"leftopifgopercularpartoftheinferiorfrontalgyrus" text,"rightorifgorbitalpartoftheinferiorfrontalgyrus" text,"leftorifgorbitalpartoftheinferiorfrontalgyrus" text,"rightpoparietaloperculum" text,"leftpoparietaloperculum" text,"rightporgposteriororbitalgyrus" text,"leftporgposteriororbitalgyrus" text,"rightprgprecentralgyrus" text,"leftprgprecentralgyrus" text,"rightscasubcallosalarea" text,"leftscasubcallosalarea" text,"rightsfgsuperiorfrontalgyrus" text,"leftsfgsuperiorfrontalgyrus" text,"rightsmcsupplementarymotorcortex" text,"leftsmcsupplementarymotorcortex" text,"righttrifgtriangularpartoftheinferiorfrontalgyrus" text,"lefttrifgtriangularpartoftheinferiorfrontalgyrus" text,"rightainsanteriorinsula" text,"leftainsanteriorinsula" text,"rightpinsposteriorinsula" text,"leftpinsposteriorinsula" text,"rightventraldc" text,"leftventraldc" text,"tiv" text,"brainstem" text,"subjectageyears" text,"subjectagemonths" text,"agegroup" text,"gender" text,"handedness" text,"clmcategory" text,"alzheimerbroadcategory" text,"parkinsonbroadcategory" text,"neurogenerativescategories" text,"adnicategory" text,"edsdcategory" text,"ppmicategory" text,"minimentalstate" text,"montrealcognitiveassessment" text,"updrstotal" text,"updrshy" text,"dataset" text'';


-- collect all hospital specific concept_cds in order to obtain the available columns
for concept, valtype in 
	select distinct on (concept_cd) concept_cd, valtype_cd 
	from observation_fact where concept_cd != ''-1''
loop
CASE
    WHEN ''T''=valtype 
    THEN 
	type_name := '' text'';
    ELSE 
	type_name := '' numeric(18,5)'';
END case;
if query = '''' then
	query := query || ''"'' || concept || ''"'' || type_name ;
else
	if query !~ (''"'' || concept || ''"'') then
		query := query || '','' || ''"'' || concept || ''"'' || type_name ;
	end if;
end if;
end loop;

query := query || '', PRIMARY KEY (pid))'';


execute format(''DROP TABLE IF EXISTS '' || table_name);
execute format(query);
table_name := ''new_table'';
for pid, startdate, concept, valtype, char_val, num_val, subjectageyears, subjectagemonths, gender in 
	select observation_fact.patient_num, observation_fact.start_date, observation_fact.concept_cd, observation_fact.valtype_cd, observation_fact.tval_char, observation_fact.nval_num, visit_dimension.patient_age, extract(month from patient_dimension.birth_date), patient_dimension.sex_cd  
	from observation_fact, patient_dimension, visit_dimension where observation_fact.patient_num=patient_dimension.patient_num and visit_dimension.encounter_num = observation_fact.encounter_num and concept_cd != ''-1''
LOOP
currentid := '','' || pid || '','';
CASE
    WHEN ''T''=valtype 
    THEN 
	IF coalesce(TRIM(char_val), '''') <> '''' AND char_val IS NOT NULL THEN
		IF ( usedid ~ currentid ) then
		execute format(''update '' || table_name  || '' set '' || ''"'' || concept || ''" = '''''' ||  char_val || '''''' where pid = '' || pid );
		ELSE
		execute format(''insert into '' || table_name  || ''( pid, subjectageyears, subjectagemonths, gender, '' || ''"'' || concept || ''"'' || '') VALUES ('' || pid || '','' || subjectageyears || '','' || subjectagemonths || '','''''' || gender || '''''','''''' ||  char_val || '''''')'');
		usedid := usedid || '','' || pid || '','';
		END IF;
	END IF;
    ELSE 
	IF num_val IS NOT NULL THEN
		IF ( usedid ~ currentid) THEN
		execute format(''UPDATE '' || table_name  || '' set '' || ''"'' || concept || ''" = '' ||  num_val || '' where pid = '' || pid );
		ELSE
		execute format(''INSERT INTO '' || table_name  || ''( pid, subjectageyears, subjectagemonths, gender, '' || ''"'' || concept || ''"'' || '') VALUES ('' || pid || '','' || subjectageyears || '','' || subjectagemonths || '','''''' || gender || '''''','''''' ||  num_val || '''''')'');
		usedid := usedid || '','' || pid ||'','';
		END IF;
	END IF;
END CASE;

END LOOP;

BEGIN
	COPY new_table FROM ''/tmp/harmonised_clinical_data.csv'' DELIMITER '','' CSV HEADER ; 
EXCEPTION
WHEN OTHERS 
        THEN raise notice ''No such file /tmp/harmonised_clinical_data.csv'';
END;
COPY (SELECT * FROM new_table) TO ''/tmp/harmonised_clinical_data.csv'' WITH CSV DELIMITER '','' HEADER;
EXECUTE FORMAT(''DROP TABLE IF EXISTS '' || table_name);
END' language plpgsql;
select pivotfunction();

