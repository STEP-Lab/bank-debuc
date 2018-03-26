import com.debuc.bank.Currency;
import com.debuc.bank.NegativeValueException;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CurrencyTest {

    private Currency amount;

    @Before
    public void setup() throws NegativeValueException {
        amount = Currency.create(1800);
    }

    @Test(expected = NegativeValueException.class)
    public void createCurrencyThrowsNegativeValueException() throws NegativeValueException {
        Currency.create(-100);
    }

    @Test
    public void addAmount() throws NegativeValueException {
        assertThat(amount.getAmount(),is(1800.0));
        amount.add(1200);
        assertThat(amount.getAmount(),is(3000.0));
    }

    @Test(expected = NegativeValueException.class)
    public void addAmountThrowsNegativeValueException() throws NegativeValueException {
        amount.add(-800);
    }

    @Test
    public void addAmountDoesNotChangeAmount() {
        assertThat(amount.getAmount(),is(1800.0));
        try {
            amount.add(-800);
        } catch (NegativeValueException e) {
            assertThat(amount.getAmount(),is(1800.0));
        }
    }
    @Test
    public void deductAmount() throws NegativeValueException {
        assertThat(amount.getAmount(),is(1800.0));
        amount.deduct(800);
        assertThat(amount.getAmount(),is(1000.0));
    }

    @Test(expected = NegativeValueException.class)
    public void deductAmountThrowsNegativeValueException() throws NegativeValueException {
        amount.deduct(-600);
    }

    @Test
    public void deductAmountDoesNotChangeAmount() {
        assertThat(amount.getAmount(),is(1800.0));
        try {
            amount.add(-800);
        } catch (NegativeValueException e) {
            assertThat(amount.getAmount(),is(1800.0));
        }
    }
}
