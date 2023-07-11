import java.util.Scanner;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

class ATM {
    private double balance;

    public ATM(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount should be positive.");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount should be positive.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds in account.");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

public class ATMMachine {
    public static void main(String[] args) {
        ATM atm = new ATM(1000);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Your balance is " + atm.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    try {
                        atm.deposit(depositAmount);
                        System.out.println("Deposit successful. Your new balance is " + atm.getBalance());
                    } catch (InvalidAmountException e) {
                        System.out.println("Invalid amount: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    try {
                        atm.withdraw(withdrawalAmount);
                        System.out.println("Withdrawal successful. Your new balance is " + atm.getBalance());
                    } catch (InvalidAmountException | InsufficientFundsException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            System.out.println();
        }
    }
}