<!DOCTYPE html>
<html>
<head>
    <title>Résultats de l'Analyse des Transactions</title>
</head>
<body>
    <h1>Résultats de l'Analyse des Transactions</h1>
    <table>
        <thead>
            <tr>
                <th>Type d'Analyse</th>
                <th>Valeur</th>
            </tr>
        </thead>
        <tbody>
            <%
                Map<String, Object> transactions = (Map<String, Object>) request.getAttribute("transactions");
                for (Map.Entry<String, Object> entry : transactions.entrySet()) {
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
    <a href="<%= request.getContextPath() %>/analyse/transactions">Retour</a>
</body>
</html>
