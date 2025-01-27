<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>URL Confirmation</title>
  <link rel="stylesheet" href="assets/stylepin.css">
  <style>
    /* Ajout de styles spécifiques pour cette page */
    .url-container {
      margin-top: 20px;
    }

    .url-input {
      width: 100%;
      padding: 10px;
      border: 2px solid #ddd;
      border-radius: 5px;
      font-size: 16px;
      outline: none;
      transition: border-color 0.3s ease;
    }

    .url-input:focus {
      border-color: #4CAF50; /* Bordure verte au focus */
      box-shadow: 0 0 10px rgba(76, 175, 80, 0.4); /* Ombre verte */
    }

    .submit-button {
      margin-top: 20px;
      padding: 10px 20px;
      background: linear-gradient(90deg, #4CAF50, #8BC34A);
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 16px;
      transition: background 0.3s ease;
    }

  
  </style>
</head>
<body>
  <div class="login-container">
    <div class="logo-container">
      <img src="assets/img/cryptoz-favicon.png" alt="Logo" class="logo">
      <h1>CRYPTO</h1>
    </div>
    <h2>Enter Your URL</h2>
    <form action="/confirm-url" method="post" id="urlForm">
      <div class="url-container">
        <input type="url" id="urlInput" name="url" class="url-input" placeholder="https://example.com" required>
      </div>
      <button type="submit" class="login-button">
        <span>Submit</span>
      </button>
    </form>
    <p>Don't have an account? <a href="/register">Sign Up</a></p>
  </div>
</body>
</html>