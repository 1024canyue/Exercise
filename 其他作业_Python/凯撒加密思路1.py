#字符串映射法正在想
key=int(input('请输入秘钥：'))
password=str(input('请输入一个要加密的信息：'))
keybook='abcdefghijklmnopqrstuvwxyz'
nw=''
#加密
for i in range(0,len(password)):
    a=keybook.find(password[i])
    if a==-1:
       nw+=password[i]#处理非字母字符 
    else:
        if a+key<25:
            nw+=keybook[a+key]
        else:#溢出的情况
            nw+=keybook[a+key-27]
#解密
jiam=nw
jm=''
for i in range(0,len(jiam)):
    a=keybook.find(jiam[i])
    if a==-1:
       jm+=jiam[i]#处理非字母字符 
    else:
            jm+=keybook[a-key]
print(" 加密后%s"%nw,'\n','解密后%s'%jm)
