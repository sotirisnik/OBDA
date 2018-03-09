IQAROS (Incremental Query Answering and Rewriting Ontology System) is a prototypical query rewriting system based on incremental query rewriting.
See http://code.google.com/p/iqaros/ for further information.

IQAROS is Copyright (c) 2011
Image, Video and Multimedia Laboratory, NTUA

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

Given an ontology schema (i.e. TBox) and conjunctive query, IQAROS generates a union of conjucntive queries such that the answers of the union of conjunctive queries over some input data and discarding the ontology schema are precisely the answers of the original query over the same input data and ontology schema.

The theory behind IQAROS is presented in the paper

Tassos Venetis, Giorgos Stoilos, and Giorgos Stamou. Rewriting By Extension: Incremental Query Rewriting for DL-Lite Ontologies

available at: http://iqaros.googlecode.com/files/main.pdf


==== 1. INSTALLATION ====


The software is comprised of four JAVA .jar files that can be found in the folder /lib. Include these jar files in your JAVA classpath when running the tool.


==== 2. QUICK START ====


The source code of the tool is also available under folder /src.

In folder /src/examples you can find two example JAVA classes that give examples on how you can run the tool. The first example EvaluationTest.java contains a main method that runs the scenarios performed for the evaluation of IQAROS against other rewriting systems in the aforementioned paper. These scenarios (ontologies plus queries) can be found in folder /dataset/Evaluation_ISWC'09. The second example Inc.java is an example on how to run IQAROS using any other scenario provided that an OWL ontology and a conjunctive query into the form recognisable by the tool are given.


==== 3. BRIEF DESCRIPTION ====


There are mainly two important classes in the tool:

a) The first one is Incremental.java that implements the join function among two rewriting graphs in order to produce a new combined rewriting graph

b) The second one is RewritingExtensionManager.java that implements a simplified version of the join function that can be used only when the last atom of a fixed query is processed.
