package com.wisdom.common.queue;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface MessageDelegate {

	void handleMessage(String message) throws JsonParseException, JsonMappingException, IOException;
}
