# 中级实训阶段1：项目启动
### 姓名：黄善恒 学号：17343046

-----
## **自学报告**
注：编写过程中由于云平台的权限等问题，部分截图是从自带电脑上截取的
1. **Vi/Vim**
    - Vim是一种简单的文件编辑器，输入指令```Vim```即可进入类似```-help```帮助界面，例如下图：
     ![images](https://github.com/Simon-Hwang/intermediate_training/blob/master/images/vim_help.png)
    - 根据实验需求输入```vim Helloworld.java```建立```java```类型文件，并输入```Esc```退出编辑后输入```wq```保存文件并退出，即如下图所示：
    ![images](https://github.com/Simon-Hwang/intermediate_training/blob/master/images/vim_java.png)
2. **Java**
     - 云平台上的```java```环境是已经配置的，输入```java -version```指令查看当前```java```版本，输出以下内容：
     ![images](https://github.com/Simon-Hwang/intermediate_training/blob/master/images/java_version.png)
     - 输入指令```javac Helloworld.java```生成```java```文件对应的```class```文件```Helloworld.class```，输入指令```java Helloworld```运行文件，输出如下结果，表示运行正常
    ![images](https://github.com/Simon-Hwang/intermediate_training/blob/master/images/java_run.png)
3. **Ant**
    -   输入指令```sudo apt-get install ant```安装```ant```程序，使用```ant -version```查看版本即如下:
    ![images](https://github.com/Simon-Hwang/intermediate_training/blob/master/images/ant_version.png)
    - 编辑完成后创建```build.xml```文件，根据教程，编写包含```init、compile、run```操作，代码如下图：
    ![images](https://github.com/Simon-Hwang/intermediate_training/blob/master/images/ant_xml.png)
    - 建立相应的文件夹，其相应的目录结构如下
      ```c
        | src
                | test
                      Helloworld.java
         build.xml
      ```
    - ```ant```代码格式与```html```类似。其中```propety```标签限制了```xml```文件的起始位置以及定义了相应的变量如```src```变量对应的值为```src```，其后以```target```标签定义了行为操作，而其中的```depends```属性约束了行为的前后顺序。```java、javac```标签则是运行```java```程序需要的指令。
    - 运行过程中首先运行```init```指令生成```class```文件夹，其后编译```java```文件到该文件夹下后运行，最后调用```clean```操作清楚文件夹，其输出结果如下图所示：
    ![images](https://github.com/Simon-Hwang/intermediate_training/blob/master/images/ant_run.png)
4. **Junit**
    - ```Junit```工具是用于测试代码的正确性，通过断言如```AssertEquals```函数来判断预测结果与实际输出结果是否相同。关于```Junit```的```java```文件需要导入以下包并注意其中一个需要添加```static```属性
    ```java
    import static org.junit.Assert.*;
    import org.junit.Test;
    ```
    - 其中在调用断言的函数前加入标记```@Test```且函数名必须以```test```开头如```testHello```。其中调整```Helloworld.java```中的函数添加返回字符串```str```并在```HelloworldTest.java```中调用断言```AssertEquals(eval, act)```判断实际返回值```act```是否与预估值```eval```相同，具体代码如下图所示：
    ![images](https://github.com/Simon-Hwang/intermediate_training/blob/master/images/junit_code.png)
    - 在调用执行之前需要下载```junit-4.10.jar```包并置于```java```文件目录下，其后分别执行如下指令```
javac -classpath .:junit-4.10.jar HelloWorldTest.java```以及
```java -classpath .:junit-4.10.jar -ea org.junit.runner.JUnitCore HelloWorldTest```。
    - 当断言正确时，输出结果如下图所示，其中显示```OK```
    ![images](https://github.com/Simon-Hwang/intermediate_training/blob/master/images/junit_succ.png)
    - 不正确时，输出结果如下图所示，其中显示```FAILURES!!!```
    ![images](https://github.com/Simon-Hwang/intermediate_training/blob/master/images/junit_fail.png)
    - 很明显的，直接使用命令行运行```junit```显得较为负责，此时可以使用```eclipse```工具运行代码，点击```Run```即可显示效果，其中正确与错误的输出结果分别如下两图，错误时还会输出相应的错误诊断如红圈所示。
    ![images](https://github.com/Simon-Hwang/intermediate_training/blob/master/images/ecl_succ.png)
    ![images](https://github.com/Simon-Hwang/intermediate_training/blob/master/images/ecl_fail.png)
