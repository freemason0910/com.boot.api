<html lang="en-US">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>OA_SYS</title>
    <link rel="shortcut icon" href="../favicon.ico">
    <link rel="stylesheet" type="text/css" media="all" href="../static/css/style.css">
    <script type="text/javascript" src="../static/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="../static/js/jquery.leanModal.min.js"></script>
    <!-- jQuery plugin leanModal under MIT License http://leanmodal.finelysliced.com.au/ -->
</head>

<body>
<div id="topbar"><a href="http://designshack.net">Back to Design Shack</a></div>
<div id="w">
    <div id="content">
        <h1>欢迎到来！</h1>
        <p>这是FreeMasonCn的个人练习项目请登录查看更加精彩</p>
        <center><a href="#loginmodal" class="flatbtn" id="modaltrigger">登录</a></center>
    </div>
</div>
<div id="loginmodal" style="display:none;">
    <h1>用户登录</h1>
    <#if errorflog>
    <h2>${errormsg}</h2>
    </#if>
    <form id="loginform" name="loginform" method="post" action="/login">
        <label for="username">用户名:</label>
        <input type="text" name="username" id="username" class="txtfield" tabindex="1">

        <label for="password">密码:</label>
        <input type="password" name="password" id="password" class="txtfield" tabindex="2">

        <div class="center"><input type="submit" name="loginbtn" id="loginbtn" class="flatbtn-blu hidemodal" value="登录" tabindex="3"></div>
    </form>
</div>
<script type="text/javascript">
    $(function(){
        $('#loginform').submit(function(e){
            return true;
        });

        $('#modaltrigger').leanModal({ top: 110, overlay: 0.45, closeButton: ".hidemodal" });
    });
</script>
</body>
</html>