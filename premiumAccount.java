public class premiumAccount extends Account {
    private static final double INTEREST_RATE = 0.07;
    private static final int REQUIRED_INSTALLMENTS = 5;
    private int installmentsPaid;

    public premiumAccount(String accountId, String accountHolderName) {
        super(accountId, accountHolderName);
        this.installmentsPaid = 0;
    }

    public premiumAccount(String accountId, String accountHolderName, int initialBalance) {
        super(accountId, accountHolderName, initialBalance);
        this.installmentsPaid = 0;
    }

    @Override
    public int deposit(int amount) {
        balance += amount;
        installmentsPaid++;
        System.out.println("Installment " + installmentsPaid + " deposited successfully into Deposit Premium Account.");
        return balance;
    }

    @Override
    public int withdraw(int amount) {
        if (installmentsPaid < REQUIRED_INSTALLMENTS) {
            System.out.println("Withdrawal denied! Complete all " + REQUIRED_INSTALLMENTS + " installments first.");
            System.out.println("Installments completed: " + installmentsPaid);
        } else {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawn successfully from Deposit Premium Account.");
            } else {
                System.out.println("Withdrawal failed! Amount exceeds balance.");
            }
        }
        return balance;
    }

    @Override
    public boolean isTransferAllowed() {
        return false; // Transfer not allowed from Deposit Premium Account
    }

    @Override
    public void applyInterest() {
        int interest = (int) (balance * INTEREST_RATE);
        balance += interest;
        System.out.println("Interest of " + interest + " applied to Deposit Premium Account.");
    }

    public int getInstallmentsPaid() {
        return installmentsPaid;
    }

    @Override
    public String toString() {
        return "DepositPremiumAccount[ID=" + getAccountId() + ", Name=" + getAccountHolderName() +
                ", Balance=" + balance + ", Installments=" + installmentsPaid + "/" + REQUIRED_INSTALLMENTS + "]";
    }
}
