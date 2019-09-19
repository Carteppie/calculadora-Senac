package com.example.calculadora;

public class Calculadora {
    // Na linha 5 a 8 foi declarado 4 variaveis fundamentais para o funcionamento da Calculadora. As variaveis são do tipo PRIVATE então apenas a classe "Calculadora" Ira utilizar elas.
    private double operando;
    private double operandoAnterior;
    private String operadorAnterior;
    private double memoria;

    // Na linha 11 a 17 Foi Criado um construtor padrão (Sem argumentos) para a calculadora
    public Calculadora(){
        // = recebe
        operando = 0; // recebe 0
        operandoAnterior = 0; // recebe 0
        operadorAnterior = ""; //recebe vazio
        memoria = 0; // recebe 0
    }

    public double getOperando(){ //Metodo publico do tipo getter, o metodo get é usado "pegar" alguma coisa,
        // ele sera utilizado para poder colocar o resultado de uma operação no visor.
        return operando; //Retorna a variavel interna OPERANDO que vale 0.
    }

    public void setOperando(double operando){ //set ira exibir no visor. metodo publico que retorna vazio (void). variavel "operando" que retorna double
        this.operando = operando; // This é usado para fazer auto-referência ao próprio contexto em que se encontra.
        // Resumidamente, this sempre será a própria classe ou o objeto já instanciado
    }

    private void realizarOperacaoAnterior(){ // metodo que retorna void "vazio"

        if (!operadorAnterior.equals("")){ // verifica se o (operadorAnterior) não (por isso o !) é igual a vazio
            if (operadorAnterior.equals("+")){ //se a pessoa escolher o "+" ira realizar a conta de adição.
                operando = operandoAnterior + operando;
            }else if (operadorAnterior.equals("-")){
                operando = operandoAnterior - operando;
            }else if (operadorAnterior.equals("x")){
                operando = operandoAnterior * operando;
            }else if (operadorAnterior.equals("/")){
                if (operando != 0){
                    operando = operandoAnterior / operando;
                }
            }
        }
    }
    public void realizarOperacao(String op){//metodo "realizarOperacao" publico que retorna void (vazio)
        if (op.equals("%")){ // if= se
            operando = (operandoAnterior * operando) / 100;

        }else if (op.equals("+/-")){ // equals = sobrescrevê-lo para implementar suas próprias regras de comparação entre objetos de uma mesma classe
            operando = -operando;

        }else if (op.equals("C")){
            operando = 0;
            memoria = 0;
            operadorAnterior = "";
        }else {
            realizarOperacaoAnterior();
            operadorAnterior = op;
            operandoAnterior = operando;
        }

    }
    // metodo publico que retorna void (vazio)
    public void realizarOperacaoDeMemoria(String opm) {

        if (opm.equals("mc")){

        memoria = 0;
        }else if (opm.equals("m+")){
            memoria += operando; // += ira juntar o que estiver na "memoria" mas o "operando"
        }else if (opm.equals("m-")){
            memoria -= operando; 
        }else if (opm.equals("mr")){
            operando = memoria;
        }else if (opm.equals("mr")){
            operando = memoria;
        }
    }
}
