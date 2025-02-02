<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.example.demo.entity.Commission" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier les Commissions</title>
</head>
<body>
    <h1>Modifier les Commissions</h1>

    <!-- Afficher un message de succès -->
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
        <p style="color: green;"><%= message %></p>
    <%
        }
    %>

    <!-- Formulaire de modification -->
    <form action="${pageContext.request.contextPath}/commission/modifier" method="post">
        <label for="idCrypto">Cryptomonnaie :</label>
        <select id="idCrypto" name="idCrypto">
            <%
                List<Commission> commissions = (List<Commission>) request.getAttribute("commissions");
                if (commissions != null) {
                    for (Commission commission : commissions) {
            %>
                        <option value="<%= commission.getIdCryptomonnaie() %>">Cryptomonnaie <%= commission.getIdCryptomonnaie() %></option>
            <%
                    }
                }
            %>
        </select><br><br>

        <label for="commissionAchat">Commission d'Achat (%) :</label>
        <input type="number" step="0.01" id="commissionAchat" name="commissionAchat" required><br><br>

        <label for="commissionVente">Commission de Vente (%) :</label>
        <input type="number" step="0.01" id="commissionVente" name="commissionVente" required><br><br>

        <button type="submit">Modifier</button>
    </form>

    <!-- Afficher la liste des commissions -->
    <h2>Liste des Commissions</h2>
    <table border="1">
        <tr>
            <th>ID Cryptomonnaie</th>
            <th>Commission Achat</th>
            <th>Commission Vente</th>
            <th>Date Modification</th>
        </tr>
        <%
            if (commissions != null) {
                for (Commission commission : commissions) {
        %>
                    <tr>
                        <td><%= commission.getIdCryptomonnaie() %></td>
                        <td><%= commission.getCommissionAchat() %></td>
                        <td><%= commission.getCommissionVente() %></td>
                        <td><%= commission.getDateModification() %></td>
                    </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>