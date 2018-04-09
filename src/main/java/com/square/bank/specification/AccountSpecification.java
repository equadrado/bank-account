package com.square.bank.specification;

import org.springframework.data.jpa.domain.Specification;

import com.square.bank.model.Account;

public class AccountSpecification {

	public static Specification<Account> equalNumber(int account_number) {
		return (root, query, builder) -> builder.equal(root.get("number"), account_number);
	}

}
