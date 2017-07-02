注：该工程由MVP+Retrofit+rxjava框架，经过测试无太大问题。但是由于是自己在搭公司项目的前期框架
之后不会进一步更新工程内容，只会更新自己封装框架的bug，欢迎看官指出我的错漏

阅读帮助
===
该工程作为启动项目的前期框架，里面只有几个接口用来测试网络引擎的正确性<br/>

工程模块：
---
该项目以MVP框架作为思想基础搭建起来，为了避免内存泄露，稍微从几个固定的搭建模式中做了点修改。<br/>
<br/>
1.ui文件夹:用于存放activity以及fragment等视图文件 相当于MVP中view的角色<br/>
<br/>
2.model文件夹:用于存放数据，相当于MVP中Model角色<br/>
<br/>
3.Contract文件夹:用于存放mvp用来相互关联的接口管理，（用法详见代码，和参照google的mvp文档）<br/>
<br/>
4.Preseneter文件夹:用于存放Mvp角色中Presenter角色。里面的类全部继承了BasePresnter(BasePresenter对持有长时间的引用用了弱引用处理)<br/>
<br/>
5.utils文件夹:用于工程中的工具类,里面有rxBus的工具类。<br/>
