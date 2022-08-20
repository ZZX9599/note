### JVM的定义

**1：Java虚拟机是整个Java平台的基石**

**是Java技术用以实现硬件无关与操作系统无关的关键部分，是Java语言生成出极小体积的编译代码的运行平台，是保障用户机器免于恶意**

**代码损害的屏障。**



**2：Java虚拟机可以看做一台抽象的计算机如同真实的计算机那样，它有自己的指令集以及各种运行时内存区域。**



**3：Java虚拟机与Java语言并没有必然的联系，它只与特定的二进制文件格式class文件格式所关联，class文件包含了Java虚拟机指令集**

**或者称为字节码bytecode和符号表，以及其他一些辅助信息。**



**总结：JVM并不是跟java绑定的，JVM只认识Class文件，JVM的存在，所以JVM是跨平台跨操作系统的**



### JVM规范

**JVM规范并不是指特定的jvm产品，而是一个规范，这个规范由oracle制定，具体的某款虚拟机实际上是遵守这个规范的JVM产品**

**1:Java虚拟机规范是官方对准确Java虚拟机在架构上进行的一个设计和约定**

**而具体的某某Java虚拟机这是对官方Java虚拟机规范的一个实现。**



**2:JDK版本不同，Java虚拟机规范也有一定差异**



**3:官方Java虚拟机规范地址: https://docs.oracle.com/javase/specs/index.html**



## JVM产品

**l Classic VM（早期Sun的Java虚拟机）**

**l Exact VM（早期Solaris平台上）**



**l HotSpot(OracleJDK与OpenJDK默认)**



**l JRockit（BEA的，被Oracle收购，并且整合到HotSpot里面）**

**l J9（IBM商用）**

**l Google Android Dalvik VM（Android上的）**

**l KVM、CDC、CLDC（嵌入式领域）**

**l Microsoft JVM（微软的）**

**l Graal VM（跨语言全栈虚拟机Run Programs Faster Anywhere）**



**HotSpot就是java的jdk默认的jvm虚拟机**

**我们学习的就是这个jvm产品【但是实际上我们并不是学具体的产品，我们只是通过这个产品来学习jvm的规范】**

