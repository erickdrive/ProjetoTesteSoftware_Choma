import org.example.checkout.CouponResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CouponResultTest {
    @Test
    public void deveAtivarFreteGratisQuandoTrue() {
        CouponResult couponResult = new CouponResult(0.10, true);
        couponResult.setFreeShipping(true);
        assertTrue(couponResult.isFreeShipping(), "O frete grátis deveria estar ativado");
    }

    @Test
    public void deveDesativarFreteGratisQuandoFalse() {
        CouponResult couponResult = new CouponResult(0.10, false);
        couponResult.setFreeShipping(false);
        assertFalse(couponResult.isFreeShipping(), "O frete grátis deveria estar desativado");
    }

    @Test
    public void deveDefinirPercentualCorretamente() {
        CouponResult couponResult = new CouponResult(0.10, true);
        couponResult.setPercent(20.0);
        assertEquals(20.0, couponResult.getPercent(), 0.0001,
                "O percentual deveria ser definido corretamente para 20.0");
    }

    @Test
    public void deveAceitarPercentualZero() {
        CouponResult couponResult = new CouponResult(0.10, false);
        couponResult.setPercent(0.0);
        assertEquals(0.0, couponResult.getPercent(), 0.0001,
                "O percentual deveria permitir valor zero");
    }

    @Test
    public void deveAceitarPercentualNegativo() {
        CouponResult couponResult = new CouponResult(0.10, false);
        couponResult.setPercent(-5.0);
        assertEquals(-5.0, couponResult.getPercent(), 0.0001,
                "O percentual negativo deveria ser armazenado corretamente");
    }
}
