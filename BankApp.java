import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in);

        bank.loadFromFile(); // restore previous session's data, if any

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(sc, "Enter choice: ");

            switch (choice) {
                case 1 -> openAccount(sc, bank);
                case 2 -> depositMoney(sc, bank);
                case 3 -> withdrawMoney(sc, bank);
                case 4 -> transferMoney(sc, bank);
                case 5 -> bank.printAllAccounts();
                case 6 -> {
                    bank.saveToFile();
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n===== BANK ACCOUNT SIMULATOR =====");
        System.out.println("1. Open New Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. View All Accounts");
        System.out.println("6. Save & Exit");
    }

    private static void openAccount(Scanner sc, Bank bank) {
        int accNo = readInt(sc, "Enter new account number: ");
        if (bank.accountExists(accNo)) {
            System.out.println("Account number already exists!");
            return;
        }
        System.out.print("Enter holder name: ");
        String name = sc.nextLine();
        double initial = readDouble(sc, "Enter initial deposit amount: ");

        System.out.println("Choose account type: 1. Savings  2. Current");
        int type = readInt(sc, "Enter choice: ");

        if (type == 1) {
            bank.openSavingsAccount(accNo, name, initial, 4.0); // 4% interest, fixed for simplicity
            System.out.println("Savings account created successfully.");
        } else if (type == 2) {
            bank.openCurrentAccount(accNo, name, initial, 5000.0); // fixed overdraft limit of 5000
            System.out.println("Current account created successfully.");
        } else {
            System.out.println("Invalid account type selected.");
        }
    }

    private static void depositMoney(Scanner sc, Bank bank) {
        int accNo = readInt(sc, "Enter account number: ");
        Account acc = bank.getAccount(accNo);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }
        double amount = readDouble(sc, "Enter amount to deposit: ");
        try {
            acc.deposit(amount);
            System.out.println("Deposit successful. New balance: " + acc.getBalance());
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void withdrawMoney(Scanner sc, Bank bank) {
        int accNo = readInt(sc, "Enter account number: ");
        Account acc = bank.getAccount(accNo);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }
        double amount = readDouble(sc, "Enter amount to withdraw: ");
        try {
            acc.withdraw(amount); // polymorphic call - runs Savings or Current logic automatically
            System.out.println("Withdrawal successful. New balance: " + acc.getBalance());
        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void transferMoney(Scanner sc, Bank bank) {
        int fromAcc = readInt(sc, "Enter your account number: ");
        int toAcc = readInt(sc, "Enter recipient's account number: ");
        double amount = readDouble(sc, "Enter amount to transfer: ");
        try {
            bank.transfer(fromAcc, toAcc, amount);
            System.out.println("Transfer successful.");
        } catch (InvalidAmountException | InsufficientBalanceException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Helper to safely read an integer, avoiding crashes on bad input
    private static int readInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.next();
            System.out.print(prompt);
        }
        int value = sc.nextInt();
        sc.nextLine(); // consume leftover newline so next nextLine() call works correctly
        return value;
    }

    private static double readDouble(Scanner sc, String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextDouble()) {
            System.out.println("Please enter a valid amount.");
            sc.next();
            System.out.print(prompt);
        }
        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }
}
