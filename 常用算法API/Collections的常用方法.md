### **Collections的常用方法**



**List重写了toString方法，直接输出list就能得到添加的元素信息**

**显示的效果如下：[?,?,?,?,?,?]**



**1：反转List集合**

**Collections.reverse(List list)**

**注意：只能反转List,不能反转Set**



**2：对List集合进行排序**

**Collections.sort(List list)**

**注意：只能对List进行排序**



**3：对List进行元素交换**

**Collections.swap(list,i,j)**

**交换对应下标的元素**

**注意：只能对List使用**



**4：对List进行复制**

**Collections.copy(list1,list2)**

**将list2复制给list1**

**前提是list1的容量要足够大，LinkedList的初始容量0，有的JDK版本下，ArrayList也是0**

**注意：只能对List使用**



##### **5：替换List的元素，全部替换**

**Collections.replaceAll(list,A,B)**

**注意：只能对List使用**





**List和Set都能用的方法**

**1：max/min(list/set)**

**返回集合的最大最小值**



**2：frequency(list/set , element)**

**返回元素出现的次数**

