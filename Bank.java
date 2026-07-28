import java.util.HashMap;
import java.util.Map;
import java.io.*;

// Bank manages a collection of accounts.
// HashMap<Integer, Account> -> key = account number, value = the Account object.
// Why HashMap? O(1) average lookup time by account number - fast and simple.
public class Bank {

    private HashMap<Integer, Account> accounts = new HashMap<>();
    private static final String FILE_NAME = "accounts.txt";

    // Create a new savings account
    public SavingsAccount openSavingsAccount(int accNo, String name, double initialBalance, double interestRate) {
        SavingsAccount acc = new SavingsAccount(accNo, name, initialBalance, interestRate);
        accounts.put(accNo, acc);
        return acc;
    }

    // Create a new current account
    public CurrentAccount openCurrentAccount(int accNo, String name, double initialBalance, double overdraftLimit) {
        CurrentAccount acc = new CurrentAccount(accNo, name, initialBalance, overdraftLimit);
        accounts.put(accNo, acc);
        return acc;
    }

    // Look up an account by number. Returns null if not found -
    // calling code must check for null (we handle this in BankApp).
    public Account getAccount(int accNo) {
        return accounts.get(accNo);
    }

    public boolean accountExists(int accNo) {
        return accounts.containsKey(accNo);
    }

    // Transfer money between two accounts.
    // Notice: we don't care if the accounts are Savings or Current -
    // we just call withdraw()/deposit() and POLYMORPHISM handles the rest.
    public void transfer(int fromAccNo, int toAccNo, double amount)
            throws InvalidAmountException, InsufficientBalanceException {

        Account from = accounts.get(fromAccNo);
        Account to = accounts.get(toAccNo);

        if (from == null || to == null) {
            throw new IllegalArgumentException("One or both account numbers do not exist.");
        }

        from.withdraw(amount); // if this throws, deposit below never runs - balance stays consistent
        to.deposit(amount);
    }

    // Print all accounts (uses toString() from Account, overridden logic handled automatically)
    public void printAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts in the bank yet.");
            return;
        }
        for (Account acc : accounts.values()) {
            System.out.println(acc);
        }
    }

    // ---------- FILE I/O: persist accounts to a text file ----------

    // Save all accounts to a simple CSV-style text file so data survives
    // between program runs.
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts.values()) {
                if (acc instanceof SavingsAccount) {
                    SavingsAccount s = (SavingsAccount) acc;
                    writer.println("SAVINGS," + s.getAccountNumber() + "," + s.getHolderName()
                            + "," + s.getBalance() + ",0"); // last field placeholder (interest not re-read here)
                } else if (acc instanceof CurrentAccount) {
                    writer.println("CURRENT," + acc.getAccountNumber() + "," + acc.getHolderName()
                            + "," + acc.getBalance() + ",0");
                }
            }
            System.out.println("Accounts saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    // Load accounts back from the file when the program starts
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return; // first run - no file yet, that's fine
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                int accNo = Integer.parseInt(parts[1]);
                String name = parts[2];
                double balance = Double.parseDouble(parts[3]);

                if (type.equals("SAVINGS")) {
                    accounts.put(accNo, new SavingsAccount(accNo, name, balance, 4.0));
                } else if (type.equals("CURRENT")) {
                    accounts.put(accNo, new CurrentAccount(accNo, name, balance, 5000.0));
                }
            }
            System.out.println("Loaded existing accounts from " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }
}
