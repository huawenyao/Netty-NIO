package com.common.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class GetProperty {
    public Map<String,List<Object>> propMap;
	public Map<String, List<Object>> getPropMap() {
		return propMap;
	}

	public void setPropMap(Map<String, List<Object>> propMap) {
		this.propMap = propMap;
	}

	public GetProperty (String url) {
		this.propMap=getPropertirsFromURI(url);
	}

	public static Map<String, String> getAllMessage(String propertyName) {
		// 获得资源包
		ResourceBundle rb = ResourceBundle.getBundle(propertyName.trim());
		// 通过资源包拿到所有的key
		Enumeration<String> allKey = rb.getKeys();
		// 遍历key 得到 value
		Map<String, String> valList = new HashMap<String, String>();
		while (allKey.hasMoreElements()) {
			String key = allKey.nextElement();
			String value = (String) rb.getString(key);
			System.out.println(key + "||" + value);
			valList.put(key, value);
		}
		return valList;
	}
public static Map<String,List<Object>> getPropertirsFromURI(String URI){
	Map <String,List<Object>> rs=new HashMap<String,List<Object>>();
	CompositeConfiguration config = new CompositeConfiguration();
	try {
		config.addConfiguration(new PropertiesConfiguration(URI));
	} catch (ConfigurationException e1) {
		e1.printStackTrace();
	}
	Iterator<String> usernameIter = config.getKeys();

	while (usernameIter.hasNext()) {
		String key = usernameIter.next();
		List<Object> value=config.getList(key);
		rs.put(key, value);
	}
	@SuppressWarnings("unchecked")
	List<String> usernameList = IteratorUtils.toList(usernameIter);
	System.out.println(usernameList);
	return rs;
}   

	public static void main(String[] args) {
		// getAllMessage("login");
		CompositeConfiguration config = new CompositeConfiguration();
		try {
			config.addConfiguration(new PropertiesConfiguration("messages.properties"));
		} catch (ConfigurationException e1) {
			e1.printStackTrace();
		}
		Iterator<String> usernameIter = config.getKeys();

		List<String> usernameList = IteratorUtils.toList(usernameIter);
		System.out.println(usernameList);
	}
}
