<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript"
			src="${pageContext.request.contextPath }/js/jquery-3.2.1.min.js">
	</script>
	<script type="text/javascript">
		function testJson() {
			//获取输入的值name为id
			var name = $("#name").val();
			var password = $("#password").val();
			var age = $("#age").val();
			alert(name);
			$.ajax({
				//请求路径
				url : "${pageContext.request.contextPath }/testJson",
				//请求类型
				type : "post",
				//data表示发送的数据
				data : JSON.stringify({name:name,password:password,age:age}),
				//定义发送请求的数据格式为JSON字符串
				contentType : "application/json;charset=utf-8",
				//定义回调响应的数据格式为JSON字符串，该属性可以省略
				dataType : "json",
				//成功响应的结果
				success : function(data){
					if(data != null){
						//返回一个Person对象
						//alert("输入的用户名:" + data.pname + "，密码：" + data.password + "，年龄：" +  data.page);
						//ArrayList<Person>对象
						/**for(var i = 0; i < data.length; i++){
							alert(data[i].name);
						}**/
						//返回一个Map<String, Object>对象
						//alert(data.name);//name为key
						//返回一个List<Map<String, Object>>对象
						for(var i = 0; i < data.length; i++){
							alert(data[i].name);
						}
					}
				}

			});
		}
	</script>
</head>
<body>
	<form action="">
		用户名：<input type="text" name="name" id="name"/><br>
		密码：<input type="password" name="password" id="password"/><br>
		年龄：<input type="text" name="age" id="age"/><br>
		<input type="button" value="测试" onclick="testJson()"/>
	</form>
</body>
</html>