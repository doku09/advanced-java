package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ConstructV2 {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		// 클래스 동적으로 찾기
		Class<?> aClass = Class.forName("reflection.data.BasicData");

		Constructor<?> constructor = aClass.getDeclaredConstructor(String.class);
		constructor.setAccessible(true);
		//객체 생성
		Object instance = constructor.newInstance("hello");
		System.out.println("instance = " + instance);

		Method method1 = aClass.getDeclaredMethod("call");
		// 메서드 호출
		method1.invoke(instance);
	}
}
