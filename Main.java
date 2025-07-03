import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AccountSystem {
    private static List<Account> accountList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("    WELCOME TO ACCOUNT SYSTEM");
        System.out.println("========================================");

        System.out.println("\n--- Creating Initial Accounts ---");

        Account acc1 = new CurrentAccount("A001", "Account1");
        Account acc2 = new SavingsAccount("B001", "Account2", 5000);
        Account acc3 = new premiumAccount("C001", "Account3");

        accountList.add(acc1);
        accountList.add(acc2);
        accountList.add(acc3);

        System.out.println("Current Account created for Anik (ID: A001)");
        System.out.println("Savings Account created for Hasan (ID: B001) with balance: 5000");
        System.out.println("Deposit Premium Account created for Mahmud (ID: C001)");

        System.out.println("\n--- Initial Account Details ---");
        for (Account account : accountList) {
            System.out.println(account);
        }

        runSystem();
    }

    private static void runSystem() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("         ACCOUNT SYSTEM MENU");
            System.out.println("========================================");
            System.out.println("1. Create New Account");
            System.out.println("2. Deposit Amount");
            System.out.println("3. Withdraw Amount");
            System.out.println("4. Transfer Money");
            System.out.println("5. Display All Accounts");
            System.out.println("6. Apply Interest to All Accounts");
            System.out.println("7. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice (1-7): ");

            int choice = getInputChoice();

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositToAccount();
                    break;
                case 3:
                    withdrawFromAccount();
                    break;
                case 4:
                    transferBetweenAccounts();
                    break;
                case 5:
                    displayAccounts();
                    break;
                case 6:
                    applyInterestToAllAccounts();
                    break;
                case 7:
                    System.out.println("\nThank you for using the Account System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int getInputChoice() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    private static void createAccount() {
        System.out.println("\n========================================");
        System.out.println("        CREATE NEW ACCOUNT");
        System.out.println("========================================");
        System.out.println("Choose Account Type:");
        System.out.println("1. Current Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Deposit Premium Account");
        System.out.print("Enter account type (1-3): ");

        int type = getInputChoice();
        if (type < 1 || type > 3) {
            System.out.println("Invalid account type!");
            return;
        }

        scanner.nextLine(); // consume newline

        System.out.print("Enter Account ID: ");
        String id = scanner.nextLine();

        for (Account account : accountList) {
            if (account.getAccountId().equals(id)) {
                System.out.println("Account ID already exists!");
                return;
            }
        }

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Initial Balance (0 for no initial balance): ");
        int balance = scanner.nextInt();

        if (balance < 0) {
            System.out.println("Balance cannot be negative.");
            return;
        }

        Account newAccount = null;

        switch (type) {
            case 1:
                newAccount = (balance > 0) ? new CurrentAccount(id, name, balance) : new CurrentAccount(id, name);
                break;
            case 2:
                if (balance > 0 && balance < 1000) {
                    System.out.println("Savings account requires minimum balance of 1000.");
                    return;
                }
                newAccount = (balance > 0) ? new SavingsAccount(id, name, balance) : new SavingsAccount(id, name);
                break;
            case 3:
                newAccount = (balance > 0) ? new premiumAccount(id, name, balance) : new premiumAccount(id, name);
                break;
        }

        accountList.add(newAccount);
        System.out.println("Account created successfully.");
        System.out.println(newAccount);
    }

    private static void depositToAccount() {
        System.out.println("\n========================================");
        System.out.println("         DEPOSIT AMOUNT");
        System.out.println("========================================");

        scanner.nextLine(); // consume newline
        System.out.print("Enter Account ID: ");
        String id = scanner.nextLine();

        Account target = findAccountById(id);
        if (target == null) return;

        System.out.print("Enter amount to deposit: ");
        int amount = scanner.nextInt();
        if (amount > 0) {
            int newBalance = target.deposit(amount);
            System.out.println("Transaction successful.");
            System.out.println("New balance: " + newBalance);
        } else {
            System.out.println("Amount must be positive.");
        }
    }

    private static void withdrawFromAccount() {
        System.out.println("\n========================================");
        System.out.println("         WITHDRAW AMOUNT");
        System.out.println("========================================");

        scanner.nextLine(); // consume newline
        System.out.print("Enter Account ID: ");
        String id = scanner.nextLine();

        Account target = findAccountById(id);
        if (target == null) return;

        System.out.print("Enter amount to withdraw: ");
        int amount = scanner.nextInt();
        if (amount > 0) {
            int newBalance = target.withdraw(amount);
            System.out.println("Current balance: " + newBalance);
        } else {
            System.out.println("Amount must be positive.");
        }
    }

    private static void transferBetweenAccounts() {
        System.out.println("\n========================================");
        System.out.println("        TRANSFER MONEY");
        System.out.println("========================================");

        scanner.nextLine();
        System.out.print("Enter Source Account ID: ");
        String fromId = scanner.nextLine();
        Account source = findAccountById(fromId);
        if (source == null) return;

        System.out.print("Enter Destination Account ID: ");
        String toId = scanner.nextLine();
        Account destination = findAccountById(toId);
        if (destination == null) return;

        if (source.getAccountId().equals(destination.getAccountId())) {
            System.out.println("Cannot transfer to the same account.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        int amount = scanner.nextInt();
        if (amount > 0) {
            System.out.println("\n--- Transfer Details ---");
            System.out.println("From: " + source);
            System.out.println("To: " + destination);
            System.out.println("Amount: " + amount);
            System.out.println("------------------------");

            source.transferTo(destination, amount);

            System.out.println("\n--- After Transfer ---");
            System.out.println("Source: " + source);
            System.out.println("Destination: " + destination);
        } else {
            System.out.println("Amount must be positive.");
        }
    }

    private static void displayAccounts() {
        System.out.println("\n========================================");
        System.out.println("        ALL ACCOUNTS");
        System.out.println("========================================");
        if (accountList.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            System.out.println("Total Accounts: " + accountList.size());
            System.out.println("----------------------------------------");
            for (int i = 0; i < accountList.size(); i++) {
                System.out.println((i + 1) + ". " + accountList.get(i));
            }
        }
        System.out.println("========================================");
    }

    private static void applyInterestToAllAccounts() {
        System.out.println("\n========================================");
        System.out.println("    APPLYING INTEREST TO ALL ACCOUNTS");
        System.out.println("========================================");
        boolean interestApplied = false;

        for (Account account : accountList) {
            System.out.println("\nProcessing: " + account.getAccountId() + " (" + account.getAccountHolderName() + ")");
            int oldBalance = account.getBalance();
            account.applyInterest();
            int newBalance = account.getBalance();

            if (newBalance > oldBalance) {
                System.out.println("Interest added: " + (newBalance - oldBalance));
                interestApplied = true;
            }
        }

        if (!interestApplied) {
            System.out.println("No interest was applicable to any accounts.");
        } else {
            System.out.println("\nInterest application completed.");
        }
        System.out.println("========================================");
    }

    private static Account findAccountById(String id) {
        for (Account account : accountList) {
            if (account.getAccountId().equals(id)) {
                System.out.println("Account found: " + account);
                return account;
            }
        }
        System.out.println("Account not found.");
        return null;
    }
}
