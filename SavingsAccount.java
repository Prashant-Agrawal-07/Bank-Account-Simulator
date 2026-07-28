// SavingsAccount IS-A Account (inheritance via "extends")
// Adds its own rule: cannot withdraw below a minimum balance
public class SavingsAccount extends Account {

    private static final double MIN_BALANCE = 1000.0; // business rule specific to savings accounts
    private double interestRate; // extra field only savings accounts have

    public SavingsAccount(int accountNumber, String holderName, double initialBalance, double interestRate) {
        super(accountNumber, holderName, initialBalance); // calls Account's constructor
        this.interestRate = interestRate;
    }

    // POLYMORPHISM: this OVERRIDES the abstract withdraw() from Account
    // with savings-specific logic.
    @Override
    public void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }
        if (balance - amount < MIN_BALANCE) {
            throw new InsufficientBalanceException(
                "Withdrawal denied: balance cannot go below minimum balance of " + MIN_BALANCE
            );
        }
        balance -= amount;
    }

    public void applyInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }
}
