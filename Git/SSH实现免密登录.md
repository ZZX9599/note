### SSH实现免密登录

**对于github  可以使用https，也可以使用ssh**

**默认情况下，点击ssh会提示没有公钥**

**命令：ssh-keygen -t rsa -C zzx@qq.com**

**会在用户目录下生成一个id_rsa【私钥】 id_rsa.pub【公钥】**



**复制公钥，然后就能够使用 SSH来实现git push和git pull命令了**

