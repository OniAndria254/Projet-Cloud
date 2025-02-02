<!DOCTYPE html>
<html>
<head>
    <title>Analyse des Commissions</title>
</head>
<body>
    <h1>Analyse des Commissions</h1>
    <form action="<%= request.getContextPath() %>/analyse/commissions/result" method="get">
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
