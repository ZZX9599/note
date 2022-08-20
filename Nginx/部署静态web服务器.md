### 部署静态web服务器

**Nginx是一个HTTP的web服务器，可以将服务器上的静态文件（如HTML、图片等）通过HTTP协议返回给浏览器客户端**

**注意：动态资源不能部署 例如jsp等**

```
location /ace{
    root  /opt/www;
    index login.html;
}
```

**注意：/opt/www   和location的  /  代表是一个东西**

**http:127.0.0.1:80===============/opt/www**

**http:127.0.0.1:80/crm ==========/opt/www/crm**

**找的文件实际上是  /opt/www/ace下的login.html**

