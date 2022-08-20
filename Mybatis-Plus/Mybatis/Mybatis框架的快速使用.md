### Mybatis框架的快速使用

**1：创建数据库和表**

**2：创建工程，添加依赖**

**3：创建实体类**

**4：添加数据库配置文件**

**5：添加mybatis主配置文件**

**6：创建mapper文件**

**7：测试功能**



**使用Junit单元测试完成各种功能测试.**

**使用@Before注解来进行所有测试前的SqlSession的创建工作.**

**使用@After注解来进行所有测试方法执行后的关闭SqlSession的工作.**

**使用@Test注解来验证每一个功能的实现**



```java
@Before
    public void openSession()throws Exception{
        //创建文件流,读取SqlMapConfig.xml
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        //取得SqlSession
        session = factory.openSession();
    }
@Test
    public void testSelectStudentAll()throws Exception{
        List<Student> list = session.selectList("com.bjpowernode.selectStudentAll");
        list.forEach(stu-> System.out.println(stu));
    }
    @Test
    public void testStudentGetById(){
        Student stu = session.selectOne("com.bjpowernode.selectStudentById",2);
        System.out.println(stu);
    }
    @Test
    public void testSelectStudentByEmail(){
        List<Student> list = session.selectList("com.bjpowernode.selectStudentByEmail","l");
        list.forEach(stu-> System.out.println(stu));
    }
    @Test
    public void testInsertStudent(){
        Student stu = new Student("李四四","23234@qq.com",22);
        int num = session.insert("com.bjpowernode.insertStudent",stu);
        //切记切记切记:因为我们的事务管理机制使用的是JDBC,所以所有的增删改查后,要手工提交事务
        //session.commit();
        session.commit();//手工提交事务
        System.out.println(num);
    }
@After
public void closeSession(){
        session.close();
    }
}
```

