### Nginx配置文件解读



#### 1：基础配置  

```yml
配置worker进程的运行用户
#user  nobody;

#配置工作进程数量，通常配置为cpu数量或者cpu数量的两倍
worker_processes  1;

#配置全局日志以及类型【一般只记录错误】
#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#配置进程pid文件【里面只有一串数字】
#pid        logs/nginx.pid;
```

**在实际的开发过程中，在基础配置里面我们只调整work_processes**

**设置为1，则有一个master进程，一个worker进程**

**设置为2，则有一个master进程，两个worker进程**





#### 2：event工作模式

```yml
#每个worker进程的连接上限，支持的总连接数就是worker进程的数量 × worker_connections
#每个worker进程的上限是65535
events {
    worker_connections  1024;
}
```

**在实际的开发中，配置多少需要测试**





#### 3：http配置：利用它的反向代理功能来提供负载均衡

```yml
http {
    #配置nginx支持哪些多媒体类型【在conf目录下面有mime.type文件】
    include       mime.types;
    #默认文件类型，流文件，可以理解为支持任意类型
    default_type  application/octet-stream;

    #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
    #                  '$status $body_bytes_sent "$http_referer" '
    #                  '"$http_user_agent" "$http_x_forwarded_for"';
	
	#配置日志以及路径，使用上面的main日志格式【不管访问哪个server，都会触发，如果要配置，上面也打开】
    #access_log  logs/access.log  main;

	#高效文件传输
    sendfile        on;
    #防止网络阻塞【一般都打开】
    #tcp_nopush     on;

	#长连接超时
    #keepalive_timeout  0;
    keepalive_timeout  65;

	#开启gzip压缩输出【一般都打开】
    #gzip  on;


	#配置虚拟主机
    server {
    	#端口
        listen       80;
        #服务名，域名
        server_name  localhost;

		#配置字符集
        #charset koi8-r;

		#访问日志【访问了这个server，就会触发】
        #access_log  logs/host.access.log  main;

		# "/"代表根路径
        location / {
        	#root代表本地磁盘根路径
            root   html;
            #访问打开的页面
            index  index.html index.htm;
            #也就是访问server的ip和端口，就会到html路径下面去找index.html/index.htm
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        
        #精准拦截
        location = /50x.html {
            root   html;
        }

        # proxy the PHP scripts to Apache listening on 127.0.0.1:80
        #
        #location ~ \.php$ {
        #    proxy_pass   http://127.0.0.1;
        #}

        # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
        #
        #location ~ \.php$ {
        #    root           html;
        #    fastcgi_pass   127.0.0.1:9000;
        #    fastcgi_index  index.php;
        #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
        #    include        fastcgi_params;
        #}

        # deny access to .htaccess files, if Apache's document root
        # concurs with nginx's one
        #
        #location ~ /\.ht {
        #    deny  all;
        #}
    }
```

