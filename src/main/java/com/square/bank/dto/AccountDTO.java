package com.square.bank.dto;

import com.square.bank.model.Account;

public class AccountDTO {
	private int id;
	private int number;
	private double balance;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public static Account bind(AccountDTO dto){
		Account account = new Account();
		
		account.setId(dto.getId());
		account.setNumber(dto.getNumber());
		account.setBalance(dto.getBalance());
		
		return account;
	}
}
