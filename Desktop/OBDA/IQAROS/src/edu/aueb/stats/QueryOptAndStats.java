package edu.aueb.stats;

import java.util.ArrayList;
import java.util.HashMap;

import org.oxford.comlab.perfectref.rewriter.Clause;
import org.oxford.comlab.perfectref.rewriter.Term;

import edu.aueb.queries.Evaluator;

public class QueryOptAndStats {

	public int computeMaxCQsSizeInRew( ArrayList<Clause> rewriting) {
		int max = 0;
		int clauseLength = 0;

		for(Clause clause:rewriting) {
			clauseLength = clause.getBody().length;
			if ( clauseLength > max )
				max = clauseLength;
		}
		return max;
	}

	public ArrayList<Clause> reorderCQsInRew (ArrayList<Clause> rewriting, String dbPath) throws Exception {
		ArrayList<Clause> result = new ArrayList<Clause>();

		Clause newClause;
		for (Clause clause:rewriting)
			newClause = reorderCQ(clause, dbPath);
		
		return result;
	}

	private Clause reorderCQ(Clause clause, String dbPath) throws Exception {
		Evaluator evaluator = new Evaluator(dbPath);
		/*HashMap<Term,int> termSizes = new HashMap<Term, int>() 
		
		for ( Term term: clause.getBody() ) {
			String sqlQuery = evaluator.getSQLFromAtom(term);
			int numOfAnsws = evaluator.evaluateSQLandReturnNumOfAnws(null, sqlQuery);
		}*/
		
		return null;
	}

}
