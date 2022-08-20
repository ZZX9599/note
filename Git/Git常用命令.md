### Git常用命令

**1：安装之后先设置用户签名**

git config --global user.name xxx

git config --global user.email xxx

必须先设置签名，不然使用git commit的时候会报错

设置完成之后会在用户的目录下生成 .gitxxx文件



**2：初始化本地库 git init**

初始化本地库的目的就是让git来管理这个对应的目录

会生成一个.git文件，不要更改这个文件夹里面的内容



**3：查看本地库状态  git status**

在git管理的目录下面，目录文件发生改变，或者有其他操作，status的状态会发生改变



**4：添加暂存区 git add**

git add xxx  表示把xxx文件提交到暂存区  如果有多个文件，或者想提交全部文件，直接使用 git add .

使用了git add命令，status会发生改变，不会生成历史版本，不会用到这个用户，所以还可以删除掉

使用  git rm --cached xxxx  可以把提交到暂存区的文件删掉，并不会把目录下的文件删除



**5：提交本地库 git commit **

将暂存区的文件提交到本地库，git commit -m "message" xxxx

这个时候会生成历史版本，会使用到user的信息，如果没有设置用户签名，就会报错

提交全部：git commit -m "message" .



**6：查看版本的信息 git reflog    git log**

会显示当前指向的版本信息，版本号，作者，修改时间等信息



**7：版本穿梭git reset --hard 版本号**

先使用git log或者git reflog查看详细信息，看到所有的版本，可以看到所有的版本，看到前面的版本号

使用git reset --hard yet632g  就能回退到yet632g这个版本号这个项目，里面的文件内容就是这个版本的信息

在.git里面有一个HEAD文件，里面他有一个目录·，目录下面的文件会有版本号信息