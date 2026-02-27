<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Success</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #eef2f7;
               display: flex; justify-content: center; align-items: center; height: 100vh; }
        .box { background: white; padding: 44px 40px; border-radius: 12px;
               box-shadow: 0 6px 24px rgba(0,0,0,0.12); width: 380px; text-align: center; }
        h2 { color: #27ae60; margin-bottom: 10px; }
        p  { color: #555; margin-bottom: 28px; font-size: 15px; }
        .btn { display: inline-block; padding: 11px 22px; border-radius: 6px;
               text-decoration: none; font-size: 14px; margin: 6px; font-weight: bold; }
        .blue  { background: #3498db; color: white; }
        .blue:hover  { background: #2980b9; }
        .green { background: #2ecc71; color: white; }
        .green:hover { background: #27ae60; }
    </style>
</head>
<body>
<div class="box">
    <h2>&#10003; Login Successful!</h2>
    <p>Welcome, <strong>${greet}</strong>!</p>

    <a href="home"     class="btn green">&#128203; Browse Policies</a>
    <a href="purchase" class="btn blue">&#128230; My Purchases</a>
</div>
</body>
</html>
