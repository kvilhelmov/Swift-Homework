
<%@page import="business.BusinessLayer"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="insurance.SocialInsuranceRecord"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <%
        String strId = request.getParameter("id");
        if (strId == null || strId.isEmpty() || !strId.matches("\\d+")) {
            response.sendRedirect("userInfo.jsp");
            return;
        }

        int citizenId = Integer.parseInt(strId);

        if (request.getParameter("submit") != null) {
            int year = Integer.parseInt(request.getParameter("year"));
            int month = Integer.parseInt(request.getParameter("month"));
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));

            SocialInsuranceRecord record = new SocialInsuranceRecord(year, month, amount);

            BusinessLayer.addSocialInsuranceRecord(citizenId, record);
        }
    %>
    <body>
        <form action="newSocialInsuranceRecord.jsp?id=<%=citizenId%>" method="POST">
            <table border="1">
                <tbody>
                    <tr>
                        <td>Year:</td>
                        <td>
                            <select name="year">
                                <% for (int i = 0; i < 15; i++) {
                                        int value = LocalDate.now().getYear() - i;%>
                                <option value="<%=value%>"><%=value%></option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Month:</td>
                        <td><select name="month">
                                <% for (int i = 0; i < 12; i++) {%>
                                <option value="<%=i + 1%>"><%=i + 1%></option>
                                <%}%>
                            </select></td>
                    </tr>
                    <tr>
                        <td>Amount:</td>
                        <td>
                            <input type="text" name="amount" value="" />
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="submit" name="submit" />            
        </form>

        <form action="userInfo.jsp?id=<%=citizenId%>" method="POST">  
            <input type="submit" value="back" name="back" />
        </form>

        <h1>Old data</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Year</th>
                    <th>Month</th>
                    <th>Amount</th>
                </tr>
            </thead>
            <tbody>
                <% for (SocialInsuranceRecord record : BusinessLayer.getSocialInsuranceRecords(citizenId)) {%>
                <tr>
                    <td><%=record.getYear()%></td>
                    <td><%=record.getMonth()%></td>
                    <td><%=record.getAmount()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>

    </body>
</html>
