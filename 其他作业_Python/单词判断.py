a=str(input("请输入单词（任意大小写）："))
a=a.upper()
pd=['MONDAY','TUESDAY','WEDNESDAY','THUSDAY','FRIDAY','SATURDAY','SUNDAY']
xq=['星期一','星期二','星期三','星期四','星期五','星期六','星期日']
if a not in pd:
    print('无效字符')
else:
    for i in range(0,7):
        if a==pd[i]:
            print(xq[i])
