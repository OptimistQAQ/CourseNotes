import numpy as np
import matplotlib.pyplot as plt
from prettytable import PrettyTable

title = PrettyTable(["k(次数)", r'a_k(左边界)', r"b_k(右边界)", r"x_k(中间点)", r"f(x_k)的符号", r"b_k-a_k(区间长度)"])  # 定义表头
title.align["f(x_k)的符号"] = "c"  # 将符号列居中显示

# 设置原函数
def function(x):
    global _string
    return eval(_string)

def draw_line_state(state, state_value):
    plt.scatter(state, state_value, c='red')  # 画点
    plt.plot([state, state], [0, state_value], color='red', linewidth=1.0, linestyle='--')  # 画出与坐标轴交点的虚线
    res = '(' + str(state) + ',' + str(state_value) + ')'
    plt.annotate(res, xy=[state, state_value], xycoords='data',  # 画出该点说明
                 xytext=(+10, +30), textcoords='offset points', fontsize=16,
                 arrowprops=dict(arrowstyle="->", connectionstyle="arc3,rad=.2"))

# 二分法求根
def ordinaryDeal(left, right, k):
    global _string  # 声明全局变量

    mid = 0.5 * (left + right)  # 计算区间中值
    left_value = function(left)  # 计算左端数值
    right_value = function(right)  # 计算右端数值
    mid_value = function(mid)  # 计算中间数值

    # plt.cla()  # 清除曲线
    # line = np.arange(left - 1.0, right + 1.0, 0.001)  # x坐标范围
    #
    # plt.title("二分法求根", fontproperties="SimHei")  # 图标标题
    # plt.xlabel("x")  # x轴标题
    # plt.ylabel("y")  # y轴标题
    # plt.plot(line, function(line))  # 画出曲线
    # plt.grid(True)  # 显示网格
    #
    # draw_line_state(left, left_value)  # 画左边点说明
    # draw_line_state(right, right_value)  # 画右边点说明

    # plt.scatter(mid, mid_value, c='green')  # 画绿色中点

    signal = "+" if mid_value > 0 else "-"  # 判断f(x_k)的符号
    title.add_row([k, left, right, mid, signal, right - left])  # 添加一行数据
    k = k + 1  # 标记行 + 1
    # print(title)  # 打印数据
    # print()

    # plt.pause(1)  # 等待

    if left_value * right_value > 0:  # 两端同号
        print("此范围内没有实数根！请重新输入区域！")  # 警告
    elif right - left < 1e-8:  # 区域小于所设最小区间
        print("根的范围为：[{},{}]".format(left, right))  # 输出
        return
    elif left_value * mid_value < 0:  # 在左段区域
        ordinaryDeal(left, mid, k)
    elif right_value * mid_value < 0:  # 在右段区域
        ordinaryDeal(mid, right, k)
    elif mid_value == 0:  # 根值已经等于0
        print("根为：{}".format(mid))  # 输出零点值
        return


if __name__ == '__main__':
    global _string
    print("1807004120秦嘉豪")
    _string = input('请输入表达式函数\n[符合python语法，例如：y==x**2  --->  只需要输入 x ** 2]:\n')
    left = float(input("请输入左边界："))
    right = float(input("请输入右边界："))

    # plt.ion()
    k = 0
    ordinaryDeal(left, right, k)

    # plt.ioff()
    # plt.show()

    # line = np.arange(left - 1.0, right + 1.0, 0.001)  # x坐标范围
    # plt.title("dichotomy")  # 图标标题
    # plt.xlabel("x")  # x轴标题
    # plt.ylabel("y")  # y轴标题
    # plt.plot(line, function(line))  # 画出曲线
    # plt.grid(True)  # 显示网格
    # ordinaryDeal(left, right, 0)

    # plt.show()

    print(title)  # 打印数据
    print()

