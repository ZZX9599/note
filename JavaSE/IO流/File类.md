### File类

**在整个io的包里面，唯一和文件本身有关的类就是File类，使用File类可以创建文件或者是删除文件**

**注意：File对象是文件和目录的抽象表现形式，File类和流没有关系，通过File类是无法完成文件读写**



#### 常用的构造方法：

**public File(String pathName)**

**注意：没有的话，并不会创建这个文件，需要自己创建**



#### 创建文件：

```java
package com.zzx.io.file;

import java.io.File;
import java.io.IOException;

/**
 * @author ZZX
 * @date 2022/6/4 21:00
 */
public class TestFile {
    public static void main(String[] args) {
        //创建一个文件对象，注意，如果不存在，并不会创建
        File file=new File("E:\\io-dir\\day07.txt");
        if (!file.exists()) {
            //创建文件
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```



#### 创建目录：

```java
package com.zzx.io.file;

import java.io.File;
import java.io.IOException;

/**
 * @author ZZX
 * @date 2022/6/4 21:00
 */
public class TestFile {
    public static void main(String[] args) {
        //创建一个文件对象，注意，如果不存在，并不会创建
        File file=new File("E:\\io-dir\\myFile");
        if (!file.exists()) {
            //创建目录
            file.mkdir();
        }
    }
}
```

