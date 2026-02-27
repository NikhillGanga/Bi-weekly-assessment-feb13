<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Purchase Success</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f0f4f8;
               display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
        .box { background: white; padding: 40px; border-radius: 10px;
               box-shadow: 0 4px 16px rgba(0,0,0,0.12); width: 380px; text-align: center; }
        h2 { color: #27ae60; }
        p { color: #555; font-size: 15px; margin: 14px 0; }
        a { display: inline-block; margin: 8px; padding: 10px 20px;
            background: #3498db; color: white; border-radius: 6px;
            text-decoration: none; font-size: 14px; }
        a:hover { background: #2980b9; }
        a.green { background: #2ecc71; }
        a.green:hover { background: #27ae60; }
    </style>
</head>
<body>
<div class="box">
    <h2>&#10003; ${msg}</h2>
    <p>Thank you for purchasing the policy!</p>

    <a href="purchase?username=${username}">View My Purchases</a>
    <a href="home" class="green">Browse More</a>
</div>
</body>
</html>
