package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import android.view.View.OnClickListener;

public class CalculadoraActivity extends AppCompatActivity {// Classe publica que herda (extends) AppCompatActivity

    //21 a 27 são as variaveis
    private Calculadora calc;

    private boolean usuarioEstaDigitandoUmNumero; //variavel que retorna boolean = é um tipo de dado que permite apenas dois valores
    private boolean separadorDecimalFoiDigitado;
    private TextView txtVisor;
    private String separador;
    private char separadorChar; //variavel que retorna char = O tipo char é utilizado para representar caracteres

    @Override
    protected void onCreate(Bundle savedInstanceState) { // metodo protected (metodo que só a classe CalculadoraActivity e quem herdar dela irar utilizar),
        // onCreate é o método responsável por carregar os layouts e outras operações de inicialização.

        super.onCreate(savedInstanceState);//
        setContentView(R.layout.activity_calculadora);//

        calc = new Calculadora();

        usuarioEstaDigitandoUmNumero = false;

        separadorDecimalFoiDigitado = false;

        txtVisor = (TextView)findViewById(R.id.txtVisor); // instancia
        txtVisor.setText("0"); //irar jogar no txtVisor o numero 0

        Locale localizacao = getResources().getConfiguration().locale;

        NumberFormat formatador = NumberFormat.getInstance(localizacao);

        //Definimos inicialmente o separador como virgula, que é o padrão nacional
        separadorChar = ',';
        if (formatador instanceof DecimalFormat){
            DecimalFormatSymbols simbolo =((DecimalFormat)formatador).getDecimalFormatSymbols();
            separadorChar = simbolo.getDecimalSeparator();
        }

        separador = String.valueOf(separadorChar);

        //Não se esqueça de trocar aqui o id do seu botão separador, se necessário!
        Button btnSeparador = (Button)findViewById(R.id.button19);
        btnSeparador.setText(separador);

        //Uso da fonte digital
        final Typeface fonteDigital = Typeface.createFromAsset(this.getAssets(), "DS-DIGI.TTF"); // fonte da calculadora

        txtVisor.setTypeface(fonteDigital);

        txtVisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Todo Auto-Generated method stub
                  if (txtVisor.getTypeface().equals(fonteDigital)){
                      txtVisor.setTypeface(Typeface.DEFAULT);
               // if (txtVisor.getTypeface().equals(fonteDigital)){
                 //   txtVisor.getTypeface(Typeface.DEFAULT);

                }else {
                    txtVisor.setTypeface(fonteDigital);
                }
            }
        });
        Toast.makeText(this, "Toque no visor para trocar sua fonte!", Toast.LENGTH_LONG).show();
    }
    public void onClickNumeros(View v){
        Button botaoTocado = (Button)v;
        String digito = botaoTocado.getText().toString();

        String textoNoVisor = txtVisor.getText().toString();

        if (!usuarioEstaDigitandoUmNumero || textoNoVisor.equals("0")){
            txtVisor.setText(digito);
            if (!digito.equals("0")) {
                usuarioEstaDigitandoUmNumero = true;
              }
            }else {
                txtVisor.setText(textoNoVisor + digito);
            }
        }

    public void onClickOperacoes(View v){ //Metodo publico "onClickOperacoes" retorna void (vazio)
        Button botaoTocado = (Button)v;
        String operacao = botaoTocado.getText().toString();

        //if (operacao.equals(",") && !separadorDecimalFoiDigitado){
        if (operacao.equals(separador) && !separadorDecimalFoiDigitado) {
            separadorDecimalFoiDigitado = true;
            if (!usuarioEstaDigitandoUmNumero)
                //txtVisor.setText("0" + ",");
                txtVisor.setText("0" + separador);
            else
                //  txtVisor.setText(txtVisor.getText().toString()+",");
                txtVisor.setText(txtVisor.getText().toString() + separador);
            usuarioEstaDigitandoUmNumero = true;
            //}else if (!operacao.equals(",")){
              }else if (!operacao.equals(separador)){
                //String valorSemVirgula =
                 //txtVisor.getText().toString().replace(',' , ',');
             String valorSemVirgula = txtVisor.getText().toString().replace(separadorChar, '.');

            calc.setOperando(Double.parseDouble(valorSemVirgula));
            calc.realizarOperacao(operacao);

            String textoResultado = String.valueOf(calc.getOperando());

            if (textoResultado.endsWith(".0")){
                textoResultado =
                  textoResultado.substring(0, textoResultado.length() - 2);

            }
            //txtVisor.setText(textoResultado.replace('.', ','));
              txtVisor.setText(textoResultado.replace('.' , separadorChar));
            usuarioEstaDigitandoUmNumero = false;
            separadorDecimalFoiDigitado = false;
        }

    }

    public void onClickMemoria(View v){ //metodo publico que retorna void (vazio)
        Button botaoTocado = (Button)v; //botaoTocado é o nome do Button
        String operacaoMemoria = botaoTocado.getText().toString(); //metodo operacaoMemoria Que retorna a String que recebe botaoTocado

       // String valorSemVirgula = txtVisor.getText().toString().replace(',' , '.');
          String valorSemVirgula = txtVisor.getText().toString().replace(separadorChar , '.');

        calc.setOperando(Double.parseDouble(valorSemVirgula));
        calc.realizarOperacaoDeMemoria(operacaoMemoria);

        usuarioEstaDigitandoUmNumero = false;
    }
}
