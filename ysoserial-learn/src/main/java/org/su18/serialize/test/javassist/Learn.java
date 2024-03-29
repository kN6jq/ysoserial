package org.su18.serialize.test.javassist;

import javassist.*;

import java.util.Arrays;

/**
 * @author su18
 */
public class Learn {

	public static void main(String[] args) {

		// 创建ClassPool对象
		ClassPool classPool = ClassPool.getDefault();

		try {
			// 获取 CtClass 类型对象
			CtClass ctClass = classPool.get("org.su18.serialize.test.Person");

			System.out.println(
					"解析类名：" + ctClass.getName() + "，父类：" + ctClass.getSuperclass().getName() +
							"，实现接口：" + Arrays.toString(ctClass.getInterfaces())
			);


			System.out.println("-----------------------------------------------------------------------------");

			// 获取所有的构造方法
			CtConstructor[] ctConstructors = ctClass.getDeclaredConstructors();

			// 获取所有的成员变量
			CtField[] ctFields = ctClass.getDeclaredFields();

			// 获取所有的成员方法
			CtMethod[] ctMethods = ctClass.getDeclaredMethods();

			// 输出所有的构造方法
			for (CtConstructor ctConstructor : ctConstructors) {
				System.out.println(ctConstructor.getMethodInfo());
			}

			System.out.println("-----------------------------------------------------------------------------");

			// 输出所有成员变量
			for (CtField ctField : ctFields) {
				System.out.println(ctField);
			}

			System.out.println("-----------------------------------------------------------------------------");

			// 输出所有的成员方法
			for (CtMethod ctMethod : ctMethods) {
				System.out.println(ctMethod);
			}

			System.out.println("-----------------------------------------------------------------------------");


		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
