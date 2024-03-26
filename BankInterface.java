package java5;
interface BankInterface {
    // contains the methods required.
    long getBalance();

    void withdraw(long amt);

    void deposit(long amt);

    void showAccount();

    boolean search(int acc_no);

    boolean checkPass(int p);

    int getAccountNumber();
}
