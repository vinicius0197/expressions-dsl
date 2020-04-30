package org.example.expressions.interpreter

import org.example.expressions.expressions.Expression
import org.example.expressions.expressions.IntConstant
import org.example.expressions.expressions.BoolConstant
import org.example.expressions.expressions.StringConstant
import org.example.expressions.expressions.And
import org.example.expressions.expressions.MulOrDiv
import org.example.expressions.expressions.Plus
import org.example.expressions.expressions.VariableRef
import org.example.expressions.expressions.AbstractElement
import org.example.expressions.typing.ExpressionsTypeComputer
import javax.inject.Inject

class ExpressionsInterpreter {
	@Inject extension ExpressionsTypeComputer
	def dispatch Object interpret(Expression e) {
		switch(e) {
			IntConstant: e.value
			BoolConstant: Boolean.parseBoolean(e.value)
			StringConstant: e.value
			And: {
				(e.left.interpret as Boolean) && (e.right.interpret as Boolean)
			}
			MulOrDiv: {
				val left = e.left.interpret as Integer
				val right = e.right.interpret as Integer
				if(e.op=='*')
					left*right
				else
					left/right
			}
			Plus: {
				if(e.left.typeFor.isStringType || e.right.typeFor.isStringType)
					e.left.interpret.toString + e.right.interpret.toString
				else
					(e.left.interpret as Integer) + (e.right.interpret as Integer)
			}
			VariableRef: e.variable.interpret
		}
	}
	
	def dispatch Object interpret(AbstractElement e) {
		e.expression.interpret
	}
}