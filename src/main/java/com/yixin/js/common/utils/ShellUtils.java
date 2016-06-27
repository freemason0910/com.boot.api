package com.yixin.js.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShellUtils{
	private static Logger logger = LoggerFactory.getLogger(ShellUtils.class);
	
	public static void callShell(String shellString) {  
	    try {  
	        Process process = Runtime.getRuntime().exec(shellString);  
	        int exitValue = process.waitFor();  
	        if (0 != exitValue) {  
	        	logger.error("call shell failed. error code is :" + exitValue);  
	        }  
	    } catch (Throwable e) {  
	    	logger.error("call shell failed. " + e);  
	    }  
	} 
	public static void main(String args){
		callShell(" make dir 2003");
	}
}
