easemob4j
========

[环信](http://www.easemob.com/docs/rest)开发工具包
-------------

功能列表
-------

* UserApi `用户API`

* NotifyApi `消息API`

项目说明
-------
环信REST接口实现

如何使用
--------
1.接口的正确调用需要将`easemob4j.properties`文件复制到项目的`classpath`中

easemob4j.properties说明

| 属性名       |       说明      |
| :---------- | :-------------- |
| easemob4j.account     | 环信账号信息 `json格式`  |
| easemob4j.tmpdir  | easemob4j使用的临时目录,比如使用文件方式保存token时,如不设置则获取java.io.tmpdir目录. |

示例(properties中换行用右斜杆\\)

	easemob4j.account={"orgName":"企业公司名","appName":"应用名",\
		"orgAdmin":"企业管理员","appAdmin":"应用管理员",\
		"clientId":"clientId","clientSecret":"clientSecret"}

	easemob4j.tmpdir=

2.实例化一个`EasemobProxy`对象,调用API.

    EasemobProxy easemobProxy = new EasemobProxy();
    easemobProxy.createUser(user);

3.针对`token`存储见：https://github.com/foxinmy/weixin4j/wiki/Cache%E7%BC%93%E5%AD%98%E5%AE%9E%E7%8E%B0

如何获取
-------
1.maven

	<dependency>
	    <groupId>com.foxinmy</groupId>
	    <artifactId>easemob4j</artifactId>
	    <version>1.1.3</version>
	</dependency>

2.git clone & mvn package.
