## 网络请求传数据之URL转义

是什么：URL中的字符只能是ASCII字符，但是ASCII字符比较少，而URL则常常包含ASCII字符集以外的字符，如非英语字符，汉字，特殊符号等等，所以要对URL进行转换。这个过程就叫做URL编码，或者叫URL转义，实质上就是将包含非ASCII字符的URL转换为有效的ASCII字符格式。

为什么：对url给后台传数据的时候特殊字符需要转义。

怎么样：

链接：https://www.cnblogs.com/zhouyideboke/p/11249961.html

URL中含有ASCII字符集以外的字符如加减乘除，则用右边代替。

回车                       %0d
换行                       %0a
空格: +                    %2B
空格: 编码                 %20
分隔目录和子目录: /         %2F
分隔实际的URL和参数: ?      %3F
指定特殊字符: %            %25
表示书签: #                %23
URL中指定参数间的分隔符: &  %26
URL中指定参数的值: =       %3D

