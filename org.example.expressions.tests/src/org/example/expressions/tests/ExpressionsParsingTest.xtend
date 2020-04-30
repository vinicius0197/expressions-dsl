package org.example.expressions.tests

import static extension org.junit.Assert.*
import org.junit.runner.RunWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.InjectWith
import javax.inject.Inject
import org.eclipse.xtext.testing.util.ParseHelper
import org.example.expressions.expressions.ExpressionsModel
import org.junit.Test
import org.example.expressions.expressions.VariableRef
import org.example.expressions.expressions.Expression
import org.example.expressions.expressions.IntConstant
import org.example.expressions.expressions.StringConstant
import org.example.expressions.expressions.BoolConstant
import org.example.expressions.expressions.Plus
import org.example.expressions.expressions.Minus
import org.example.expressions.expressions.MulOrDiv

@RunWith(XtextRunner)
@InjectWith(ExpressionsInjectorProvider)
class ExpressionsParsingTest {
	def private assertRepr(CharSequence input, CharSequence expected) {
		("eval " + input).parse => [
			expected.assertEquals(
				elements.last.expression.stringRepr
			)
		]
	}
	
	def private String stringRepr(Expression e) {
		switch (e) {
			Plus:
			'''(«e.left.stringRepr» + «e.right.stringRepr»)'''
			Minus:
			'''(«e.left.stringRepr» - «e.right.stringRepr»)'''
			MulOrDiv:
			'''(«e.left.stringRepr» «e.op» «e.right.stringRepr»)'''
			IntConstant: '''«e.value»'''
			StringConstant: '''«e.value»'''
			BoolConstant: '''«e.value»'''
			VariableRef: '''«e.variable.name»'''
		}.toString
	}
	
	@Inject extension ParseHelper<ExpressionsModel>
	@Test def void testEvalExpression() {
		"eval 10".parse.assertNotNull
	}
	@Test def void testVariable() {
		"var i = 10".parse.assertNotNull
	}
	@Test def void testVariableReference() {
		'''
		var i = 10
		eval i
		'''.parse => [
			(elements.last.expression as VariableRef).variable.
			assertSame(elements.head)
		]
	}
	// Testing associavitiy	
	@Test def void testPlus() {
		"10 + 5 + 1 + 2".assertRepr("(((10 + 5) + 1) + 2)")
	}
	@Test def void testParenthesis() {
		10.assertEquals(
			("eval (10)".parse.elements.head.expression as IntConstant).value)
	}
	@Test def void testPlusWithParenthesis() {
		"( 10 + 5 ) + ( 1 + 2 )".assertRepr("((10 + 5) + (1 + 2))")
	}
	
	// Testing precedence
	@Test def void testPlusMulPrecedence() {
		"10 + 5 * 2 - 5 / 1".assertRepr("((10 + (5 * 2)) - (5 / 1))")
	}
	
	// TODO: add tests for the remaining expressions
	
	@Test def void testPrecedences() {
		"!true||false&&1>(1/3+5*2)".
			assertRepr
				("((!true) || (false && (1 > ((1 / 3) + (5 * 2)))))")
	}
}