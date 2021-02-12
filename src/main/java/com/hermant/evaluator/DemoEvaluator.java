package com.hermant.evaluator;

import com.hermant.generated.DemoExpressionBaseVisitor;
import com.hermant.generated.DemoExpressionLexer;
import com.hermant.generated.DemoExpressionParser;
import com.hermant.generated.DemoExpressionParser.*;
import org.antlr.v4.runtime.*;

import java.math.BigDecimal;
import java.math.MathContext;

public class DemoEvaluator extends DemoExpressionBaseVisitor<BigDecimal> {

    public BigDecimal evaluate(String expression) {
        DemoExpressionLexer lexer = new DemoExpressionLexer(CharStreams.fromString(expression));

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DemoExpressionParser parser = new DemoExpressionParser(tokens);
        parser.removeErrorListeners();

        EvaluatorErrorReporter reporter = new EvaluatorErrorReporter();
        parser.addErrorListener(reporter);

        ExpressionContext tree = parser.expression();

        if(reporter.getErrors() != 0) {
            return null;
        }

        return visit(tree.additive());
    }

    @Override
    public BigDecimal visitAdd(AddContext ctx) {
        BigDecimal left = visit(ctx.multiplicative());
        BigDecimal right = visit(ctx.additive());

        return left.add(right);
    }

    @Override
    public BigDecimal visitDivide(DivideContext ctx) {
        BigDecimal left = visit(ctx.multiplicative());
        BigDecimal right = visit(ctx.unary());

        return left.divide(right, MathContext.DECIMAL128);
    }

    @Override
    public BigDecimal visitBool(BoolContext ctx) {
        return Boolean.parseBoolean(ctx.BOOL().getText()) ? new BigDecimal(1) : new BigDecimal(0);
    }

    @Override
    public BigDecimal visitModulo(ModuloContext ctx) {
        BigDecimal left = visit(ctx.multiplicative());
        BigDecimal right = visit(ctx.unary());

        return left.remainder(right);
    }

    @Override
    public BigDecimal visitUnaryPlus(UnaryPlusContext ctx) {
        return visit(ctx.unary());
    }

    @Override
    public BigDecimal visitPars(ParsContext ctx) {
        return visit(ctx.additive());
    }

    @Override
    public BigDecimal visitMultiply(MultiplyContext ctx) {
        BigDecimal left = visit(ctx.multiplicative());
        BigDecimal right = visit(ctx.unary());

        return left.multiply(right);
    }

    @Override
    public BigDecimal visitReal(RealContext ctx) {
        return new BigDecimal(ctx.REAL().getText());
    }

    @Override
    public BigDecimal visitSubtract(SubtractContext ctx) {
        BigDecimal left = visit(ctx.additive());
        BigDecimal right = visit(ctx.multiplicative());

        return left.subtract(right);
    }

    @Override
    public BigDecimal visitUnaryMinus(UnaryMinusContext ctx) {
        return visit(ctx.unary()).negate();
    }

    @Override
    public BigDecimal visitInt(IntContext ctx) {
        return new BigDecimal(ctx.INT().getText());
    }
}
