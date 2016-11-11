package com.common.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Bean2Map {
	public static <T> HashMap<String, Object> readClassAttr(T tb) throws Exception{
		
	    Field[] fields=tb.getClass().getDeclaredFields();
	    HashMap<String,Object> onemap=new HashMap<String,Object>();
	
	    for(Field field:fields){
	        field.setAccessible(true);  
            
	        if(field.get(tb)!=null&&!"".equals(field.get(tb).toString())){

	            if("a".equals(field.getName())){
	               

	            }else{
	            	 onemap.put(field.getName(), field.get(tb));
	            }
	            System.out.println(field.getName()+"   "+field.get(tb).toString());
	        }
	    }


	    return onemap;
	}
	   public static Object map2Bean(Class type, Map map) 
	            throws IntrospectionException, IllegalAccessException, 
	            InstantiationException, InvocationTargetException { 
	        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性 
	        Object obj = type.newInstance(); // 创建 JavaBean 对象 
	 
	        // 给 JavaBean 对象的属性赋值 
	        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
	        for (int i = 0; i< propertyDescriptors.length; i++) { 
	            PropertyDescriptor descriptor = propertyDescriptors[i]; 
	            String propertyName = descriptor.getName(); 
	 
	            if (map.containsKey(propertyName)) { 
	                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。 
	                Object value = map.get(propertyName); 
	                
	                Object[] args = new Object[1]; 
	                
	                try{
		                if  (descriptor.getPropertyType().equals(Class.forName("java.util.Date"))){
		                	Date timestamp=new Date(Long.parseLong((String) value));
		                	args[0] =timestamp;
		                }else {args[0] = value; }
	                }catch (Exception e){                	
	                	break;
	                }
	                descriptor.getWriteMethod().invoke(obj, args); 
	            } 
	        } 
	        return obj; 
	    } 
}
