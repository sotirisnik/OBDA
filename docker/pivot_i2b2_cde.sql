create or replace function pivotfunction() returns void as '
Declare concept text;
Declare valtype text;
Declare type_name text;
Declare query_cde text;
Declare query_non_cde text;
Declare table_name_cde text;
Declare table_name text;
Declare char_val text;
Declare num_val numeric(18,5);
Declare usedid_cde text;
Declare usedid_non_cde text;
Declare currentid text;
Declare sex text;
Declare year integer;
Declare startdate timestamp without time zone;
Declare subjectcode integer;
Declare subjectageyears integer;
Declare subjectage numeric(18,5);
Declare gender text;
Declare dataset text;
Declare agegroup text;
BEGIN
query_non_cde := '''';
usedid_cde := '''';
usedid_non_cde := '''';

-- create table for cde variables - the list of variables in this table are fixed (cde variables)
table_name_cde := ''new_table_cde'';
query_cde := ''CREATE TABLE '' || table_name_cde || ''( "subjectcode" integer, "subjectageyears" text, "subjectage" text, "gender" text, "3rdventricle" text, "4thventricle" text, "rightaccumbensarea" text, "leftaccumbensarea" text, "rightamygdala" text, "leftamygdala" text, "brainstem" text, "rightcaudate" text, "leftcaudate" text, "rightcerebellumexterior" text, "leftcerebellumexterior" text, "rightcerebellumwhitematter" text, "leftcerebellumwhitematter" text, "rightcerebralwhitematter" text, "leftcerebralwhitematter" text, "csfglobal" text, "righthippocampus" text, "lefthippocampus" text, "rightinflatvent" text, "leftinflatvent" text, "rightlateralventricle" text, "leftlateralventricle" text, "rightpallidum" text, "leftpallidum" text, "rightputamen" text, "leftputamen" text, "rightthalamusproper" text, "leftthalamusproper" text, "rightventraldc" text, "leftventraldc" text, "opticchiasm" text, "cerebellarvermallobulesiv" text, "cerebellarvermallobulesvivii" text, "cerebellarvermallobulesviiix" text, "leftbasalforebrain" text, "rightbasalforebrain" text, "rightacgganteriorcingulategyrus" text, "leftacgganteriorcingulategyrus" text, "rightainsanteriorinsula" text, "leftainsanteriorinsula" text, "rightaorganteriororbitalgyrus" text, "leftaorganteriororbitalgyrus" text, "rightangangulargyrus" text, "leftangangulargyrus" text, "rightcalccalcarinecortex" text, "leftcalccalcarinecortex" text, "rightcocentraloperculum" text, "leftcocentraloperculum" text, "rightcuncuneus" text, "leftcuncuneus" text, "rightententorhinalarea" text, "leftententorhinalarea" text, "rightfofrontaloperculum" text, "leftfofrontaloperculum" text, "rightfrpfrontalpole" text, "leftfrpfrontalpole" text, "rightfugfusiformgyrus" text, "leftfugfusiformgyrus" text, "rightgregyrusrectus" text, "leftgregyrusrectus" text, "rightioginferioroccipitalgyrus" text, "leftioginferioroccipitalgyrus" text, "rightitginferiortemporalgyrus" text, "leftitginferiortemporalgyrus" text, "rightliglingualgyrus" text, "leftliglingualgyrus" text, "rightlorglateralorbitalgyrus" text, "leftlorglateralorbitalgyrus" text, "rightmcggmiddlecingulategyrus" text, "leftmcggmiddlecingulategyrus" text, "rightmfcmedialfrontalcortex" text, "leftmfcmedialfrontalcortex" text, "rightmfgmiddlefrontalgyrus" text, "leftmfgmiddlefrontalgyrus" text, "rightmogmiddleoccipitalgyrus" text, "leftmogmiddleoccipitalgyrus" text, "rightmorgmedialorbitalgyrus" text, "leftmorgmedialorbitalgyrus" text, "rightmpogpostcentralgyrusmedialsegment" text, "leftmpogpostcentralgyrusmedialsegment" text, "rightmprgprecentralgyrusmedialsegment" text, "leftmprgprecentralgyrusmedialsegment" text, "rightmsfgsuperiorfrontalgyrusmedialsegment" text, "leftmsfgsuperiorfrontalgyrusmedialsegment" text, "rightmtgmiddletemporalgyrus" text, "leftmtgmiddletemporalgyrus" text, "rightocpoccipitalpole" text, "leftocpoccipitalpole" text, "rightofugoccipitalfusiformgyrus" text, "leftofugoccipitalfusiformgyrus" text, "rightopifgopercularpartoftheinferiorfrontalgyrus" text, "leftopifgopercularpartoftheinferiorfrontalgyrus" text, "rightorifgorbitalpartoftheinferiorfrontalgyrus" text, "leftorifgorbitalpartoftheinferiorfrontalgyrus" text, "rightpcggposteriorcingulategyrus" text, "leftpcggposteriorcingulategyrus" text, "rightpcuprecuneus" text, "leftpcuprecuneus" text, "rightphgparahippocampalgyrus" text, "leftphgparahippocampalgyrus" text, "rightpinsposteriorinsula" text, "leftpinsposteriorinsula" text, "rightpoparietaloperculum" text, "leftpoparietaloperculum" text, "rightpogpostcentralgyrus" text, "leftpogpostcentralgyrus" text, "rightporgposteriororbitalgyrus" text, "leftporgposteriororbitalgyrus" text, "rightppplanumpolare" text, "leftppplanumpolare" text, "rightprgprecentralgyrus" text, "leftprgprecentralgyrus" text, "rightptplanumtemporale" text, "leftptplanumtemporale" text, "rightscasubcallosalarea" text, "leftscasubcallosalarea" text, "rightsfgsuperiorfrontalgyrus" text, "leftsfgsuperiorfrontalgyrus" text, "rightsmcsupplementarymotorcortex" text, "leftsmcsupplementarymotorcortex" text, "rightsmgsupramarginalgyrus" text, "leftsmgsupramarginalgyrus" text, "rightsogsuperioroccipitalgyrus" text, "leftsogsuperioroccipitalgyrus" text, "rightsplsuperiorparietallobule" text, "leftsplsuperiorparietallobule" text, "rightstgsuperiortemporalgyrus" text, "leftstgsuperiortemporalgyrus" text, "righttmptemporalpole" text, "lefttmptemporalpole" text, "righttrifgtriangularpartoftheinferiorfrontalgyrus" text, "lefttrifgtriangularpartoftheinferiorfrontalgyrus" text, "rightttgtransversetemporalgyrus" text, "leftttgtransversetemporalgyrus" text, "montrealcognitiveassessment" text, "minimentalstate" text, "agegroup" text, "handedness" text, "updrstotal" text, "updrshy" text, "adnicategory" text, "edsdcategory" text, "ppmicategory" text, "alzheimerbroadcategory" text, "parkinsonbroadcategory" text, "neurogenerativescategories" text, "dataset" text, "apoe4" text, "rs3818361_t" text, "rs744373_c" text, "rs190982_g" text, "rs1476679_c" text, "rs11767557_c" text, "rs11136000_t" text, "rs610932_a" text, "rs3851179_a" text, "rs17125944_c" text, "rs10498633_t" text, "rs3764650_g" text, "rs3865444_t" text, "rs2718058_g" text, "fdg" text, "pib" text, "av45" text, PRIMARY KEY (subjectcode))'';
-- execute statement
execute format(''DROP TABLE IF EXISTS '' || table_name_cde);
execute format(query_cde);


-- collect all hospital specific concept_cds in order to obtain the extra variables
table_name := ''new_table'';
query_non_cde := ''CREATE TABLE '' || table_name || ''( "subjectcode" integer '';
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
if query_non_cde = '''' then
	query_non_cde := query_non_cde || ''"'' || concept || ''"'' || type_name ;
else
	-- if the concept_cd is not in cde
	if query_cde not like (''%"'' || concept || ''"%'') then
		-- if the concept_cd has not been inserted before
		if query_non_cde not like (''%"'' || concept || ''"%'') then
			-- add it in the create statement
			query_non_cde := query_non_cde || '','' || ''"'' || concept || ''"'' || type_name ;
		end if;
	end if;
end if;
end loop;
-- add primary key
query_non_cde := query_non_cde || '', PRIMARY KEY (subjectcode))'';

-- execute statement
execute format(''DROP TABLE IF EXISTS '' || table_name);
execute format(query_non_cde);

for subjectcode, startdate, concept, valtype, char_val, num_val, subjectageyears, subjectage, gender, dataset in 
	select observation_fact.patient_num, observation_fact.start_date, observation_fact.concept_cd, observation_fact.valtype_cd, observation_fact.tval_char, observation_fact.nval_num, round(visit_dimension.patient_age), visit_dimension.patient_age, patient_dimension.sex_cd , observation_fact.provider_id
	from observation_fact, patient_dimension, visit_dimension, (select v.patient_num, v.encounter_num from (select patient_num, min(patient_age) mindate from visit_dimension group by patient_num) as foo, visit_dimension as v where foo.patient_num = v.patient_num and foo.mindate = v.patient_age) as encounter_num_with_min_age where observation_fact.patient_num=patient_dimension.patient_num and encounter_num_with_min_age.encounter_num = observation_fact.encounter_num and encounter_num_with_min_age.encounter_num = visit_dimension.encounter_num and encounter_num_with_min_age.patient_num = observation_fact.patient_num order by patient_num
LOOP
currentid := '','' || subjectcode || '','';

CASE 
	WHEN subjectageyears is null
		THEN agegroup = null;
	WHEN 50 <= subjectageyears and subjectageyears < 60
		THEN agegroup = ''50-59y'';
	WHEN 60 <= subjectageyears and subjectageyears < 70
		THEN agegroup = ''60-69y'';
	WHEN 70 <= subjectageyears and subjectageyears < 90
		THEN agegroup = ''70-79y'';
	WHEN 80 <= subjectageyears
		THEN agegroup = ''+80y'';
	ELSE
		agegroup = ''-50y'';
END CASE;

CASE
    WHEN ''T''=valtype 
    THEN
    	-- in case we deal with a variable that is in the cde variables
    	if query_cde like (''%"'' || concept || ''"%'') then
			IF coalesce(TRIM(char_val), '''') <> '''' AND char_val IS NOT NULL THEN
				IF ( usedid_cde ~ currentid ) then
					execute format(''update '' || table_name_cde  || '' set '' || ''"'' || concept || ''" = '''''' ||  char_val || '''''' where subjectcode = '' || subjectcode );
				ELSE
					execute format(''insert into '' || table_name_cde  || ''( subjectcode, subjectageyears, subjectage, gender, dataset, agegroup, '' || ''"'' || concept || ''"'' || '') VALUES ('' || subjectcode || '','' || subjectageyears || '','' || subjectage || '','''''' || gender || '''''','''''' || dataset || '''''','''''' || agegroup || '''''','''''' ||  char_val || '''''')'');
					usedid_cde := usedid_cde || '','' || subjectcode || '','';
				END IF;
			END IF;
		else -- we have to deal with an extra variable
			IF coalesce(TRIM(char_val), '''') <> '''' AND char_val IS NOT NULL THEN
				IF ( usedid_non_cde ~ currentid ) then
					execute format(''update '' || table_name  || '' set '' || ''"'' || concept || ''" = '''''' ||  char_val || '''''' where subjectcode = '' || subjectcode );
				ELSE
					execute format(''insert into '' || table_name  || ''( subjectcode, '' || ''"'' || concept || ''"'' || '') VALUES ('' || subjectcode || '','''''' ||  char_val || '''''')'');
					usedid_non_cde := usedid_non_cde || '','' || subjectcode || '','';
				END IF;
			END IF;
		end if;		
    ELSE 
    	-- in case we deal with a variable that is in the cde variables
    	if query_cde like (''%"'' || concept || ''"%'') then
			IF num_val IS NOT NULL THEN
				IF ( usedid_cde ~ currentid) THEN
				execute format(''UPDATE '' || table_name_cde  || '' set '' || ''"'' || concept || ''" = '' ||  num_val || '' where subjectcode = '' || subjectcode );
				ELSE
				execute format(''INSERT INTO '' || table_name_cde  || ''( subjectcode, subjectageyears, subjectage, gender, dataset, agegroup, '' || ''"'' || concept || ''"'' || '') VALUES ('' || subjectcode || '','' || subjectageyears || '','' || subjectage || '','''''' || gender || '''''','''''' || dataset || '''''','''''' || agegroup || '''''','''''' ||  num_val || '''''')'');
				usedid_cde := usedid_cde || '','' || subjectcode ||'','';
				END IF;
			END IF;
		else -- we have to deal with an extra variable
			IF num_val IS NOT NULL THEN
				IF ( usedid_non_cde ~ currentid) THEN
				execute format(''UPDATE '' || table_name  || '' set '' || ''"'' || concept || ''" = '' ||  num_val || '' where subjectcode = '' || subjectcode );
				ELSE
				execute format(''INSERT INTO '' || table_name  || ''( subjectcode, '' || ''"'' || concept || ''"'' || '') VALUES ('' || subjectcode || '','''''' ||  num_val || '''''')'');
				usedid_non_cde := usedid_non_cde || '','' || subjectcode ||'','';
				END IF;
			END IF;
		end if;
END CASE;
END LOOP;
BEGIN
	COPY new_table_cde FROM ''/tmp/clm_data_cde.csv'' DELIMITER '','' CSV HEADER ; 
	COPY new_table FROM ''/tmp/clm_data_other.csv'' DELIMITER '','' CSV HEADER ; 
EXCEPTION
WHEN OTHERS 
        THEN raise notice ''One of the files does not exist'';
END;
COPY (SELECT * FROM new_table_cde) TO ''/tmp/clm_data_cde.csv'' WITH CSV DELIMITER '','' HEADER;
COPY (SELECT * FROM new_table) TO ''/tmp/clm_data_other.csv'' WITH CSV DELIMITER '','' HEADER;

EXECUTE FORMAT(''DROP TABLE IF EXISTS '' || table_name_cde);
EXECUTE FORMAT(''DROP TABLE IF EXISTS '' || table_name);

END' language plpgsql;
select pivotfunction();
