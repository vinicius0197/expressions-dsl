package org.example.expressions.tests;

import com.google.inject.Inject;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.example.expressions.expressions.AbstractElement;
import org.example.expressions.expressions.ExpressionsModel;
import org.example.expressions.interpreter.ExpressionsInterpreter;
import org.example.expressions.tests.ExpressionsInjectorProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(ExpressionsInjectorProvider.class)
@SuppressWarnings("all")
public class ExpressionsInterpreterTests {
  @Inject
  @Extension
  private ParseHelper<ExpressionsModel> _parseHelper;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Inject
  @Extension
  private ExpressionsInterpreter _expressionsInterpreter;
  
  @Test
  public void intConstant() {
    this.assertInterpret("eval 1", Integer.valueOf(1));
  }
  
  @Test
  public void boolConstant() {
    this.assertInterpret("eval true", Boolean.valueOf(true));
  }
  
  @Test
  public void stringConstant() {
    this.assertInterpret("eval \'abc\'", "abc");
  }
  
  @Test
  public void multi() {
    this.assertInterpret("eval 5 * 3", Integer.valueOf(15));
  }
  
  @Test
  public void div() {
    this.assertInterpret("eval 6 / 3", Integer.valueOf(2));
  }
  
  @Test
  public void minus() {
    this.assertInterpret("eval 6 - 2", Integer.valueOf(4));
  }
  
  @Test
  public void intPlus() {
    this.assertInterpret("eval 6 + 2", Integer.valueOf(8));
  }
  
  @Test
  public void stringPlus() {
    this.assertInterpret("eval \'a\' + \'b\'", "ab");
  }
  
  @Test
  public void intStringPlus() {
    this.assertInterpret("eval \'a\' + 1", "a1");
  }
  
  @Test
  public void boolStringPlus() {
    this.assertInterpret("eval \'a\' + true", "atrue");
  }
  
  @Test
  public void lessThanInt() {
    this.assertInterpret("eval 1 < 2", Boolean.valueOf(true));
  }
  
  @Test
  public void lessEqualsThanInt() {
    this.assertInterpret("eval 2 <= 2", Boolean.valueOf(true));
  }
  
  @Test
  public void greaterThanInt() {
    this.assertInterpret("eval 1 > 2", Boolean.valueOf(false));
  }
  
  @Test
  public void greaterEqualsThanInt() {
    this.assertInterpret("eval 2 >= 1", Boolean.valueOf(true));
  }
  
  @Test
  public void lessThanString() {
    this.assertInterpret("eval \'a\' < \'b\'", Boolean.valueOf(true));
  }
  
  @Test
  public void lessEqualsThanString() {
    this.assertInterpret("eval \'a\' <= \'ab\'", Boolean.valueOf(true));
  }
  
  @Test
  public void greaterThanString() {
    this.assertInterpret("eval \'ab\' > \'a\'", Boolean.valueOf(true));
  }
  
  @Test
  public void greaterEqualsThanString() {
    this.assertInterpret("eval \'a\' >= \'ab\'", Boolean.valueOf(false));
  }
  
  @Test
  public void equalsString() {
    this.assertInterpret("eval \'a\' == \'a\'", Boolean.valueOf(true));
  }
  
  @Test
  public void notEqualsString() {
    this.assertInterpret("eval \'a\' != \'b\'", Boolean.valueOf(true));
  }
  
  @Test
  public void equalsInt() {
    this.assertInterpret("eval 1 == 1", Boolean.valueOf(true));
  }
  
  @Test
  public void notEqualsInt() {
    this.assertInterpret("eval 0 != 1", Boolean.valueOf(true));
  }
  
  @Test
  public void equalsBool() {
    this.assertInterpret("eval true == true", Boolean.valueOf(true));
  }
  
  @Test
  public void notEqualsBool() {
    this.assertInterpret("eval true != false", Boolean.valueOf(true));
  }
  
  @Test
  public void and() {
    this.assertInterpret("eval true && !false", Boolean.valueOf(true));
  }
  
  @Test
  public void or() {
    this.assertInterpret("eval false || true", Boolean.valueOf(true));
  }
  
  @Test
  public void varRef() {
    this.assertInterpret("var i = 1 var j = i + 2 eval j+1", Integer.valueOf(4));
  }
  
  @Test
  public void varSameVarRef() {
    this.assertInterpret("var i = 1 eval i+i", Integer.valueOf(2));
  }
  
  @Test
  public void complex() {
    this.assertInterpret("eval ((5 * 3)+1) / (7 + 1)", Integer.valueOf(2));
  }
  
  public ExpressionsModel assertInterpret(final CharSequence input, final Object expected) {
    try {
      ExpressionsModel _parse = this._parseHelper.parse(input);
      final Procedure1<ExpressionsModel> _function = (ExpressionsModel it) -> {
        this._validationTestHelper.assertNoErrors(it);
        Assert.assertEquals(expected, this._expressionsInterpreter.interpret(IterableExtensions.<AbstractElement>last(it.getElements()).getExpression()));
      };
      return ObjectExtensions.<ExpressionsModel>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
