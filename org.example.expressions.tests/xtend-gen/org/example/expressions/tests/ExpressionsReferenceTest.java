package org.example.expressions.tests;

import javax.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.example.expressions.expressions.ExpressionsModel;
import org.example.expressions.expressions.ExpressionsPackage;
import org.example.expressions.expressions.Variable;
import org.example.expressions.validation.ExpressionsModelUtil;
import org.example.expressions.validation.ExpressionsValidator;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class ExpressionsReferenceTest {
  @Inject
  @Extension
  private ParseHelper<ExpressionsModel> _parseHelper;
  
  @Inject
  @Extension
  private ExpressionsModelUtil _expressionsModelUtil;
  
  @Inject
  @Extension
  private ValidationTestHelper _validationTestHelper;
  
  @Test
  public void variablesBeforeVariable() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("eval true");
      _builder.newLine();
      _builder.append("// (0)");
      _builder.newLine();
      _builder.append("var i = 0");
      _builder.newLine();
      _builder.append("// (1)");
      _builder.newLine();
      _builder.append("eval i + 10");
      _builder.newLine();
      _builder.append("// (2)");
      _builder.newLine();
      _builder.append("var j = i");
      _builder.newLine();
      _builder.append("// (3)");
      _builder.newLine();
      _builder.append("eval i + j");
      _builder.newLine();
      _builder.append("// (4)");
      _builder.newLine();
      ExpressionsModel _parse = this._parseHelper.parse(_builder);
      final Procedure1<ExpressionsModel> _function = (ExpressionsModel it) -> {
        this.assertVariablesDefinedBefore(it, 0, "");
        this.assertVariablesDefinedBefore(it, 1, "");
        this.assertVariablesDefinedBefore(it, 2, "i");
        this.assertVariablesDefinedBefore(it, 3, "i");
        this.assertVariablesDefinedBefore(it, 4, "i,j");
      };
      ObjectExtensions.<ExpressionsModel>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testForwardReferenceInExpression() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = j var j = 10");
      ExpressionsModel _parse = this._parseHelper.parse(_builder);
      final Procedure1<ExpressionsModel> _function = (ExpressionsModel it) -> {
        this._validationTestHelper.assertError(it, ExpressionsPackage.eINSTANCE.getVariableRef(), 
          ExpressionsValidator.FORWARD_REFERENCE, 
          "variable forward reference not allowed: \'j\'");
        Assert.assertEquals(1, this._validationTestHelper.validate(it).size());
      };
      ObjectExtensions.<ExpressionsModel>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testNoForwardReference() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var j = 10 var i = j");
      this._validationTestHelper.assertNoErrors(this._parseHelper.parse(_builder));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void assertVariablesDefinedBefore(final ExpressionsModel model, final int elemIndex, final CharSequence expectedVars) {
    final Function1<Variable, String> _function = (Variable it) -> {
      return it.getName();
    };
    Assert.assertEquals(expectedVars, 
      IterableExtensions.join(IterableExtensions.<Variable, String>map(this._expressionsModelUtil.variablesDefinedBefore(model.getElements().get(elemIndex)), _function), ","));
  }
}
