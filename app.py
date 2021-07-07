#!/usr/bin/python3
import sys, getopt

app_en_name = ""
app_cn_name = ""
port = -1
logfile = None

'''
todo
1. 每一步操作，需要在界面上输出进度
'''

'''
加载脚本日志，主要是为了在某一步失败时，下次运行脚本可以继续从这里开始
'''
def load_log():
	pass

'''
配置中心创建服务
'''
def create_leo():
	# 如果脚本已经执行过，则跳过
	# 创建服务
	# 配置数据库参数
	pass

'''
创建测试数据库
'''
def create_mysql_test():
	# 如果脚本已经执行过，则跳过
	# 创建数据库
	# 创建user表与log表
	pass

'''
创建生产数据库
'''
def create_mysql_prod():
	# 如果脚本已经执行过，则跳过
	# 创建数据库
	# 创建user表与log表
	pass

'''
创建github的repo
'''
def init_code():
	# 如果脚本已经执行过，则跳过
	# 本地初始git项目
	# 项目代码初始化
	# 推送到远端仓库
	pass

'''
服务器部署-测试环境
'''
def deploy_test():
	# 如果已经执行过，则跳过
	# 服务器上下载代码
	# 代码打包
	# supervisor添加新服务
	# 健康测试
	pass

'''
服务器部署-生产环境
'''
def deploy_prod():
	# 如果已经执行过，则跳过
	# 服务器上下载代码
	# 代码打包
	# supervisor添加新服务
	# 健康测试
	pass

'''
程序输入参数说明
'''
def args_usage():
	print("-e    : 服务英文名称")
	print("-c    : 服务中文名")
	print("-p    : 端口号")

'''
服务创建结束后的声明
'''
def finish():
	# 服务创建结束，后续怎么发布、查看日志、dataway开发、接口文档等
	pass

'''
程序入口
'''
if __name__ == '__main__':
	opts, args = getopt.getopt(sys.argv[1:], "he:c:")
	if len(opts) == 0:
		print("请输入正确的参数，参数说明\n")
		args_usage()
	for op, value in opts:
		if op == "-e":
			app_en_name = value
		elif op == "-c":
			app_cn_name = value
		elif op == "-p":
			port = value
		elif op == "-h":
			args_usage()
		else:
			print("请输入正确的参数，参数说明:\n")
			args_usage()