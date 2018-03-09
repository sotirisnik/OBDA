package org.oxford.comlab.perfectref.rewriter;

import java.util.HashMap;

public abstract class Term {
    public static final Term[] NO_ARGUMENTS=new Term[0];

    private boolean toBeSentToOWLim = true;
    
	public boolean getToBeSentToOWLim () {
		return toBeSentToOWLim;
	}
	
	public void setToBeSentToOWLim(boolean sentToOWLim) {
		toBeSentToOWLim = sentToOWLim;
	}
    
    protected Term() {}

    public abstract boolean isConstant();
    
    public abstract boolean isVariable();
    
    public abstract String getName();

    public abstract int getArity();

    public abstract Term getArgument(int argumentIndex);

    public abstract Term[] getArguments();

    public abstract boolean contains(Term term);

    public abstract Term apply(Substitution substitution,TermFactory termFactory);

    public abstract Term offsetVariables(TermFactory termFactory,int offset);

    public abstract Term renameVariables(TermFactory termFactory, HashMap<Variable, Integer> mapping);

    public abstract int getMinVariableIndex();

    public abstract void toString(StringBuffer buffer);

    public abstract int getDepth();

    public abstract Term getVariableOrConstant();

    public abstract String getFunctionalPrefix();

    @Override
	public String toString() {
        StringBuffer buffer=new StringBuffer();
        toString(buffer);
        return buffer.toString();
    }
}
