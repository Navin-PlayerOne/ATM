package ATM;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.TreeMap;

public class Core {
	public static void loadCashToATM(){
		Scanner sc = new Scanner(System.in);
		ATM atm = new ATM();
		System.out.println("Enter count of 100 : ");
		int hun=sc.nextInt();
		System.out.println("Enter count of 500 : ");
		int five = sc.nextInt();
		System.out.println("Enter count of 2000 : ");
		int two = sc.nextInt();
		atm.loadCash(hun, five, two);
		saveATM(atm);
		sc.close();
	}
	public static ATM restoreATM() {
		FileInputStream file;
		ATM atm= new ATM();
		try {
			file = new FileInputStream("atm.txt");
			ObjectInputStream os = new ObjectInputStream(file);
			atm=(ATM)os.readObject();
		} catch (Exception e) {
			System.out.println("No cash Avilabe in ATM at this moment!!!");
		}
		return atm;
	}
	public static TreeMap<Integer,Customer> restoreCustomer() {
		TreeMap<Integer,Customer> customers = new TreeMap<Integer,Customer>();
		try {
			FileInputStream file = new FileInputStream("customers.txt");
			ObjectInputStream os = new ObjectInputStream(file);
			//TreeMap<Integer,Customer> cs;
			//os.reset();
			try {
				while(true){
					customers=(TreeMap<Integer,Customer>)os.readObject();
				}
			}
			catch (Exception e) {
				os.close();
				file.close();
				//e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("No customers Avilable");
		}
		return customers;
	}
	public static void saveATM(ATM atm) {
		int a=0,b=0,c=0;
		try {
			TreeMap<Integer,Integer> bal = getCurBal();
			a=bal.get(100);
			b=bal.get(500);
			c=bal.get(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			FileOutputStream file = new FileOutputStream("atm.txt",false);
			ObjectOutputStream os = new ObjectOutputStream(file);
			//System.out.println("77777777777777777777777");
			atm.loadCash(a, b, c);
			os.writeObject(atm);
			os.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void storeATMpermanent(ATM a) {
		try {
			FileOutputStream file = new FileOutputStream("atm.txt",false);
			ObjectOutputStream os = new ObjectOutputStream(file);
			os.writeObject(a);
			os.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void saveCustomer(TreeMap<Integer,Customer> customers) {
		try {
			FileOutputStream file = new FileOutputStream("customers.txt",false);
			ObjectOutputStream os = new ObjectOutputStream(file);
			os.writeObject(customers);
			os.close();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void showCustomer() {
		TreeMap<Integer,Customer> customers = new TreeMap<Integer,Customer>();
		customers = restoreCustomer();
		customers.forEach((b,a)->{
			System.out.println("<---------**---------->");
			System.out.println("Account No : "+a.accountNumber);
			System.out.println("Name : "+a.name);
			System.out.println("Pin: "+a.pin);
			System.out.println("Balance : "+a.balance);
			System.out.println("<---------**---------->");
		});
	}
	public static void addCutomer() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Account  Number : ");
		int accno=sc.nextInt();
		System.out.println("Name : ");
		String name = sc.next();
		System.out.println("Pin : ");
		int pin = sc.nextInt();
		System.out.println("Initial Account Balance : ");
		int bal = sc.nextInt();
		TreeMap<Integer,Customer> customers = new TreeMap<Integer,Customer>();
		customers = restoreCustomer();
		if(customers.containsKey(accno)) {
			System.out.println("Cant create account. This account number already exists");
		}
		else {
			Customer customer = new Customer(accno, name, bal, pin);
			customers.put(accno, customer);
			saveCustomer(customers);
			System.out.println("Account created with entered balance successfully");
		}
	}
	public static void checkBalace() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account Number : ");
		int accno = sc.nextInt();
		System.out.println("Enter Pin : ");
		int pin = sc.nextInt();
		TreeMap<Integer,Customer> customers = new TreeMap<Integer,Customer>();
		customers = restoreCustomer();
		if(customers.containsKey(accno)) {
			Customer customer = customers.get(accno);
			if(customer.pin==pin) {
				System.out.println("Avilable Balance : "+customer.balance);
			}
			else {
				System.out.println("something went wrong try again later");
			}
		}
		else {
			System.out.println("Account Doesnot exixts");
		}
	}
	public static void atmBalance() {
		ATM a = restoreATM();
		TreeMap<Integer,Integer> money = a.getBalanceByCash();
		System.out.println("-----------------");
		money.forEach((x,y)->{
			System.out.println(x+" : "+y);
		});
		System.out.println("-----------------");
		System.out.println("ATM avilable Total Balance :"+a.getBalance());
	}
	public static void transferMoney() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Your Account Number : ");
		int sno=sc.nextInt();
		System.out.println("Enter the recivers account number :");
		int rno = sc.nextInt();
		System.out.println("ENter the Amount want to transfer");
		int amount = sc.nextInt();
		System.out.println("Enter Your Pin : ");
		int pin = sc.nextInt();
		TreeMap<Integer,Customer> customers = new TreeMap<Integer,Customer>();
		customers = restoreCustomer();
		if(customers.containsKey(sno) && customers.containsKey(rno)) {
			Customer sender = customers.get(sno);
			Customer reciver = customers.get(rno);
			if(sender.pin==pin && sender.balance<amount) {
				System.out.println("Insufficient Balance transaction cancelled");
			}
			else if(sender.pin==pin){
				sender.balance-=amount;
				reciver.balance+=amount;
				customers.put(rno, reciver);
				customers.put(sno, sender);
				saveCustomer(customers);
				System.out.println("Transaction successfully completed ");
			}
		}
		else {
			System.out.println("Somenthing went wrong try again later");
		}
	}
	public static TreeMap<Integer,Integer> getCurBal() {
		ATM a = restoreATM();
		TreeMap<Integer,Integer> money = a.getBalanceByCash();
		return money;
	}
	public static void withdraw() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Account Number : ");
		int accno=sc.nextInt();
		System.out.println("Pin : ");
		int pin = sc.nextInt();
		System.out.println("Enter Amount to withdraw : ");
		int amnt=sc.nextInt();
		if(amnt>10000) {
			System.out.println("Limit Exceeded returning!");
		}
		else {
			TreeMap<Integer,Customer> customers = new TreeMap<Integer,Customer>();
			customers = restoreCustomer();
			if(customers.containsKey(accno)) {
				Customer client = customers.get(accno);
				if(client.pin==pin) {
					if(amnt%100!=0) {
						System.out.println("cannot process amount");
					}
					else {
						makePay(accno,amnt);
					}
				}
				else {
					System.out.println("Something went wrong try again later");
				}
			}
			else {
				System.out.println("something went wrong try again later");
			}
		}
	}
	private static void makePay(int accno, int amnt) {
		TreeMap<Integer,Customer> customers = new TreeMap<Integer,Customer>();
		customers = restoreCustomer();
		ATM atm = new ATM();
		atm.loadCash(0, 0, 0);
		int cash=amnt;
		while(atm.getBalance()!=amnt && cash!=0) {
			if(amnt<=5000) {
				atm.loadCash(0, 0, amnt/2000);
				atm.loadCash(0, (amnt%2000)/500, 0);
				atm.loadCash(((amnt%2000)%500)/100, 0, 0);
			}
			else {
				atm.loadCash(2, 2, 0);
				int rem = atm.getBalance();
				int ans=amnt-rem;
				atm.loadCash(0, ans/500, 0);
				if(atm.getBalance()!=amnt) {
					atm.loadCash((amnt%500)/100, 0, 0);
				}
			}
			
		}
		
		int a=0,b=0,c=0,a1=0,b1=0,c1=0;
		try {
			TreeMap<Integer,Integer> bal = getCurBal();
			a=bal.get(100);
			b=bal.get(500);
			c=bal.get(2000);
			TreeMap<Integer,Integer> cur = atm.getBalanceByCash();
			a1=cur.get(100);
			b1=cur.get(500);
			c1=cur.get(2000);
			if(a1<=a && b1<=b && c1<=c) {
				Customer cc =customers.get(accno);
				ATM original = restoreATM();
				TreeMap<Integer,Integer> tmp = original.getBalanceByCash();
				tmp.forEach((x,y)->{
					System.out.println(x+" "+y);
				});
				//original.loadCash(-1*a1,-1*b1,-1*c1);
				original.seth(a1);
				original.setf(b1);
				original.sett(c1);
				storeATMpermanent(original);
				cc.balance-=amnt;
				customers.put(accno, cc);
				saveCustomer(customers);
				System.out.println("Transaction successfull");
			}
			else {
				System.out.println("Not enough money in ATM try again later");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
