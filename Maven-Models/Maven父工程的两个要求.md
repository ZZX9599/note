### Maven父工程的两个要求



### 1:packaging必须设置为pom，默认为jar

### 2:把src删除掉



### pom:项目对象模型  Project Object Model

### pom文件是可以被他的子工程继承的

### 实际上，就是让他的子模块的pom文件来继承父工程的pom



```java
<groupId>com.zzx</groupId>
<version>1.0.0</version>
这两个是父工程已经定义好了的
子模块只能改变<artifactId>
```