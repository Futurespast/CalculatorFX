package com.example.calculatorfx;

public class Operand implements Token{
    private double value;

    public Operand(double value){
        this.value=value;
    }

    public double getOperand() {
        return value;
    }

    @Override
    public boolean isOperand() {
        return true;
    }

}
