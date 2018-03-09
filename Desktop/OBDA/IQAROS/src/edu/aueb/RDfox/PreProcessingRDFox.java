package edu.aueb.RDfox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.log4j.PropertyConfigurator;
import org.oxford.comlab.perfectref.rewriter.Clause;
import org.oxford.comlab.perfectref.rewriter.Term;
import org.oxford.comlab.perfectref.rewriter.TermFactory;
import org.oxford.comlab.perfectref.rewriter.Variable;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLProperty;

import uk.ac.ox.cs.JRDFox.JRDFStoreException;

/**
 * Create clauses that consist of one or two conjuncts and have no answers
 *
 * These will be later used to cut off queries from the UCQ rewriting
 *
 */

public class PreProcessingRDFox {

	private static TermFactory m_termFactory = new TermFactory();

	static String originalPath = "/Users/avenet/Academia/Ntua/Ontologies/";
	static String uri = "";
	static String optimizationPath = "";


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("./logger.properties");

		String ontologyFile, queryPath, optPath, path, dbPath, dataFiles;
		RDFoxQueryEvaluator evaluator = null;

		boolean RDFoxOpt = true;

//		/**
//		 * LUBM Tests
//		 */
//		path = originalPath + "LUBM/";
//		ontologyFile = "file:" + path + "univ-bench-gstoil.owl";
//		queryPath = path + "QueriesWithConstants/";
////		uri = "http://www.lehigh.edu/~zhp2/2004/0401/univ-bench.owl#";
//		/*
//		 * LUBM DB
//		 */
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/LUBM";
//		optPath = path + "OptimizationClauses/optimizationClausesLUBM.txt";
//		dataFiles = "/Users/avenet/Academia/Ntua/JavaWorkspace/uba1.7/Ontos1/";
//		System.out.println("**************************");
//		System.out.println("**\tLUBM\t\t**");
//		System.out.println("**************************");
//		optimizationPath = path + "/OptimizationClausesRDFox/optimizationClauses_noOpt.txt";
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optimizationPath);
//		/*
//		 * LUBM10 DB
//		 */
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/LUBM10"; //This database contains data from 15 universities
//		optPath = path + "OptimizationClauses/optimizationClausesLUBM10.txt";
//		dataFiles = "/Users/avenet/Academia/Ntua/JavaWorkspace/uba1.7/Ontos10/";
//		System.out.println("**************************");
//		System.out.println("**\tLUBM10\t\t**");
//		System.out.println("**************************");
//		optimizationPath = path + "/OptimizationClausesOWLim/optimizationClauses10.txt";
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optimizationPath);
//		/*
//		 * LUBM30 DB
//		 */
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/LUBM30"; //This database contains data from 30 universities
//		optPath = path + "OptimizationClauses/optimizationClausesLUBM30.txt";
//		dataFiles = "/Users/avenet/Academia/Ntua/JavaWorkspace/uba1.7/Ontos30/";
//		System.out.println("**************************");
//		System.out.println("**\tLUBM30\t\t**");
//		System.out.println("**************************");
//		optimizationPath = path + "/OptimizationClausesOWLim/optimizationClauses30.txt";
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optimizationPath);
//		/**
//		 * LUBM-ex Tests
//		 */
//		path = originalPath + "LUBM-ex/";
//		ontologyFile = "file:" + path + "/EUGen/ontologies/lubm-ex-20.owl";
//		queryPath = path + "EUGen/QueriesOWLim/";
//		uri = "";
//		/*
//		 * LUBM-20ex DB
//		 */
//		dataFiles = "/Users/avenet/Academia/Ntua/JavaWorkspace/uba1.7/lubmex20-1Univ/";
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/LUBM_20ex_1";
//		optPath = path + "OptimizationClausesOWLim/OptimizationClauses20ex.txt";
//		System.out.println("**************************");
//		System.out.println("**\tLUBM-ex\t\t**");
//		System.out.println("**************************");
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optPath);
//		/**
//		 * UOBM Tests
//		 */
//		path = originalPath + "UOBM/";
////		ontologyFile = "file:" + path + "univ-bench-dl-Zhou.owl";
//		ontologyFile = "file:" + path + "univ-bench-dl-Zhou_DL-Lite_mine.owl";
//		queryPath = path + "QueriesWithConstants/";
//		optPath = path + "OptimizationClauses/optimizationClausesUOBM.txt";
////		uri = "http://semantics.crl.ibm.com/univ-bench-dl.owl#";
//		dataFiles = path + "UOBMGenerator/preload_generated_uobm50_dbcreation/";
//		/*
//		 * UOBM DB
//		 */
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/UOBM";
//		System.out.println("**************************");
//		System.out.println("**\tUOBM\t\t**");
//		System.out.println("**************************");
//		optimizationPath = path + "/OptimizationClausesRDFox/optimizationClausesUOBM_eval.txt";
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optimizationPath);
//		/*
//		 * UOBM10 DB
//		 */
//		optPath = path + "OptimizationClauses/optimizationClausesUOBM10.txt";
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/UOBM10"; //This database contains data from 15 universities
//		System.out.println("**************************");
//		System.out.println("**\tUOBM10\t\t**");
//		System.out.println("**************************");
//		optimizationPath = path + "/OptimizationClausesRDFox/optimizationClausesUOBM10_eval.txt";
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optimizationPath);
//		/*
//		 * UOBM30 DB
//		 */
//		optPath = path + "OptimizationClauses/optimizationClausesUOBM30.txt";
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/UOBM30"; //This database contains data from 30 universities
//		System.out.println("**************************");
//		System.out.println("**\tUOBM30\t\t**");
//		System.out.println("**************************");
//		optimizationPath = path + "/OptimizationClausesRDFox/optimizationClausesUOBM30_Evaluation.txt";
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optimizationPath);
//		/**
//		 * Semintec Tests
//		 */
//		path = originalPath + "Semintec/";
//		ontologyFile = "file:" + path + "bigFile_DL-Lite_mine.owl";
//		queryPath = path + "Queries/";
//		optPath = path + "OptimizationClauses/optimizationClauses.txt";
//		uri = "http://www.owl-ontologies.com/unnamed.owl#";
//		/*
//		 * Semintex DB
//		 */
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/Semintec";
//		dataFiles = path;
//		System.out.println("**************************");
//		System.out.println("**\tSemintec\t\t**");
//		System.out.println("**************************");
//		optimizationPath = path + "/OptimizationClausesOWLim/optimizationClauses.txt";
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optimizationPath);
//		/**
//		 * Vicodi Tests
//		 */
//		path = originalPath + "Vicodi/";
//		ontologyFile = "file:" + path + "vicodi_all_DL-Lite_mine.owl";
//		queryPath = path + "Queries/";
//		optPath = path + "OptimizationClauses/optimizationClauses.txt";
//		uri = "http://vicodi.org/ontology#";
//		/*
//		 * Vicodi DB
//		 */
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/Vicodi";
//		dataFiles = path;
//		System.out.println("**************************");
//		System.out.println("**\tVicodi\t\t**");
//		System.out.println("**************************");
//		optimizationPath = path + "/OptimizationClausesOWLim/optimizationClauses.txt";
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optimizationPath);
//		/**
//		 * Reactome tests
//		 */
//		path =  originalPath + "reactome/";
//		ontologyFile = "file:" + path + "biopax-level3-processed_DL-Lite_mine.owl";
//		queryPath = path + "Queries/";
//		optPath = path + "OptimizationClauses/optimizationClauses.txt";
//		uri = "http://www.biopax.org/release/biopax-level3.owl#";
//		/*
//		 * Reactome DB
//		 */
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/reactome";
//		dataFiles = path;
//		System.out.println("**************************");
//		System.out.println("**\tReactome\t\t**");
//		System.out.println("**************************");
//		optimizationPath = path + "/OptimizationClausesOWLim/optimizationClauses.txt";
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optimizationPath);
//
//		/**
//		 * Fly tests
//		 */
//		path =  originalPath + "Fly/";
////		ontologyFile = "file:" + path + "fly_anatomy_XP_with_GJ_FC_individuals_owl-tBox-AssNorm.owl";
////		ontologyFile = "file:" + path + "fly_anatomy_XP_with_GJ_FC_individuals_owl-tBox-AssNorm-new.owl";
//		ontologyFile = "file:" + path + "fly_anatomy_XP_with_GJ_FC_individuals_owl-tBox-AssNorm-new-noTrans.owl";
////		uri = "http://purl.obolibrary.org/obo/";
//		/*
//		 * Fly DB
//		 */
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/Fly";
//		dataFiles = path;
//		System.out.println("**************************");
//		System.out.println("**\tFly\t\t**");
//		System.out.println("**************************");
////		optimizationPath = path + "/OptimizationClausesRDFox/OptimizationClauses---.txt";
////		optimizationPath = path + "/OptimizationClausesRDFox/OptimizationClauses-new.txt";
//		optimizationPath = path + "/OptimizationClausesRDFox/OptimizationClauses-new-noTrans--.txt";
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optimizationPath);
		
//		/**
//		 * Chembl
//		 */
//		path =  originalPath + "Chembl/";
//		ontologyFile = "file:" + path + "cco-noDPR_rdfxml.owl";
//		/*
//		 * Uniprot DB
//		 */
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/Chembl";
//		dataFiles = path;
//		System.out.println("**************************");
//		System.out.println("**\tChembl_evaluation\t\t**");
//		System.out.println("**************************");
//		optimizationPath = path + "/OptimizationClausesRDFox/OptimizationClauses_opt_Eval.txt";
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optimizationPath);
		
//		/**
//		 * Uniprot
//		 */
//		path =  originalPath + "Uniprot/";
//		ontologyFile = "file:" + path + "core-sat-processed-cardNorm.owl";
//		/*
//		 * Uniprot DB
//		 */
//		dbPath = "jdbc:postgresql://127.0.0.1:5432/Uniprot";
//		dataFiles = path;
//		System.out.println("**************************");
//		System.out.println("**\tUniprot\t\t**");
//		System.out.println("**************************");
//		optimizationPath = path + "/OptimizationClausesRDFox/OptimizationClauses_Evaluation.txt";
//		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
//		createOptFiles(ontologyFile, evaluator, optimizationPath);

		/**
		 * NPD
		 */
		path =  originalPath + "npd-benchmark-master/";
		ontologyFile = "file:" + path + "ontology/npd-v2-ql.owl";
		/*
		 * NPD DB
		 */
		dbPath = "jdbc:postgresql://127.0.0.1:5432/NPD";
		dataFiles = path;
		System.out.println("**************************");
		System.out.println("**\tNPD\t\t**");
		System.out.println("**************************");
		optimizationPath = path + "/OptimizationClausesRDFox/OptimizationClauses_npd-v2-ql-mysql_gstoil_avenet.obda_v2_Eval.txt";
		evaluator = createDataSetsAndEvaluator(dataFiles, dbPath, ontologyFile);
		createOptFiles(ontologyFile, evaluator, optimizationPath);
		System.exit(0);
	}

	public static void createOptFiles(String ontologyFile, RDFoxQueryEvaluator ev, String optimizationPath) throws Exception {


		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		IRI physicalURI = IRI.create(ontologyFile);
        OWLOntology ontology = manager.loadOntology(physicalURI);

        Set<OWLEntity> signature = ontology.getSignature();
		Set<OWLClass> concepts = new HashSet<OWLClass>();
		Set<OWLProperty> props = new HashSet<OWLProperty>();

		Set<Term> conceptTermsWithAnswers = new HashSet<Term>();
		Set<Term> roleTermsWithAnswers = new HashSet<Term>();


		Set<Clause> clausesWithNoAnswers = new HashSet<Clause>();

		Variable var1 = m_termFactory.getVariable(0);
		Variable var2 = m_termFactory.getVariable(1);

		int clausesWithAnswers = 0;
		/*
		 * Identify all entities of the ontology
		 * Entities are:	classes
		 * 					properties (object + data properties)
		 */
		long startIdentification = System.currentTimeMillis();
		for (OWLEntity entity: signature)
		{
			if ( entity instanceof OWLClass )
				concepts.add((OWLClass) entity);
			if ( entity instanceof OWLObjectProperty || entity instanceof OWLDataProperty )
				props.add((OWLProperty) entity);
		}
		System.out.print("Identified " +concepts.size() + " Concepts and " + props.size() + " Roles in ");
		System.out.println((System.currentTimeMillis() - startIdentification) + "ms");

		int counter = 0;
		/*
		 * Evaluate all classes on the DB and store
		 */
		long startConcepts = System.currentTimeMillis();
		int processed=0;
		double progress=0;
		double lastprogress=0;
		int allWork=concepts.size();
		System.out.println("\n\nEvaluating all concepts: " + allWork);
		for ( OWLClass cl: concepts )
		{
			processed++;
			progress=processed;
			progress=progress/allWork;
//			System.out.println(progress);
			if (progress>lastprogress+0.05) {
				System.out.print((int)(progress*100) + "%, ");
				counter++;
				if (counter%10 == 0)
					System.out.println();
				lastprogress=progress;
			}
//			System.out.println(cl.getIRI().getFragment());
			String clName;
//			if ( ontologyFile.contains("Fly") ) {
//				clName = cl.toString();
//			}
//			else if (ontologyFile.contains("lubm-ex-2"))
				clName = cl.getIRI().toString();
//			else {
//				clName= cl.getIRI().getFragment();
//			}
//			System.out.println(clName);
			Term clTerm = m_termFactory.getFunctionalTerm(clName, var1);
			Term[] body = new Term[1];
			body[0] = clTerm;
			Clause clause = new Clause(body, m_termFactory.getFunctionalTerm("Q", var1));
			long ans = ev.evaluateQuery(clause);
			if (ans == 0) {
//				entitiesWithNoAnswersMap.put(clName, ans);
//				conceptTermsWithNoAnswers.add(clTerm);
				clausesWithNoAnswers.add(clause);

			/**
			 * ΤΟ ικαρος χρησιμοποειεί ολόκληρο το uri για να αναπαραστήσει ένα concept/property
			 * Συνεπώς πρέπει να δω εάν κρατώντας τα empty concepts/prorpeties με το  URI βοηθάει
			 *
			 * Πρέπει να δω πως τρέχει το owlim
			 */

			}
			else {
				conceptTermsWithAnswers.add(clTerm);
			}
		}
		System.out.println("\nClauses With Answers " + conceptTermsWithAnswers.size());
		System.out.println("ClausesWithNoAnswersSize = " + clausesWithNoAnswers.size() + " in " + (System.currentTimeMillis() - startConcepts) + "ms");

		counter = 0;
		/*
		 * Evaluate all properties on the DB and store
		 * 		entitiesWithNoAnswer
		 * 		roleTermsTermWithNoAnswer
		 */
		System.out.println("\n\nEvaluating all properties: " + props.size() );
		long startProperties = System.currentTimeMillis();
		processed=0;
		progress=0;
		lastprogress=0;
		allWork=props.size();
		for ( OWLProperty prop: props )
		{
			processed++;
			progress=processed;
			progress=progress/allWork;
			if (progress>lastprogress+0.05) {
				System.out.print((int)(progress*100) + "%, ");
				counter++;
				if (counter%10 == 0)
					System.out.println();
				lastprogress=progress;
			}

			String propName;
//			if ( ontologyFile.contains("Fly"))
//				propName = prop.toString();
//			else if (ontologyFile.contains("lubm-ex-2"))
				propName = prop.getIRI().toString();
//			else
//				propName = prop.getIRI().getFragment();
			Term propTerm = m_termFactory.getFunctionalTerm(propName, var1, var2);
			Term propTermInv = m_termFactory.getFunctionalTerm(propName, var2, var1);
			Term[] body = new Term[1];
			body[0] = propTerm;
			Clause clause = new Clause(body, m_termFactory.getFunctionalTerm("Q", var1));
			long ans = ev.evaluateQuery(clause);
			if (ans == 0) {
				clausesWithNoAnswers.add(clause);
				//Create the clause with the inverse role and add it also to the list
//				body = new Term[1];
//				body[0] = propTermInv;
//				clause = new Clause(body, m_termFactory.getFunctionalTerm("Q", var1));
//				clausesWithNoAnswers.add(clause);
			}
			else {
				roleTermsWithAnswers.add(propTerm);
//				roleTermsWithAnswers.add(propTermInv);
			}
		}
		System.out.println("\nClauses With Answers " + roleTermsWithAnswers.size());
		System.out.println("ClausesWithNoAnswersSize = " + clausesWithNoAnswers.size() + " in " + (System.currentTimeMillis() - startProperties) + "ms");

		counter = 0;

		/**
		 * Evaluate COMBINATIONS of CONCEPTS
		 * For example for concepts C, D create clause
		 *
		 * Q(x) <- C(x), D(x)
		 *
		 * and identify if there exist answers
		 */
		System.out.println("\n\nEvaluating combination of concepts");
		System.out.println("Possible combinations: " + (conceptTermsWithAnswers.size() * conceptTermsWithAnswers.size()));
		long startConceptCombination = System.currentTimeMillis();
		processed=0;
		progress=0;
		lastprogress=0;
		allWork=conceptTermsWithAnswers.size() * conceptTermsWithAnswers.size();
		HashSet<String> checkedConcepts = new HashSet<String>();
		for (Term conceptTerm1 : conceptTermsWithAnswers ) {
			for (Term conceptTerm2 : conceptTermsWithAnswers ) {
				if ( checkedConcepts.contains(conceptTerm2.toString()))
					continue;
				if ( !conceptTerm1.equals(conceptTerm2) ) {

					processed++;
					progress=processed;
					progress=progress/allWork;
					if (progress>lastprogress+0.05) {
						System.out.print(String.format("%.3f", progress*100) + "%, ");
						lastprogress=progress;
						counter++;
						if (counter%10 == 0)
							System.out.println();
					}

					Term[] body = new Term[2];
					body[0] = conceptTerm1;
					body[1] = conceptTerm2;
					Clause clause = new Clause(body, m_termFactory.getFunctionalTerm("Q", var1));

					long ans = ev.evaluateQuery(clause, uri);
//					int ans = ev.getNumberOfReturnedAnswersCompletePart();
					if (ans == 0) {
						clausesWithNoAnswers.add(clause);
//						System.out.println("clause = " + clause + "\t\t" + ans);
					}
					else {
						clausesWithAnswers++;
					}

				}
			}
			checkedConcepts.add(conceptTerm1.toString());
		}
		System.out.println("\nCombinations of concepts with answers " + clausesWithAnswers);
		System.out.println("ClausesWithNoAnswersSize = " + clausesWithNoAnswers.size() + " in " + (System.currentTimeMillis() - startConceptCombination) + "ms");

		clausesWithAnswers=0;
		counter = 0;

		/**
		 * Evaluate COMBINATIONS of CONCEPTS and ROLES
		 * For example for concept C and role R create clause
		 *
		 * Q(x) <- C(x), R(x,y) and Q(x) <- C(x), R(y,x) 
		 *
		 * and identify if there exist answers
		 */
		System.out.println("\n\nEvaluating combinations of concepts and properties");
		System.out.println("Possible Combinations: " + ( conceptTermsWithAnswers.size() * roleTermsWithAnswers.size() ));
		long startConceptRoleCombination = System.currentTimeMillis();
		processed=0;
		progress=0;
		lastprogress=0;
		allWork=conceptTermsWithAnswers.size() * roleTermsWithAnswers.size();
		for (Term conceptTerm : conceptTermsWithAnswers ) {
			for (Term roleTerm : roleTermsWithAnswers ) {

				processed++;
				progress=processed;
				progress=progress/allWork;
				if (progress>lastprogress+0.05) {
					System.out.print(String.format("%.3f", progress*100) + "%, ");
					lastprogress=progress;
					counter++;
					if (counter%10 == 0)
						System.out.println();
				}

				Term[] body = new Term[2];
				body[0] = conceptTerm;
				body[1] = roleTerm;
				Clause clause = new Clause(body, m_termFactory.getFunctionalTerm("Q", var1));
				long ans = ev.evaluateQuery(clause);
				if (ans == 0) {
					clausesWithNoAnswers.add(clause);
//					System.out.println("clause = " + clause + "\t\t" + ans);
				}
				else
					clausesWithAnswers++;
				
				/**
				 * C(x), R(y,x)
				 */
				
				body[0] = conceptTerm;
				body[1] = m_termFactory.getFunctionalTerm(roleTerm.toString().replace("(X0,X1)", ""), var2, var1);
				clause = new Clause(body, m_termFactory.getFunctionalTerm("Q", var1));
				ans = ev.evaluateQuery(clause);
				if (ans == 0) {
					clausesWithNoAnswers.add(clause);
//					System.out.println("clause = " + clause + "\t\t" + ans);
				}
				else {
					clausesWithAnswers++;
//					System.out.print("|");
				}
				
				
			}
		}
		System.out.println("\nCombinations of concepts with roles with answers " + clausesWithAnswers);
		System.out.println("ClausesWithNoAnswersSize = " + clausesWithNoAnswers.size() + " in " + (System.currentTimeMillis() - startConceptRoleCombination) + "ms");

		clausesWithAnswers=0;

		/**
		 * Evaluate COMBINATIONS of ROLES
		 * For example for roles S and role R create clause
		 *
		 * Q(x) <- S(x,y), R(x,y) and Q(x) <- S(x,y), R(y,x)
		 *
		 * and identify if there exist answers
		 */
		System.out.println("\n\nEvaluating combinations of properties");
		System.out.println("Possible Combinations: " + ( roleTermsWithAnswers.size() * roleTermsWithAnswers.size() ));
		long startRoleRoleCombination = System.currentTimeMillis();
		processed=0;
		progress=0;
		lastprogress=0;
		allWork=roleTermsWithAnswers.size() * roleTermsWithAnswers.size();
		Set<String> checkedRoles = new HashSet<String>();
		for (Term roleTerm1 : roleTermsWithAnswers ) {
			for (Term roleTerm2 : roleTermsWithAnswers ) {
				if ( checkedRoles.contains(roleTerm2.toString()) )
					continue;
				if (!roleTerm1.equals(roleTerm2))
				{
					processed++;
					progress=processed;
					progress=progress/allWork;
					if (progress>lastprogress+0.05) {
						System.out.print(String.format("%.3f", progress*100) + "%, ");
						lastprogress=progress;
					}
					Term[] body = new Term[2];
					body[0] = roleTerm1;
					body[1] = roleTerm2;
					Clause clause = new Clause(body, m_termFactory.getFunctionalTerm("Q", var1));
					long ans = ev.evaluateQuery(clause, uri);
//					System.out.println(clause + "\t\t" + ans);
					if (ans == 0) {
						clausesWithNoAnswers.add(clause);
//						System.out.println("clause = " + clause + "\t\t" + ans);
					}
					else
						clausesWithAnswers++;
					
					/**
					 * R(x,y), P(y,x)
					 */
					body[0] = roleTerm1;
					body[1] = m_termFactory.getFunctionalTerm(roleTerm2.toString().replace("(X0,X1)", ""), var2, var1);
					clause = new Clause(body, m_termFactory.getFunctionalTerm("Q", var1));
					ans = ev.evaluateQuery(clause);
					if (ans == 0) {
						clausesWithNoAnswers.add(clause);
//						System.out.println("clause = " + clause + "\t\t" + ans);
					}
					else {
						clausesWithAnswers++;
//						System.out.print("|");
					}

				}
			}
			checkedRoles.add(roleTerm1.toString());
		}
		System.out.println("\nCombinations of roles with answers " + clausesWithAnswers);
		System.out.println("ClausesWithNoAnswersSize = " + clausesWithNoAnswers.size() + " in " + (System.currentTimeMillis() - startRoleRoleCombination) + "ms");


		System.out.println("\n\nOverall Time = " + (System.currentTimeMillis() - startIdentification) + "ms");

		/**
		 * Store this info in a file
		 */
		printResultToFile(optimizationPath, clausesWithNoAnswers);
	}

	private static void printResultToFile(String outputFilestr, Set<Clause> clauses) throws Exception{
		File outputFile = new File(outputFilestr);
        FileWriter out = new FileWriter(outputFile);

        for(Clause c: clauses){
			out.write( c.m_canonicalRepresentation + "\n");
		}

        out.close();
	}

	private static RDFoxQueryEvaluator createDataSetsAndEvaluator(String dataFiles, String dbPath, String ontologyFile) throws FileNotFoundException, org.semanticweb.owlapi.model.OWLOntologyCreationException, URISyntaxException, JRDFStoreException {

		String[] datasetFiles = null;
		RDFoxQueryEvaluator evaluator = new RDFoxQueryEvaluator();

//		System.out.println( "dataFiles = " + dataFiles  );
		if ( dataFiles.contains("uba1.7/lubm") ){
			System.out.println("LUBM_ex\t" + dataFiles);
			datasetFiles = createDataSetFilesLUBM_ex(dataFiles);
		}
		if ( dataFiles.contains("uba1.7/Onto") ){
			System.out.println("LUBM" + dataFiles);
			datasetFiles = createDataSetFilesLUBM(dataFiles);
		}
		else if (dbPath.contains("UOBM10") ) {
			System.out.println("UOBM10");
			datasetFiles = createDataSetFilesUOBM(dataFiles,10);
		}
		else if (dbPath.contains("UOBM30") ) {
			System.out.println("UOBM30");
			datasetFiles = createDataSetFilesUOBM(dataFiles,30);
		}
		else if (dbPath.contains("UOBM") ) {
			System.out.println("UOBM");
			datasetFiles = createDataSetFilesUOBM(dataFiles,1);
		}
		else if (dataFiles.contains("Semintec") ) {
			System.out.println("Semintec");
			datasetFiles = createDataSetFilesSemintec(dataFiles);
		}
		else if (dataFiles.contains("Vicodi") ) {
			System.out.println("Vicodi");
			datasetFiles = createDataSetFilesVicodi(dataFiles);
		}
		else if (dataFiles.contains("reactome") ) {
			System.out.println("reactome");
			datasetFiles = createDataSetFilesReactome(dataFiles);
		}
		else if (dataFiles.contains("Fly")) {
//			System.out.println("Fly");
			datasetFiles = createDataSetFilesFly(dataFiles);
		}
		else if (dataFiles.contains("Chembl")  ) {
			System.out.println("Chembl");
			datasetFiles = createDataSetFilesChembl(dataFiles);
		}
		else if (dataFiles.contains("Uniprot")  ) {
			System.out.println("Uniprot");
			datasetFiles = createDataSetFilesUniprot(dataFiles);
		}
		else if (dataFiles.contains("npd")  ) {
			System.out.println("NPD");
			datasetFiles = createDataSetFilesNPD(dataFiles);
		}

		evaluator.loadInputToSystem(ontologyFile, datasetFiles);
    	return evaluator;
	}
	
	public static String[] createDataSetFilesNPD(String dataFilesPath) {
		String[] result = new String[1];

		result[0] = dataFilesPath + "ontology/npd-v2-ql-abox_gstoil_avenet.ttl";

		return result;
	}

	
	public static String[] createDataSetFilesUniprot(String dataFilesPath) {
		String[] result = new String[1];

		result[0] = dataFilesPath + "dataset/sample_1.nt";

		return result;
	}

	public static String[] createDataSetFilesChembl(String dataFilesPath) {
		String[] result = new String[1];

		result[0] = dataFilesPath + "dataset/sample_1.nt";

		return result;
	}

	public static String[] createDataSetFilesLUBM(String dataFilesPath) {

		File dataDir = new File( dataFilesPath );
		File[] dataFiles = dataDir.listFiles();

		int k = 0;

		for( int i=0 ; i<dataFiles.length ; i++ ) {
//			if( dataFiles[i] == null )
//				// Either dir does not exist or is not a directory
//				return;
//			else
			if( !dataFiles[i].toString().contains(".svn") && !dataFiles[i].toString().contains(".DS_Store") && dataFiles[i].toString().contains(".ttl") ){
				k++;
			}
		}

//		System.out.println( "k = " + k);

		String[] result = new String[k];
		int globalCounter = 0;
		for ( int j = 0 ; globalCounter < k ; j++) {
//			System.out.println(globalCounter);

			int limit = 0;
			if (j==0)
				limit = 14;
			else if (j==1)
				limit = 18;
			else if (j==2)
				limit = 15;
			else if (j==3)
				limit = 20;
			else if (j==4)
				limit = 21;
			else if (j==5)
				limit = 14;
			else if (j==6)
				limit = 23;
			else if (j==7)
				limit = 16;
			else if (j==8)
				limit = 17;
			else if (j==9)
				limit = 21;
			else if (j==10)
				limit = 19;
			else if (j==11)
				limit = 23;
			else if (j==12)
				limit = 24;
			else if (j==13)
				limit = 18;
			else if (j==14)
				limit = 15;
			else if (j==15)
				limit = 20;
			else if (j==16)
				limit = 18;
			else if (j==17)
				limit = 22;
			else if (j==18)
				limit = 20;
			else if (j==19)
				limit = 24;
			else if (j==20)
				limit = 14;
			else if (j==21)
				limit = 18;
			else if (j==22)
				limit = 16;
			else if (j==23)
				limit = 20;
			else if (j==24)
				limit = 21;
			else if (j==25)
				limit = 15;
			else if (j==26)
				limit = 23;
			else if (j==27)
				limit = 17;
			else if (j==28)
				limit = 17;
			else if (j==29)
				limit = 22;
			for ( int i = 0 ; i <= limit ; i++) {
				result[globalCounter++] = dataFilesPath + "University"+ j + "_" + i + ".ttl";
//				System.out.println(globalCounter + ": " + dataFilesPath + "University"+ j + "_" + i + ".ttl");
			}

		}
		return result;
	}


	public static String[] createDataSetFilesUOBM(String dataFilesPath, int limit) {
		String[] result = new String[limit];

		for ( int j = 0 ; j < limit ; j++) {
			result[j] = dataFilesPath + "univ"+ j + ".ttl";
		}

		return result;
	}

	public static String[] createDataSetFilesVicodi(String dataFilesPath) {
		String[] result = new String[1];

		result[0] = dataFilesPath + "vicodi_all.ttl";

		return result;
	}

	public static String[] createDataSetFilesSemintec(String dataFilesPath) {
		String[] result = new String[1];

		result[0] = dataFilesPath + "bigFile.ttl";

		return result;
	}

	public static String[] createDataSetFilesReactome(String dataFilesPath) {
		String[] datasetFiles = new String[1];
		datasetFiles[0] = dataFilesPath + "sample_10.ttl";
		return datasetFiles;
	}

	public static String[] createDataSetFilesFly(String dataFilesPath) {
		String[] result = new String[1];

		result[0] = dataFilesPath + "fly_anatomy_XP_with_GJ_FC_individuals_owl-aBox-AssNorm.ttl";

		return result;
	}

	public static String[] createDataSetFilesLUBM_ex(String dataFilesPath) {

		File dataDir = new File( dataFilesPath );
		File[] dataFiles = dataDir.listFiles();

		int k = 0;

		for( int i=0 ; i<dataFiles.length ; i++ ) {
//			if( dataFiles[i] == null )
//				// Either dir does not exist or is not a directory
//				return;
//			else
			if( !dataFiles[i].toString().contains(".svn") && !dataFiles[i].toString().contains(".DS_Store") && dataFiles[i].toString().contains(".owl")){
				k++;
			}
		}

//		System.out.println( "k = " + k);

		String[] result = new String[k];
		int globalCounter = 0;
		for ( int j = 0 ; globalCounter < k ; j++) {
//			System.out.println(globalCounter);

			int limit = 0;
			if (j==0)
				limit = 14;
			else if (j==1)
				limit = 18;
			else if (j==2)
				limit = 15;
			else if (j==3)
				limit = 20;
			else if (j==4)
				limit = 21;
			else if (j==5)
				limit = 14;
			else if (j==6)
				limit = 23;
			else if (j==7)
				limit = 16;
			else if (j==8)
				limit = 17;
			else if (j==9)
				limit = 21;
			else if (j==10)
				limit = 19;
			else if (j==11)
				limit = 23;
			else if (j==12)
				limit = 24;
			else if (j==13)
				limit = 18;
			else if (j==14)
				limit = 15;
			else if (j==15)
				limit = 20;
			else if (j==16)
				limit = 18;
			else if (j==17)
				limit = 22;
			else if (j==18)
				limit = 20;
			else if (j==19)
				limit = 24;
			else if (j==20)
				limit = 14;
			else if (j==21)
				limit = 18;
			else if (j==22)
				limit = 16;
			else if (j==23)
				limit = 20;
			else if (j==24)
				limit = 21;
			else if (j==25)
				limit = 15;
			else if (j==26)
				limit = 23;
			else if (j==27)
				limit = 17;
			else if (j==28)
				limit = 17;
			else if (j==29)
				limit = 22;
			for ( int i = 0 ; i <= limit ; i++) {
				result[globalCounter++] = dataFilesPath + "University"+ j + "_" + i + ".owl";
//				System.out.println(result[globalCounter]);
			}

		}
		return result;
	}

	private static boolean haveCommonAnswers(Set<String> term1Ans, Set<String> term2Ans) {

		for ( String s1: term1Ans )
			for (String s2 : term2Ans)
				if (Objects.equals(s1,s2))
					return true;
		return false;

//		for ( String s1: term1Ans )
//			if ( term2Ans.contains(s1) )
//				return true;
//		return false;
	}

}
