<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.training.web.entity.Policy" %>
<!DOCTYPE html>
<html>
<head>
    <title>Available Policies</title>
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
        h2 { color: #2c3e50; margin-bottom: 18px; }
        table { width: 100%; border-collapse: collapse; background: white;
                border-radius: 10px; overflow: hidden;
                box-shadow: 0 3px 14px rgba(0,0,0,0.09); }
        th { background: #2c3e50; color: white; padding: 12px 16px; text-align: left; }
        td { padding: 11px 16px; border-bottom: 1px solid #eee; font-size: 14px; }
        tr:hover td { background: #eaf4fb; }
        .btn-buy { padding: 7px 16px; background: #3498db; color: white;
                   border: none; border-radius: 5px; cursor: pointer; font-size: 13px; }
        .btn-buy:hover { background: #2980b9; }
        .empty { color: #999; font-style: italic; text-align: center; padding: 20px; }
    </style>
</head>
<body>

<div class="top-nav">
    <span>&#128737; Insurance Policy Management</span>
    <div>
        <a href="purchase">&#128230; My Purchases</a>
        <a href="login.jsp">&#128274; Logout</a>
    </div>
</div>

<div class="container">
    <h2>Available Policies</h2>

    <table>
        <tr>
            <th>#</th>
            <th>Policy Name</th>
            <th>Description</th>
            <th>Premium (Rs.)</th>
            <th>Duration (Months)</th>
            <th>Action</th>
        </tr>

        <%
            List<Policy> policyList = (List<Policy>) request.getAttribute("policyList");
            if (policyList != null && !policyList.isEmpty()) {
                int i = 1;
                for (Policy p : policyList) {
        %>
        <tr>
            <td><%= i++ %></td>
            <td><%= p.getPolicyName() %></td>
            <td><%= p.getDescription() %></td>
            <td>Rs. <%= p.getPremiumAmount() %></td>
            <td><%= p.getDuration() %></td>
            <td>
                <form action="purchase" method="post" style="margin:0">
                    <input type="hidden" name="policyId" value="<%= p.getId() %>" />
                    <button type="submit" class="btn-buy">&#128722; Buy Now</button>
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="6" class="empty">No policies available at the moment.</td></tr>
        <% } %>
    </table>
</div>

</body>
</html>
