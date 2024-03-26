package java5;

public class Customer {
    private String name;
    private int age;
    private long salary;
    private long customerId; //should be unique.
    private int number_of_account;
    Customer(String name, int age, long salary, long customerId, int number_of_account){
        this.name=name;
        this.age=age;
        this.salary=salary;
        this.customerId=customerId;
        this.number_of_account=number_of_account;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public long getSalary(){
        return salary;
    }
    public long getCustomerId(){
        return customerId;
    }
    public int getNumberOfAccount(){
        return number_of_account;
    }
    public void setNumberOfAccount(int n){
        number_of_account=n;
    }
    
    public void showCustomer(){
        System.out.println("Name of account holder: "+name);
        System.out.println("Age: "+age);
        System.out.println("Salary: "+salary);
        System.out.println("Customer Id: "+customerId);
        System.out.println("Number of account is: "+number_of_account);
    }
    
}
class Account implements BankInterface{
    private String acc_type;
    private int acc_no;
    private long balance;
    private int pass;
    private long customerId;
    Account(long customerId,String acc_type, int acc_no, long balance, int pass){
        this.customerId=customerId;
        this.acc_no=acc_no;
        this.acc_type=acc_type;
        this.balance=balance;
        this.pass=pass;
    }
    //is override required her??
    public long getCustomerId(){
        return customerId;
    }
    public int getAccountNumber(){
        return acc_no;
    }
    public String getAccountType(){
        return acc_type;
    } 
    public long getBalance(){
        return balance;
    }
    public int getPass(){
        return pass;
    }
    public void withdraw(long amt){
        if(balance>=amt){
            balance-=amt;
            System.out.println("Now balance is: "+ balance);
        }
        else{
            System.out.println("Balance is less than "+amt);
        }
    }
    public void deposit(long amt){
        balance+=amt;
        System.out.println("Now Balance is: "+balance);
    }

    public boolean search(int accno) {
        if (acc_no == accno) {
            return true;
        }
        return false;
    }
    public void showAccount() {
        //showCustomer();
        //This needs to be done in main method.
        System.out.println("Account number: " + acc_no);
        System.out.println("Account Type: " + acc_type);
        System.out.println("Balance is: " + balance);
    }
    public boolean checkPass(int p) {
        if (pass == p) {
            return true;
        }
        return false;
    }
}
