# 新web服务上线
1. 确定一个不重复的服务名 appKey，需要在如下服务中确定名称不重复
   1. <a href="https://github.com/MisakaMikotoStudio">github</a> ：仓库中，确保没有重名的项目
   2. <a href="http://config.ztwcy1314.cn/project?id=2">配置中心</a> ：appid不重复
   3. 数据库：没有重复的库名。当下测试、生产各一个数据库，地址、账号密码等在配置中心任一服务的配置中查看即可。
2. 服务初始化-运行当前目录下的脚本 `python app.py -e appkey -c appname -p xxx` 
   1. 脚本参数说明：
      1. -e：项目的唯一英文表示
      2. -c：项目中文名
      3. -p：端口号，与现有端口号不要重复
   2. 脚本执行内容
      1. 在配置中心创建应用：(appkey，appname)
      2. 在数据库中创建库：appkey
      3. 本地初始化项目代码
      4. 本地代码推送到远端仓库
      5. 在服务器上部署代码，并启动
3. 服务发布-运行项目根目录下的脚本：`python publish.py -e xxx -b xxxx `
   1. -e：发布环境。目前可选参数：test-测试、prod-生产。测试环境可以发布任意分支，但是生产只能发布main分支
   2. -b：分支名。该参数可以不传，不传参数时，默认发布main分支最新版本，传参数时，代表发布该分支的最新版本

