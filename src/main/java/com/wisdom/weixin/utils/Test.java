package com.wisdom.weixin.utils;

import com.wisdom.common.model.Invoice;

public class Test {

	public static void main(String[] args) {
		Invoice invoice = new Invoice();
		invoice.setAmount(11.7876696987);
		System.out.println(invoice.getAmount()==null?0.0:invoice.getAmount());
	}
	
}
