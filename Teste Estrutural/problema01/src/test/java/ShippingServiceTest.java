import org.example.checkout.ShippingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShippingServiceTest {
    @Test
    public void freteGratisQuandoSubtotalMaior300() {
        var service = new ShippingService();
        assertEquals(0.0, service.calculate("SUL", 10, 350, false));
    }

    @Test
    public void freteSulPeso2() {
        var service = new ShippingService();
        assertEquals(20.0, service.calculate("SUL", 2, 100, false));
    }

    @Test
    public void freteSulPeso3() {
        var service = new ShippingService();
        assertEquals(35.0, service.calculate("SUL", 3, 100, false));
    }

    @Test
    public void freteSulPeso6() {
        var service = new ShippingService();
        assertEquals(50.0, service.calculate("SUL", 6, 100, false));
    }

    @Test
    public void freteNortePeso2() {
        var service = new ShippingService();
        assertEquals(30.0, service.calculate("NORTE", 2, 100, false));
    }

    @Test
    public void freteNortePeso4() {
        var service = new ShippingService();
        assertEquals(55.0, service.calculate("NORTE", 4, 100, false));
    }

    @Test
    public void freteNortePeso6() {
        var service = new ShippingService();
        assertEquals(80.0, service.calculate("NORTE", 6, 100, false));
    }

    @Test
    public void freteRegiaoDesconhecida() {
        var service = new ShippingService();
        assertEquals(40.0, service.calculate("CENTRO", 4, 100, false));
    }

    @Test
    public void freteGratisQuandoFreeShippingTrue() {
        var service = new ShippingService();
        assertEquals(0.0, service.calculate("SUL", 4, 100, true));
    }
}
