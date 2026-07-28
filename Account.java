// Abstract class = a blueprint that CANNOT be instantiated directly.
// It defines what EVERY type of account must have, but leaves some behavior
// to be defined by the specific subclasses (SavingsAccount, CurrentAccount).
public abstract class Account {

    // private fields = ENCAPSULATION. Outside classes cannot touch these directly.
    private int accountNumber;
    private String holderName;
    protected double balance; // protected so subclasses can access it directly if needed

    // Constructor - runs when a new account object is created
    public Account(int accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
    }

    // Getters - controlled read access to private fields (encapsulation in action)
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    // Common behavior shared by ALL account types - deposit logic never changes
    // regardless of account type, so it lives here in the parent class.
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    // ABSTRACT METHOD: no body here. Every subclass MUST provide its own
    // implementation, because withdrawal rules differ:
    // - SavingsAccount: cannot go below a minimum balance
    // - CurrentAccount: can go negative up to an overdraft limit
    public abstract void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException;

    // Another abstract method - each account type describes itself differently
    public abstract String getAccountType();

    // A method that can be reused for saving to file / printing details
    @Override
    public String toString() {
        return getAccountType() + " | Acc No: " + accountNumber +
               " | Holder: " + holderName +
               " | Balance: " + String.format("%.2f", balance);
    }
}
