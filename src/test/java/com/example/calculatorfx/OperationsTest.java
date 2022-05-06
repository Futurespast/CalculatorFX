package com.example.calculatorfx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OperationsTest {
    @Test
    public void
    AdditionEvaluate_TwoPositiveOperands_AddTheNumbers(){
        Operand op1 = new Operand(2);
        Operand op2 = new Operand(3);
        Addition addition = new Addition();
        Operand result = addition.evaluate(op1, op2);
        assertEquals(result.getOperand(),5);

    }
    @Test
    public void
    SubtractionEvaluate_TwoPositiveOperands_SubtractionsTheNumbers(){
        Operand op1 = new Operand(2);
        Operand op2 = new Operand(3);
        Subtraction subtraction = new Subtraction();
        Operand sub = subtraction.evaluate(op1,op2);
        assertEquals(sub.getOperand(),-1);
    }
    @Test
    public void
    DivisionEvaluate_TwoPositiveOperands_DividesTheNumbers() {
        Operand op1 = new Operand(4);
        Operand op2 = new Operand(2);
        Division division = new Division();
        Operand div = division.evaluate(op1,op2);
        assertEquals(div.getOperand(),2);
    }
    @Test
    public void
    MultiplicationEvaluate_TwoPositiveOperands_MultiplyTheNumbers() {
        Operand op1 = new Operand(4);
        Operand op2 = new Operand(2);
        Multiplication multiplication = new Multiplication();
        Operand multi = multiplication.evaluate(op1,op2);
        assertEquals(multi.getOperand(),8);
    }
    @Test
    public void PostfixEvaluator_CorrectExpression_EvaluateToANumber(){
        PostFixEvaluator evaluator = new PostFixEvaluator();
        evaluator.addToken(new Operand(3));
        evaluator.addToken(new Operand(2));
        evaluator.addToken(new Multiplication());
        evaluator.addToken(new Operand(5));
        evaluator.addToken(new Addition());
        evaluator.addToken(new Operand(6));
        evaluator.addToken(new Operand(4));
        evaluator.addToken(new Multiplication());
        evaluator.addToken(new Addition());
        assertEquals(evaluator.evaluate(), 35);
    }
    @Test
    public void
    PostfixEvaluator_ExpressionWithOneOperandOnlyAndOneOperator_ThrowsArithmeticException()
    {
        Exception exception = assertThrows(ArithmeticException.class, ()->{
            PostFixEvaluator evaluator = new PostFixEvaluator();
            evaluator.addToken(new Operand(3));
            evaluator.addToken(new Multiplication());
            evaluator.evaluate();
        });
        String expectedMessage = "less than 2 operands in calculation";
        String actualMessage = exception.getMessage();
        //asserting the exception message if it contains
        //our expected message
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void
    PostfixEvaluator_ExpressionWithMoreThanOneOperandAtTheEnd_ThrowsArithmeticException()
    {
        Exception exception = assertThrows(ArithmeticException.class, ()->{
            PostFixEvaluator evaluator = new PostFixEvaluator();
            evaluator.addToken(new Operand(3));
            evaluator.addToken(new Operand(3));
            evaluator.addToken(new Multiplication());
            evaluator.addToken(new Operand(2));
            evaluator.evaluate();
        });
        String expectedMessage = "more than one operand left in the stack";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void PostfixEvaluator_TestOurTeacherAskedUsToWrite_WeDontKnowYet(){
        Exception exception = assertThrows(ArithmeticException.class, ()->{
        PostFixEvaluator evaluator = new PostFixEvaluator();
        evaluator.evaluate();
        });
        String expectedMessage = "No operands or operators were entered";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    }


