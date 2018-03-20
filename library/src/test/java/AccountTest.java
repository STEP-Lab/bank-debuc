import com.debuc.bank.Account;
import com.debuc.bank.MinimumBalanceException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account tony;
    private Account steve;

    @Before
    public void setUp() throws Exception, MinimumBalanceException {
        tony = new Account("Tony", "123-456", 1100);
        steve = new Account("Steve", "123-455", 3000);
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
    public void depositMoney() {
        assertThat(tony.getBalance(),is(1100.0));
        tony.credit(1000);
        assertThat(tony.getBalance(),is(2100.0));
    }

    @Test
    public void withdrawMoney() throws MinimumBalanceException {
        assertThat(steve.getBalance(),is(3000.0));
        steve.debit(1000);
        assertThat(steve.getBalance(),is(2000.0));
    }

    @Test
    public void withdrawMoneyFailure() throws MinimumBalanceException {
        assertThat(steve.getBalance(),is(3000.0));
        try {
            steve.debit(2100);
        } catch (MinimumBalanceException e) {
            assertThat(steve.getBalance(),is(3000.0));
        }
    }
}
