<!DOCTYPE html>
<html>
<head>
    <title>Validation de votre inscription</title>
</head>
<body>
    <h1>Bienvenue sur notre site !</h1>
    <p>Veuillez cliquer sur le lien ci-dessous pour valider votre inscription :</p>
    <a href="{{ url('/validate?token=' . $token) }}">{{ url('/validate?token=' . $token) }}</a>
</body>
</html>