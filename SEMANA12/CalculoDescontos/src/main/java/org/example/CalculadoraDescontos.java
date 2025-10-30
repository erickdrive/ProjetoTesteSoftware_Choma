package org.example;

public class CalculadoraDescontos {

    public double calcular(double valorCompra) {
       if(valorCompra < 0){

           throw new IllegalArgumentException("valor da compra não pode ser negativo");
       }
        if(valorCompra < 100){
            return valorCompra;
        } else if (valorCompra <= 500) {
            return valorCompra - (valorCompra * 0.05);
        }else{
            return valorCompra - (valorCompra * 0.10);
        }
    }
}
