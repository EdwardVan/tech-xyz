###查看log
- git log                   查看log
- git log --pretty=oneline  查看简单版log
- git log --graph           查看分支合并图
- git reflog                记录每一次命令
###版本回退
- git reset --hard HEAD^   版本回退到上一个版本
- git reset --hard <版本号>  回退到指定的版本

###差异比较
- git diff             #工作区(work dict)和暂存区(stage)的比较
- git diff --cached    #暂存区(stage)和分支(master)的比较
- git diff HEAD        #工作区(work dict)和分支(master)里面最新版本的比较

###还原
- git checkout -- <file>  把指定文件工作区(work dict)恢复到最近一次add或commit时的状态(add优先)
- git checkout .          把所有工作区(work dict)恢复到最近一次add或commit时的状态(add优先)
- git reset HEAD  <file>  把指定文件暂存区(stage)的文件清除
- git reset HEAD		  把全部暂存区(stage)的文件清除
- git stash               把工作区的文件存储起来并且工作区恢复到分支
- git stash pop           恢复stash内容

###分支
- git branch              查看分支
- git branch -a           查看所有分支(本地分支,本地远程分支镜像,远程分支)
- git branch <name>       创建分支
- git branch -d <name>    删除分支

- git checkout <name>     切换分支
- git checkout -b <name>  创建+切换分支
- git checkout -b <本地分支名> <远程仓库名称>/<远程分支名>

- git merge <本地仓库分支>     合并本地仓库分支到当前分支
- git merge <远程仓库名称>/<远程分支名>  合并指定远程分支名到当前分支

###标签
- git tag <name>             打一个新标签
- git tag                    查看所有标签
- git show <tagname>         查看标签信息
- git tag -d <tagname>       删除标签
- git push origin <tagname>  推送标签到远程仓库

###远程仓库
- git remote -v 查看远程仓库列表及状态
- git remote add <远程仓库名称> <远程仓库地址>  添加远程仓库
- git remote rm <远程仓库名称> 删除远程仓库
- git clone  克隆远程仓库到本地,默认执行 git remote add origin <远程仓库地址>


- git config --global push.default simple    把push命令的全局默认模式设置成simple
- git config --global pull.default simple    把pull命令的全局默认模式设置成simple

- git fetch <远程仓库名称>  取回远程仓库所有分支的更新到本地远程分支镜像
- git fetch <远程仓库名称> <远程分支名>  取回远程仓库指定分支的更新到本地远程分支镜像

- git branch --set-upstream-to=<远程仓库名称>/<远程分支名> <本地仓库分支> 设置远程分支名和本地仓库分支的关联关系

- git pull <远程仓库名称> <远程分支名>:<本地仓库分支> 等价于 git fetch <远程仓库名称> + git merge <远程仓库名称>/<远程分支名>
- git pull <远程仓库名称>  当前分支与远程分支存在追踪关系,可以省略远程分支名称
- git pull                 如果本地分支只有一个追踪分支  远程仓库名称可以省略

- git push <远程仓库名称> <本地仓库分支>:<远程分支名>  推送到远程主机
- git push <远程仓库名称>      当前分支与远程分支存在追踪关系,可以省略远程分支名称
- git push                     如果本地分支只有一个追踪分支  远程仓库名称可以省略
- git push -u <远程仓库名称> <远程分支名> 相当于 git push <远程仓库名称> <远程分支名> + git branch --set-upstream-to=<远程仓库名称>/<远程分支名> <本地仓库分支>

###合并方式:

- 快进模式合并  Fast-forward  --no-ff参数可以禁用该模式

- 三路合并原理
    >取本地冲突的B节点,对方分支的C节点,B,C节点的共同最近祖先节点A.
    如果B,C节点的某个文件和A节点中的相同,那么不产生冲突;
    如果B或C只有一个和A节点相比发生变化,那么该文件将会采用该变化了的版本;
    如果B和C和A相比都发生了变化,且变化不相同,那么则需要手动去合并;
    如果B,C都发生了变化,且变化相同,那么并不产生冲突,会自动采用该变化的版本.
    最终合并后会产生D节点,D节点有两个父节点,分别为B和C.

