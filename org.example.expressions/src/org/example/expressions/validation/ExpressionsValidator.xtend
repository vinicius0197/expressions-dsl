package org.example.expressions.validation
import static extension org.eclipse.xtext.EcoreUtil2.*
import org.example.expressions.expressions.VariableRef
import org.example.expressions.expressions.ExpressionsModel
import org.example.expressions.expressions.Variable
import org.example.expressions.expressions.AbstractElement
import org.example.expressions.expressions.Expression
import org.example.expressions.expressions.ExpressionsPackage
import org.eclipse.xtext.validation.Check
import javax.inject.Inject
import org.example.expressions.typing.ExpressionsTypeComputer
import org.eclipse.emf.ecore.EReference
import org.example.expressions.typing.ExpressionsType
import org.example.expressions.expressions.Not
import org.example.expressions.expressions.And
import org.example.expressions.expressions.Equality
import org.example.expressions.expressions.Comparison
import org.example.expressions.expressions.Plus

class ExpressionsValidator extends AbstractExpressionsValidator {
	protected static val ISSUE_CODE_PREFIX = "org.example.expressions."
	public static val FORWARD_REFERENCE =
		ISSUE_CODE_PREFIX + "ForwardReference"
		
	public static val TYPE_MISMATCH =
		ISSUE_CODE_PREFIX + "TypeMismatch"
	
	@Inject extension ExpressionsModelUtil
	@Check
	def void checkForwardReference(VariableRef varRef) {
		val variable = varRef.getVariable()
		if (!varRef.isVariableDefinedBefore)
			error("variable forward reference not allowed: '"
			+ variable.name + "'",
			ExpressionsPackage.eINSTANCE.variableRef_Variable,
			FORWARD_REFERENCE, variable.name)
	}
	
	@Inject extension ExpressionsTypeComputer
	def private checkExpectedBoolean(Expression exp, EReference reference) {
		checkExpectedType(exp,
			ExpressionsTypeComputer.BOOL_TYPE, reference)
	}
	
	def private checkExpectedInt(Expression exp, EReference reference) {
		checkExpectedType(exp,
			ExpressionsTypeComputer.INT_TYPE, reference)
	}
	
	def private checkExpectedType(Expression exp,
		ExpressionsType expectedType, EReference reference) {
		val actualType = getTypeAndCheckNotNull(exp, reference)
		if (actualType != expectedType)
			error("expected " + expectedType +
			" type, but was " + actualType,
			reference, TYPE_MISMATCH)
	}
	
	def private ExpressionsType getTypeAndCheckNotNull(
		Expression exp, EReference reference) {
		var type = exp?.typeFor
		if (type == null)
			error("null type", reference, TYPE_MISMATCH)
		return type;
	}
	
	@Check def checkType(Not not) {
		checkExpectedBoolean(not.expression,
		ExpressionsPackage.Literals.NOT__EXPRESSION)
	}
	@Check def checkType(And and) {
		checkExpectedBoolean(and.left,
		ExpressionsPackage.Literals.AND__LEFT)
		checkExpectedBoolean(and.right,
		ExpressionsPackage.Literals.AND__RIGHT)
	}
	@Check def checkType(Equality equality) {
		val leftType = getTypeAndCheckNotNull(equality.left,
			ExpressionsPackage.Literals.EQUALITY__LEFT)
		val rightType = getTypeAndCheckNotNull(equality.right,
			ExpressionsPackage.Literals.EQUALITY__RIGHT)
		checkExpectedSame(leftType, rightType)
	}
	@Check def checkType(Comparison comparison) {
		val leftType = getTypeAndCheckNotNull(comparison.left,
			ExpressionsPackage.Literals.COMPARISON__LEFT)
		val rightType = getTypeAndCheckNotNull(comparison.right,
			ExpressionsPackage.Literals.COMPARISON__RIGHT)
		checkExpectedSame(leftType, rightType)
		checkNotBoolean(leftType,
			ExpressionsPackage.Literals.COMPARISON__LEFT)
		checkNotBoolean(rightType,
			ExpressionsPackage.Literals.COMPARISON__RIGHT)
	}
	def private checkExpectedSame(ExpressionsType left,
		ExpressionsType right) {
		if (right != null && left != null && right != left) {
			error("expected the same type, but was "+left+", "+right,
			ExpressionsPackage.Literals.EQUALITY.getEIDAttribute(),
			TYPE_MISMATCH)
		}
	}
	def private checkNotBoolean(ExpressionsType type,
		EReference reference) {
		if (type.isBoolType) {
			error("cannot be boolean", reference, TYPE_MISMATCH)
		}
	}
	@Check def checkType(Plus plus) {
		val leftType = getTypeAndCheckNotNull(plus.left,
			ExpressionsPackage.Literals.PLUS__LEFT)
		val rightType = getTypeAndCheckNotNull(plus.right,
			ExpressionsPackage.Literals.PLUS__RIGHT)
		if (leftType.isIntType
			|| rightType.isIntType
			|| (!leftType.isStringType &&
			!rightType.
			isStringType)) {
				checkNotBoolean(leftType,
				ExpressionsPackage.Literals.PLUS__LEFT)
				checkNotBoolean(rightType,
				ExpressionsPackage.Literals.PLUS__RIGHT)
		}
	}
}



class ExpressionsModelUtil {
	def isVariableDefinedBefore(VariableRef varRef) {
		varRef.variablesDefinedBefore.contains(varRef.variable)
	}
	def variablesDefinedBefore(Expression e) {
		e.getContainerOfType(AbstractElement).variablesDefinedBefore
	}
	def variablesDefinedBefore(AbstractElement containingElement) {
		val allElements =
			(containingElement.eContainer as ExpressionsModel).elements

		allElements.subList(0,
			allElements.indexOf(containingElement)).typeSelect(Variable).
			toSet
	}
}