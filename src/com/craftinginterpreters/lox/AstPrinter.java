package com.craftinginterpreters.lox;

import java.util.Arrays;

class AstPrinter implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        return parenthesize(expr.name.lexeme, expr.value);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitCallExpr(Expr.Call expr) {
        StringBuilder builder = new StringBuilder();
        builder.append(expr.callee.accept(this)).append("(");

        for (Expr argument : expr.arguments) {
            builder.append(argument.accept(this)).append(", ");
        }

        if (!expr.arguments.isEmpty()) {
            builder.delete(builder.length() - 2, builder.length());
        }

        builder.append(")");
        return builder.toString();
    }

    @Override
    public String visitGetExpr(Expr.Get expr) {
        return parenthesize("get " + expr.name.lexeme, expr.object);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitSetExpr(Expr.Set expr) {
        return parenthesize("set " + expr.name.lexeme, expr.object, expr.value);
    }

    @Override
    public String visitSuperExpr(Expr.Super expr) {
        return "super." + expr.method.lexeme;
    }

    @Override
    public String visitThisExpr(Expr.This expr) {
        return "this";
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        return expr.name.lexeme;
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    public static void main(String[] args) {
        // Test 1
        Expr expression1 = new Expr.Binary(
                new Expr.Unary(new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(new Expr.Literal(45.67)));

        System.out.println(new AstPrinter().print(expression1));

        // Test 2
        Expr expression2 = new Expr.Binary(
                new Expr.Literal(1),
                new Token(TokenType.PLUS, "+", null, 1),
                new Expr.Binary(
                        new Expr.Literal(2),
                        new Token(TokenType.STAR, "*", null, 1),
                        new Expr.Literal(3)));

        System.out.println(new AstPrinter().print(expression2));

        // Test 3
        Expr expression3 = new Expr.Logical(
                new Expr.Variable(new Token(TokenType.IDENTIFIER, "x", null, 1)),
                new Token(TokenType.OR, "or", null, 1),
                new Expr.Literal(true));

        System.out.println(new AstPrinter().print(expression3));

        // Test 4
        Expr expression4 = new Expr.Call(
                new Expr.Variable(new Token(TokenType.IDENTIFIER, "foo", null, 1)),
                new Token(TokenType.LEFT_PAREN, "(", null, 1),
                Arrays.asList(new Expr.Literal("arg1"), new Expr.Literal("arg2")));

        System.out.println(new AstPrinter().print(expression4));
    }
}
