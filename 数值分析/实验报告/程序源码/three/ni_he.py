import numpy as np
import matplotlib.pyplot as plt
from lab_report.two.Gaussian import *

# 解决中文显示问题
plt.rcParams['font.sans-serif'] = ['KaiTi']  # 指定默认字体
plt.rcParams['axes.unicode_minus'] = False  # 解决保存图像是负号'-'显示为方块的问题

array = np.array([
    [2010, 2011, 2012, 2013, 2014, 2015, 2016],
    [70, 122, 144, 152, 174, 196, 202],
    [10, 11, 12, 13, 14, 15, 16],
])

# 测试数据
x_test = np.array([0.0, 1.0, 2.0, 3.0, 4.0, 5.0])
y_test = np.array([2.1, 7.7, 13.6, 27.2, 40.9, 61.1])

def sum_matrix_A(xList, degree):
    MatA = np.zeros([degree, degree])  # 创建degree × degree长度的张量MatB
    for i in range(0, degree):
        for j in range(0, degree):  # 遍历整个MatB矩阵
            x_i = 0.0
            for k in range(0, len(xList)):  #
                dx = 1.0  # 左上角第一个数默认为1
                for l in range(j + i):  # 乘积的次数，通过j+i可以确定xi的次方数
                    dx = dx * xList[k]  # xList[k] 也是就x_i
                x_i += dx  # 接着对每一项进行求和求和可得
            MatA[i][j] = x_i  # 添加到矩阵当中
    return MatA

def sum_matrix_B(xList, yList, degree):
    MatB = np.zeros([degree])  # 创建degree长度的向量MatB
    for i in range(0, degree):  # 对每一项进行计算
        y_i = 0.0  # 每项初值为0
        for j in range(0, len(xList)):
            dx = 1.0 * yList[j]  # 默认第一行是yi
            for k in range(0, i):  # 求出求和内部系数和，即其他行为xi的i次方给乘以yi
                dx = dx * xList[j]
            y_i += dx  # 再加到yi上
        MatB[i] = y_i  # 添加到MatB向量当中
    return MatB

# 根据求解向量X求解方程
def function(X, x):
    fuc = 0
    for i in range(0, len(X)):
        fuc += X[i] * np.power(x-2000, i)
    return fuc

# 绘图函数
def drawLine():
    xList = array[0]
    yList = array[1]
    line = np.arange(xList[0], xList[-1], 0.001)  # x坐标范围
    # 绘制拟合曲线
    plt.plot(line, function(X, line), label='拟合曲线')
    plt.title(str(degree) + "阶方程多项式拟合曲线")
    # 画原始数据点
    plt.scatter(xList, yList, label='原数据点标记', color='red')
    plt.legend()  # 渲染视图
    plt.grid()  # 显示网格
    plt.show()

if __name__ == '__main__':
    xList = array[2]
    yList = array[1]
    # xList = x_test
    # yList = y_test
    print("学号：1807004120 姓名：秦嘉豪")
    degree = int(input("请输入拟合方程阶数："))
    MatA = sum_matrix_A(xList, degree + 1)
    MatB = sum_matrix_B(xList,yList,degree + 1)
    MatB = MatB.reshape(-1,1)  # 将MatB转为列向量
    # 添加到MatA矩阵后面
    Mat = np.concatenate((MatA, MatB), axis=1)
    print("多项式矩阵：")
    print(Mat)
    X, Mat = Gaussian(Mat)  # 使用高斯消去法消元求解
    print("求解矩阵[X]为：")
    print(X)  # 输出对应结果
    print("2017年的利润预测为：{}".format(function(X, 2017)))
    print("2018年的利润预测为：{}".format(function(X, 2018)))
    drawLine()  # 绘制拟合曲线
