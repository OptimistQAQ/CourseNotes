import numpy as np

# 构造增广矩阵
Mat = np.array([[0.9428, 0.3475, -0.8468, 0.4127],
                [0.3475, 1.8423, 0.4759, 1.7321],
                [-0.8468, 0.4759, 1.2147, -0.8621]])

# 高斯消去法求值
def Gaussian(mat):
    row = len(mat)  # mat矩阵的行数
    column = len(mat[0])  # mat矩阵的列数
    for i in range(0, row-1):  # 矩阵的行下标i
        for j in range(i+1, row):
            # 计算乘数
            m = mat[j][i] / mat[i][i]
            # 遍历第j行，从i开始到行尾的所有的数
            for k in range(i, column):
                # 每个数字进行对应相减
                mat[j][k] = mat[j][k] - m * mat[i][k]
            # 输出每一步的数学意义
            print("第{}行减去{}乘以第{}行得：".format(j+1, m, i+1))
            print(mat)  # 输出该矩阵
            print()
    return Solution(mat)

# 对上三角增广矩阵进行回代求解X
def Solution(mat):
    row = len(mat)  # mat矩阵的行数
    column = len(mat[0])  # mat矩阵的列数
    X = np.zeros(row)  # 结果X向量
    for i in range(row-1, -1, -1):
        b_i = mat[i][column-1]  # bi的值
        for j in range(i+1, column-1):
            b_i -= X[j] * mat[i][j]  # 减去已知解的占比
        X[i] = b_i/mat[i][i]  # 除以所在行的主元素
    return X, mat

# 列主消元法
def AllElimination(mat):
    row = len(mat)  # mat矩阵的行数
    column = len(mat[0])  # mat矩阵的列数
    # 将矩阵化为上三角增广矩阵，并返回行下标
    for i in range(row-1):  # 矩阵行下标
        maximum = 0
        temp = i  # 保存主元行号
        # 寻找列主元行
        for j in range(i, row):
            if mat[j][i] > maximum:
                maximum = mat[j][i]
                temp = j
        # 如果当前行不是列主元行，进行交换
        if temp != i:
            for j in range(i, column):
                T = mat[temp][j]
                mat[i][j] = mat[temp][j]
                mat[temp][j] = T
            print("第{}行与第{}行进行交换：".format(i+1, temp+1))
            print(mat)

        for j in range(i+1, row):
            # 计算乘数
            m = mat[j][i] / mat[i][i]
            # 遍历第j行，从i到行尾的所有数
            for k in range(i, column):
                # 每一个数字进行相减
                mat[j][k] = mat[j][k] - m * mat[i][k]
            # 输出每一步的数学意义
            print("第{}行减去{}乘以第{}行得：".format(j+1, m, j+1))
            print(mat)  # 输出该矩阵
            print()
    return Solution(mat)

if __name__ == '__main__':
    print("1807004120秦嘉豪")
    print("1.高斯消去法")
    print("2.列主消元法")
    flag = int(input("请输入选择："))
    print("原矩阵为：")
    print(Mat)

    if flag == 1:
        X, Mat = Gaussian(Mat)
        print("上三角增广矩阵为：")
        print(Mat)
        print("原始消去消去法求出的解为：")
        print(X)
    elif flag == 2:
        X, Mat = AllElimination(Mat)
        print("上三角增广矩阵为：")
        print(Mat)
        print("列主消去消去法求出的解为：")
        print(X)