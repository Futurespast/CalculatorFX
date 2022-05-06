package com.example.calculatorfx;

import java.util.ArrayDeque;

public class PostFixEvaluator {

    private ArrayDeque<Token> expression = new ArrayDeque<>();

    public void addToken(Token t)
    {
        expression.add(t);
    }

    public double evaluate(){
        if(expression.size()==0){
            throw new ArithmeticException("No operands or operators were entered");
        }
        ArrayDeque<Operand> stack = new ArrayDeque<>();
        for (Token t:expression) {
            if(t.isOperand()){
             stack.push((Operand) t);
            }
            else{
                if(stack.size()<2){
                    throw new ArithmeticException("less than 2 operands in calculation");
                    }
                t =(Operator) t;
                Operand op1 = stack.pop();
                Operand op2 = stack.pop();
               Operand result= ((Operator) t).evaluate(op1,op2);
                stack.push(result);
                }
            }
        if(stack.size()>1){
            throw new ArithmeticException("more than one operand left in the stack");
        }

        return stack.pop().getOperand();
        }

    }

