from datetime import date
from functools import cached_property
from os import system as sys
import time
import datetime
import calendar

from numpy.lib.npyio import mafromtxt
def start():   #用来初始化环境
    '''该函数用来来初始化程序运行环境,载入需要的数据，并对数据就行预处理
    顺带看看电脑环境是否完善'''
    import pandas as pd
    import numpy as np
    import matplotlib.pyplot as plt
    from sklearn.model_selection import train_test_split
    plt.rcParams['font.sans-serif'] = ['SimHei']  #更改一个有中文的字体
    plt.rcParams['axes.unicode_minus']=False  #解决符号不显示问题

    #载入数据
    df = pd.read_csv("./data/福州天气.csv",sep="\\s+")  #读取原数据
    data=df[['月','日','时','气温']]   #切片
    #数据的预处理
    weather = pd.DataFrame(columns=('月', '日', '最低温℃' , '最高温℃'))  #新DataFrame
    for m in range(1,13):  #遍历月
        d_max=int(data[data["月"]==m].max().日)  #轻松得到每个月最大的日期
        for d in range(1,d_max+1):  #根据最大天数遍历天
            weather.loc[weather.index.size] = [m,d,get_min(data,month=m,day=d),get_max(data,month=m,day=d)] #向写入到新DataFrame
    return weather   #返回预处理数据

def get_max(x,month,day):
    '''该函数用来提取每天最高温，并还原缩放'''
    return x[(x.月==month) & (x.日==day)].气温.max()/10
def get_min(x,month,day):
    '''该函数用来提取每天最低温，并还原缩放'''
    return x[(x.月==month) & (x.日==day)].气温.min()/10

def min_tpm(weather):   #最低温模型
    '''向该函数传入往年天气数据，将返回训练好的模型'''
    X = weather.values[:,:2]   #日期
    Y = weather.values[:,2]    #最低温
    from sklearn.ensemble import RandomForestRegressor
    reg_min = RandomForestRegressor(n_estimators=500,n_jobs=-1)
    reg_min.fit(X,Y)
    return reg_min

def max_tpm(weather):   #最高温模型
    '''向该函数传入往年天气数据，将返回训练好的模型'''
    X = weather.values[:,:2]   #日期
    Y = weather.values[:,3]    #最高温
    from sklearn.ensemble import RandomForestRegressor
    reg_max = RandomForestRegressor(n_estimators=500,n_jobs=-1)
    reg_max.fit(X,Y)
    return reg_max

def max_min(m,d):
    '''直接向该函数传入日期即可返回预计的最高温和最低温'''
    Max=reg_max.predict([[m,d]])[0]
    Min=reg_min.predict([[m,d]])[0]
    return Max,Min

def debug():
    '''该函数用来初始化环境以及验证环境是否完善'''
    try:
        weather=start()
    except FileNotFoundError:
        print("环境异常:本程序依赖于数据文件，您的电脑缺失数据文件或路径异常(缺少：福州天气.csv)")
        input()  #防止窗口直接被关掉而看不到异常提示
        sys.exit(0)
    except ModuleNotFoundError:
        print("环境异常:您的电脑缺失依赖的包环境请安装以下环境：")
        print("建议版本：\n pandas 1.3 \n numpy 1.19 \n scikit-learn 0.24")
        input()  #防止窗口直接被关掉而看不到异常提示
        sys.exit(0)
    except:
        print("未知异常:因为某些原因导致程序无法正常使用，该异常可能是预料之外的bug所产生")
        input()  #防止窗口直接被关掉而看不到异常提示
        sys.exit(0)
    else:
        return weather

def PrintMenu():
    '''主页'''
    mom = time.localtime(time.time()).tm_mon
    day = time.localtime(time.time()).tm_mday     
    today_max,today_min=max_min(mom,day)
    print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n")
    print(time.strftime("今天是:%Y年%m月%d日", time.localtime()),"新的一天，新的开始")
    print("预计今天福州最高温{:.2f}℃，最低温{:.2f}℃".format(today_max,today_min))
    if today_max-today_min>10:
        print("今天温差有点大，可别小瞧福州的气温，忽冷忽热要小心感冒")
    if today_max>35:
        print("有一种热叫福州的热，外出要做好防暑工作")
    if today_min<12:
        print("有一种冷叫南方的冷，晚上要小心着凉")
    print("\n■■■■■■■■■■■■■■■■■■■■■菜单■■■■■■■■■■■■■■■■■■■■■■■")
    print("1:预测其他日期")
    print("2:预测未来几天")
    print("3:打印月历")
    print("4:关于")
    print("敬请期待。。。。。")



print("程序正在初始化，请稍等。。。。")
weather = debug()  #初始化好数据
reg_max = max_tpm(weather)  #训练模型在初始化过程中统一完成
reg_min = min_tpm(weather)  #以免反复调用影响运行速度


while True:
    PrintMenu()
    key=input("请输入相应序号(exit/e退出)")

    #用户交互
    if key=="1":
        while True:
            Str=input("请输入日期(如6 3)")
            try:
                m=int(Str.split()[0])
                d=int(Str.split()[1])
            except:
                print("输入的格式有误！数字之请间用空格隔开")
                continue
            max_day=[31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
            if m>0 and m<13:   #土方法，不是很完美
                if d<max_day[m-1]:
                    Max,Min=max_min(m,d)
                    print("预计{}月{}日福州最高温{:.2f}℃，最低温{:.2f}℃".format(m,d,Max,Min))
                else:print("时间错误:{}月没有{}日".format(m,d))
            else:print("时间错误:没有{}月".format(m))
            key=input("是否继续查询(Y继续/n退出)")
            if key=="Y" or key=="y" or key=="":
                pass
            else:
                break
    if key=="2":
        key=input("需要预测未来几天(默认7天)：")
        if key.isdigit() and key!="":
            Long=int(key)
        else:
            Long=7
        print("--日期-----最高温-----最低温----")
        for t in range(Long):
            T = datetime.datetime.now()+datetime.timedelta(days=t)
            y = T.year
            m = T.month
            d = T.day
            Max,Min=max_min(m,d)
            print("{}-{}-{}  {:.2f}℃     {:.2f}℃".format(y,m,d,Max,Min))
        input("回车键返回")
    if key=="3":
        key=input("请输入年月(默认本月)(格式:2021 8)")
        if key!="":
            try:
                y=int(Str.split()[0])
                m=int(Str.split()[1])
            except:
                print("输入的格式有误！数字之请间用空格隔开")
        else:
            y=datetime.datetime.now().year
            m=datetime.datetime.now().month
        print(calendar.month(y, m))
        input("回车键返回")
    if key=="4":
        print("这个程序是我在闲暇时写的,原理主要是靠随机森林模型拟合本地的历史数据,")
        print("从而通过历史数据得到\"经验\",找到每天气温的规律。")
        print("模型在实验中对数据预测的准确度均在95%以上，但真实的天气变化多端，模型")
        print("的预测也不能代表实际，主要还是以参考为主，及时发现温度变化。")
        print("版本 V1.1  修改日期 2021-08-17")
        input("回车键返回")

