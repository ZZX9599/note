### each循环

each循环， 可以循环List，Array等

语法：

在一个html标签中，使用th:each

例如：

```xml
<div th:each="集合循环成员,循环的状态变量:${key}">
    <p th:text="${集合循环成员}" ></p>
</div>

集合循环成员,循环的状态变量:两个名称都是自定义的。 
“循环的状态变量”这个名称可以不定义
默认是"集合循环成员Stat"
```

循环的状态变量是一个对象，里面有若干属性



th:each="user, iterStat : ${userlist}"中的 ${userList} 是后台传过来的集合

user 定义变量，去接收遍历${userList}集合中的一个数据

iterStat ${userList} 循环体的信息【不定义的话，默认是集合循环成员Stat】

其中 user 及 iterStat 自己可以随便取名

interStat 是循环体的信息，通过该变量可以获取如下信息：



index: 当前迭代对象的 index（从 0 开始计算） 

count: 当前迭代对象的个数（从 1 开始计算）这两个用的较多 

size: 被迭代对象的大小 

current: 当前迭代变量 

even/odd: 布尔值，当前循环是否是偶数/奇数（从 0 开始计算） 

first: 布尔值，当前循环是否是第一个 last: 布尔值，当前循环是否是最后一个 

注意：循环体信息 interStat 也可以不定义，则默认采用迭代变量加上 Stat 后缀，即 userStat



```Java
@GetMapping("/eachList")
public String eachList(Model model){
    List<Student> list=new ArrayList<>();
    list.add(new Student(1001,"周志雄",20));
    list.add(new Student(1002,"蒋雪丽",22));
    list.add(new Student(1003,"周星怡",22));
    list.add(new Student(1004,"周宗立",48));
    list.add(new Student(1005,"石庆润",47));
    model.addAttribute("studentList",list);
    return "eachList";
}
```





```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>eachList</title>
</head>
<body>
<!--student:每个成员 studentState:成员状态变量,是一个对象，有很多属性  ${studentList}集合-->
    <div>
        <table border="1">
            <thead>
            <tr>
                <td>编号</td>
                <td>id</td>
                <td>name</td>
                <td>age</td>
            </tr>
            </thead>
            <tbody>
            <tr th:each="student,studentState:${studentList}">
                <td th:text="${studentState.count}"></td>
                <td th:text="${student.id}"></td>
                <td th:text="${student.name}"></td>
                <td th:text="${student.age}"></td>
            </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
```

**循环Array和循环List完全相同**



#### 循环Map

```Java
@GetMapping("/eachMap")
public String eachMap(Model model){
    Map<String,Student> studentMap=new TreeMap<>();
    studentMap.put("student1",new Student(1001,"周志雄",20));
    studentMap.put("student2",new Student(1002,"蒋雪丽",22));
    studentMap.put("student3",new Student(1003,"周星怡",22));
    studentMap.put("student4",new Student(1004,"周宗立",48));
    studentMap.put("student5",new Student(1005,"石庆润",47));
    model.addAttribute("studentMap",studentMap);
    return "eachMap";
}
```



```HTML
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>eachList</title>
</head>
<body>
    <div>
        <table border="2">
            <thead>
            <tr>
                <td>key值</td>
                <td>编号</td>
                <td>id</td>
                <td>name</td>
                <td>age</td>
            </tr>
            </thead>
            <tbody>
            <tr th:each="map,studentState:${studentMap}">
                <td th:text="${map.key}"></td>
                <td th:text="${studentState.count}"></td>
                <td th:text="${map.value.id}"></td>
                <td th:text="${map.value.name}"></td>
                <td th:text="${map.value.age}"></td>
            </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
```