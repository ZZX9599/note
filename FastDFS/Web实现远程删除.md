### Web实现远程删除



**FastDfs**

```java
/**
 * 删除文件
 */
public static void delete(String groupName,String remoteName) {
    try {
        /**
         * 参数一：组名
         * 参数二：远程文件名
         * 返回0代表成功，返回其他数字代表失败
         */
        int result=sc.delete_file(groupName,remoteName);
        System.out.println(result);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (MyException e) {
        e.printStackTrace();
    }
}
```



```java
@Override
public void deleteById(Integer id) {
    CreditorInfo creditorInfo=creditorInfoMapper.selectById(id);
    //先删除文件，再更新数据库记录
    FastDfsUtil.delete(creditorInfo.getGroupname(),creditorInfo.getRemotefilepath());
    creditorInfo.setGroupname("");
    creditorInfo.setRemotefilepath("");
    creditorInfo.setOldFilename("");
    creditorInfo.setFileType("");
    creditorInfo.setFileSize(0L);
    creditorInfoMapper.updateById(creditorInfo);
}
```