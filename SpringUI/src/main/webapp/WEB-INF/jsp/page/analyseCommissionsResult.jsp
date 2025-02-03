<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>Résultats de l'Analyse des Commissions</title>
</head>
<body>
    <h1>Résultats de l'Analyse des Commissions</h1>
    <table>
        <thead>
            <tr>
                <th>Type d'Analyse</th>
                <th>Valeur</th>
            </tr>
        </thead>
        <tbody>
            <%
                Map<String, Object> commissions = (Map<String, Object>) request.getAttribute("commissions");
                for (Map.Entry<String, Object> entry : commissions.entrySet()) {
            %>
                <tr>
                    <td><%= entry.getKey() %></td>
                    <td><%= entry.getValue() %></td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <a href="<%= request.getContextPath() %>/analyse/commissions">Retour</a>
</body>
</html>
