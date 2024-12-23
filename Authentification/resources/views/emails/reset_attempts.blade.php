<!DOCTYPE html>
<html>
<head>
    <title>Réinitialisation des tentatives de connexion</title>
</head>
<body>
    <h1>Réinitialisation des tentatives</h1>
    <p>Bonjour,</p>
    <p>Vous avez dépassé la limite de tentatives de connexion autorisées.</p>
    <p>Veuillez cliquer sur le lien ci-dessous pour réinitialiser vos tentatives de connexion :</p>
    <a href="{{ url('/reset-attempts?token=' . $token) }}">{{ url('/reset-attempts?token=' . $token) }}</a>
</body>
</html>
