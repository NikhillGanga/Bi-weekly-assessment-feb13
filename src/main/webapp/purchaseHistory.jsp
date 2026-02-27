<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.training.web.entity.Purchase" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Purchase History</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: Arial, sans-serif; background: #f5f6fa; }
        .top-nav { background: #2c3e50; color: white; padding: 13px 30px;
                   display: flex; justify-content: space-between; align-items: center; }
        .top-nav span { font-size: 16px; font-weight: bold; }
        .top-nav a { color: white; text-decoration: none; font-size: 13px;
                     background: rgba(255,255,255,0.15); padding: 6px 14px;
                     border-radius: 5px; margin-left: 10px; }
        .top-nav a:hover { background: rgba(255,255,255,0.3); }
        .container { max-width: 1000px; margin: 30px auto; padding: 0 20px; }
        .box { background: white; padding: 28px; border-radius: 10px;
               box-shadow: 0 3px 14px rgba(0,0,0,0.09); }
        h2 { color: #2c3e50; margin-bottom: 18px; }
        table { width: 100%; border-collapse: collapse; }
        th { background: #2c3e50; color: white; padding: 11px 14px; text-align: left; }
        td { padding: 10px 14px; border-bottom: 1px solid #eee; font-size: 14px; }
        tr:hover td { background: #f0f4f8; }
        .status { display: inline-block; background: #eafaf1; color: #27ae60;
                  padding: 3px 10px; border-radius: 20px; font-size: 12px; font-weight: bold; }
        .empty  { color: #999; font-style: italic; text-align: center; padding: 20px; }
    </style>
</head>
<body>

<div class="top-nav">
    <span>&#128230; My Purchase History</span>
    <div>
        <a href="home">&#128203; Browse Policies</a>
        <a href="login.jsp">&#128274; Logout</a>
    </div>
</div>

<div class="container">
    <div class="box">
        <h2>My Purchased Policies</h2>
        <table>
            <tr>
                <th>#</th>
                <th>Policy Name</th>
                <th>Description</th>
                <th>Premium (rs.)</th>
                <th>Duration (Months)</th>
                <th>Purchase Date</th>
                
            </tr>

            <%
                List<Purchase> purchaseList = (List<Purchase>) request.getAttribute("purchaseList");
                if (purchaseList != null && !purchaseList.isEmpty()) {
                    int i = 1;
                    for (Purchase pur : purchaseList) {
            %>
            <tr>
                <td><%= i++ %></td>
                <td><%= pur.getPolicy().getPolicyName() %></td>
                <td><%= pur.getPolicy().getDescription() %></td>
                <td>Rs. <%= pur.getPolicy().getPremiumAmount() %></td>
                <td><%= pur.getPolicy().getDuration() %></td>
                <td><%= pur.getPurchaseDate() %></td>
                
            </tr>
            <%
                    }
                } else {
            %>
            <tr><td colspan="7" class="empty">No purchases found. <a href="home">Browse policies</a>.</td></tr>
            <% } %>
        </table>
    </div>
</div>

</body>
</html>
