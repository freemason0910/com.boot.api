package com.yixin.js.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public final class WebUtils {

	
	
	 
	
	public final static Map<String, String> HTML_CHAR = new HashMap<String, String>();
	static {
		HTML_CHAR.put("&", "&#38;");		
		HTML_CHAR.put("\"", "&#34;");
		HTML_CHAR.put("<", "&#60;");
		HTML_CHAR.put(">", "&#62;");	
		HTML_CHAR.put("'", "&#39;");	
	}
	
	public final static Map<String, String> HTML = new HashMap<String, String>();
	static {
		HTML.put("&amp;", "&");		
		HTML.put("&nbsp;", " ");
		HTML.put("&lt;", "<");
		HTML.put("&gt;", ">");	
		HTML.put("&#xa0;", " ");	
		 
	}
	public final static String[] searchList = new String[100];
	 
	public final static String[] replacementList = new String[100];
	static{
		Set<Map.Entry<String, String>> set = HTML.entrySet();
		Iterator<Map.Entry<String, String>> it  = set.iterator();
		int i = 0;
		while(it.hasNext()){
			Map.Entry<String, String> entry = it.next();
			searchList[i] = entry.getKey();
			replacementList[i] = entry.getValue();
			i++;
		}
	}
	
	
	//过滤html字符串中的特殊符号
	public static String html2text(String htmlStr){
		return org.apache.commons.lang.StringUtils.replaceEach(htmlStr, searchList, replacementList);
	}
 
	
	public static final StringBuilder toHTMLChar(String str) {
		if (str == null) {
			return new StringBuilder();
		}		
		StringBuilder sb = new StringBuilder(str);

		char tempChar;
		String tempStr;
		for (int i = 0; i < sb.length(); i++) {
			tempChar = sb.charAt(i);
			if (HTML_CHAR.containsKey(Character.toString(tempChar))) {
				tempStr = (String) HTML_CHAR.get(Character
						.toString(tempChar));
				sb.replace(i, i + 1, tempStr);
				i += tempStr.length() - 1;
			}
		}
		return sb;
	}
	
     public static String getParameter(String name,HttpServletRequest request){
    	 String value=request.getParameter(name);
    	 if(StringUtils.isNotBlank(value)){
    		 request.setAttribute(name, value);
    		 return value;
    	 }
    	 return "";
     }
    
     public static int getParameterInt(String name,HttpServletRequest request){
    	 String v = StringUtils.trim(request.getParameter(name));
    	 if(StringUtils.isNotEmpty(v)){
    		 request.setAttribute(name, v);
    		 return Integer.parseInt(v);
    	 } 
    	 return 0;
     }
     public static boolean getParameterBoolean(String name,HttpServletRequest request){
    	 String v = StringUtils.trim(request.getParameter(name));
    	 if(StringUtils.isNotEmpty(v)){
    		 request.setAttribute(name, Boolean.parseBoolean(v));
    		 return Boolean.parseBoolean(v);
    	 } 
    	 return false;
     }

	
	/**
	 * 获得当前页
	 * @param request
	 * @return
	 */
	public static int getCurrentPage(HttpServletRequest request) {
		String currentPageStr = StringUtils.trim(request.getParameter("currentPage"));
		int currentPage = 1;
		if (!"".equals(currentPageStr)) {
			currentPage = Integer.parseInt(currentPageStr);
			if (currentPage < 1) {
				currentPage = 1;
			}
		}
		request.setAttribute("currentPage", currentPage);
		return currentPage;
	}
	public static int getFirstRec(int currentPage, int rows) {
		return (currentPage-1)*rows;
	}
  
}
