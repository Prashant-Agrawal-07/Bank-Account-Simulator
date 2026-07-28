// CurrentAccount IS-A Account too, but has DIFFERENT withdrawal rules than Savings.
// This is the whole point of polymorphism: same method name (withdraw),
// different behavior depending on the actual object type.
public class CurrentAccount extends Account {

    private double overdraftLimit; // current accounts allow going negative up to this limit

    public CurrentAccount(int accountNumber, String holderName, double initialBalance, double overdraftLimit) {
        super(accountNumber, holderName, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }
        // Current account can go negative, but only up to the overdraft limit
        if (balance - amount < -overdraftLimit) {
            throw new InsufficientBalanceException(
                "Withdrawal denied: exceeds overdraft limit of " + overdraftLimit
            );
        }
        balance -= amount;
    }

    @Override
    public String getAccountType() {
        return "Current Account";
    }
}
