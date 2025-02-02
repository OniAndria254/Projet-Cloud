<!DOCTYPE html>
<html>
<head>
    <title>Analyse des Transactions</title>
</head>
<body>
    <h1>Analyse des Transactions</h1>
    <form action="<%= request.getContextPath() %>/analyse/transactions/result" method="get">
        <label for="typeAnalyse">Type d'analyse:</label>
        <select id="typeAnalyse" name="typeAnalyse">
            <option value="quartile">1er Quartile</option>
            <option value="max">Max</option>
            <option value="min">Min</option>
            <option value="moyenne">Moyenne</option>
            <option value="ecart-type">Écart-type</option>
        </select><br><br>

        <label for="crypto">Crypto:</label>
        <select id="crypto" name="idCrypto">
            <option value="0">Tous</option>
            <%= 
                List<Crypto> cryptos = (List<Crypto>) request.getAttribute("cryptos");
                for (Crypto crypto : cryptos) { 
            %>
                <option value="<%= crypto.getId() %>"><%= crypto.getNom() %></option>
            <%
                }
            %>
        </select><br><br>

        <label for="dateMin">Date et Heure Min:</label>
        <input type="datetime-local" id="dateMin" name="dateMin"><br><br>

        <label for="dateMax">Date et Heure Max:</label>
        <input type="datetime-local" id="dateMax" name="dateMax"><br><br>

        <button type="submit">Valider</button>
    </form>
</body>
</html>
