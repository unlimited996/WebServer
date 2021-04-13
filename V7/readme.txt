此版本对代码进行重构
解决两个问题
1:ClientHandler发送响应的工作(正常响应和404页面的响应)现在大部分代码是重复的,要进行重用
2:功能拆分.将发送响应的细节工作拆分到一个单独的类:HttpResponse中.

实现
与请求的思路一致,设计一个响应对象HttpResponse,使用它的每一个实例用于表示一个要给客户端发送
的响应内容.
1:在com.webserver.http包下新建一个类:HttpResponse
2:定义方法flush.用于将当前响应内容发送给客户端(解决发送响应的代码重复问题)
3:在ClientHandler中实例化HttpResponse,并调用flush方法完成发送响应(功能拆分出去)