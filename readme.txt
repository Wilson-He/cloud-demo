program arguments:--spring.profiles.active=Peer1

SVN分支流程：
1.checkout master-url到文件夹trunk
2.文件夹trunk选择Brach/Tag到服务器分支branch-url
3.新建branch文件夹,branch文件夹SVN switch到服务器分支branch-url
4.对branch进行修改提交
5.对文件夹trunk执行Merge,from master-url to branch-url