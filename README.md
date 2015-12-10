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
| easemob4j.token.path  | 使用FileTokenHolder时token保存的物理路径 |
| easemob4j.media.path  | 调用媒体接口时保存媒体文件的物理路径 |

示例(properties中换行用右斜杆\\)

	easemob4j.account={"orgName":"企业公司名","appName":"应用名",\
		"orgAdmin":"企业管理员","appAdmin":"应用管理员",\
		"clientId":"clientId","clientSecret":"clientSecret"}
		
	easemob4j.token.path=/tmp/easemob/token
	easemob4j.media.path=/tmp/easemob/media

2.实例化一个`EasemobProxy`对象,调用API.

    EasemobProxy easemobProxy = new EasemobProxy();
    weixinProxy.createUser(user);

3.针对`token`存储有两种方案,`File存储`/`Redis存储`,当然也可自己实现`TokenHolder`,默认使用文件(xml)的方式保存token,如果环境中支持`redis`,建议使用`RedisTokenHolder`并在自己项目中依赖`jedis`.

    EasemobProxy easemobProxy = new EasemobProxy(new RedisTokenHolder());
    // easemobProxy = new EasemobProxy(new RedisTokenHolder(emAccount));

如何获取
-------
1.maven

	<dependency>
	    <groupId>com.foxinmy</groupId>
	    <artifactId>easemob4j</artifactId>
	    <version>1.1.0</version>
	</dependency>
	
2.git clone & mvn package.