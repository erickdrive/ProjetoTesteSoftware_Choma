import org.example.checkout.*;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CheckoutServiceTest {
    @Test
    public void deveCalcularBasicoSemDescontosEImpostoApenasNaoBook() {
        var couponSvc = new CouponService();
        var shipSvc = new ShippingService();
        var service = new CheckoutService(couponSvc, shipSvc);

        var itens = List.of(
                new Item("BOOK", 100.00, 1),
                new Item("ELETRONICO", 50.00, 2) // tributável
        );

        var res = service.checkout(
                itens,
                CustomerTier.BASIC,
                false,
                "SUL",
                3.0,
                null,
                LocalDate.now(),
                null
        );

        assertEquals(200.00, res.subtotal);          // 100 + (50*2)
        assertEquals(0.00, res.discountValue);
        // imposto 12% sobre parte tributável: 100 (eletrônicos)
        assertEquals(12.00, res.tax);
        // frete SUL com peso 3 → 35
        assertEquals(35.00, res.shipping);
        assertEquals(247.00, res.total);
    }

    @Test
    public void testeCheckoutCustomerTierSilver() {
        var couponSvc = new CouponService();
        var shipSvc = new ShippingService();
        var service = new CheckoutService(couponSvc, shipSvc);

        var itens = List.of(new Item("BOOK", 100.00, 1));

        var res = service.checkout(
                itens,
                CustomerTier.SILVER,
                false,
                "SUL",
                3.0,
                null,
                LocalDate.now(),
                null
        );

        assertEquals(100.00, res.subtotal);
        assertEquals(5.00, res.discountValue);
    }

    @Test
    public void deveAplicarDescontoTierGoldEPrimeiraCompraComCupom() {
        var couponSvc = new CouponService();
        var shipSvc = new ShippingService();
        var service = new CheckoutService(couponSvc, shipSvc);

        var itens = List.of(new Item("ELETRONICO", 100.00, 1));

        var res = service.checkout(
                itens,
                CustomerTier.GOLD,
                true,
                "SUL",
                4.0,
                "DESC10",
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );
        assertEquals(25.00, res.discountValue);
    }

    @Test
    public void deveNaoAplicarImpostoParaItensBook() {
        var couponSvc = new CouponService();
        var shipSvc = new ShippingService();
        var service = new CheckoutService(couponSvc, shipSvc);

        var itens = List.of(new Item("BOOK", 50.00, 2));

        var res = service.checkout(
                itens,
                CustomerTier.BASIC,
                false,
                "SUL",
                2.0,
                null,
                LocalDate.now(),
                null
        );

        // todo valor é BOOK → imposto 0
        assertEquals(100.00, res.subtotal);
        assertEquals(0.00, res.tax);
    }

    @Test
    public void deveAplicarFreteGratisQuandoCupomPermitir() {
        var couponSvc = new CouponService();
        var shipSvc = new ShippingService();
        var service = new CheckoutService(couponSvc, shipSvc);

        var itens = List.of(new Item("ELETRONICO", 100.00, 1));

        var res = service.checkout(
                itens,
                CustomerTier.BASIC,
                false,
                "SUL",
                3.0,
                "FRETEGRATIS",
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        assertEquals(0.00, res.shipping);
    }

    @Test
    public void deveAplicarCupomDESC20QuandoSubtotalMaiorIgual100() {
        var couponSvc = new CouponService();
        var shipSvc = new ShippingService();
        var service = new CheckoutService(couponSvc, shipSvc);

        var itens = List.of(
                new Item("ELETRONICO", 50.00, 2)
        ); // subtotal = 100

        var res = service.checkout(
                itens,
                CustomerTier.BASIC,
                false,
                "SUL",
                3.0,
                "DESC20",
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        assertEquals(100.00, res.subtotal);
        assertEquals(20.00, res.discountValue); // 20% do subtotal
    }

    @Test
    public void deveLimitarDescontoAte30Porcento() {
        var couponSvc = new CouponService();
        var shipSvc = new ShippingService();
        var service = new CheckoutService(couponSvc, shipSvc);

        var itens = List.of(new Item("ELETRONICO", 200.00, 1));

        var res = service.checkout(
                itens,
                CustomerTier.GOLD,       // 10%
                true,                     // primeira compra 5%
                "SUL",
                3.0,
                "DESC20",                 // 20%
                LocalDate.now(),
                LocalDate.now().plusDays(1)
        );

        // Soma = 35%, mas teto = 30%
        assertEquals(60.00, res.discountValue); // 30% de 200
    }

    @Test
    public void deveCalcularFreteParaRegiaoNorte() {
        var couponSvc = new CouponService();
        var shipSvc = new ShippingService();
        var service = new CheckoutService(couponSvc, shipSvc);

        var itens = List.of(new Item("ELETRONICO", 50.00, 1));

        var res = service.checkout(
                itens,
                CustomerTier.BASIC,
                false,
                "NORTE",
                3.0,
                null,
                LocalDate.now(),
                null
        );

        // peso = 3 -> frete 55
        assertEquals(55.00, res.shipping);
    }


}
