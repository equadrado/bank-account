package com.square.bank.dto;

import com.square.bank.model.Account;

public class AccountDTO {
	private Long id;
	private Long number;
	private double balance;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
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
