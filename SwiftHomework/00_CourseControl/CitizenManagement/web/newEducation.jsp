<%-- 
    Document   : newEducation
    Created on : Nov 1, 2016, 1:36:54 PM
    Author     : livanov
--%>

<%@page import="business.BusinessLayer"%>
<%@page import="education.*"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New education</title>
    </head>
    <%
        String strId = request.getParameter("id");
        if (strId == null || strId.isEmpty() || !strId.matches("\\d+")) {
            response.sendRedirect("userInfo.jsp");
            return;
        }

        int citizenId = Integer.parseInt(strId);

        if (request.getParameter("submit") != null) {
            String institutionName = request.getParameter("institution");
            LocalDate enrollmentDate = LocalDate.parse(request.getParameter("enrollmentDate"));
            LocalDate graduationDate = LocalDate.parse(request.getParameter("graduationDate"));
            EducationDegree degree = Enum.valueOf(EducationDegree.class, request.getParameter("degree"));

            Education education;

            if (degree == EducationDegree.Primary) {
                education = new PrimaryEducation(institutionName, enrollmentDate, graduationDate);
            } else if (degree == EducationDegree.Secondary) {
                education = new SecondaryEducation(institutionName, enrollmentDate, graduationDate);
            } else {
                education = new HigherEducation(institutionName, enrollmentDate, graduationDate, degree);
            }

            BusinessLayer.addEducation(citizenId, education);

            response.sendRedirect("userInfo.jsp?id=" + citizenId);
            return;
        }
    %>

    <body>
        <form action="newEducation.jsp?id=<%=citizenId%>" method="POST">
            <table border="1">
                <tbody>
                    <tr>
                        <td>Institution name:</td>
                        <td>
                            <input type="text" name="institution" value="" />
                        </td>
                    </tr>
                    <tr>
                        <td>Enrollment date:</td>
                        <td>
                            <input type="text" name="enrollmentDate" value="<%=LocalDate.now()%>" />
                        </td>
                    </tr>
                    <tr>
                        <td>Graduation date:</td>
                        <td>
                            <input type="text" name="graduationDate" value=""  />
                        </td>
                    </tr>
                    </tr>
                    <tr>
                        <td>Degree:</td>
                        <td>
                            <select name="degree">
                                <%for (EducationDegree dgr : EducationDegree.values()) {%> 
                                <option value="<%=dgr%>"><%=dgr%></option>
                                <% }%>
                            </select>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" name="submit" value="submit" />
        </form>

        <form action="userInfo.jsp?id=<%=citizenId%>" method="POST">  
            <input type="submit" value="back" name="back" />
        </form>

    </body>
</html>
