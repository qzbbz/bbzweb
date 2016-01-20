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
import com.wisdom.dispatch.service.IDispatcherService;
import com.wisdom.invoice.service.IInvoiceApprovalService;
import com.wisdom.invoice.service.IInvoiceService;


@Service
public class DefaultMessageDelegate implements MessageDelegate {
	
	@Autowired IInvoiceService invoiceService;
	
	@Autowired IDispatcherService dispatcherService;
	



	@Override
	public synchronized void handleMessage(String message) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		System.out.println(message);
		
		
		
		JsonFactory factory = new JsonFactory(); 
	    ObjectMapper mapper = new ObjectMapper(factory);
	    TypeReference<List<HashMap<String,Object>>> typeRef 
        = new TypeReference<List<HashMap<String,Object>>>() {};
        List<HashMap<String, Object>> data2 = null;
		try {
			data2 = mapper.readValue(message, typeRef);
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
        boolean fA = false;
        HashMap<String, Object> data = new HashMap<>();
        data = data2.get(0);
        if(data.get("fa").equals("yes")){
        	fA = true;
        }
		
		
        long invoiceId = Long.parseLong((String) data.get("id"));
	    JsonFactory factory2 = new JsonFactory();        
	    ObjectMapper mapper2 = new ObjectMapper(factory2);
	    TypeReference<List<HashMap<String,Object>>> typeRef2
        = new TypeReference<List<HashMap<String,Object>>>() {};
        String contentStr = (String) data.get("data");
        List<Map<String,String>> content = mapper2.readValue(contentStr, typeRef); 
        String requestId = UUID.randomUUID().toString();
        invoiceService.setIsFAOfInvoice(invoiceId, fA, requestId);
	    invoiceService.addInvoiceArtifact(invoiceId, content, requestId);
	    dispatcherService.updateDispatcherStatus(invoiceId, 0);


/*
    HashMap<String,Object> o = mapper.readValue(message, typeRef); 
	System.out.println(o);
	String path = (String) o.get("path");
	System.out.println(path);
	String name = (String)o.get("name");
	String company = (String)o.get("company");
	Integer priority = 10;
	Integer invoiceId = (Integer)o.get("invoice_id");
	Integer companyId = (Integer)o.get("company_id");*/

	}

}
