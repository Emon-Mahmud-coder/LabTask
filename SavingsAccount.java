public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.025;
    private static final int MINIMUM_BALANCE = 1000;

    public SavingsAccount(String accountId, String accountHolderName) {
        super(accountId, accountHolderName);
    }

    public SavingsAccount(String accountId, String accountHolderName, int initialBalance) {
        super(accountId, accountHolderName, initialBalance);
    }

    @Override
    public int deposit(int amount) {
        balance += amount;
        System.out.println("Deposited successfully into Savings Account.");
        return balance;
    }

    @Override
    public int withdraw(int amount) {
        if (amount <= balance) {
            if ((balance - amount) >= MINIMUM_BALANCE) {
                balance -= amount;
                System.out.println("Withdrawn successfully from Savings Account.");
            } else {
                System.out.println("Withdrawal denied! Must maintain minimum balance of " + MINIMUM_BALANCE + ".");
            }
        } else {
            System.out.println("Withdrawal failed! Insufficient balance.");
        }
        return balance;
    }

    @Override
    public boolean isTransferAllowed() {
        return true;
    }

    @Override
    public void applyInterest() {
        int interest = (int) (balance * INTEREST_RATE);
        balance += interest;
        System.out.println("Interest of " + interest + " applied to Savings Account.");
    }

    @Override
    public String toString() {
        return "SavingsAccount[ID=" +getAccountId() + ", Name=" + getAccountHolderName() + ", Balance=" + balance + "]";
    }
}
