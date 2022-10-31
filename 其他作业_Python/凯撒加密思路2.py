#字符串映射法2.0
key=int(input('请输入秘钥：'))
password=str(input('请输入一个要加密的信息：'))
keybook='abcdefghijklmnopqrstuvwxyz'
jiami=keybook[key:]+keybook[0:key]
num=[]
for i in range(0,len(password)):
    num.append(keybook.find(password[i]))
for i in range(0,len(num)):
    if keybook.find(password[i])==-1:
        print(password[i],end='')
    else:
        print(jiami[num[i]],end='')
print('\n')
for i in range(0,len(num)):
    if keybook.find(password[i])==-1:
        print(password[i],end='')
    else:
        print(keybook[num[i]],end='')
