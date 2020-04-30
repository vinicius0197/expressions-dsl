package org.example.expressions.tests

import javax.inject.Inject
import org.eclipse.xtext.testing.util.ParseHelper
import org.example.expressions.validation.ExpressionsModelUtil
import org.example.expressions.expressions.ExpressionsModel
import org.junit.Test
import static extension org.junit.Assert.*
import org.example.expressions.expressions.ExpressionsPackage
import org.example.expressions.validation.ExpressionsValidator
import org.eclipse.xtext.testing.validation.ValidationTestHelper

class ExpressionsReferenceTest {
	@Inject extension ParseHelper<ExpressionsModel>
	@Inject extension ExpressionsModelUtil
	@Inject extension ValidationTestHelper
	
	@Test def void variablesBeforeVariable() {
		'''
		eval true
		// (0)
		var i = 0
		// (1)
		eval i + 10
		// (2)
		var j = i
		// (3)
		eval i + j
		// (4)
		'''.parse => [
			assertVariablesDefinedBefore(0, "")
			assertVariablesDefinedBefore(1, "")
			assertVariablesDefinedBefore(2, "i")
			assertVariablesDefinedBefore(3, "i")
			assertVariablesDefinedBefore(4, "i,j")
		]
	}
	@Test
	def void testForwardReferenceInExpression() {
		'''var i = j var j = 10'''.parse => [
			assertError(ExpressionsPackage.eINSTANCE.variableRef,
			ExpressionsValidator.FORWARD_REFERENCE,
				"variable forward reference not allowed: 'j'"
			)
			// check that it is the only error
			1.assertEquals(validate.size)
		]
	}
	@Test
	def void testNoForwardReference() {
		'''var j = 10 var i = j'''.parse.assertNoErrors
	}
	
	// TODO: test method isVariableDefinedBefore
	
	def void assertVariablesDefinedBefore(ExpressionsModel model,
		int elemIndex, CharSequence expectedVars) {
		expectedVars.assertEquals(
			model.elements.get(elemIndex).variablesDefinedBefore.
			map[name].join(",")
		)
	}
}