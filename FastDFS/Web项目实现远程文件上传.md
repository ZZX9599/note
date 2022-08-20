### Web项目实现远程文件上传

**在实际的工作中，我们并不是直接把整个文件上传，而是传输这个字节数组流**

**配合spring的MultipartFile，Nginx，FastDFS整合配置**



**FastDFS的文件上传有很多的重载方法，我们一般都使用字节数组上传**

**1：我们只需要拿到上传文件的字节数组，通过 MultipartFile 对象的getBytes拿到字节数组**



**2：文件的拓展名  使用 MultipartFile 对象的getName来拿到文件的全名**

**再通过subString来截取字符串拿到文件拓展名    **

**使用String fileExtName=fileName.substring(fileName.lastIndexOf(".")+1)**



**3：参数三是文件的属性文件，基本上不需要**



**使用Java的FastDFS上传文件，得到的结果都是String数组**

**这个数组的结果很重要：第一个元素是远程文件的组名   第二个是远程文件的名称**



**这个时候拿到返回的数组，解析它里面的元素，就能够拿到对应的数据，写入数据库了**



**代码如下**



**FastDfsUtil工具类**

```Java
public class FastDfsUtil {
    //Tracker服务端对象
    public static TrackerServer ts;
    //Storage服务端对象
    public static StorageServer ss;
    //Tracker客户端对象
    public static TrackerClient tc;
    //Storage客户端对象
    public static StorageClient sc;
    
    static {
        //初始化：读取FastDFS的配置文件，将所有的Tracker_server的地址读取到内存
        try {
            ClientGlobal.init("fastdfs.conf");
            //Tracker客户端对象
            tc=new TrackerClient();
            //Tracker服务对象
            ts=tc.getTrackerServer();
            //通过Tracker客户端对象拿到Storage服务对象【传入Tracker服务对象】
            ss=tc.getStoreStorage(ts);
            //拿到具体的Storage客户端对象【可以执行上传，下载等】
            sc=new StorageClient(ts,ss);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }


    /**
     * 文件上传
     * buffFile:文件对应的字节数组
     * fileExtName:文件的扩展名
     */
    public static String[] upload(byte[] buffFile,String fileExtName) {
        try {
             //这个是追加文件【通常适用在Web工程】
             //sc.upload_appender_file()
             //本地文件上传【适合本地java工程】
             /**
             * 文件上传都返回String数组【这个数组的内容很重要，一般存入数据库】
             * 参数一：文件字节数组
             * 参数二：文件扩展名
             * 参数三：不写
             */
            String[] strings=sc.upload_file(buffFile,fileExtName,null);
            return strings;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```



**控制层**

```java
@PostMapping("/upload")
public String upload(Integer id, Model model, MultipartFile myFile) throws IOException {
    //获取字节数组
    byte[] bytes=myFile.getBytes();
    //获取文件大小
    Long fileSize=myFile.getSize();
    //获取文件类型
    String fileType= myFile.getContentType();
    //获取文件名
    String fileName=myFile.getOriginalFilename();
    //获取文件拓展名
    String fileExtName=fileName.substring(fileName.lastIndexOf(".")+1);
    System.out.println("拓展名:"+fileExtName);
    String[] upload = FastDfsUtil.upload(bytes, fileExtName);
    CreditorInfo creditorInfo=new CreditorInfo();
    creditorInfo.setId(id);
    creditorInfo.setFileSize(fileSize);
    creditorInfo.setFileType(fileType);
    creditorInfo.setOldFilename(fileName);
    creditorInfo.setGroupname(upload[0]);
    creditorInfo.setRemotefilepath(upload[1]);
    creditorInfoService.updateFileInfo(creditorInfo);
    return "upload";
}
```

**把MultipartFile的信息和远程库和远程文件名都拿到，存入mysql数据库**

