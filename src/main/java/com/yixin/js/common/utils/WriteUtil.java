package com.yixin.js.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteUtil {
	 public static void write(HttpServletResponse response, String json) throws IOException{
	        /* 
	         * 在调用getWriter之前未设置编码(既调用setContentType或者setCharacterEncoding方法设置编码), 
	         * HttpServletResponse则会返回一个用默认的编码(既ISO-8859-1)编码的PrintWriter实例。这样就会 
	         * 造成中文乱码。而且设置编码时必须在调用getWriter之前设置,不然是无效的。 
	         * */  
	        response.setContentType("text/json;charset=utf-8");  
	        //response.setCharacterEncoding("UTF-8");  
	        PrintWriter out = response.getWriter();  
	        out.println(json);  
	        out.flush();  
	        out.close();  
	    }  
	 
	 public static void writeToHtml(HttpServletResponse response, String html) throws IOException{
	        response.setContentType("text/html;charset=utf-8");  
	        response.setCharacterEncoding("UTF-8");  
	        PrintWriter out = response.getWriter();  
	        out.println(html);  
	        out.flush();  
	        out.close();  
	    }
}
