package com.square.bank.dto;

import com.square.bank.model.Client;

public class ClientDTO {
	
	private int id;	
	private String name;
	private String phone;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public static Client bind(ClientDTO dto) {
		Client client = new Client();
		
		client.setId(dto.getId());
		client.setName(dto.getName());
		client.setPhone(dto.getPhone());
		
		return client;
	}
}
