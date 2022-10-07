package ATM;

import java.io.Serializable;
import java.util.TreeMap;

public class ATM implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3850364922161405745L;
	public TreeMap<Integer,Integer>money=new TreeMap<Integer,Integer>();
	void loadCash(int hundred,int fivehundred,int twothousand) {
		if(money.containsKey(100)) {
			money.put(100,money.get(100)+hundred);
		}else {
			money.put(100,hundred);
		}
		if(money.containsKey(500)) {
			money.put(500,money.get(500)+fivehundred);
		}else {
			money.put(500,fivehundred);
		}
		if(money.containsKey(2000)) {
			money.put(2000,money.get(2000)+twothousand);
		}else {
			money.put(2000,twothousand);
		}
	}
	int getBalance() {
		int cash=0;
		cash+=100*money.get(100);
		cash+=500*money.get(500);
		cash+=2000*money.get(2000);
		return cash;
	}
	TreeMap<Integer,Integer> getBalanceByCash(){
		return money;
	}
	int getH() {
		return money.get(100)==null ? 0 : money.get(100);
	}
	int getF() {
		return money.get(500)==null ? 0 : money.get(500);
	}
	int getT() {
		return money.get(2000)==null ? 0 :money.get(2000);
	}
	void dismantleCash(int hundred,int fivehundred,int twothousand) {
		if(money.containsKey(100)) {
			//System.out.println("hiijosiucguysvdduygfuysd");
			money.put(100,money.get(100)-hundred);
		}else {
			money.put(100,0);
		}
		if(money.containsKey(500)) {
			money.put(500,money.get(500)-fivehundred);
		}else {
			money.put(500,0);
		}
		if(money.containsKey(2000)) {
			money.put(2000,money.get(2000)-twothousand);
		}else {
			money.put(2000,0);
		}
	}
	void seth(int n) {
		money.put(100,money.get(100)-n);
	}
	void setf(int n) {
		money.put(500,money.get(500)-n);
	}
	void sett(int n) {
		money.put(2000,money.get(2000)-n);
	}
}
