package vn.trinhtung;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Main {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Object object = new Person("Tung");

		Class<?> clazz = object.getClass();
		Method method = clazz.getDeclaredMethod("getName");
		System.out.println((String) method.invoke(object));
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
	private String name;
}
