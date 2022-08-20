### Web项目实现远程文件下载



**文件下载的Controller**

```java
/**
 * 完成文件的下载
 * 参数：需要下载的文件主键
 * @return ResponseEntity  表示响应的实体，这个类是spring提供的类
 * 是Spring响应数据的对象，包含响应的编码例如404等，以及头文件信息
 * 以及响应的具体信息，可以是Html代码，也可以是一段js
 * 也可以是一段普通的字符串
 * 也可以是一个文件的流
 */
@RequestMapping("/download/{id}")
public ResponseEntity<byte[]>download(@PathVariable Integer id){
    //先查询信息，拿到远程文件名+远程库
    CreditorInfo creditorInfo=creditorInfoService.selectById(id);
    String groupName=creditorInfo.getGroupname();
    String remoteName=creditorInfo.getRemotefilepath();
    //调用下载，拿到文件流
    byte[] download = FastDfsUtil.download(groupName, remoteName);
    HttpHeaders httpHeaders=new HttpHeaders();
    //设置响应头信息为文件类型
    httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    //设置文件大小，提供下载进度
    httpHeaders.setContentLength(creditorInfo.getFileSize());
    //设置下载的默认文件名
    httpHeaders.setContentDispositionFormData("attachment",creditorInfo.getOldFilename());

    ResponseEntity<byte[]>responseEntity=new ResponseEntity<>(download,httpHeaders, HttpStatus.OK);
    return responseEntity;
}
```



**FastDFSUtil**

```java
/**
 * 文件下载
 */
public static byte[] download(String groupName,String remoteFile) {
    try {
        /**
         * 参数1：组名
         * 参数二：远程文件名
         * 结果为byte数组【也就是文件流】
         */

        byte[] bytes = sc.download_file(groupName, remoteFile);
        return bytes;
    } catch (IOException e) {
        e.printStackTrace();
    } catch (MyException e) {
        e.printStackTrace();
    }
    return null;
}
```



```xml
#设置springMVC允许上传的单个文件的大小
#spring.servlet.multipart.max-file-size=1MB

#设置springMVC允许的表单请求文件的总大小
#spring.servlet.multipart.max-request-size=10MB
```