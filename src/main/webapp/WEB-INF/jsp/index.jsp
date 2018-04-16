<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="/static/resource/js/jquery.js"></script>
<title>123321</title>
<script>
	$(document).ready(function() {
		$("p").click(function() {
			$(this).hide();
		});
	});
</script>
</head>
<body>
	<p>如果您点击我，我会消失。</p>
	<p>点击我，我会消失。</p>
	<p>也要点击我哦。</p>
</body>
</html>