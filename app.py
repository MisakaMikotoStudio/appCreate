#!/usr/bin/python3
import sys, getopt

'''
程序输入参数说明
'''
def args_usage():
	print("-e    : 服务英文名称")
	print("-c    : 服务中文名")

if __name__ == '__main__':
	opts, args = getopt.getopt(sys.argv[1:], "he:c:")
	app_en_name = ""
	app_cn_name = ""
	if len(opts) == 0:
		print("请输入正确的参数，参数说明\n")
		args_usage()
	for op, value in opts:
		if op == "-e":
			app_en_name = value
		elif op == "-c":
			app_cn_name = value
		elif op == "-h":
			args_usage()
		else:
			print("请输入正确的参数，参数说明:\n")
			args_usage()
	# Leo上创建服务
	# github上创建仓库
	# mysql上创建默认的库，与log表、user表
	# 从默认模板拷贝生成项目初始代码
	# 服务发布、日志查看等后续使用流程(也用脚本实现一下)