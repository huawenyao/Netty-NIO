package tooldemo;

import com.common.utils.GetProperty;

public class Main {

	public static void main(String[] args) {
		System.out.println(new GetProperty("messages.properties").propMap);
	}

}
