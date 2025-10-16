import org.example.checkout.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemTest {
    @Test
    public void deveCalcularSubtotalCorretamente() {
        var item = new Item("ELETRONICO", 10.0, 3);
        assertEquals(30.0, item.subtotal());
    }

    @Test
    public void deveLancarErroQuandoPrecoNegativo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Item("BOOK", -5.0, 1)
        );
        assertEquals("precoUnitario < 0", exception.getMessage());
    }

    @Test
    public void deveLancarErroQuandoQuantidadeMenorOuIgualZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Item("BOOK", 10.0, 0)
        );
        assertEquals("quantidade <= 0", exception.getMessage());
    }

    @Test
    public void deveLancarErroQuandoCategoriaNula() {
        Exception exception = assertThrows(NullPointerException.class, () ->
                new Item(null, 10.0, 1)
        );
        assertEquals("categoria", exception.getMessage());
    }
}
