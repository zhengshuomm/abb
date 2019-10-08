package airbnb2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BankSystem {
	public static void main(String args[]) {
		BankSystem bs = new BankSystem();
		System.out.println(bs.withdraw(0, 100, 0)); // false
		bs.deposite(0, 100, 1);
		bs.deposite(1, 250, 2);
		bs.withdraw(0, 30, 3);
		System.out.println(bs.check(0, 0, 2)[0]); // 0
		System.out.println(bs.check(0, 0, 2)[1]); // 100
		bs.deposite(1, 5, 7);
		System.out.println(bs.check(1, 3, 9)[0]); // 250
		System.out.println(bs.check(1, 3, 9)[1]); // 255
	}

	Map<Integer, Integer> accountBalance; // id -> balance
	Map<Integer, TreeMap<Long, Integer>> accountStatement; // id -> timestamp ->
															// balance

	public BankSystem() {
		this.accountBalance = new HashMap<>();
		this.accountStatement = new HashMap<>();
	}

	public void deposite(int id, int amount, long timestamp) {
		if (!accountBalance.containsKey(id)) {
			accountBalance.put(id, 0);
			accountStatement.put(id, new TreeMap<Long, Integer>());
		}
		accountBalance.put(id, accountBalance.get(id) + amount);
		accountStatement.get(id).put(timestamp, accountBalance.get(id));
	}

	public boolean withdraw(int id, int amount, long timestamp) {
		if (!accountBalance.containsKey(id) || accountBalance.get(id) < amount) {
			return false;
		}
		accountBalance.put(id, accountBalance.get(id) - amount);
		accountStatement.get(id).put(timestamp, accountBalance.get(id));
		return true;
	}

	public int[] check(int id, long startTime, long endTime) {
		if (!accountBalance.containsKey(id)) {
			return new int[0];
		}
		int[] res = new int[2];
		TreeMap<Long, Integer> statement = accountStatement.get(id);
		res[0] = statement.floorEntry(startTime) == null ? 0 :  statement.floorEntry(startTime).getValue();
		res[1] = statement.floorEntry(endTime) == null ? 0 : statement.floorEntry(endTime).getValue();
		return res;
	}
}
