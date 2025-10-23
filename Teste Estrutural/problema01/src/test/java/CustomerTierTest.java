import org.example.checkout.CustomerTier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTierTest {

    @Test
    public void basicCustomerTierTest(){
        assertEquals(CustomerTier.BASIC , CustomerTier.BASIC);
    }

    @Test
    public void silverCustomerTierTest(){
        assertEquals(CustomerTier.SILVER , CustomerTier.SILVER);
    }

    @Test
    public void goldCustomerTierTest(){
        assertEquals(CustomerTier.GOLD , CustomerTier.GOLD);
    }
}
