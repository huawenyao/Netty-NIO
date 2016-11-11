package tooldemo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.common.utils.ReflectUtils;

public class ReflectTest {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> ff() {
		List<String> list = new ArrayList<String>();
		list.add("111");
		list.add("222");
		return list;
	}

	public static void main(String[] args) {
		ReflectTest test = new ReflectTest();
		Object list = ReflectUtils.invokeMethodByName(test, "ff", null);
		System.out.println(list);
		ReflectUtils.setFieldValue(test, "name", "panie");
		System.out.println("对象实例:"+JSON.toJSONString(test));
		String name = (String) ReflectUtils.getFieldValue(test, "name");
		System.out.println(name);
	}

}
