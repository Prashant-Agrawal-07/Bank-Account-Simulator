// Custom checked exception - thrown when a withdrawal/transfer exceeds available balance
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message); // passes the message up to the parent Exception class
    }
}
