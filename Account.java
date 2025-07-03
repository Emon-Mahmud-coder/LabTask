public abstract class Account {
    private String accountId;
    private String accountHolderName;
    protected int balance;

    public Account(String accountId, String accountHolderName) {
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.balance = 0;
    }

    public Account(String accountId, String accountHolderName, int initialBalance) {
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public int getBalance() {
        return balance;
    }

    public abstract int deposit(int amount);
    public abstract int withdraw(int amount);
    public abstract boolean isTransferAllowed();
    public abstract void applyInterest();

    public int transferTo(Account targetAccount, int amount) {
        if (!this.isTransferAllowed()) {
            System.out.println("Transfer not allowed from this account type.");
            return this.balance;
        }

        if (amount <= this.balance) {
            this.balance -= amount;
            targetAccount.deposit(amount);
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Transfer failed! Amount exceeds balance.");
        }

        return this.balance;
    }

    @Override
    public String toString() {
        return "Account[ID=" + accountId + ", Name=" + accountHolderName + ", Balance=" + balance + "]";
    }
}
