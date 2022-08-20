### join和interrupt

**join方法，可以在一个线程中，调用另外一个线程的join方法，这个时候，当前的线程会被暂停，直到被调用的线程执行完成，才会执行当前的线程**



```java
package com.zzx.thread.thread;

/**
 * @author ZZX
 * @date 2022/6/7 20:05
 */
public class TestJoin {
    public static void main(String[] args) {
        Thread thread01=new Thread(new Process02());
        thread01.setName("线程一");
        thread01.start();

    }
}

class Process02 implements Runnable{
    private static final String THREAD_NAME="线程一";
    @Override
    public void run() {

        if (THREAD_NAME.equals(Thread.currentThread().getName())){
            Process02 process02=new Process02();
            Thread thread=new Thread(process02);
            try {
                thread.setName("线程二");
                thread.start();
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("当前线程的名字:"+Thread.currentThread().getName());
    }
}
```

**不管怎么执行，都是会等线程二先输出，才会有线程一**

**线程一在启动的时候，执行run方法，在过程中，把线程二加入到了线程一，就会等线程二执行结束之后才会向下执行**





#### interrupt

**如果我们的线程正在睡眠，可以使用interrupt中断睡眠**

```java
package com.zzx.thread.thread;

/**
 * @author ZZX
 * @date 2022/6/8 19:32
 */
public class TestInterrupt {
    public static void main(String[] args) {
        Thread thread=new Thread(new Process03());

        thread.start();
        
        thread.interrupt();
    }
}

class Process03 implements Runnable{

    @Override
    public void run() {
        for (int i=0;i<10;i++){
            if(i==5){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("睡眠中断");
                }
            }
        }
    }
}
```

**睡眠被中断，会抛出异常！**