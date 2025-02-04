<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, itu.p16.crypto.entity.Commission" %>
<%@ page import="itu.p16.crypto.entity.Cryptomonnaie" %>
<%
    List<Commission> commissions = (List<Commission>) request.getAttribute("commissions");
    List<Cryptomonnaie> crytos = (List<Cryptomonnaie>) request.getAttribute("crypto");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier les Commissions</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: "Inter", sans-serif;
            background-color: #0d1117;
            color: #c9d1d9;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: auto;
            background-color: #161b22;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0,0,0,0.1);
        }
        .form-control, .btn {
            border-radius: 5px;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        table {
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #21262d;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Modifier les Commissions</h1>
        
        <% String message = (String) request.getAttribute("message");
           if (message != null) { %>
            <div class="alert alert-success" role="alert"><%= message %></div>
        <% } %>

        <form action="${pageContext.request.contextPath}/commission/modifier" method="post">
            <div class="form-group">
                <label for="idCrypto">Cryptomonnaie :</label>
                <select id="idCrypto" name="idCrypto" class="form-control">
                    <%
                       if (crytos != null) {
                           for (Cryptomonnaie crypto : crytos) { %>
                        <option value="<%= crypto.getIdCryptomonnaie() %>">Cryptomonnaie <%= crypto.getIdCryptomonnaie() %></option>
                    <% } } %>
                </select>
            </div>
            <div class="form-group">
                <label for="commissionAchat">Commission d'Achat (%) :</label>
                <input type="number" step="0.01" id="commissionAchat" name="commissionAchat" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="commissionVente">Commission de Vente (%) :</label>
                <input type="number" step="0.01" id="commissionVente" name="commissionVente" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Modifier</button>
        </form>

        <h2 class="mt-4">Liste des Commissions</h2>
        <table class="table table-dark table-striped">
            <thead>
                <tr>
                    <th>ID Cryptomonnaie</th>
                    <th>Commission Achat</th>
                    <th>Commission Vente</th>
                    <th>Date Modification</th>
                </tr>
            </thead>
            <tbody>
                <% if (commissions != null) {
                       for (Commission commission : commissions) { %>
                    <tr>
                        <td><%= commission.getIdCryptomonnaie() %></td>
                        <td><%= commission.getCommissionAchat() %></td>
                        <td><%= commission.getCommissionVente() %></td>
                        <td><%= commission.getDateModification() %></td>
                    </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
</body>
</html>
