package com.charhoo.behave.template;

public abstract class AbstractCalculator {

	/**
	 * 定义了方法的基本骨架和执行顺序
	 * @param exp
	 * @param opt
	 * @return
	 */
	public final int calculate(String exp, String opt) {
		int array[] = split(exp, opt);
		method2();
		return calculate(array[0], array[1]);
	}

	/**
	 * 私有方法，只能由父类提供
	 * @param exp
	 * @param opt
	 * @return
	 */
	private int[] split(String exp, String opt) {
		String array[] = exp.split(opt);
		int arrayInt[] = new int[2];
		arrayInt[0] = Integer.parseInt(array[0]);
		arrayInt[1] = Integer.parseInt(array[1]);
		return arrayInt;
	}

	/**
	 * 钩子方法, 父类提供默认实现，子类可重写
	 */
	public void method2(){
		System.out.println("父类提供默认实现");
	}

	/**
	 * 抽象方法，需要子类实现
	 * @param num1
	 * @param num2
	 * @return
	 */
	abstract protected int calculate(int num1, int num2);

}
