package com.wisdom.common.queue;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wisdom.invoice.service.IInvoiceService;


@Service
public class InvalidMessageDelegate implements MessageDelegate {
	
	@Autowired IInvoiceService invoiceService;
	



	@Override
	public synchronized void handleMessage(String message) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		System.out.println(message);
		long invoiceId = Long.parseLong(message);
		String status = "INVALID";
		invoiceService.updateInvoiceStatus(invoiceId, status);
	}

}
