### 反射String的类的信息

```java
package com.zzx.reflect.getstructure;

import java.lang.reflect.Modifier;

/**
 * @Author: ZZX
 * @Date: 2022/6/10 16:27
 */
public class GetString {
    public static void main(String[] args) {
        //拼接字符串【使用StringBuilder，效率比String的"+"要快】
        StringBuilder builder=new StringBuilder();

        try {
            //拿到类
            Class stringClass=Class.forName("java.lang.String");

            //拿到修饰符
            String modifiers = Modifier.toString(stringClass.getModifiers());

            //添加进入
            builder.append(modifiers);

            builder.append(" ");

            //拿到类名

            String simpleName = stringClass.getSimpleName();

            builder.append(simpleName);

            //添加父类
            if (stringClass.getSuperclass().getSimpleName()!=null){
                builder.append(" extends ");
                builder.append(stringClass.getSuperclass().getSimpleName());
            }

            //拿到所有实现的接口数组
            Class[] interfaces = stringClass.getInterfaces();
            //判断是否实现了接口
            if(interfaces.length!=0){
                builder.append(" implements ");
            }

            //循环添加
            for(int i=0;i<interfaces.length;i++){
                //拿到接口
                Class interfaceClass=interfaces[i];

                //添加接口信息
                builder.append(interfaceClass.getSimpleName());

                //如果不是最后一个，添加逗号
                if(i!=interfaces.length-1){
                    builder.append(",");
                }
            }

            //添加花括号
            builder.append("{}");

            //拿到最后的结果
            System.out.println(builder.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

```

**结果：public final String extends Object implements Serializable,Comparable,CharSequence{}**