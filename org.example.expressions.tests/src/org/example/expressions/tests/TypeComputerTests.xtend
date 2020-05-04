package org.example.expressions.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.example.expressions.expressions.Expression
import org.example.expressions.expressions.ExpressionsModel
import org.example.expressions.typing.ExpressionsType
import org.example.expressions.typing.ExpressionsTypeComputer
import org.junit.Test
import org.junit.runner.RunWith

import static org.example.expressions.typing.ExpressionsTypeComputer.*

import static extension org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(ExpressionsInjectorProvider)
class TypeComputerTests {

	@Inject extension ParseHelper<ExpressionsModel>
	@Inject extension ExpressionsTypeComputer

	@Test def void intConstant() { "10".assertEvalType(INT_TYPE) }
	@Test def void stringConstant() { "'foo'".assertEvalType(STRING_TYPE) }
	@Test def void boolConstant() { "true".assertEvalType(BOOL_TYPE) }

	@Test def void notExp() { "!true".assertEvalType(BOOL_TYPE) }

	@Test def void multiExp() { "1 * 2".assertEvalType(INT_TYPE) }
	@Test def void divExp() { "1 / 2".assertEvalType(INT_TYPE) }

	@Test def void minusExp() { "1 - 2".assertEvalType(INT_TYPE) }

	@Test def void comparisonExp() { "1 < 2".assertEvalType(BOOL_TYPE) }
	@Test def void equalityExp() { "1 == 2".assertEvalType(BOOL_TYPE) }
	@Test def void andExp() { "true && false".assertEvalType(BOOL_TYPE) }
	@Test def void orExp() { "true || false".assertEvalType(BOOL_TYPE) }

	@Test def void numericPlus() { "1 + 2".assertEvalType(INT_TYPE) }
	@Test def void stringPlus() { "'a' + 'b'".assertEvalType(STRING_TYPE) }
	@Test def void numAndStringPlus() { "'a' + 2".assertEvalType(STRING_TYPE) }
	@Test def void numAndStringPlus2() { "2 + 'a'".assertEvalType(STRING_TYPE) }
	@Test def void boolAndStringPlus() { "'a' + true".assertEvalType(STRING_TYPE) }
	@Test def void boolAndStringPlus2() { "false + 'a'".assertEvalType(STRING_TYPE) }

	@Test def void incompletePlusRight() { "1 + ".assertEvalType(INT_TYPE) }

	@Test def void varWithExpression() { "var i = 0".assertType(INT_TYPE) }

	@Test def void varRef() { "var i = 0 eval i".assertType(INT_TYPE) }
	@Test def void varRefToVarDefinedAfter() { "var i = j var j = i".assertType(null) }

	@Test def void testIsInt() { 
		(ExpressionsTypeComputer.INT_TYPE).isIntType.assertTrue
	}

	@Test def void testIsString() { 
		(ExpressionsTypeComputer.STRING_TYPE).isStringType.assertTrue
	}

	@Test def void testIsBool() { 
		(ExpressionsTypeComputer.BOOL_TYPE).isBoolType.assertTrue
	}

	@Test def void testNotIsInt() { 
		(ExpressionsTypeComputer.STRING_TYPE).isIntType.assertFalse
	}

	@Test def void testNotIsString() { 
		(ExpressionsTypeComputer.INT_TYPE).isStringType.assertFalse
	}

	@Test def void testNotIsBool() { 
		(ExpressionsTypeComputer.INT_TYPE).isBoolType.assertFalse
	}

	def assertEvalType(CharSequence input, ExpressionsType expectedType) {
		("eval " + input).assertType(expectedType)
	}

	def assertType(CharSequence input, ExpressionsType expectedType) {
		input.parse.elements.last.
			expression.assertType(expectedType)
	}

	def assertType(Expression e, ExpressionsType expectedType) {
		expectedType.assertSame(e.typeFor)
	}

}