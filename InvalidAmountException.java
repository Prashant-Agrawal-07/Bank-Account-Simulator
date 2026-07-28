// Custom checked exception - thrown when user enters a negative or zero amount
public class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}
