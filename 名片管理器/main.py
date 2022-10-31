#思路1-1：利用字典定位查找上的便利,序号将再需要时临时生成
#应该是学期的最后一个实验了，那我就尽量做的完善好用，代码偏长
def displayMenu():
    print("\n \n")
    print("       【名片管理系统】  v1.0")
    print("■"*19)
    print("1. 添加名片")
    print("2. 删除名片")
    print("3. 修改名片")
    print("4. 查询名片")
    print("5. 获取所有名片信息【当前存在%s个数据】"%len(info))
    print("6. 退出系统")
    print("="*30)
    
#该函数只为向字典填入数据
def addInfo(n,x,t):
    cache=[]
    cache.append(x)
    cache.append(t)
    info[n]=cache
    
#用户的选择
def getChoice():
    selectedKey = input("请输入选择的序号：")
    return int(selectedKey)

# 查看所有名片的信息，针对现状，自动生成序号
def printAllInfo(tempList):
    k=1
    print("\n \n \n              现存所有信息如下 \n")
    print("序号     姓名     性别       电话号码")
    print("=" * 50)
    for i in tempList:
        print(k,end="       ")
        k+=1
        print(i,end="      ")
        for j in tempList[i]:
            print(j,end="         ")
        print("\n")
    print("=" * 50)

#修改某个用户信息
def modifyinfo(n,x,t):
    info[n]=[x,t]
    print("【%s】的信息修改完成"%namecache[int(key)-1],"\n")
    print("姓名     性别       电话号码")
    print("=" * 50)
    print(n,end="       ")
    for i in info[n]:
        print(i,end="         ")
    print("\n")
    print("=" * 50,"\n")
#删除某个用户信息
def deleteinfo(k):
    del info[k]
    print("用户【%s】的信息已删除"%namecache[int(key)-1])
#查询某个用户信息
def searchinfo(n):
    #临时变量储存现在已经有的名字，好判断
    namecache=list(info.keys())
    if n not in namecache:
        print("查无此人，请检查您输入的是否正确！！！")
    else:
        print("姓名     性别       电话号码")
        print("=" * 50)
        print(n,end="       ")
        for i in info[n]:
            print(i,end="         ")
    print("\n")
    print("=" * 50)

info={"小明":["男",112233],"小红":["女",223344],"小兰":["女",223344]}
import time
#启动
if __name__ == "__main__":
    print("欢迎使用[软2]名片管理系统\n当前时间：【%s】\n祝您工作愉快)ง •_•)ง"%time.strftime("%Y-%m-%d  %H:%M:%S", time.localtime()))
    while True:  #非用户主动退出，程序不主动结束，内部每个功能亦是如此
        #用户菜单的选择
        displayMenu()
        key = getChoice()
        #根据选择执行，功能均可连续操作，输入“exit”并回车退出该功能
        if key == 1:
            while True:
                name=input("请输入要添加的人名;键入exit且回车可取消添加状态:")
                if name=="exit":
                    break
                else:
                    xb=str(input("请输入【%s】的性别"%name))
                    telnum=int(input("请输入【%s】的电话号码"%name))
                    print("【%s】的信息已添加\n"%name,'-'*40)
                    addInfo(name,xb,telnum)
            print("\n \n")
            printAllInfo(info)
        elif key == 2:
            while True:
                printAllInfo(info)#列出选项供用户选择，且不应该删除完后让用户猜序号排序
                key=input("请输入要删除的信息的序号，键入exit且敲击回车可退出删除状态")
                namecache=list(info.keys())#用于转化序号与字典键
                if key=="exit":  #解释下判断用户输入的是不是"exit"这个工作为什么不放在函数模块里，因为那样break就没有确定的循环可以结束，就会报错,也算是我挖的一个坑了
                    break
                elif int(key)>len(namecache) or int(key)<1:
                    print("您输入的序号有误，请核实后输入")
                else:
                    namesub=namecache[int(key)-1]
                    deleteinfo(namesub)
        elif key == 3:
            printAllInfo(info)#列出选项供用户选择
            while True:#自己给自己挖了个坑，字典键不能直接修改，导致我不好在不掉乱序号的情况下修改名称
                key=input("请输入要修改的信息的序号，键入exit且敲击回车可退出修改状态(不支持修改姓名)")
                namecache=list(info.keys())#用于转化序号与字典键
                if key=="exit":  
                    break
                elif int(key)>len(namecache) or int(key)<1:
                    print("您输入的序号有误，请核实后输入")
                else:
                    namesub=namecache[int(key)-1]
                    xb=input("请输入修改后【%s】的性别"%namecache[int(key)-1])
                    telnum=input("请输入修改后【%s】的电话号码"%namecache[int(key)-1])
                    modifyinfo(namesub,xb,telnum)
        elif key == 4:
            while True:
                print("\n \n")
                key=input("请输入想要查询的名字，键入exit且敲击回车可退出查询状态")
                if key=="exit":
                    break
                else:
                    searchinfo(key)
        elif key == 5:
            printAllInfo(info)
        elif key == 6:
            key=input("确定要退出程序吗(Y/N)")
            if key=="Y" or key=="y":
                print("\n\nBay----小的这就先撤了-----\n\n")
                break
        else:
            print("输入有误，请重新输入...")
