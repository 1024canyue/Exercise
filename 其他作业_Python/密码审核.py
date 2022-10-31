name=str(input('请输入用户名：'))
print('你好 %s ，首次使用请设置密码！！！'%name)
while True:
    cf=0
    print('='*70)#来个分割线，防止多次运行时一堆字堆一起看不清
    password=str(input('请输入密码（应为长度至少8位数的字母和数字构成，且不能出现重复字符）'))
    if len(password)<8:
        print('▶您设置的密码位数不足8为，请重新设置！！！')
    if password.isalpha() or password.isdigit():
        print('▶您设置的密码不能仅仅包含数字或字母，请重新设置！！！')
    for i in range(0,len(password)): 
        if password.count(password[i])>1:
            cf=1
    if cf==1:
        print('▶您设置的密码出现了重复的字符，请重新设置！！！')
    if not (len(password)<8 or (password.isalpha() or password.isdigit()) or cf==1):
        print('密码设置成功！！！')
        break
    
"""
解释下为什么我不用课堂上教的多个嵌套的if，因为当我们密码有多个不合规点时，应该一口气
告诉用户，不然用户可能会出现多次密码不合规反复输入，我感觉这样的交互就很不友好
"""
        
