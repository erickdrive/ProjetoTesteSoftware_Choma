import org.example.CalculadoraDescontos;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculoDescontoTest {
    @Test
    public void retornarMesmoValorAbaixoDe100() {
        CalculadoraDescontos calculadoraDescontos = new CalculadoraDescontos();
        double resultado = calculadoraDescontos.calcular(50.0);
        assertEquals(50.0, resultado, 0.001);
    }

    @Test
    public void aplica5PorCentoDeDescontoComprasEntre100e500() {
        CalculadoraDescontos calculadoraDescontos = new CalculadoraDescontos();
        double resultado = calculadoraDescontos.calcular(200.0);
        assertEquals(190.0, resultado, 0.001);
    }

    @Test
    public void aplicar10PorCentoDeDescontoComprasAcimaDe500() {
        CalculadoraDescontos calculadoraDescontos = new CalculadoraDescontos();
        double resultado = calculadoraDescontos.calcular(600.0);
        assertEquals(540.0, resultado, 0.001);
    }

    @Test
    public void lancarExcecaoValorNegativo() {
        CalculadoraDescontos calculadoraDescontos = new CalculadoraDescontos();
        assertThrows(IllegalArgumentException.class, () -> calculadoraDescontos.calcular(-50.0));
    }
}
