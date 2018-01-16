jwtclient（使用的是maven的Tomcat插件）
	http://localhost:8081/jwtclient/index.html 首页不需要权限
	http://localhost:8081/jwtclient/index2.html 	user权限
	http://localhost:8081/jwtclient/first		admin权限
	http://localhost:8081/jwtclient/exit 退出登录
	
hello-sso-jwt-auth 登录验证服务器
	使用的是自定义的用户名密码
	user权限	hellosso hellosso 
	admin权限	admin	 admin
	
	
问题：
退出是通过删除"JWT-TOKEN" 的cookie的方法，退出经常会延迟
