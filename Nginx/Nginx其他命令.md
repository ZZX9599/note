### Nginx其他命令

**一般启动Nginx，推荐使用配置文件启动**

**在启动之前，推荐检查一下配置文件**

**命令：/usr/local/nginx/sbin/nginx -c /usr/local/nginx/conf/nginx.conf -t**

**也就是带配置文件启动的时候，加上参数 -t ，返回success，就代表配置文件有效且成功！**



### Linux上查看nginx版本

**/usr/local/nginx/sbin/nginx -V**

**-v （小写的v）显示 nginx 的版本**

**-V （大写的V）显示 nginx 的版本、编译器版本和配置参数**