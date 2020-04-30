/*
 * generated by Xtext 2.21.0
 */
package org.example.expressions.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.example.expressions.parser.antlr.internal.InternalExpressionsParser;
import org.example.expressions.services.ExpressionsGrammarAccess;

public class ExpressionsParser extends AbstractAntlrParser {

	@Inject
	private ExpressionsGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalExpressionsParser createParser(XtextTokenStream stream) {
		return new InternalExpressionsParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "ExpressionsModel";
	}

	public ExpressionsGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(ExpressionsGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
