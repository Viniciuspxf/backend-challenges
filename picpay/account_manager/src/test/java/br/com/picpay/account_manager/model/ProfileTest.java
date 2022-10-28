package br.com.picpay.account_manager.model;
import br.com.picpay.account_manager.exception.InvalidTransactionException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class ProfileTest {

    @Test
    public void shouldThrowError() {
        Profile profile = new Profile();
        profile.setBalance(new BigDecimal(3));
        assertThrows(InvalidTransactionException.class, ()-> profile.decreaseAmount( new BigDecimal(30)));
    }

    @Test
    public void shouldDecreaseAmount() {
        Profile profile = new Profile();
        profile.setBalance(new BigDecimal(3));
        profile.decreaseAmount(new BigDecimal(3));
        assertEquals(new BigDecimal(0), profile.getBalance());
    }


    @Test
    public void shouldReturnTrue() {
        Profile profile = new Profile();
        profile.setShopKeeper(false);
        assertTrue(profile.isAbleToTransferMoney());
    }

    @Test
    public void shouldReturnFalse() {
        Profile profile = new Profile();
        profile.setShopKeeper(true);
        assertFalse(profile.isAbleToTransferMoney());
    }

}
