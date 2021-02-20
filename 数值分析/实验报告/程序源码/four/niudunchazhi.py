import numpy as np
import matplotlib.pyplot as plt
from prettytable import PrettyTable
# 解决中文显示问题
plt.rcParams['font.sans-serif'] = ['KaiTi']  # 指定默认字体
plt.rcParams['axes.unicode_minus'] = False  # 解决保存图像是负号'-'显示为方块的问题
title = PrettyTable()  # 定义表头

# 给出的数据存放
plane = np.array([
    [0, 3, 5, 7, 9, 11, 12, 13, 14, 15],
    [0, 1.2, 1.7, 2.0, 2.1, 2.0, 1.8, 1.2, 1.0, 1.6]
])

# plt.scatter(plane[0], plane[1], color='red', label='原始数据标记点')
# plt.title("战斗机机翼外形工艺数据-牛顿插值")
# plt.xlabel("x")
# plt.ylabel("y")
# plt.legend()  # 进行渲染
# plt.grid()  # 绘制格线
# plt.show()

# 获取n阶差商
def get_quotient(xList, yList):
    count = 1
    result = [yList[0]]  # 存放差商表对角线的第一个元素
    while len(yList) > 1:
        list = []
        for i in range(len(yList)-1):
            # 计算差商
            commerce = (yList[i + 1] - yList[i]) / (xList[i + count] - xList[i])
            list.append(commerce)  # 添加到每列差商列表
        result.append(list[0])  # 只保存写斜对角线我们所用到的差商
        count += 1  # 距离值加1
        yList = list  # 更新yList列表
    return result

# 获取第i阶差商的因子
# 根据n阶差商因子和差商求函数
def get_w(xList, yList):
    list = get_quotient(xList, yList)

    # 定义关于x的Wi函数
    def Wi(x):
        result = 0.0
        for j in range(len(xList)):
            w_i = list[j]  # 赋值计算后的差商
            for k in range(0, j):  # 计算因子
                # 与每上一项的因子相乘
                w_i = w_i * (x - xList[k])
            result += w_i  # 结果相加
        return result  # 返回关于x的因子
    return Wi  # 返回对应函数

if __name__ == '__main__':
    plt.scatter(plane[0], plane[1], color='red', label='原始数据标记点')
    plt.title("战斗机机翼外形工艺数据-牛顿插值")
    plt.xlabel("x")
    plt.ylabel("y")
    Wi = get_w(plane[0], plane[1])  # 获取计算得到的
    line = np.arange(0, 15, 0.01)
    plt.plot(line, Wi(line), label='插值拟合曲线')
    plt.legend()  # 进行渲染
    plt.grid()  # 绘制格线
    plt.show()
    print("牛顿插值斜对角差商：", end="")
    print(get_quotient(plane[0], plane[1]))
    for j in range(0, 15):
        title.add_column("x", ["y"])
        xi = np.arange(j, j + 1, 0.1)
        for i in xi:
            title.add_column(str(round(i, 1)), [round(Wi(i), 3)])
        print(title)
        title.clear()