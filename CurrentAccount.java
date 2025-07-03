class CurrentAccount extends Account {

    public CurrentAccount(String accountId, String accountHolderName) {
        super(accountId, accountHolderName);
    }

    public CurrentAccount(String accountId, String accountHolderName, int initialBalance) {
        super(accountId, accountHolderName, initialBalance);
    }

    @Override
    public int deposit(int amount) {
        balance += amount;
        System.out.println("Deposited successfully into Current Account.");
        return balance;
    }

    @Override
    public int withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn successfully from Current Account.");
        } else {
            System.out.println("Insufficient balance.");
        }
        return balance;
    }



    @Override
    public void applyInterest() {

        System.out.println("No interest for Current Account.");
    }
    public boolean isTransferAllowed() {
        return true;
    }
    @Override
    public String toString() {
        return "CurrentAccount[ID=" + getAccountId() + ", Name=" + getAccountHolderName() + ", Balance=" + balance + "]";
    }
}
