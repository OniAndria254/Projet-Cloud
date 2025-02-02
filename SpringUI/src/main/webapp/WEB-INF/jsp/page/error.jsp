<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Erreur</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet"/>
  </head>
  <body>
    <div class="container mt-5">
      <h1 class="text-danger">Une erreur est survenue</h1>
      <p>${errorMessage}</p>
      <pre>${errorDetails}</pre>
      <a href="${pageContext.request.contextPath}/transaction/buy-sell" class="btn btn-primary">Retour</a>
    </div>
  </body>
</html>
