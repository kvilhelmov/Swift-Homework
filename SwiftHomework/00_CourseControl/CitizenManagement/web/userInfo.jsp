<%@page import="business.BusinessLayer"%>
<%@page import="education.GradedEducation"%>
<%@page import="education.Education"%>
<%@page import="personaldetails.Citizen"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Info Page</title>
    </head>

    <%
        String strId = request.getParameter("id");
    %>
    <body>
        <form action="userInfo.jsp">
            <input type="text" name="id" value="<%=strId == null ? "" : strId%>" />
            <input type="submit" value="find" />
        </form>
        <%
            if (strId != null && !strId.isEmpty()) {
                if (!strId.matches("\\d+")) {
                    response.sendRedirect("userInfo.jsp");
                    return;
                }

                int citizenId = Integer.parseInt(strId);
                Citizen citizen = BusinessLayer.getCitizen(citizenId);

                if (citizen == null) {
                    response.sendRedirect("userInfo.jsp");
                    return;
                }
        %>

        <br/>
        <table border="1px solid black;">
            <tbody>
                <tr>
                    <td>First name:</td>
                    <td><%= citizen.getFirstName()%></td>
                    <td rowspan="6"><img width="120" height="180" border="1px solid black;" /></td>
                </tr>
                <tr>
                    <td>Middle name: </td>
                    <td><%= citizen.getMiddleName()%></td>
                </tr>
                <tr>
                    <td>Last name: </td>
                    <td><%= citizen.getLastName()%></td>
                </tr>
                <tr>
                    <td>Date of birth: </td>
                    <td><%= citizen.getDateOfBirth()%></td>
                </tr>
                <tr>
                    <td>Gender: </td>
                    <td><%= citizen.getGender()%></td>
                </tr>
                <tr>
                    <td>Height: </td>
                    <td><%= citizen.getHeight()%></td>
                </tr>
                <tr>
                    <td>Address: </td>
                    <td><%= citizen.getAddress().toString().replace("\n", "<br/>")%></td>
                    <%if (request.getParameter("checkEligible") != null) {%>
                    <td><%="Eligible for: " + BusinessLayer.calculateSocialBenefits(citizenId)%></td> 
                    <%} else {%>
                    <td><form action="userInfo.jsp?id=<%=strId%>&checkEligible=true" method="POST">
                            <input type="submit" value="Is Eligible?" />
                        </form>
                        <%}%>
                </tr>
                <% if (!citizen.getEducations().isEmpty()) {%>
                <tr>
                    <% for (Education education : citizen.getEducations()) {%>
                    <td>
                        Institution name: <%=education.getInstitutionName()%><br/>
                        Degree: <%=education.getDegree()%><br/>
                        Enrollment date: <%=education.getEnrollmentDate()%><br/>
                        <% if (education.isGraduated()) {%>
                        Graduation date: <%=education.getGraduationDate()%><br/>
                        <% if (education instanceof GradedEducation) {%>
                        Final grade: <%=((GradedEducation) education).getFinalGrade()%>
                        <% }
                        } else {%>
                        Expected graduation date:<%=education.getGraduationDate()%>
                        <% } %> <br/>  
                    </td>
                    <% }%>
                </tr>
                <% }%>
            </tbody>
        </table>
        <form action="newEducation.jsp?id=<%=strId%>" method="POST">
            <input type="submit" value="Add New Education" name="addNewEducation" />
        </form>
        <form action="newSocialInsuranceRecord.jsp?id=<%=strId%>" method="POST">
            <input type="submit" value="Add New Social Insurance Record" name="addNewSocialInsuranceRecord" />
        </form>
        <% }%>
    </body>
</html>
