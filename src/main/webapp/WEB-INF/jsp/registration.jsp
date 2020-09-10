<%--
  Created by IntelliJ IDEA.
  User: Piotrek
  Date: 09.09.2020
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri ="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri ="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Registration</title>
    <style>
        .error {
            color: red;
        }
        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body>
<h1>registration</h1>
<form:form modelAttribute="registration">
    <form:errors  path="*" cssClass="errorblock" element="div" />

    <table>
        <tr>
            <td>
                <spring:message code = "name"/>
            </td>
            <td>
                <form:input path="name"/>
            </td>
            <td>
                <form:errors path="name" cssClass="error" />
            </td>
        </tr>

        <tr>

            <td colspan="3">
                <input type ="submit" value = "Add Registration">
            </td>
        </tr>

    </table>

</form:form>

</body>
</html>
