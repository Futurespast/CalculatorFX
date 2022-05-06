package com.example.calculatorfx;

public class Division extends Operator {
    @Override
    public Operand evaluate(Operand op1, Operand op2) {
        double result;
        result = op1.getOperand()/op2.getOperand();
        Operand opResult= new Operand(result);
        return opResult ;
    }
}
