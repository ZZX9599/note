### Java操作FastDFS



**加入java的fastdfs依赖库**

```xml
<dependency>
       <groupId>org.csource</groupId>
       <artifactId>fastdfs-client-java</artifactId>
       <version>1.29-SNAPSHOT</version>
</dependency>
```



**代码**

```java
package com.zzx.fastfds;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
public class FastDFSUtil {
    //Tracker服务端对象
    public static TrackerServer ts;
    //Storage服务端对象
    public static StorageServer ss;
    //Tracker客户端对象
    public static TrackerClient tc;
    //Storage客户端对象
    public static StorageClient sc;

    public static void main(String[] args) {
        //upload();
        //download();
        delete();
    }
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
     */
    private static void upload() {
        try {
            //这个是追加文件【通常适用在Web工程】
            //sc.upload_appender_file()
            //本地文件上传【适合本地java工程】
            /**
             * 文件上传都返回String数组【这个数组的内容很重要，一般存入数据库】
             * 参数一：文件绝对路径
             * 参数二：文件扩展名
             * 参数三：文件的属性文件【一般不上传】
             */
            String[] strings=sc.upload_file("C:\\Users\\ZZX\\Documents\\Navicat\\test.txt","txt",null);
            for(String str:strings){
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件下载
     */
    private static void download() {
        try {
            /**
             * 参数1：组名
             * 参数二：远程文件名
             * 参数三：保存的本地文件名
             * 结果为0的话代表下载成功，其他都代表失败
             */
            int result=sc.download_file("group1","M00/00/00/wKjch2JWwbuAZRt_AAAABv02uM8353.txt","E:\\test.txt");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     */
    private static void delete() {
        try {
            /**
             * 参数一：组名
             * 参数二：远程文件名
             * 返回0代表成功，返回其他数字代表失败
             */
            int result=sc.delete_file("group1","M00/00/00/wKjch2JWxsOABQ_IAAAABv02uM8906.txt");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}

```

