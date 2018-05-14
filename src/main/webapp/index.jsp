<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<title>首页</title>
<script type="text/javascript">
	$(document).ready(function() {

		$("#login").on("click", function() {
			var account = $("#name").val();
			var password = $("#password").val();
			$.ajax({
				//客户端向服务器发送请求时采取的方式
				type : "post",
				dataType : 'json',
				//指明客户端要向哪个页面里面的哪个方法发送请求
				url : "user/login",
				data : {
					"account" : account,
					"password" : password
				},

				//客户端调用服务器端方法成功后执行的回调函数
				success : function(msg) {
					if (msg.id == 'login_success') {
						console.log(msg)
						location.href = "user/getAllUser";
					} else {
						alert("Username Or Password is not correct");
					}
				},
				error : function(msg) {
					alert(1);
					alert(msg);
				}
			})
		});
	})
</script>
</head>
<body>
	<form method="post" align="center">
		<div class="form-group">
			姓名：<input id="name" name="name" type="text" />
		</div>
		<div class="form-group">
			密码：<input id="password" name="password" type="password" />
		</div>

		<input type="button" value="登陆" class="btn btn-default" id="login" />
	</form>
</body>
</html>
