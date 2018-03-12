<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>政策申请材料上传</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/common.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/policy.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/uploadfive/uploadifive.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/js/bootstrap3.3.7/css/bootstrap.css">

    <script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="${ctx}/js/comm_change.js"></script>
    <script src="${ctx}/js/uploadfive/jquery.uploadifive.js"></script>
    <script src="${ctx}/js/bootstrap3.3.7/js/bootstrap.js"></script>
    <script src="${ctx}/js/bootbox.min.js"></script>

</head>
<body>
<form action="${ctx}/local/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" value="submit"/>
</form>
</body>
</html>