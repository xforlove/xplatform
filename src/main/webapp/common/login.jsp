<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@include file="/common/meta.jsp"%>
<title>登录</title>
<%@include file="/common/s.jsp"%>
<link href="${ctx }/s/customer/css/signin.css" rel="stylesheet" media="screen">
</head>
<body>

	<div class="container">

		<div class="col-sm-3 col-md-4">&nbsp;</div>
		
		<div class="col-sm-6 col-md-4">
			<form class="form-signin" action="/${scopeUrl }/login.do" method="POST" role="form">
				<div class="form-group">
					<label for="loginId">账号 </label>
					<input type="text" class="form-control" id="loginId" name="loginId" placeholder="Please enter..." required autofocus>
				</div>
				<div class="form-group">
					<label for="loginPass">密码 </label>
					<input type="password" class="form-control" id="loginPass" name="loginPass" placeholder="Please enter..." required>
				</div>
				<div class="checkbox">
					<label>
						<input type="checkbox"> 记住我
					</label>
				</div>
				<button type="submit" class="btn btn-lg btn-primary btn-block">登录</button>
			</form>
		</div>
	
		<div class="col-sm-3 col-md-4">&nbsp;</div>
		
	</div>
	<!-- /container -->

</body>
</html>