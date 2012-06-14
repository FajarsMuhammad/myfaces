package com.application.utility;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public final class ResourceHelper {
	private static final Logger log = Logger.getLogger(ResourceHelper.class);
	
	private static final String MESSAGE_RESOURCE = "config/messages";

	public ResourceHelper() {
	}
	
	public static String getResources(String key){
		String keyValue = "";
		try{
			keyValue = ResourceBundle.getBundle(MESSAGE_RESOURCE).getString(key);
		}catch (MissingResourceException ex) {
			log.debug("the key :"+key+", is not found or properties file not found");
			keyValue="";
		}catch (Exception e) {
			log.debug("Exception : "+e.getMessage());
			keyValue="";
		}
		return keyValue;
	}

}
