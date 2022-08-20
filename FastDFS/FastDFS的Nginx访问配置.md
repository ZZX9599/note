### FastDFS的Nginx访问配置

**1：将拓展模块下面的src目录下的mod_fastdfs.conf文件拷贝到 /etc/fdfs/目录下，这样才能正常启动Nginx**

**我的拓展模块src路径：/opt/resources/fastdfs-nginx-module-master/src**



#### 2：修改mod_fastdfs.conf配置文件

**base_path=/opt/fastdfs/nginx_mod**

**tracker_server=192.168.220.125:22122**

**url_have_group_name = true**

**store_path0=/opt/fastdfs/storage/files**



#### 3：在/opt/fastdfs/目录下创建nginx_mod目录



#### 4：配置Nginx的配置文件

**拦截请求路径中包含 /group[1-9]/M0[0-9] 的请求，用 fastdfs的Nginx 模块进行转发**

**location ~ /group[1-9]/M0[0-9] {**  

   **ngx_fastdfs_module;** 

**}**



**ngx_fastdfs_module; #这个指令不是Nginx本身提供的，是扩展模块提供的，根据这个指令找到FastDFS提供的Nginx模块配置文件，然后找到Tracker，最终找到Storage**



#### FastDFS的Nginx访问启动与测试

**1：启动带有Fastdfs模块的Nginx**

**2：重启或启动FastDFS服务进程**

**fdfs_trackerd /etc/fdfs/tracker.conf restart**

**fdfs_storaged /etc/fdfs/storage.conf restart**

**3：上传一个文件进行测试验证**

**fdfs_test /etc/fdfs/client.conf upload /root/text.txt**

**4：在浏览器访问上传的文件**

**当遇到400错误，检查配置/etc/fdfs/mod_fastdfs.conf url_have_group_name=true** 

**该配置表示访问路径中是否需要带有group1，改为true表示路径中需要有group1**

**这个时候文件上传之后的url路径就能通过：**

**http://192.168.220.135/group1/M00/00/00/wKjch2JVf-OALbwaAAAACNsMNUM566_big.txt来访问了**

