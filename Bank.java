public interface Bank {
    public void deposite(double amt) throws InvalidAmountException;

    public double withdraw(double amt) throws InvalidAmountException, InSufficientFundsException;

    public void balanceEnquiry();
}