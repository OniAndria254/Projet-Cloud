<!DOCTYPE html>
<html>
<head>
    <title>Validation de votre inscription</title>
</head>
<body>
    <h1>Bienvenue sur notre site !</h1>
    <p>Veuillez copier sur le lien ci-dessous et le coller dans le site pour valider votre inscription :</p>
    <a href="{{ url('/validate?token=' . $token) }}">{{ url('/validate?token=' . $token) }}</a>
</body>
</html>