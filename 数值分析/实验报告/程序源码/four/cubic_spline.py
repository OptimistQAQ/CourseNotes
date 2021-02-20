import numpy as np
import matplotlib.pyplot as plt
from scipy import interpolate
from prettytable import PrettyTable

# 解决中文乱码问题
plt.rcParams['font.sans-serif'] = ['KaiTi']  # 指定默认字体
plt.rcParams['axes.unicode_minus'] = False  # 解决保存图像是负号'-'显示为方块的问题

title = PrettyTable(["x", "y"])  # 定义表头

# 存放给出的数据
plane = np.array([
    [0, 3, 5, 7, 9, 11, 12, 13, 14, 15],
    [0, 1.2, 1.7, 2.0, 2.1, 2.0, 1.8, 1.2, 1.0, 1.6]
])

plt.scatter(plane[0], plane[1], color='red', label='原始数据标记点')
plt.title("战斗机机翼外形工艺数据-三次样条插值")
plt.xlabel("x")
plt.ylabel("y")

# 求一维曲面的样条插值
t = interpolate.splrep(plane[0], plane[1])
# x 数据点
xi = np.arange(-5, 20, 0.01)
yi = interpolate.splev(xi, t)
plt.plot(xi, yi, color='blue', label='三次样条插值')
plt.legend()  # 进行渲染
plt.grid()  # 显示表格

x_test = np.arange(0, 15, 0.1)
y_test = interpolate.splev(xi, t)
for i in range(0, len(x_test)):
    title.add_row([round(x_test[i], 1), y_test[i]])
print(title)
plt.show()