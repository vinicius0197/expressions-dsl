package org.example.expressions.tests;

import com.google.inject.Inject;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.example.expressions.expressions.AbstractElement;
import org.example.expressions.expressions.Expression;
import org.example.expressions.expressions.ExpressionsModel;
import org.example.expressions.tests.ExpressionsInjectorProvider;
import org.example.expressions.typing.ExpressionsType;
import org.example.expressions.typing.ExpressionsTypeComputer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(ExpressionsInjectorProvider.class)
@SuppressWarnings("all")
public class TypeComputerTests {
  @Inject
  @Extension
  private ParseHelper<ExpressionsModel> _parseHelper;
  
  @Inject
  @Extension
  private ExpressionsTypeComputer _expressionsTypeComputer;
  
  @Test
  public void intConstant() {
    this.assertEvalType("10", ExpressionsTypeComputer.INT_TYPE);
  }
  
  @Test
  public void stringConstant() {
    this.assertEvalType("\'foo\'", ExpressionsTypeComputer.STRING_TYPE);
  }
  
  @Test
  public void boolConstant() {
    this.assertEvalType("true", ExpressionsTypeComputer.BOOL_TYPE);
  }
  
  @Test
  public void notExp() {
    this.assertEvalType("!true", ExpressionsTypeComputer.BOOL_TYPE);
  }
  
  @Test
  public void multiExp() {
    this.assertEvalType("1 * 2", ExpressionsTypeComputer.INT_TYPE);
  }
  
  @Test
  public void divExp() {
    this.assertEvalType("1 / 2", ExpressionsTypeComputer.INT_TYPE);
  }
  
  @Test
  public void minusExp() {
    this.assertEvalType("1 - 2", ExpressionsTypeComputer.INT_TYPE);
  }
  
  @Test
  public void comparisonExp() {
    this.assertEvalType("1 < 2", ExpressionsTypeComputer.BOOL_TYPE);
  }
  
  @Test
  public void equalityExp() {
    this.assertEvalType("1 == 2", ExpressionsTypeComputer.BOOL_TYPE);
  }
  
  @Test
  public void andExp() {
    this.assertEvalType("true && false", ExpressionsTypeComputer.BOOL_TYPE);
  }
  
  @Test
  public void orExp() {
    this.assertEvalType("true || false", ExpressionsTypeComputer.BOOL_TYPE);
  }
  
  @Test
  public void numericPlus() {
    this.assertEvalType("1 + 2", ExpressionsTypeComputer.INT_TYPE);
  }
  
  @Test
  public void stringPlus() {
    this.assertEvalType("\'a\' + \'b\'", ExpressionsTypeComputer.STRING_TYPE);
  }
  
  @Test
  public void numAndStringPlus() {
    this.assertEvalType("\'a\' + 2", ExpressionsTypeComputer.STRING_TYPE);
  }
  
  @Test
  public void numAndStringPlus2() {
    this.assertEvalType("2 + \'a\'", ExpressionsTypeComputer.STRING_TYPE);
  }
  
  @Test
  public void boolAndStringPlus() {
    this.assertEvalType("\'a\' + true", ExpressionsTypeComputer.STRING_TYPE);
  }
  
  @Test
  public void boolAndStringPlus2() {
    this.assertEvalType("false + \'a\'", ExpressionsTypeComputer.STRING_TYPE);
  }
  
  @Test
  public void incompletePlusRight() {
    this.assertEvalType("1 + ", ExpressionsTypeComputer.INT_TYPE);
  }
  
  @Test
  public void varWithExpression() {
    this.assertType("var i = 0", ExpressionsTypeComputer.INT_TYPE);
  }
  
  @Test
  public void varRef() {
    this.assertType("var i = 0 eval i", ExpressionsTypeComputer.INT_TYPE);
  }
  
  @Test
  public void varRefToVarDefinedAfter() {
    this.assertType("var i = j var j = i", null);
  }
  
  @Test
  public void testIsInt() {
    Assert.assertTrue(this._expressionsTypeComputer.isIntType(ExpressionsTypeComputer.INT_TYPE));
  }
  
  @Test
  public void testIsString() {
    Assert.assertTrue(this._expressionsTypeComputer.isStringType(ExpressionsTypeComputer.STRING_TYPE));
  }
  
  @Test
  public void testIsBool() {
    Assert.assertTrue(this._expressionsTypeComputer.isBoolType(ExpressionsTypeComputer.BOOL_TYPE));
  }
  
  @Test
  public void testNotIsInt() {
    Assert.assertFalse(this._expressionsTypeComputer.isIntType(ExpressionsTypeComputer.STRING_TYPE));
  }
  
  @Test
  public void testNotIsString() {
    Assert.assertFalse(this._expressionsTypeComputer.isStringType(ExpressionsTypeComputer.INT_TYPE));
  }
  
  @Test
  public void testNotIsBool() {
    Assert.assertFalse(this._expressionsTypeComputer.isBoolType(ExpressionsTypeComputer.INT_TYPE));
  }
  
  public void assertEvalType(final CharSequence input, final ExpressionsType expectedType) {
    this.assertType(("eval " + input), expectedType);
  }
  
  public void assertType(final CharSequence input, final ExpressionsType expectedType) {
    try {
      this.assertType(IterableExtensions.<AbstractElement>last(this._parseHelper.parse(input).getElements()).getExpression(), expectedType);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void assertType(final Expression e, final ExpressionsType expectedType) {
    Assert.assertSame(expectedType, this._expressionsTypeComputer.typeFor(e));
  }
}
