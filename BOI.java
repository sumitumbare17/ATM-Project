import java.sql.PreparedStatement;

public class BOI implements Bank {
    public double balance;

    public void deposite(double amt) throws InvalidAmountException {
        if (amt <= 0) {
            throw new InvalidAmountException(amt + " is invalid amount");
        }
        balance = balance + amt;
        String sql = "update atm set deposite_amt = ? where acc_no =? ";

    }

    public double withdraw(double amt) throws InvalidAmountException, InSufficientFundsException {
        if (amt <= 0) {
            throw new InvalidAmountException(amt + " is invalid amount");
        }
        if (balance < amt) {
            throw new InSufficientFundsException("InSufficientFunds");
        }
        balance = balance - amt;
        return amt;
    }

    public void balanceEnquiry() {
        System.out.printf("Current Balance " + balance);
    }

}