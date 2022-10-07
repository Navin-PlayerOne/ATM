package ATM;

import java.util.Scanner;

public class Menu {
	public static void atmOperations() {
		int choice=0;
		Scanner sc = new Scanner(System.in);
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("1. Check Balance");
		System.out.println("2. Withdraw Money");
		System.out.println("3. Transfer Money");
		System.out.println("4. Check ATM balance");
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		System.out.print("=>");
		choice = sc.nextInt();
		switch(choice) {
		case 1:
			Core.checkBalace();
			break;
		case 2:
			Core.withdraw();
			break;
		case 3:
			Core.transferMoney();
			break;
		case 4:
			Core.atmBalance();
			break;
		default:
			System.out.println("Bad Choce! returned");
			
		}
	}
	public static void main(String[] args) {
		int choice=8;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			System.out.println("1. Load Cash to ATM");
			System.out.println("2. Show Customer Details");
			System.out.println("3. Show ATM operations");
			System.out.println("4. Add Customer");
			System.out.println("5. Exit");
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
			System.out.print("=>");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				Core.loadCashToATM();
				break;
				case 2:
				Core.showCustomer();
				break;
				case 3:
				atmOperations();
				break;
				case 4:
				Core.addCutomer();
				break;
				case 5:
				System.exit(1);
				default:
				System.out.println("InvalidInput");
			}
		}
	}
}
