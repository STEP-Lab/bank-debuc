import com.debuc.bank.Account;
import com.debuc.bank.MinimumBalanceException;
import com.debuc.bank.NegativeValueException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account tony;
    private Account steve;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws MinimumBalanceException, NegativeValueException {
        tony = Account.create("Tony", "123-456", 1100);
        steve = Account.create("Steve", "123-455", 3000);
    }

    @Test(expected = MinimumBalanceException.class)
    public void createNewAccountFailureForMinimumBalance() throws MinimumBalanceException, NegativeValueException {
        Account.create("Thanos","000-000",100);
    }

    @Test
    public void checkBalance(){
        assertThat(tony.getBalance(),is(1100.0));
    }

    @Test
    public void checkAccountName() {
        assertThat(tony.getAccountOwner(),is("Tony"));
    }

    @Test
    public void checkAccountNo() {
        assertThat(tony.getAccountNo(),is("123-456"));
    }

    @Test
    public void depositMoney() throws NegativeValueException {
        assertThat(tony.getBalance(),is(1100.0));
        tony.credit(1000);
        assertThat(tony.getBalance(),is(2100.0));
    }

    @Test(expected = NegativeValueException.class)
    public void depositMoneyThrowsNegativeValueException() throws NegativeValueException {
        tony.credit(-1000);
    }

    @Test
    public void depositMoneyFailureDoesNotAffectBalance() throws MinimumBalanceException {
        assertThat(tony.getBalance(),is(1100.0));
        try {
            tony.debit(-600);
        } catch (NegativeValueException e) {
            assertThat(tony.getBalance(),is(1100.0));
        }
    }

    @Test
    public void withdrawMoney() throws MinimumBalanceException, NegativeValueException {
        assertThat(steve.getBalance(),is(3000.0));
        steve.debit(1000);
        assertThat(steve.getBalance(),is(2000.0));
    }

    @Test(expected = MinimumBalanceException.class)
    public void withdrawMoneyThrowsMinimumBalanceException() throws MinimumBalanceException, NegativeValueException {
        steve.debit(2100);
    }

    @Test
    public void withdrawMoneyFailureDoesNotAffectBalance() throws NegativeValueException {
        assertThat(steve.getBalance(),is(3000.0));
        try {
            steve.debit(2100);
        } catch (MinimumBalanceException e) {
            assertThat(steve.getBalance(),is(3000.0));
        }
    }
}
