## Android系统架构

Android运行机制和原理：应用层-应用程序框架层-安卓运行时库层-硬件抽象层-linux内核层。

xml解析，pull解析(android内核)

**问题1：AndroidManifest.xml文件何时解析，如何解析?**

何时解析：Android系统启动(Zygote)过程中。（不是安装apk的时候）

如何解析：

SystemServer进程中启动PackageManagerService服务，创建一个PackageManagerService对象，并将这个对象添加到ServiceManager中为其他组件提供服务，遍历系统目录解析目录中的apk文件：解析Manifest的主要方法是创建一个PackagerParser对象，并调用parsePackage方法。经过循环遍历，整个androidManifest的节点信息就被解析并保存在了Package对象中。
Android。

* android系统启动之后解析固定目录下的apk文件，解析完Manifest后将apk的Manifest信息保存在Settings对象中持久化，然后执行重新安装的操作。
* 解析流程：Zygote进程 –> SystemServer进程 –> PackageManagerService服务 –> scanDirLI方法 –> scanPackageLI方法 –> PackageParser.parserPackage方法；

链接：https://juejin.im/entry/579f0b79165abd006115c5a4

**深度探究apk安装过程：**

* 系统安装apk文件调用的是底层的adb命令
* Android系统在启动过程中，会启动一个应用程序管理服务PackageManagerService，这个服务负责扫描系统中特定的目录，找到里面的应用程序文件，即以Apk为后缀的文件，然后对这些文件进解析，得到应用程序的相关信息，完成应用程序的安装过程。
* 真个apk的安装过程，主要分为如下几步：
* 拷贝apk文件到指定目录
* 解压apk，拷贝文件，创建应用的数据目录
* 解析apk的AndroidManifinest.xml文件
* 向Launcher应用申请添加创建快捷方式

链接：https://www.imooc.com/article/16876





