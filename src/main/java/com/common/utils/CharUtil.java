package com.common.utils;

public class CharUtil {
	public static String captureName(String name) {
		        char[] cs=name.toCharArray();
		        cs[0]-=32;
		        return String.valueOf(cs);
		        
		    }
	public static String spliteline(String name){//转大写加_  举例：myName  ->  my_name
		char [] chs=name.toCharArray();
		for (char ch :chs){
			if (ch>64&&ch<97){
				char replacewith=(char) (ch+32);
				name=name.replace(String.valueOf(ch),"_"+String.valueOf(replacewith));
			}
		}
		return name;
		
	}
}
