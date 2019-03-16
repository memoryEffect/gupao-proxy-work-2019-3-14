
JDKProxy 生成对象的步骤：

1.拿到被代理对象的引用，并且获取到他的所有接口，反射获取。

2.JDKProxy 类重新生成一个新的类、同时新的类要实现被代理类所有实现的所有接口

3.动态生成Java代码，把新加的业务逻辑方法由一定的逻辑代码去调用（在代码种体现）。

4.编译新生成的Java代码.class

5.编译生成的.class文件加载到jvm中来

6.返回字节码重组以后新的代理对象


为什么JDK动态代理中要求目标类实现的接口数量不能超过65535个？

答： 这个是JAVA虚拟机规范规定的。

首先要知道Class类文件结构：

Class文件是一组以8字节为基础单位的二进制流，

各个数据项目严格按照顺序紧凑排列在class文件种，

中间没有任何分隔符，这使得class文件中存储的内容几乎是全部程序运行的程序。

ClassFile 结构体，如下：

ClassFile {

u4 magic;

u2 minor_version;

u2 major_version;

u2 constant_pool_count;

cp_info constant_pool[constant_pool_count-1];

u2 access_flags;

u2 this_class;

u2 super_class;

u2 interfaces_count;

u2 interfaces[interfaces_count];

u2 fields_count;

field_info fields[fields_count];

u2 methods_count;

method_info methods[methods_count];

u2 attributes_count;

attribute_info attributes[attributes_count];

}

各项的含义描述：

1.无符号数，以u1、u2、u4、u8分别代表1个字节、2个字节、4个字节、8个字节的无符号数

2.表是由多个无符号数或者其它表作为数据项构成的复合数据类型，所以表都以“_info”结尾，由多个无符号数或其它表构成的复合数据类型

看上边第18行 interfaces_count 接口计数器,interfaces_count 的值表示当前类或接口的直接父类接口数量。类型是u2，2个字节，即2^16-1 =65536-1=65535

所以目标实现的接口数量不能超过65535个
