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
import org.example.expressions.expressions.Comparison
import org.example.expressions.expressions.Equality
import org.example.expressions.expressions.And
import org.example.expressions.expressions.Or
import org.example.expressions.expressions.Not

@RunWith(XtextRunner)
@InjectWith(ExpressionsInjectorProvider)
class ExpressionsParsingTest {

	@Inject extension ParseHelper<ExpressionsModel>

	@Test def void testEvalIntConstant() {
		"eval 10".parse.assertNotNull
	}

	@Test def void testEvalStringConstant() {
		'eval "a string"'.parse.assertNotNull
	}

	@Test def void testEvalBoolConstant() {
		"eval true".parse.assertNotNull
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

	@Test
	def void testMinus() {
		"10 + 5 - 1 - 2".assertRepr("(((10 + 5) - 1) - 2)")
	}

	@Test
	def void testMulOrDiv() {
		"10 * 5 / 1 * 2".assertRepr("(((10 * 5) / 1) * 2)")
	}

	@Test
	def void testPlusMulPrecedence() {
		"10 + 5 * 2 - 5 / 1".assertRepr("((10 + (5 * 2)) - (5 / 1))")
	}

	@Test def void testComparison() {
		"10 <= 5 < 2 > 5".assertRepr("(((10 <= 5) < 2) > 5)")
	}

	@Test def void testEqualityAndComparison() {
		"true == 5 <= 2".assertRepr("(true == (5 <= 2))")
	}

	@Test def void testAndOr() {
		"true || false && 1 < 0".assertRepr("(true || (false && (1 < 0)))")
	}

	@Test def void testNot() {
		"!true||false".assertRepr("((!true) || false)")
	}

	@Test def void testNotWithParentheses() {
		"!(true||false)".assertRepr("(!(true || false))")
	}

	@Test def void testPrecedences() {
		"!true||false&&1>(1/3+5*2)".
		assertRepr("((!true) || (false && (1 > ((1 / 3) + (5 * 2)))))")
	}

	def private assertRepr(CharSequence input, CharSequence expected) {
		("eval " + input).parse => [
			expected.assertEquals(
				elements.last.expression.stringRepr
			)
		]
	}

	def private String stringRepr(Expression e) {
		switch (e) {
			Plus: '''(«e.left.stringRepr» + «e.right.stringRepr»)'''
			Minus: '''(«e.left.stringRepr» - «e.right.stringRepr»)'''
			MulOrDiv: '''(«e.left.stringRepr» «e.op» «e.right.stringRepr»)'''
			Comparison: '''(«e.left.stringRepr» «e.op» «e.right.stringRepr»)'''
			Equality: '''(«e.left.stringRepr» «e.op» «e.right.stringRepr»)'''
			And: '''(«e.left.stringRepr» && «e.right.stringRepr»)'''
			Or: '''(«e.left.stringRepr» || «e.right.stringRepr»)'''
			Not: '''(!«e.expression.stringRepr»)'''
			IntConstant: '''«e.value»'''
			StringConstant: '''«e.value»'''
			BoolConstant: '''«e.value»'''
			VariableRef: '''«e.variable.name»'''
		}.toString
	}

}