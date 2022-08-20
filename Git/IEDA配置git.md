### IEDA配置git

**idea项目有很多文件不需要上传，例如.iml .class .log .jar .idea等**

**建议创建一个git.ignore文件放在用户目录下，为了便于让.gitconfig引用**



**仅仅配置了，默认不会使用，需要在.gitconfig里面引用这个文件**

**[core]**

​	**excludesfile= c:users/zzx/git.ignore**   **注意斜线不要使用反了**



**在idea里面直接使用图形化界面进行操作，如果是红色，代表未被追踪的文件，也就是没add**

**绿色的话，代表是add了，但是没有commit**

**蓝色的话，代表已经commit，但是又修改过**



**git里面的选项，有一个黄色的指针，一个绿色的指针**

**一个绿色的指针，代表一个分支，有master分支和hot-fix分支，就有两个绿色的指针**

**黄色的指针，代表指向的版本信息**



**在idea里面创建分支**

**选中项目，git->branch->new branch**

**或者在右下角有git的选项，里面可以找到**



**在idea里面合并分支**

**1：正常合并   选中master分支的情况下，点击hot-fix  Merge select into Current就可以合并了**

**2：非正常合并  合并会提示冲突，左侧是master代码，中间是没冲突的代码，右边是分支代码**

