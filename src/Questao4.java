@FunctionalInterface
interface OperacaoMatematica {
    double executar(double a, double b);
}

public class Questao4 {
    public static void main(String[] args) {
        OperacaoMatematica soma          = (a, b) -> a + b;
        OperacaoMatematica subtracao     = (a, b) -> a - b;
        OperacaoMatematica multiplicacao = (a, b) -> a * b;
        OperacaoMatematica divisao       = (a, b) -> {
            if (b == 0) {
                throw new ArithmeticException("Divisão por zero!");
            }
            return a / b;
        };

        double x = 10, y = 4;
        System.out.println(x + " + " + y + " = " + soma.executar(x, y));
        System.out.println(x + " - " + y + " = " + subtracao.executar(x, y));
        System.out.println(x + " * " + y + " = " + multiplicacao.executar(x, y));
        System.out.println(x + " / " + y + " = " + divisao.executar(x, y));
    }
}
