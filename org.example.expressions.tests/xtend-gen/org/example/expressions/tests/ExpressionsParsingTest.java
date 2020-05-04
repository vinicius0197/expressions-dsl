package org.example.expressions.tests;

import javax.inject.Inject;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.example.expressions.expressions.AbstractElement;
import org.example.expressions.expressions.And;
import org.example.expressions.expressions.BoolConstant;
import org.example.expressions.expressions.Comparison;
import org.example.expressions.expressions.Equality;
import org.example.expressions.expressions.Expression;
import org.example.expressions.expressions.ExpressionsModel;
import org.example.expressions.expressions.IntConstant;
import org.example.expressions.expressions.Minus;
import org.example.expressions.expressions.MulOrDiv;
import org.example.expressions.expressions.Not;
import org.example.expressions.expressions.Or;
import org.example.expressions.expressions.Plus;
import org.example.expressions.expressions.StringConstant;
import org.example.expressions.expressions.VariableRef;
import org.example.expressions.tests.ExpressionsInjectorProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(ExpressionsInjectorProvider.class)
@SuppressWarnings("all")
public class ExpressionsParsingTest {
  @Inject
  @Extension
  private ParseHelper<ExpressionsModel> _parseHelper;
  
  @Test
  public void testEvalIntConstant() {
    try {
      Assert.assertNotNull(this._parseHelper.parse("eval 10"));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testEvalStringConstant() {
    try {
      Assert.assertNotNull(this._parseHelper.parse("eval \"a string\""));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testEvalBoolConstant() {
    try {
      Assert.assertNotNull(this._parseHelper.parse("eval true"));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testVariable() {
    try {
      Assert.assertNotNull(this._parseHelper.parse("var i = 10"));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testVariableReference() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("var i = 10");
      _builder.newLine();
      _builder.append("eval i");
      _builder.newLine();
      ExpressionsModel _parse = this._parseHelper.parse(_builder);
      final Procedure1<ExpressionsModel> _function = (ExpressionsModel it) -> {
        Expression _expression = IterableExtensions.<AbstractElement>last(it.getElements()).getExpression();
        Assert.assertSame(((VariableRef) _expression).getVariable(), IterableExtensions.<AbstractElement>head(it.getElements()));
      };
      ObjectExtensions.<ExpressionsModel>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testPlus() {
    this.assertRepr("10 + 5 + 1 + 2", "(((10 + 5) + 1) + 2)");
  }
  
  @Test
  public void testParenthesis() {
    try {
      Expression _expression = IterableExtensions.<AbstractElement>head(this._parseHelper.parse("eval (10)").getElements()).getExpression();
      Assert.assertEquals(10, 
        ((IntConstant) _expression).getValue());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testPlusWithParenthesis() {
    this.assertRepr("( 10 + 5 ) + ( 1 + 2 )", "((10 + 5) + (1 + 2))");
  }
  
  @Test
  public void testMinus() {
    this.assertRepr("10 + 5 - 1 - 2", "(((10 + 5) - 1) - 2)");
  }
  
  @Test
  public void testMulOrDiv() {
    this.assertRepr("10 * 5 / 1 * 2", "(((10 * 5) / 1) * 2)");
  }
  
  @Test
  public void testPlusMulPrecedence() {
    this.assertRepr("10 + 5 * 2 - 5 / 1", "((10 + (5 * 2)) - (5 / 1))");
  }
  
  @Test
  public void testComparison() {
    this.assertRepr("10 <= 5 < 2 > 5", "(((10 <= 5) < 2) > 5)");
  }
  
  @Test
  public void testEqualityAndComparison() {
    this.assertRepr("true == 5 <= 2", "(true == (5 <= 2))");
  }
  
  @Test
  public void testAndOr() {
    this.assertRepr("true || false && 1 < 0", "(true || (false && (1 < 0)))");
  }
  
  @Test
  public void testNot() {
    this.assertRepr("!true||false", "((!true) || false)");
  }
  
  @Test
  public void testNotWithParentheses() {
    this.assertRepr("!(true||false)", "(!(true || false))");
  }
  
  @Test
  public void testPrecedences() {
    this.assertRepr("!true||false&&1>(1/3+5*2)", "((!true) || (false && (1 > ((1 / 3) + (5 * 2)))))");
  }
  
  private ExpressionsModel assertRepr(final CharSequence input, final CharSequence expected) {
    try {
      ExpressionsModel _parse = this._parseHelper.parse(("eval " + input));
      final Procedure1<ExpressionsModel> _function = (ExpressionsModel it) -> {
        Assert.assertEquals(expected, 
          this.stringRepr(IterableExtensions.<AbstractElement>last(it.getElements()).getExpression()));
      };
      return ObjectExtensions.<ExpressionsModel>operator_doubleArrow(_parse, _function);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private String stringRepr(final Expression e) {
    String _switchResult = null;
    boolean _matched = false;
    if (e instanceof Plus) {
      _matched=true;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("(");
      String _stringRepr = this.stringRepr(((Plus)e).getLeft());
      _builder.append(_stringRepr);
      _builder.append(" + ");
      String _stringRepr_1 = this.stringRepr(((Plus)e).getRight());
      _builder.append(_stringRepr_1);
      _builder.append(")");
      _switchResult = _builder.toString();
    }
    if (!_matched) {
      if (e instanceof Minus) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("(");
        String _stringRepr = this.stringRepr(((Minus)e).getLeft());
        _builder.append(_stringRepr);
        _builder.append(" - ");
        String _stringRepr_1 = this.stringRepr(((Minus)e).getRight());
        _builder.append(_stringRepr_1);
        _builder.append(")");
        _switchResult = _builder.toString();
      }
    }
    if (!_matched) {
      if (e instanceof MulOrDiv) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("(");
        String _stringRepr = this.stringRepr(((MulOrDiv)e).getLeft());
        _builder.append(_stringRepr);
        _builder.append(" ");
        String _op = ((MulOrDiv)e).getOp();
        _builder.append(_op);
        _builder.append(" ");
        String _stringRepr_1 = this.stringRepr(((MulOrDiv)e).getRight());
        _builder.append(_stringRepr_1);
        _builder.append(")");
        _switchResult = _builder.toString();
      }
    }
    if (!_matched) {
      if (e instanceof Comparison) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("(");
        String _stringRepr = this.stringRepr(((Comparison)e).getLeft());
        _builder.append(_stringRepr);
        _builder.append(" ");
        String _op = ((Comparison)e).getOp();
        _builder.append(_op);
        _builder.append(" ");
        String _stringRepr_1 = this.stringRepr(((Comparison)e).getRight());
        _builder.append(_stringRepr_1);
        _builder.append(")");
        _switchResult = _builder.toString();
      }
    }
    if (!_matched) {
      if (e instanceof Equality) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("(");
        String _stringRepr = this.stringRepr(((Equality)e).getLeft());
        _builder.append(_stringRepr);
        _builder.append(" ");
        String _op = ((Equality)e).getOp();
        _builder.append(_op);
        _builder.append(" ");
        String _stringRepr_1 = this.stringRepr(((Equality)e).getRight());
        _builder.append(_stringRepr_1);
        _builder.append(")");
        _switchResult = _builder.toString();
      }
    }
    if (!_matched) {
      if (e instanceof And) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("(");
        String _stringRepr = this.stringRepr(((And)e).getLeft());
        _builder.append(_stringRepr);
        _builder.append(" && ");
        String _stringRepr_1 = this.stringRepr(((And)e).getRight());
        _builder.append(_stringRepr_1);
        _builder.append(")");
        _switchResult = _builder.toString();
      }
    }
    if (!_matched) {
      if (e instanceof Or) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("(");
        String _stringRepr = this.stringRepr(((Or)e).getLeft());
        _builder.append(_stringRepr);
        _builder.append(" || ");
        String _stringRepr_1 = this.stringRepr(((Or)e).getRight());
        _builder.append(_stringRepr_1);
        _builder.append(")");
        _switchResult = _builder.toString();
      }
    }
    if (!_matched) {
      if (e instanceof Not) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("(!");
        String _stringRepr = this.stringRepr(((Not)e).getExpression());
        _builder.append(_stringRepr);
        _builder.append(")");
        _switchResult = _builder.toString();
      }
    }
    if (!_matched) {
      if (e instanceof IntConstant) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        int _value = ((IntConstant)e).getValue();
        _builder.append(_value);
        _switchResult = _builder.toString();
      }
    }
    if (!_matched) {
      if (e instanceof StringConstant) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _value = ((StringConstant)e).getValue();
        _builder.append(_value);
        _switchResult = _builder.toString();
      }
    }
    if (!_matched) {
      if (e instanceof BoolConstant) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _value = ((BoolConstant)e).getValue();
        _builder.append(_value);
        _switchResult = _builder.toString();
      }
    }
    if (!_matched) {
      if (e instanceof VariableRef) {
        _matched=true;
        StringConcatenation _builder = new StringConcatenation();
        String _name = ((VariableRef)e).getVariable().getName();
        _builder.append(_name);
        _switchResult = _builder.toString();
      }
    }
    return _switchResult.toString();
  }
}
