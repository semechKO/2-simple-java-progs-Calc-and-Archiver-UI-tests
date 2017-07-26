package ru.ncedu.Polyvyan.ConsoleCalc;

/**
 * Created by dpolyvyan on 26.08.2016.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CalcMain extends Calc {
    public static void main(String[] args) throws Exception {
        BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
        String sIn;
        Calc calc = new Calc();
        while (true){
            try {
                System.out.println("Введте первое число:");
                sIn = d.readLine();
                calc.operand(sIn);
                System.out.println("Введите символ арифметической операции(+,-,/,*)");
                sIn = d.readLine();
                calc.isOp(sIn);
                System.out.println("Введте второе число:");
                sIn = d.readLine();
                calc.operand(sIn);
                System.out.println("Результат равен: "+"\n" + calc.calculate()+"\n");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }}
}