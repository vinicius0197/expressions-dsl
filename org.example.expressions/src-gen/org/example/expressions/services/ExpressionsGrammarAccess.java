/*
 * generated by Xtext 2.21.0
 */
package org.example.expressions.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.common.services.TerminalsGrammarAccess;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class ExpressionsGrammarAccess extends AbstractGrammarElementFinder {
	
	public class ExpressionsModelElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.ExpressionsModel");
		private final Assignment cElementsAssignment = (Assignment)rule.eContents().get(1);
		private final RuleCall cElementsAbstractElementParserRuleCall_0 = (RuleCall)cElementsAssignment.eContents().get(0);
		
		//ExpressionsModel:
		//	elements+=AbstractElement*;
		@Override public ParserRule getRule() { return rule; }
		
		//elements+=AbstractElement*
		public Assignment getElementsAssignment() { return cElementsAssignment; }
		
		//AbstractElement
		public RuleCall getElementsAbstractElementParserRuleCall_0() { return cElementsAbstractElementParserRuleCall_0; }
	}
	public class AbstractElementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.AbstractElement");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cVariableParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cEvalExpressionParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		
		//AbstractElement:
		//	Variable | EvalExpression;
		@Override public ParserRule getRule() { return rule; }
		
		//Variable | EvalExpression
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//Variable
		public RuleCall getVariableParserRuleCall_0() { return cVariableParserRuleCall_0; }
		
		//EvalExpression
		public RuleCall getEvalExpressionParserRuleCall_1() { return cEvalExpressionParserRuleCall_1; }
	}
	public class VariableElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.Variable");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cVarKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final Keyword cEqualsSignKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Assignment cExpressionAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cExpressionExpressionParserRuleCall_3_0 = (RuleCall)cExpressionAssignment_3.eContents().get(0);
		
		//Variable:
		//	'var' name=ID '=' expression=Expression;
		@Override public ParserRule getRule() { return rule; }
		
		//'var' name=ID '=' expression=Expression
		public Group getGroup() { return cGroup; }
		
		//'var'
		public Keyword getVarKeyword_0() { return cVarKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//'='
		public Keyword getEqualsSignKeyword_2() { return cEqualsSignKeyword_2; }
		
		//expression=Expression
		public Assignment getExpressionAssignment_3() { return cExpressionAssignment_3; }
		
		//Expression
		public RuleCall getExpressionExpressionParserRuleCall_3_0() { return cExpressionExpressionParserRuleCall_3_0; }
	}
	public class EvalExpressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.EvalExpression");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cEvalKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cExpressionAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cExpressionExpressionParserRuleCall_1_0 = (RuleCall)cExpressionAssignment_1.eContents().get(0);
		
		//EvalExpression:
		//	'eval' expression=Expression;
		@Override public ParserRule getRule() { return rule; }
		
		//'eval' expression=Expression
		public Group getGroup() { return cGroup; }
		
		//'eval'
		public Keyword getEvalKeyword_0() { return cEvalKeyword_0; }
		
		//expression=Expression
		public Assignment getExpressionAssignment_1() { return cExpressionAssignment_1; }
		
		//Expression
		public RuleCall getExpressionExpressionParserRuleCall_1_0() { return cExpressionExpressionParserRuleCall_1_0; }
	}
	public class ExpressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.Expression");
		private final RuleCall cOrParserRuleCall = (RuleCall)rule.eContents().get(1);
		
		//Expression:
		//	Or;
		@Override public ParserRule getRule() { return rule; }
		
		//Or
		public RuleCall getOrParserRuleCall() { return cOrParserRuleCall; }
	}
	public class OrElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.Or");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cAndParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Action cOrLeftAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Keyword cVerticalLineVerticalLineKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		private final Assignment cRightAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cRightAndParserRuleCall_1_2_0 = (RuleCall)cRightAssignment_1_2.eContents().get(0);
		
		//Or Expression:
		//	And ({Or.left=current} "||" right=And)*;
		@Override public ParserRule getRule() { return rule; }
		
		//And ({Or.left=current} "||" right=And)*
		public Group getGroup() { return cGroup; }
		
		//And
		public RuleCall getAndParserRuleCall_0() { return cAndParserRuleCall_0; }
		
		//({Or.left=current} "||" right=And)*
		public Group getGroup_1() { return cGroup_1; }
		
		//{Or.left=current}
		public Action getOrLeftAction_1_0() { return cOrLeftAction_1_0; }
		
		//"||"
		public Keyword getVerticalLineVerticalLineKeyword_1_1() { return cVerticalLineVerticalLineKeyword_1_1; }
		
		//right=And
		public Assignment getRightAssignment_1_2() { return cRightAssignment_1_2; }
		
		//And
		public RuleCall getRightAndParserRuleCall_1_2_0() { return cRightAndParserRuleCall_1_2_0; }
	}
	public class AndElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.And");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cEqualityParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Action cAndLeftAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Keyword cAmpersandAmpersandKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		private final Assignment cRightAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cRightEqualityParserRuleCall_1_2_0 = (RuleCall)cRightAssignment_1_2.eContents().get(0);
		
		//And Expression:
		//	Equality ({And.left=current} "&&" right=Equality)*;
		@Override public ParserRule getRule() { return rule; }
		
		//Equality ({And.left=current} "&&" right=Equality)*
		public Group getGroup() { return cGroup; }
		
		//Equality
		public RuleCall getEqualityParserRuleCall_0() { return cEqualityParserRuleCall_0; }
		
		//({And.left=current} "&&" right=Equality)*
		public Group getGroup_1() { return cGroup_1; }
		
		//{And.left=current}
		public Action getAndLeftAction_1_0() { return cAndLeftAction_1_0; }
		
		//"&&"
		public Keyword getAmpersandAmpersandKeyword_1_1() { return cAmpersandAmpersandKeyword_1_1; }
		
		//right=Equality
		public Assignment getRightAssignment_1_2() { return cRightAssignment_1_2; }
		
		//Equality
		public RuleCall getRightEqualityParserRuleCall_1_2_0() { return cRightEqualityParserRuleCall_1_2_0; }
	}
	public class EqualityElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.Equality");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cComparisonParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Action cEqualityLeftAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Assignment cOpAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final Alternatives cOpAlternatives_1_1_0 = (Alternatives)cOpAssignment_1_1.eContents().get(0);
		private final Keyword cOpEqualsSignEqualsSignKeyword_1_1_0_0 = (Keyword)cOpAlternatives_1_1_0.eContents().get(0);
		private final Keyword cOpExclamationMarkEqualsSignKeyword_1_1_0_1 = (Keyword)cOpAlternatives_1_1_0.eContents().get(1);
		private final Assignment cRightAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cRightComparisonParserRuleCall_1_2_0 = (RuleCall)cRightAssignment_1_2.eContents().get(0);
		
		//Equality Expression:
		//	Comparison ({Equality.left=current} op=("==" | "!=") right=Comparison)*;
		@Override public ParserRule getRule() { return rule; }
		
		//Comparison ({Equality.left=current} op=("==" | "!=") right=Comparison)*
		public Group getGroup() { return cGroup; }
		
		//Comparison
		public RuleCall getComparisonParserRuleCall_0() { return cComparisonParserRuleCall_0; }
		
		//({Equality.left=current} op=("==" | "!=") right=Comparison)*
		public Group getGroup_1() { return cGroup_1; }
		
		//{Equality.left=current}
		public Action getEqualityLeftAction_1_0() { return cEqualityLeftAction_1_0; }
		
		//op=("==" | "!=")
		public Assignment getOpAssignment_1_1() { return cOpAssignment_1_1; }
		
		//("==" | "!=")
		public Alternatives getOpAlternatives_1_1_0() { return cOpAlternatives_1_1_0; }
		
		//"=="
		public Keyword getOpEqualsSignEqualsSignKeyword_1_1_0_0() { return cOpEqualsSignEqualsSignKeyword_1_1_0_0; }
		
		//"!="
		public Keyword getOpExclamationMarkEqualsSignKeyword_1_1_0_1() { return cOpExclamationMarkEqualsSignKeyword_1_1_0_1; }
		
		//right=Comparison
		public Assignment getRightAssignment_1_2() { return cRightAssignment_1_2; }
		
		//Comparison
		public RuleCall getRightComparisonParserRuleCall_1_2_0() { return cRightComparisonParserRuleCall_1_2_0; }
	}
	public class ComparisonElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.Comparison");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cPlusOrMinusParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Action cComparisonLeftAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Assignment cOpAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final Alternatives cOpAlternatives_1_1_0 = (Alternatives)cOpAssignment_1_1.eContents().get(0);
		private final Keyword cOpGreaterThanSignEqualsSignKeyword_1_1_0_0 = (Keyword)cOpAlternatives_1_1_0.eContents().get(0);
		private final Keyword cOpLessThanSignEqualsSignKeyword_1_1_0_1 = (Keyword)cOpAlternatives_1_1_0.eContents().get(1);
		private final Keyword cOpGreaterThanSignKeyword_1_1_0_2 = (Keyword)cOpAlternatives_1_1_0.eContents().get(2);
		private final Keyword cOpLessThanSignKeyword_1_1_0_3 = (Keyword)cOpAlternatives_1_1_0.eContents().get(3);
		private final Assignment cRightAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cRightPlusOrMinusParserRuleCall_1_2_0 = (RuleCall)cRightAssignment_1_2.eContents().get(0);
		
		//Comparison Expression:
		//	PlusOrMinus ({Comparison.left=current} op=(">=" | "<=" | ">" | "<") right=PlusOrMinus)*;
		@Override public ParserRule getRule() { return rule; }
		
		//PlusOrMinus ({Comparison.left=current} op=(">=" | "<=" | ">" | "<") right=PlusOrMinus)*
		public Group getGroup() { return cGroup; }
		
		//PlusOrMinus
		public RuleCall getPlusOrMinusParserRuleCall_0() { return cPlusOrMinusParserRuleCall_0; }
		
		//({Comparison.left=current} op=(">=" | "<=" | ">" | "<") right=PlusOrMinus)*
		public Group getGroup_1() { return cGroup_1; }
		
		//{Comparison.left=current}
		public Action getComparisonLeftAction_1_0() { return cComparisonLeftAction_1_0; }
		
		//op=(">=" | "<=" | ">" | "<")
		public Assignment getOpAssignment_1_1() { return cOpAssignment_1_1; }
		
		//(">=" | "<=" | ">" | "<")
		public Alternatives getOpAlternatives_1_1_0() { return cOpAlternatives_1_1_0; }
		
		//">="
		public Keyword getOpGreaterThanSignEqualsSignKeyword_1_1_0_0() { return cOpGreaterThanSignEqualsSignKeyword_1_1_0_0; }
		
		//"<="
		public Keyword getOpLessThanSignEqualsSignKeyword_1_1_0_1() { return cOpLessThanSignEqualsSignKeyword_1_1_0_1; }
		
		//">"
		public Keyword getOpGreaterThanSignKeyword_1_1_0_2() { return cOpGreaterThanSignKeyword_1_1_0_2; }
		
		//"<"
		public Keyword getOpLessThanSignKeyword_1_1_0_3() { return cOpLessThanSignKeyword_1_1_0_3; }
		
		//right=PlusOrMinus
		public Assignment getRightAssignment_1_2() { return cRightAssignment_1_2; }
		
		//PlusOrMinus
		public RuleCall getRightPlusOrMinusParserRuleCall_1_2_0() { return cRightPlusOrMinusParserRuleCall_1_2_0; }
	}
	public class PlusOrMinusElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.PlusOrMinus");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cMulOrDivParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Alternatives cAlternatives_1_0 = (Alternatives)cGroup_1.eContents().get(0);
		private final Group cGroup_1_0_0 = (Group)cAlternatives_1_0.eContents().get(0);
		private final Action cPlusLeftAction_1_0_0_0 = (Action)cGroup_1_0_0.eContents().get(0);
		private final Keyword cPlusSignKeyword_1_0_0_1 = (Keyword)cGroup_1_0_0.eContents().get(1);
		private final Group cGroup_1_0_1 = (Group)cAlternatives_1_0.eContents().get(1);
		private final Action cMinusLeftAction_1_0_1_0 = (Action)cGroup_1_0_1.eContents().get(0);
		private final Keyword cHyphenMinusKeyword_1_0_1_1 = (Keyword)cGroup_1_0_1.eContents().get(1);
		private final Assignment cRightAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cRightMulOrDivParserRuleCall_1_1_0 = (RuleCall)cRightAssignment_1_1.eContents().get(0);
		
		//PlusOrMinus Expression:
		//	MulOrDiv (({Plus.left=current} '+' | {Minus.left=current} '-') right=MulOrDiv)*;
		@Override public ParserRule getRule() { return rule; }
		
		//MulOrDiv (({Plus.left=current} '+' | {Minus.left=current} '-') right=MulOrDiv)*
		public Group getGroup() { return cGroup; }
		
		//MulOrDiv
		public RuleCall getMulOrDivParserRuleCall_0() { return cMulOrDivParserRuleCall_0; }
		
		//(({Plus.left=current} '+' | {Minus.left=current} '-') right=MulOrDiv)*
		public Group getGroup_1() { return cGroup_1; }
		
		//({Plus.left=current} '+' | {Minus.left=current} '-')
		public Alternatives getAlternatives_1_0() { return cAlternatives_1_0; }
		
		//{Plus.left=current} '+'
		public Group getGroup_1_0_0() { return cGroup_1_0_0; }
		
		//{Plus.left=current}
		public Action getPlusLeftAction_1_0_0_0() { return cPlusLeftAction_1_0_0_0; }
		
		//'+'
		public Keyword getPlusSignKeyword_1_0_0_1() { return cPlusSignKeyword_1_0_0_1; }
		
		//{Minus.left=current} '-'
		public Group getGroup_1_0_1() { return cGroup_1_0_1; }
		
		//{Minus.left=current}
		public Action getMinusLeftAction_1_0_1_0() { return cMinusLeftAction_1_0_1_0; }
		
		//'-'
		public Keyword getHyphenMinusKeyword_1_0_1_1() { return cHyphenMinusKeyword_1_0_1_1; }
		
		//right=MulOrDiv
		public Assignment getRightAssignment_1_1() { return cRightAssignment_1_1; }
		
		//MulOrDiv
		public RuleCall getRightMulOrDivParserRuleCall_1_1_0() { return cRightMulOrDivParserRuleCall_1_1_0; }
	}
	public class MulOrDivElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.MulOrDiv");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cPrimaryParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Action cMulOrDivLeftAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Assignment cOpAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final Alternatives cOpAlternatives_1_1_0 = (Alternatives)cOpAssignment_1_1.eContents().get(0);
		private final Keyword cOpAsteriskKeyword_1_1_0_0 = (Keyword)cOpAlternatives_1_1_0.eContents().get(0);
		private final Keyword cOpSolidusKeyword_1_1_0_1 = (Keyword)cOpAlternatives_1_1_0.eContents().get(1);
		private final Assignment cRightAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cRightPrimaryParserRuleCall_1_2_0 = (RuleCall)cRightAssignment_1_2.eContents().get(0);
		
		//MulOrDiv Expression:
		//	Primary ({MulOrDiv.left=current} op=('*' | '/') right=Primary)*;
		@Override public ParserRule getRule() { return rule; }
		
		//Primary ({MulOrDiv.left=current} op=('*' | '/') right=Primary)*
		public Group getGroup() { return cGroup; }
		
		//Primary
		public RuleCall getPrimaryParserRuleCall_0() { return cPrimaryParserRuleCall_0; }
		
		//({MulOrDiv.left=current} op=('*' | '/') right=Primary)*
		public Group getGroup_1() { return cGroup_1; }
		
		//{MulOrDiv.left=current}
		public Action getMulOrDivLeftAction_1_0() { return cMulOrDivLeftAction_1_0; }
		
		//op=('*' | '/')
		public Assignment getOpAssignment_1_1() { return cOpAssignment_1_1; }
		
		//('*' | '/')
		public Alternatives getOpAlternatives_1_1_0() { return cOpAlternatives_1_1_0; }
		
		//'*'
		public Keyword getOpAsteriskKeyword_1_1_0_0() { return cOpAsteriskKeyword_1_1_0_0; }
		
		//'/'
		public Keyword getOpSolidusKeyword_1_1_0_1() { return cOpSolidusKeyword_1_1_0_1; }
		
		//right=Primary
		public Assignment getRightAssignment_1_2() { return cRightAssignment_1_2; }
		
		//Primary
		public RuleCall getRightPrimaryParserRuleCall_1_2_0() { return cRightPrimaryParserRuleCall_1_2_0; }
	}
	public class PrimaryElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.Primary");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cAlternatives.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_0_0 = (Keyword)cGroup_0.eContents().get(0);
		private final RuleCall cExpressionParserRuleCall_0_1 = (RuleCall)cGroup_0.eContents().get(1);
		private final Keyword cRightParenthesisKeyword_0_2 = (Keyword)cGroup_0.eContents().get(2);
		private final Group cGroup_1 = (Group)cAlternatives.eContents().get(1);
		private final Action cNotAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Keyword cExclamationMarkKeyword_1_1 = (Keyword)cGroup_1.eContents().get(1);
		private final Assignment cExpressionAssignment_1_2 = (Assignment)cGroup_1.eContents().get(2);
		private final RuleCall cExpressionPrimaryParserRuleCall_1_2_0 = (RuleCall)cExpressionAssignment_1_2.eContents().get(0);
		private final RuleCall cAtomicParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		
		//Primary Expression:
		//	'(' Expression ')' | {Not} "!" expression=Primary | Atomic;
		@Override public ParserRule getRule() { return rule; }
		
		//'(' Expression ')' | {Not} "!" expression=Primary | Atomic
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//'(' Expression ')'
		public Group getGroup_0() { return cGroup_0; }
		
		//'('
		public Keyword getLeftParenthesisKeyword_0_0() { return cLeftParenthesisKeyword_0_0; }
		
		//Expression
		public RuleCall getExpressionParserRuleCall_0_1() { return cExpressionParserRuleCall_0_1; }
		
		//')'
		public Keyword getRightParenthesisKeyword_0_2() { return cRightParenthesisKeyword_0_2; }
		
		//{Not} "!" expression=Primary
		public Group getGroup_1() { return cGroup_1; }
		
		//{Not}
		public Action getNotAction_1_0() { return cNotAction_1_0; }
		
		//"!"
		public Keyword getExclamationMarkKeyword_1_1() { return cExclamationMarkKeyword_1_1; }
		
		//expression=Primary
		public Assignment getExpressionAssignment_1_2() { return cExpressionAssignment_1_2; }
		
		//Primary
		public RuleCall getExpressionPrimaryParserRuleCall_1_2_0() { return cExpressionPrimaryParserRuleCall_1_2_0; }
		
		//Atomic
		public RuleCall getAtomicParserRuleCall_2() { return cAtomicParserRuleCall_2; }
	}
	public class AtomicElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.example.expressions.Expressions.Atomic");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cAlternatives.eContents().get(0);
		private final Action cIntConstantAction_0_0 = (Action)cGroup_0.eContents().get(0);
		private final Assignment cValueAssignment_0_1 = (Assignment)cGroup_0.eContents().get(1);
		private final RuleCall cValueINTTerminalRuleCall_0_1_0 = (RuleCall)cValueAssignment_0_1.eContents().get(0);
		private final Group cGroup_1 = (Group)cAlternatives.eContents().get(1);
		private final Action cStringConstantAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Assignment cValueAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cValueSTRINGTerminalRuleCall_1_1_0 = (RuleCall)cValueAssignment_1_1.eContents().get(0);
		private final Group cGroup_2 = (Group)cAlternatives.eContents().get(2);
		private final Action cBoolConstantAction_2_0 = (Action)cGroup_2.eContents().get(0);
		private final Assignment cValueAssignment_2_1 = (Assignment)cGroup_2.eContents().get(1);
		private final Alternatives cValueAlternatives_2_1_0 = (Alternatives)cValueAssignment_2_1.eContents().get(0);
		private final Keyword cValueTrueKeyword_2_1_0_0 = (Keyword)cValueAlternatives_2_1_0.eContents().get(0);
		private final Keyword cValueFalseKeyword_2_1_0_1 = (Keyword)cValueAlternatives_2_1_0.eContents().get(1);
		private final Group cGroup_3 = (Group)cAlternatives.eContents().get(3);
		private final Action cVariableRefAction_3_0 = (Action)cGroup_3.eContents().get(0);
		private final Assignment cVariableAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final CrossReference cVariableVariableCrossReference_3_1_0 = (CrossReference)cVariableAssignment_3_1.eContents().get(0);
		private final RuleCall cVariableVariableIDTerminalRuleCall_3_1_0_1 = (RuleCall)cVariableVariableCrossReference_3_1_0.eContents().get(1);
		
		//Atomic Expression:
		//	{IntConstant} value=INT | {StringConstant} value=STRING | {BoolConstant} value=('true' | 'false') | {VariableRef}
		//	variable=[Variable];
		@Override public ParserRule getRule() { return rule; }
		
		//{IntConstant} value=INT | {StringConstant} value=STRING | {BoolConstant} value=('true' | 'false') | {VariableRef}
		//variable=[Variable]
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//{IntConstant} value=INT
		public Group getGroup_0() { return cGroup_0; }
		
		//{IntConstant}
		public Action getIntConstantAction_0_0() { return cIntConstantAction_0_0; }
		
		//value=INT
		public Assignment getValueAssignment_0_1() { return cValueAssignment_0_1; }
		
		//INT
		public RuleCall getValueINTTerminalRuleCall_0_1_0() { return cValueINTTerminalRuleCall_0_1_0; }
		
		//{StringConstant} value=STRING
		public Group getGroup_1() { return cGroup_1; }
		
		//{StringConstant}
		public Action getStringConstantAction_1_0() { return cStringConstantAction_1_0; }
		
		//value=STRING
		public Assignment getValueAssignment_1_1() { return cValueAssignment_1_1; }
		
		//STRING
		public RuleCall getValueSTRINGTerminalRuleCall_1_1_0() { return cValueSTRINGTerminalRuleCall_1_1_0; }
		
		//{BoolConstant} value=('true' | 'false')
		public Group getGroup_2() { return cGroup_2; }
		
		//{BoolConstant}
		public Action getBoolConstantAction_2_0() { return cBoolConstantAction_2_0; }
		
		//value=('true' | 'false')
		public Assignment getValueAssignment_2_1() { return cValueAssignment_2_1; }
		
		//('true' | 'false')
		public Alternatives getValueAlternatives_2_1_0() { return cValueAlternatives_2_1_0; }
		
		//'true'
		public Keyword getValueTrueKeyword_2_1_0_0() { return cValueTrueKeyword_2_1_0_0; }
		
		//'false'
		public Keyword getValueFalseKeyword_2_1_0_1() { return cValueFalseKeyword_2_1_0_1; }
		
		//{VariableRef} variable=[Variable]
		public Group getGroup_3() { return cGroup_3; }
		
		//{VariableRef}
		public Action getVariableRefAction_3_0() { return cVariableRefAction_3_0; }
		
		//variable=[Variable]
		public Assignment getVariableAssignment_3_1() { return cVariableAssignment_3_1; }
		
		//[Variable]
		public CrossReference getVariableVariableCrossReference_3_1_0() { return cVariableVariableCrossReference_3_1_0; }
		
		//ID
		public RuleCall getVariableVariableIDTerminalRuleCall_3_1_0_1() { return cVariableVariableIDTerminalRuleCall_3_1_0_1; }
	}
	
	
	private final ExpressionsModelElements pExpressionsModel;
	private final AbstractElementElements pAbstractElement;
	private final VariableElements pVariable;
	private final EvalExpressionElements pEvalExpression;
	private final ExpressionElements pExpression;
	private final OrElements pOr;
	private final AndElements pAnd;
	private final EqualityElements pEquality;
	private final ComparisonElements pComparison;
	private final PlusOrMinusElements pPlusOrMinus;
	private final MulOrDivElements pMulOrDiv;
	private final PrimaryElements pPrimary;
	private final AtomicElements pAtomic;
	
	private final Grammar grammar;
	
	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public ExpressionsGrammarAccess(GrammarProvider grammarProvider,
			TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pExpressionsModel = new ExpressionsModelElements();
		this.pAbstractElement = new AbstractElementElements();
		this.pVariable = new VariableElements();
		this.pEvalExpression = new EvalExpressionElements();
		this.pExpression = new ExpressionElements();
		this.pOr = new OrElements();
		this.pAnd = new AndElements();
		this.pEquality = new EqualityElements();
		this.pComparison = new ComparisonElements();
		this.pPlusOrMinus = new PlusOrMinusElements();
		this.pMulOrDiv = new MulOrDivElements();
		this.pPrimary = new PrimaryElements();
		this.pAtomic = new AtomicElements();
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("org.example.expressions.Expressions".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//ExpressionsModel:
	//	elements+=AbstractElement*;
	public ExpressionsModelElements getExpressionsModelAccess() {
		return pExpressionsModel;
	}
	
	public ParserRule getExpressionsModelRule() {
		return getExpressionsModelAccess().getRule();
	}
	
	//AbstractElement:
	//	Variable | EvalExpression;
	public AbstractElementElements getAbstractElementAccess() {
		return pAbstractElement;
	}
	
	public ParserRule getAbstractElementRule() {
		return getAbstractElementAccess().getRule();
	}
	
	//Variable:
	//	'var' name=ID '=' expression=Expression;
	public VariableElements getVariableAccess() {
		return pVariable;
	}
	
	public ParserRule getVariableRule() {
		return getVariableAccess().getRule();
	}
	
	//EvalExpression:
	//	'eval' expression=Expression;
	public EvalExpressionElements getEvalExpressionAccess() {
		return pEvalExpression;
	}
	
	public ParserRule getEvalExpressionRule() {
		return getEvalExpressionAccess().getRule();
	}
	
	//Expression:
	//	Or;
	public ExpressionElements getExpressionAccess() {
		return pExpression;
	}
	
	public ParserRule getExpressionRule() {
		return getExpressionAccess().getRule();
	}
	
	//Or Expression:
	//	And ({Or.left=current} "||" right=And)*;
	public OrElements getOrAccess() {
		return pOr;
	}
	
	public ParserRule getOrRule() {
		return getOrAccess().getRule();
	}
	
	//And Expression:
	//	Equality ({And.left=current} "&&" right=Equality)*;
	public AndElements getAndAccess() {
		return pAnd;
	}
	
	public ParserRule getAndRule() {
		return getAndAccess().getRule();
	}
	
	//Equality Expression:
	//	Comparison ({Equality.left=current} op=("==" | "!=") right=Comparison)*;
	public EqualityElements getEqualityAccess() {
		return pEquality;
	}
	
	public ParserRule getEqualityRule() {
		return getEqualityAccess().getRule();
	}
	
	//Comparison Expression:
	//	PlusOrMinus ({Comparison.left=current} op=(">=" | "<=" | ">" | "<") right=PlusOrMinus)*;
	public ComparisonElements getComparisonAccess() {
		return pComparison;
	}
	
	public ParserRule getComparisonRule() {
		return getComparisonAccess().getRule();
	}
	
	//PlusOrMinus Expression:
	//	MulOrDiv (({Plus.left=current} '+' | {Minus.left=current} '-') right=MulOrDiv)*;
	public PlusOrMinusElements getPlusOrMinusAccess() {
		return pPlusOrMinus;
	}
	
	public ParserRule getPlusOrMinusRule() {
		return getPlusOrMinusAccess().getRule();
	}
	
	//MulOrDiv Expression:
	//	Primary ({MulOrDiv.left=current} op=('*' | '/') right=Primary)*;
	public MulOrDivElements getMulOrDivAccess() {
		return pMulOrDiv;
	}
	
	public ParserRule getMulOrDivRule() {
		return getMulOrDivAccess().getRule();
	}
	
	//Primary Expression:
	//	'(' Expression ')' | {Not} "!" expression=Primary | Atomic;
	public PrimaryElements getPrimaryAccess() {
		return pPrimary;
	}
	
	public ParserRule getPrimaryRule() {
		return getPrimaryAccess().getRule();
	}
	
	//Atomic Expression:
	//	{IntConstant} value=INT | {StringConstant} value=STRING | {BoolConstant} value=('true' | 'false') | {VariableRef}
	//	variable=[Variable];
	public AtomicElements getAtomicAccess() {
		return pAtomic;
	}
	
	public ParserRule getAtomicRule() {
		return getAtomicAccess().getRule();
	}
	
	//terminal ID:
	//	'^'? ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	}
	
	//terminal INT returns ecore::EInt:
	//	'0'..'9'+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	}
	
	//terminal STRING:
	//	'"' ('\\' . | !('\\' | '"'))* '"' |
	//	"'" ('\\' . | !('\\' | "'"))* "'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	}
	
	//terminal ML_COMMENT:
	//	'/*'->'*/';
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	}
	
	//terminal SL_COMMENT:
	//	'//' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	}
	
	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	}
}
