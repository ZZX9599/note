### 回顾JDBC



```java
public void findStudent() {
Connection conn = null; 
Statement stmt = null;
ResultSet rs = null;
try {
//注册 mysql 驱动 
  Class.forName("com.mysql.jdbc.Driver");
//连接数据的基本信息 url ，username，password
String url = "jdbc:mysql://localhost:3306/springdb"; 
String username = "root";
String password = "123456";
//创建连接对象 
conn = DriverManager.getConnection(url, username, password);
//保存查询结果 
List<Student> stuList = new ArrayList<>();
//创建 Statement, 用来执行 sql 语句 
stmt = conn.createStatement();
//执行查询，创建记录集， 
rs = stmt.executeQuery("select * from student");
while (rs.next()) {
Student stu = new Student(); 
stu.setId(rs.getInt("id"));
stu.setName(rs.getString("name"));
stu.setAge(rs.getInt("age"));
//从数据库取出数据转为 Student 对象，封装到 List 集合 
stuList.add(stu);}
  }catch(Exception e){
      e.printStackTrace();
}finally{
   try{
if(rs != null)
      rs.lose();
if(pstm != null)
      pstm.close();
   if(con != null)
      con.close();
   
}catch(Exception e){
  e.printStackTrace();
}
}

```

**使用JDBC的缺点**

**\1. 代码比较多，开发效率低**

**\2. 需要关注 Connection ,Statement, ResultSet 对象创建和销毁**

**\3.  对 ResultSet 查询的结果，需要自己封装为 List**

**\4. 重复的代码比较多些**

**\5. 业务代码和数据库的操作混在一起**