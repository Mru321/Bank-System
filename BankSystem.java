package java5;
import java.util.Scanner;
import java.util.ArrayList;
//use reference.
//Auto generate account number and customer id.
//Check number of accounts.XX
public class BankSystem {
    
    public static boolean checkCustomerId(ArrayList<Customer> customerArray, long cid){
        boolean b=false;
        for(int i=0; i<customerArray.size(); i++){
            if(customerArray.get(i).getCustomerId()==cid){
                b=true;
                break;
            }
        }
        return b;

    }
    
    public static boolean checkPassLength(int pass){
        boolean b=false;
        String passString=Integer.toString(pass);
        if(passString.length()>=4){
            b=true;
        }
        else{
            System.out.println("Pass is short.");
        }
        return b;
    }
    
    public static Account createAccount(ArrayList<Customer> customerArray, int acc_no, long customerId){
        Scanner sc= new Scanner(System.in);
        boolean b=false;
        Account ac= null;
        do{
            System.out.println("1.Already have an account \n 2.Do not have an account");
            int ch=sc.nextInt();
            //Insert check password length.
            switch (ch) {
                case 1:
                    System.out.println("Enter your customer id: ");
                    long cid= sc.nextLong();
                    if(!checkCustomerId(customerArray, cid)){
                        System.out.println("You do not have an account");
                        System.out.println("Plaese try again");
                        b=false;
                        //insert a do while loop instead of recursion.
                    }
                    else{
                        for(int i=0; i<customerArray.size(); i++){
                            if(customerArray.get(i).getCustomerId()==cid){
                                customerArray.get(i).setNumberOfAccount(customerArray.get(i).getNumberOfAccount()+1);
                                break;
                            }
                        }
                        System.out.println("Enter account type: ");
                        String acc_type= sc.next();
                        System.out.println("Enter balance: ");
                        long balance= sc.nextLong();
                        System.out.println("Enter Pass: ");
                        int pass= sc.nextInt();
                        b=checkPassLength(pass);
                        if (b) {
                            Account a1= new Account(cid,acc_type, acc_no, balance, pass);
                            
                            ac=a1;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter your name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println("Enter age: ");
                    int age= sc.nextInt();
                    System.out.println("ENter salary: ");
                    int salary=sc.nextInt();
                    int num_of_acc=1;//This is the first account of this customer.
                    
                    
                    Customer c1= new Customer(name, age, salary, customerId, num_of_acc);
                    customerArray.add(c1);
                    System.out.println("ENter account type: ");
                    String acc_type2=sc.next();
                    System.out.println("Enter balance: ");
                    long balance2= sc.nextLong();
                    System.out.println("Enter pass: ");
                    int pass2=sc.nextInt();
                    b=checkPassLength(pass2);//Checking pass length.AND//loop
                    
                    if(b){
                        Account a2= new Account(customerId,acc_type2,acc_no,balance2,pass2);
                        ac=a2;
                    }
                    break;
                default:
                    b=false;//loop
                    System.out.println("Invalid choice: ");
                    break;
            }
        }
        while(b==false);
        
        return ac;
    }
    public static void displayAllAccount(ArrayList<Account> accountArray, ArrayList<Customer> customerArray){
        
        for(int i=0; i<accountArray.size(); i++){
            long cid=accountArray.get(i).getCustomerId();
            for(int j=0; j<customerArray.size(); j++){
                if(cid==customerArray.get(j).getCustomerId()){
                    customerArray.get(j).showCustomer();
                    accountArray.get(i).showAccount();
                    System.out.println("====================");
                    break;
                }
            }
        }
    }
    public static void depositeAmt(ArrayList<Account> accountArray){
        Scanner sc= new Scanner (System.in);
        System.out.println("Enter account number: ");
        int an=sc.nextInt();
        System.out.println("Enter amount to deposite: ");
        long amt=sc.nextInt();
        boolean b=false;
        for(int i=0; i<accountArray.size(); i++){
            if(an==accountArray.get(i).getAccountNumber()){
                accountArray.get(i).deposit(amt);
                b=true;
            }
        }
        if(b==false){
            System.out.println("Account not found!");
        }
    }
    public static void withdrawAmt(ArrayList<Account> accountArray){
        Scanner sc= new Scanner (System.in);
        System.out.println("Enter account number: ");
        int an=sc.nextInt();
        System.out.println("Enter amount to deposite: ");
        long amt=sc.nextInt();
        boolean b=false;
        for(int i=0; i<accountArray.size(); i++){
            if(an==accountArray.get(i).getAccountNumber()){
                accountArray.get(i).withdraw(amt);
                b=true;
            }
        }
        if(b==false){
            System.out.println("Account not found!");
        }
    }
    public static void removeAccount(ArrayList<Account> accountArray, ArrayList<Customer> customerArray){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the account number to remove: ");
        int an=sc.nextInt();
        int index=-1;
        int cindex=-1;
        long cid=0;
        for(int i=0; i<accountArray.size(); i++){
            if(an==accountArray.get(i).getAccountNumber()){
                cid=accountArray.get(i).getCustomerId();
                index=i;
                break;
            }
        }
        for(int i=0; i<customerArray.size(); i++){
            if(cid==customerArray.get(i).getCustomerId()){
                cindex=i;
                break;
            }
        }
        if(index==-1){
            System.out.println("Account not found!");
        }
        else{
            customerArray.get(cindex).setNumberOfAccount(customerArray.get(cindex).getNumberOfAccount()-1);
            accountArray.remove(index);
        }
    }
    public static void removeCustomer(ArrayList<Customer> customerArray){
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter the customer id to remove: ");
        long cid=sc.nextLong();
        int index=-1;
        for(int i=0; i<customerArray.size(); i++){
            if(cid==customerArray.get(i).getCustomerId()){
                index=i;
                break;
            }
        }
        if(index==-1){
            System.out.println("Account not found!");
        }
        else{
            customerArray.remove(index);
        }
    }
    public static ArrayList bankManager(ArrayList<Customer> customerArray, ArrayList<Account> accountArray, int acc_no, long customerId){
        Scanner sc=new Scanner(System.in);
        int ch;
        ArrayList id= new ArrayList<>();
        do{ 
            System.out.println("====================");
            System.out.println("#Banking System Application.#");
            System.out.println("1.Create account \n2. Display all account details \n3.Search by account number \n4.deposite amount \n5.Withdraw amount \n6.Remove account \n7.Remove Customer \n8.Exit");
            System.out.println("Enter your choice: ");
            ch = sc.nextInt();
            switch(ch){
                case 1:
                System.out.println("====================");
                    acc_no++;
                    customerId++;
                    Account ac=createAccount(customerArray, acc_no,customerId);
                    accountArray.add(ac);
                    id.add(acc_no);
                    id.add(customerId);
                    break;
                case 2: 
                System.out.println("====================");
                    displayAllAccount(accountArray, customerArray);
                    break;
                case 3: 
                System.out.println("====================");
                    System.out.println("ENter account number: ");
                    int an=sc.nextInt();
                    boolean b=false;
                    //2 for loops are used.
                    for(int i=0; i<accountArray.size(); i++){
                        if(an==accountArray.get(i).getAccountNumber()){
                            long cid=accountArray.get(i).getCustomerId();
                            b=true;
                            for(int j=0; j<customerArray.size(); j++){
                                if(cid==customerArray.get(j).getCustomerId()){
                                    customerArray.get(j).showCustomer();
                                    accountArray.get(i).showAccount();
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    if(b==false){
                        System.out.println("Account number not found.");
                    }
                break;
                case 4:
                System.out.println("====================");
                    depositeAmt(accountArray);
                break;
                case 5: 
                System.out.println("====================");
                    withdrawAmt(accountArray);
                break;
                case 6://remove account
                System.out.println("====================");
                    removeAccount(accountArray,customerArray);
                break;
                case 7: //remove customer
                System.out.println("====================");
                    removeCustomer(customerArray);
                break;
                case 8:
                break;
                default:
                System.out.println("Invalid choice!");
                break;

            }
        }
        while(ch!=8);
        return id;
    }
    public static boolean login(ArrayList<Account> accountArray, int acc_no){
        Scanner sc= new Scanner(System.in);
        boolean b=false;
        boolean b2=false;
        System.out.println("ENter pass: ");
        int pass=sc.nextInt();
        for(int i=0; i<accountArray.size(); i++){
            if(acc_no==accountArray.get(i).getAccountNumber()){
                b=true;
                if(pass==accountArray.get(i).getPass()){
                    b2=true;
                }
            }
        }
        if(b==false){
            System.out.println("Account number Invalid");
        }
        else if(b2==false){
            System.out.println("Pass incorrect");
        }
        return (b&&b2);
    }
    public static void customerManage(ArrayList<Account> accountArray, ArrayList<Customer> customerArray){
        Scanner sc= new Scanner(System.in);
        System.out.println("##Customer Login##");
        int ch;
        System.out.println("ENter accountNumber: ");
        int acc_no=sc.nextInt();
        int aindex=-1;
        long cid=0;
        for(int i=0; i<accountArray.size(); i++){
            if(acc_no==accountArray.get(i).getAccountNumber()){
                cid=accountArray.get(i).getCustomerId();
                aindex=i;
            }
        }
        int cindex=-1;
        for(int i=0; i<customerArray.size(); i++){
            if(cid==customerArray.get(i).getCustomerId()){
                cindex=i;
            }
        }
        if(login(accountArray, acc_no)){
            do{
                System.out.println("1.Display account details \n2.check balance \n3.deposite amount \n4.Withdraw amount \n5.Remove account\n6.Exit");
                ch=sc.nextInt();
                switch(ch){
                    case 1: 
                    System.out.println("====================");
                        customerArray.get(cindex).showCustomer();
                        accountArray.get(aindex).showAccount();
                    break;
                    case 2:
                    System.out.println("====================");
                        System.out.println("Your current balance is: "+accountArray.get(aindex).getBalance()); 
                    break;
                    case 3: 
                    System.out.println("====================");
                        System.out.println("Enter amt to deposite: ");
                        long amt=sc.nextLong();
                        accountArray.get(aindex).deposit(amt);
                    break;
                    case 4: 
                    System.out.println("====================");
                        System.out.println("Enter amt to withdraw: ");
                        long amt2=sc.nextLong();
                        accountArray.get(aindex).withdraw(amt2);
                    break;
                    case 5: 
                    System.out.println("====================");
                        customerArray.get(cindex).setNumberOfAccount(customerArray.get(cindex).getNumberOfAccount()-1);
                        accountArray.remove(aindex);
                    break;
                    case 6: 
                    break;
                    default:
                    System.out.println("invalid choice!");
                    break;
                }
    
            }
            while(ch!=6);
        }
    }
    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);
        System.out.println("Create account: ");
        ArrayList<Account> accountArray= new ArrayList<>();
        ArrayList<Customer> customerArray=new ArrayList<>();
        int acc_no=10000;
        long customerId=10000000;
        acc_no++;
        customerId++;
        Account ac=createAccount(customerArray, acc_no,customerId);
        accountArray.add(ac);
        System.out.println("Account Array size: "+accountArray.size());
        System.out.println("Customer Array size is: "+customerArray.size());
        displayAllAccount(accountArray, customerArray);
        int ch;
        do{
            System.out.println("1.Bank MAnager\n2.Customer login\n3.Exit");
            ch=sc.nextInt();
            switch(ch){
                case 1:
                    System.out.println("====================");
                    ArrayList id=bankManager(customerArray, accountArray, acc_no, customerId);
                    if(id.size()>0){
                        int index=id.size();
                        acc_no=(int)id.get(index-2);
                        customerId=(long)id.get(index-1);
                    }
                break;
                case 2:
                System.out.println("====================");
                customerManage(accountArray, customerArray);
                break;
                case 3:
                break;
                default:
                System.out.println("Invalid choice!");
                break;
            }
        }
        while(ch!=3);
    }
}
