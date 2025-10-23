import org.example.checkout.CouponResult;
import org.example.checkout.CouponService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CouponServiceTest {
    private final CouponService service = new CouponService();

    @Test
    void testDesc10SempreValido() {
        CouponResult result = service.evaluate("DESC10", LocalDate.now(), null, 50.0);
        assertEquals(0.10, result.getPercent());
        assertFalse(result.isFreeShipping());
    }

    @Test
    void testDesc20ValidoComSubtotalEValidade() {
        LocalDate hoje = LocalDate.of(2025, 10, 23);
        LocalDate expira = LocalDate.of(2025, 10, 25);

        CouponResult result = service.evaluate("DESC20", hoje, expira, 150.0);
        assertEquals(0.20, result.getPercent());
        assertFalse(result.isFreeShipping());
    }

    @Test
    void testDesc20Expirado() {
        LocalDate hoje = LocalDate.of(2025, 10, 26);
        LocalDate expira = LocalDate.of(2025, 10, 25);
        CouponResult result = service.evaluate("DESC20", hoje, expira, 150.0);
        assertEquals(0.0, result.getPercent());
        assertFalse(result.isFreeShipping());
    }

    @Test
    void testDesc20ComSubtotalMenorQue100() {
        LocalDate hoje = LocalDate.now();
        LocalDate expira = hoje.plusDays(2);
        CouponResult result = service.evaluate("DESC20", hoje, expira, 90.0);
        assertEquals(0.0, result.getPercent());
        assertFalse(result.isFreeShipping());
    }

    @Test
    void testFreteGratisAtivo() {
        CouponResult result = service.evaluate("FRETEGRATIS", LocalDate.now(), null, 80.0);
        assertEquals(0.0, result.getPercent());
        assertTrue(result.isFreeShipping());
    }

    @Test
    void testCodigoDesconhecido() {
        CouponResult result = service.evaluate("OUTRODESCONTO", LocalDate.now(), null, 200.0);
        assertEquals(0.0, result.getPercent());
        assertFalse(result.isFreeShipping());
    }

    @Test
    void testCodigoNuloOuVazio() {
        CouponResult result1 = service.evaluate(null, LocalDate.now(), null, 200.0);
        assertEquals(0.0, result1.getPercent());
        assertFalse(result1.isFreeShipping());

        CouponResult result2 = service.evaluate("   ", LocalDate.now(), null, 200.0);
        assertEquals(0.0, result2.getPercent());
        assertFalse(result2.isFreeShipping());
    }
}
