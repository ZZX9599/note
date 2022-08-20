### FastDFS启动



**FastDFS服务启动需要启动两个脚本：**



**1：启动FastDFS的tracker服务**

**在任意目录下执行：fdfs_trackerd /etc/fdfs/tracker.conf**

​                               

**2：启动FastDFS的storage服务**

**在任意目录下执行：fdfs_storaged /etc/fdfs/storage.conf start**

 

**3：查看启动进程**

 **ps -ef | grep fdfs**

**有启动的执行命令即为启动成功**



**4：查看storage是否已经注册到了tracker下**

**fdfs_monitor /etc/fdfs/storage.conf**



### 首次启动storage后，会在配置的路径下创建存储文件目录创建data文件夹

### **一共有256*256=65535个文件夹**





## FastDFS重启

### 1：重启tracker

**fdfs_trackerd /etc/fdfs/tracker.conf restart**

​                               

### 2：重启storage

**fdfs_storaged /etc/fdfs/storage.conf restart**

 

## FastDFS关闭

### 1：关闭tracker执行命令

**在任意目录下执行：fdfs_trackerd /etc/fdfs/tracker.conf stop**

 

### 2：关闭storage执行命令

**在任意目录下执行：fdfs_storaged /etc/fdfs/storage.conf stop**

 

### 3：或者kill关闭fastdfs，但不建议在线上使用 kill -9 强制关闭，因为可能会导致文件信息不同步问题