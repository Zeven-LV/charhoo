package com.charhoo.javaBase.thread;

public class TreadLocalTest {
    
        private static final ThreadLocal<Object> threadLocal = new ThreadLocal<Object>(){
            /**
             * ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法，返回此方法值
             */
            @Override
            protected Object initialValue()
            {
                System.out.println("调用get方法时，当前线程共享变量没有设置，调用initialValue获取默认值！");
                return null;
            }
        };

        public static void main(String[] args)
        {
            new Thread(new MyIntegerTask("IntegerTask1")).start();
            new Thread(new MyStringTask("StringTask1")).start();
            new Thread(new MyIntegerTask("IntegerTask2")).start();
            new Thread(new MyStringTask("StringTask2")).start();
        }

        public static class MyIntegerTask implements Runnable
        {
            private String name;

            MyIntegerTask(String name)
            {
                this.name = name;
            }

            @Override
            public void run()
            {
                for(int i = 0; i < 5; i++)
                {
                    // ThreadLocal.get方法获取线程变量
                    if(null == TreadLocalTest.threadLocal.get())
                    {
                        // ThreadLocal.et方法设置线程变量
                        TreadLocalTest.threadLocal.set(0);
                        System.out.println("线程" + name + ": 0");
                    }
                    else
                    {
                        int num = (Integer)TreadLocalTest.threadLocal.get();
                        TreadLocalTest.threadLocal.set(num + 1);
                        System.out.println("线程" + name + ": " + TreadLocalTest.threadLocal.get());
                        if(i == 3)
                        {
                            TreadLocalTest.threadLocal.remove();
                        }
                    }
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        }

        public static class MyStringTask implements Runnable
        {
            private String name;

            MyStringTask(String name)
            {
                this.name = name;
            }

            @Override
            public void run()
            {
                for(int i = 0; i < 5; i++)
                {
                    if(null == TreadLocalTest.threadLocal.get())
                    {
                        TreadLocalTest.threadLocal.set("a");
                        System.out.println("线程" + name + ": a");
                    }
                    else
                    {
                        String str = (String)TreadLocalTest.threadLocal.get();
                        TreadLocalTest.threadLocal.set(str + "a");
                        System.out.println("线程" + name + ": " + TreadLocalTest.threadLocal.get());
                    }
                    try
                    {
                        Thread.sleep(800);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        }
}
