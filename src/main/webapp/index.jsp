<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Insurance Policy Management</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #eef2f7;
               display: flex; justify-content: center; align-items: center; height: 100vh; }
        .box { background: white; padding: 50px 40px; border-radius: 12px;
               box-shadow: 0 6px 24px rgba(0,0,0,0.12); width: 380px; text-align: center; }
        h2 { color: #2c3e50; margin-bottom: 10px; font-size: 22px; }
        p  { color: #777; font-size: 14px; margin-bottom: 34px; }
        .btn { display: block; width: 100%; padding: 13px; border: none;
               border-radius: 7px; font-size: 15px; cursor: pointer;
               text-decoration: none; margin-bottom: 14px; font-weight: bold; }
        .btn-admin  { background: #e74c3c; color: white; }
        .btn-admin:hover  { background: #c0392b; }
        .btn-user   { background: #3498db; color: white; }
        .btn-user:hover   { background: #2980b9; }
        .divider { color: #ccc; margin: 4px 0 14px; font-size: 13px; }
    </style>
</head>
<body>
<div class="box">
    <h2>&#128737; Insurance Policy</h2>
    <p>Management System</p>

    <a href="adminLogin.jsp" class="btn btn-admin">&#128272; Admin Login</a>
    <div class="divider">— or —</div>
    <a href="login.jsp" class="btn btn-user">&#128100; Customer Login</a>

    <p style="margin-top:20px; font-size:13px;">
        New customer? <a href="register.jsp" style="color:#3498db;">Register here</a>
    </p>
</div>
</body>
</html>
