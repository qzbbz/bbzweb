package com.wisdom.web.utils;


public class RedisSetting {

	public static final String ADDRESS = "139.196.40.99";
	
	public static final Integer PORT = 6379;
	
	public static final String PUBLISH_CHANNEL = "EXPORTED_INVOICE";
	
	public static final String SUBSCRIBE_CHANNEL = "IMPORTED_INVOICE";
	
	public static final Integer MAX_IDLE = 5;
	
	public static final Integer MIN_IDLE = 1;
	
	public static final Boolean TEST_ON_BORROW = true;
	
	public static final Integer NUM_TESTS_PER_EVICTION_RUN = 10;
	
	public static final Integer TIME_BETWEEN_EVICTION_RUNS_MILLIS = 6000;
	
	public static final Integer MAX_WAIT_MILLIS = 10000;

}