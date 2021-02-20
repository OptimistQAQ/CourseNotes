"""
多项式除法
@Author: Optimist
@Time: 2020/11/24
"""

"""
input:
被除数各次项的系数（低次系数在前）、
除数各次项的系数（低次系数在前）
output：
商的系数、
余数
"""

# 多项式加法，同次项系数相加
def add(L1, L2):
    R = []
    if len(L1) > len(L2):  # 默认L2比较长
        L1, L2 = L2, L1
    i = 0
    while i < len(L1):
        R.append(L1[i]+L2[i])  # 从低次项开始对应相加
        i += 1
    R = R+L2[len(L1):len(L2)]  # 较长的多项式高次项直接复制
    return R

def subtract(L1, L2):  # 多项式减法
    L2 = L2[:]  # 为了不改变传入的L2
    for i in range(len(L2)):
        L2[i] = -L2[i]
    return add(L1, L2)


def multiply(L1, L2):  # 多项式乘法
    if len(L1) > len(L2):
        L1, L2 = L2, L1
    zero = []
    R = []
    for i in L1:
        T = zero[:]  # 存储中间产生的结果多项式，每次更新结果多项式的列表长度
        for j in L2:  # 一个单项式乘以多项式的每一项
            T.append(i * j)
        R = add(R, T)
        zero = zero + [0]  # 每一个新的多形式都要比前一个多项式次数高1，列表长度增加，所以多补一个0
    return R


def divide(L1, L2):
    if len(L1) < len(L2):
        return 0, L1  # 商为0的情况
    d = len(L1)-len(L2)  # 循环除的次数为d+1
    T = L1[:]  # 被除数
    R = []  # 商
    for i in range(d+1):
        n = T[len(T)-1]/L2[len(L2)-1]  # 被除数的最高次项系数除以除数的最高次项的系数
        R = [n]+R
        T1 = [0]*(d-i)+[n]  # 对得到的商补若干个0
        T2 = multiply(T1, L2)  # 商乘以除数结果为T2
        T = subtract(T, T2)  # 被除数减去T2
        T = T[:len(T)-1]  # 切片，被除数减少一次方
    R = list(map(int, R))
    T = list(map(int, T))
    return R, T

if __name__ == '__main__':
    l1 = input("请输入被除数的L1系数，低次在前，空格隔开: \n")
    l1 = l1.split(" ")
    l1 = [int(e) for e in l1]
    l2 = input("请输入除数的L2系数，低次在前，空格隔开: \n")
    l2 = l2.split(" ")
    l2 = [int(e) for e in l2]
    R, T = divide(l1, l2)
    print("商的系数为（阶数从小到大）：\n" + str(R))
    print("余数的系数为（阶数从小到大）：\n" + str(T))
