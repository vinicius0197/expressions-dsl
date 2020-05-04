package org.example.expressions.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.example.expressions.expressions.ExpressionsModel
import org.example.expressions.interpreter.ExpressionsInterpreter
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(ExpressionsInjectorProvider)
class ExpressionsInterpreterTests {

	@Inject extension ParseHelper<ExpressionsModel>
	@Inject extension ValidationTestHelper
	@Inject extension ExpressionsInterpreter

	@Test def void intConstant() { "eval 1".assertInterpret(1) }

	@Test def void boolConstant() { "eval true".assertInterpret(true) }

	@Test def void stringConstant() { "eval 'abc'".assertInterpret("abc") }

	@Test def void multi() { "eval 5 * 3".assertInterpret(15) }

	@Test def void div() { "eval 6 / 3".assertInterpret(2) }

	@Test def void minus() { "eval 6 - 2".assertInterpret(4) }

	@Test def void intPlus() { "eval 6 + 2".assertInterpret(8) }

	@Test def void stringPlus() { "eval 'a' + 'b'".assertInterpret('ab') }

	@Test def void intStringPlus() { "eval 'a' + 1".assertInterpret('a1') }

	@Test def void boolStringPlus() { "eval 'a' + true".assertInterpret('atrue') }

	@Test def void lessThanInt() { "eval 1 < 2".assertInterpret(true) }

	@Test def void lessEqualsThanInt() { "eval 2 <= 2".assertInterpret(true) }

	@Test def void greaterThanInt() { "eval 1 > 2".assertInterpret(false) }

	@Test def void greaterEqualsThanInt() { "eval 2 >= 1".assertInterpret(true) }

	@Test def void lessThanString() { "eval 'a' < 'b'".assertInterpret(true) }

	@Test def void lessEqualsThanString() { "eval 'a' <= 'ab'".assertInterpret(true) }

	@Test def void greaterThanString() { "eval 'ab' > 'a'".assertInterpret(true) }

	@Test def void greaterEqualsThanString() { "eval 'a' >= 'ab'".assertInterpret(false) }

	@Test def void equalsString() { "eval 'a' == 'a'".assertInterpret(true) }

	@Test def void notEqualsString() { "eval 'a' != 'b'".assertInterpret(true) }

	@Test def void equalsInt() { "eval 1 == 1".assertInterpret(true) }

	@Test def void notEqualsInt() { "eval 0 != 1".assertInterpret(true) }

	@Test def void equalsBool() { "eval true == true".assertInterpret(true) }

	@Test def void notEqualsBool() { "eval true != false".assertInterpret(true) }

	@Test def void and() { "eval true && !false".assertInterpret(true) }

	@Test def void or() { "eval false || true".assertInterpret(true) }

	@Test def void varRef() { "var i = 1 var j = i + 2 eval j+1".assertInterpret(4) }

	@Test def void varSameVarRef() { "var i = 1 eval i+i".assertInterpret(2) }

	@Test def void complex() { "eval ((5 * 3)+1) / (7 + 1)".assertInterpret(2) }

	def assertInterpret(CharSequence input, Object expected) {
		input.parse => [
			assertNoErrors
			expected.assertEquals(elements.last.expression.interpret)
		]
	}

}