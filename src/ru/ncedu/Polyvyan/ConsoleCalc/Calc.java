package ru.ncedu.Polyvyan.ConsoleCalc;

/**
 * Created by dpolyvyan on 26.08.2016.
 */


public class Calc {
    public int number_count = 0;
    public double a;
    public double b;
    public char operation;


    /**
     * Функция проверяет, является ли текущая строка числом и заносит значения в соответствующие переменные.
     *
     * @param sIn Входная строка
     */
    public void operand(String sIn) throws Exception {
        number_count = number_count + 1;
        int res_int;
        double res_double;
        if (sIn.matches("[-+]?\\d+") || sIn.matches("^[+-]?\\d*\\.\\d+$|\\d+")) {
            switch (number_count) {
                case 1:
                    a = Double.parseDouble(sIn);
                    break;
                case 2:
                    b = Double.parseDouble(sIn);
                    number_count = 0;
                    break;
            }
        } else throw new Exception("Неверно введено число" + "\n");
    }

    /**
     * Функция проверяет, является ли текущая строка поддерживаемой арифметической операцией и заносит значение в
     * соответствующую переменную
     *
     * @param sIn Входная строка
     */
    public void isOp(String sIn) throws Exception {
        boolean isOpChar = false;

        char test = sIn.charAt(0);
        switch (test) {
            case '+':
                operation = '+';
                isOpChar = true;
                break;
            case '-':
                operation = '-';
                isOpChar = true;
                break;
            case '/':
                operation = '/';
                isOpChar = true;
                break;
            case '*':
                operation = '*';
                isOpChar = true;
                break;
            case '^':
                operation = '^';
                isOpChar = true;
                break;
        }
        ;
        if (isOpChar && sIn.length() == 1) {

        } else throw new Exception("Неверный сиивол операции" + "\n");

    }

    /**
     * Считает выражение и выводит результат
     * @param
     * @return double result
     */
    public double calculate() throws Exception {
        double res;
        switch (operation) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '/':
                if (b == 0) {
                    throw new Exception("Недопустимая операция " + "\n");
                }
                res = a / b;
                break;
            case '*':
                res = a * b;
                break;
            case '^':
                res = Math.pow(a, b);
                break;
            default:
                throw new Exception("Недопустимая операция ");
        }
        return res;
    }

}
