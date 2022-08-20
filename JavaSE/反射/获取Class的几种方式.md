### 获取Class的几种方式

**1：通过.class属性，java的任何一种类型【包括基本数据类型】都有.class属性**

```java
public class TestOne {
    public static void main(String[] args) {
        Class intClass = int.class;
        System.out.println(intClass);
    }
}
```

**输出int**



```java
public class TestOne {
    public static void main(String[] args) {
        Class intClass = Integer.class;
        System.out.println(intClass);
    }
}
```

**输出class java.lang.Integer**



**2：通过getClass()方法，这个是Object类的方法，每个引用都可以使用**

```java
public class TestTwo {
    public static void main(String[] args) {
        String s="zzx";
        Class stringClass=s.getClass();
        System.out.println(stringClass);
    }
}
```

**输出class java.lang.String**





**3：使用Class.forName**

```java
public class TestThree {
    public static void main(String[] args) {
        try {
            Class dateClass = Class.forName("java.util.Date");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
```

**注意：必须是全限定类名，不然抛出异常！**



#### **深入Class.forName**

```java
public class TestClassForName {
    public static void main(String[] args) {
        try {
            Class.forName("com.zzx.reflect.getclass.User");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


class User{
    static {
        System.out.println("静态代码块执行，类被加载了！");
    }
}
```

**结果：输出了这句话，表明class.forName会导致类加载【因为类加载的时候会执行静态代码块】**

**如果只想类加载，就可以使用forNmae，例如注册驱动，初始化连接等！**