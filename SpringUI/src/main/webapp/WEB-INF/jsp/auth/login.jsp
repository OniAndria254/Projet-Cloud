<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login - Crypto Planet</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

  <style>
    body {
      font-family: 'Inter', sans-serif;
      background-color: #0d1117;
      color: #c9d1d9;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      overflow: hidden;
    }

    .container {
      position: relative;
      width: 800px;
      max-width: 100%;
      min-height: 480px;
      background: #161b22;
      border-radius: 10px;
      overflow: hidden;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }

    .form-container {
      position: absolute;
      top: 0;
      height: 100%;
      width: 50%;
      transition: all 0.5s ease-in-out;
    }

    .sign-in-container {
      left: 0;
      z-index: 2;
    }

    .sign-up-container {
      right: 0;
      opacity: 1; /* Keep opacity at 1 */
      transform: translateX(0%); /* Neutral transformation for the initial view */
      z-index: 1;
    }

    .container.right-panel-active .sign-in-container {
      transform: translateX(100%);
      opacity: 0;
      z-index: 1;
    }

    .container.right-panel-active .sign-up-container {
      transform: translateX(0);
      opacity: 1;
      z-index: 2;
    }

    .form-container form {
      background: #21262d;
      display: flex;
      flex-direction: column;
      padding: 2rem;
      height: 100%;
      justify-content: center;
      text-align: center;
    }

    h1 {
      margin-bottom: 10px;
    }

    .form-control {
      background-color: #30363d;
      border: none;
      color: #c9d1d9;
      margin-bottom: 10px;
    }

    .form-control:focus {
      border-color: #58a6ff;
      box-shadow: none;
    }

    .btn-primary {
      background-color: #58a6ff;
      border: none;
      width: 100%;
    }

    .error-message {
      display: none;
      background-color: #ff4d4d;
      color: white;
      padding: 0.5rem;
      border-radius: 5px;
      margin-bottom: 1rem;
      text-align: center;
    }

    .success-message {
      display: none;
      background-color: #28a745;
      color: white;
      padding: 0.5rem;
      border-radius: 5px;
      margin-bottom: 1rem;
      text-align: center;
    }

    .btn-primary:hover {
      background-color: #007bff;
    }

    .overlay-container {
      position: absolute;
      top: 0;
      left: 50%;
      width: 50%;
      height: 100%;
      transition: all 0.5s ease-in-out;
      background: linear-gradient(to right, #4B49AC, #1089ff);
      z-index: 3;
    }

    .overlay {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 100%;
      text-align: center;
      color: white;
    }

    .overlay button {
      background-color: transparent;
      border: 2px solid white;
      padding: 10px 20px;
      color: white;
      font-size: 16px;
      cursor: pointer;
      border-radius: 5px;
      margin-top: 15px;
      transition: 0.3s;
    }

    .overlay button:hover {
      background-color: white;
      color: #1089ff;
    }

    .container.right-panel-active .overlay-container {
      transform: translateX(-100%);
    }

    #toggleLogin {
      position: relative;
      z-index: 9999;
    }

    .signup-link {
      color: #58a6ff;
      text-decoration: none;
      padding: 10px 0;
      transition: all 0.3s ease;
    }

    .signup-link:hover {
      color: #007bff;
      text-decoration: none;
    }

    .signup-link:active {
      color: #0056b3;
    }

  </style>
</head>
<body>

<div class="container" id="container">
  <!-- Admin Login -->
  <div class="form-container sign-up-container">
    <form action="/auth/loginAdmin" method="post">
      <h3>Administrator</h3>
      <div class="form-group">
        <label for="admin-email">Email</label>
        <input type="email" class="form-control" id="admin-email" name="email" value="admin@gmail.com" required>
      </div>
      <div class="form-group">
        <label for="admin-password">Password</label>
        <input type="password" class="form-control" id="admin-password" name="password" value="admin123" required>
      </div>
      <button class="btn btn-primary" type="submit">Login</button>
    </form>
  </div>

  <!-- User Login -->
  <div class="form-container sign-in-container">
    <form action="/auth/login" method="post">
      <h3>User</h3>

      <div class="form-group">
        <label for="user-email">Email</label>
        <input type="email" class="form-control" id="user-email" name="email" placeholder="Enter your email" required>
      </div>
      <div class="form-group">
        <label for="user-password">Password</label>
        <input type="password" class="form-control" id="user-password" name="password" placeholder="Enter your password" required>
      </div>
      <button class="btn btn-primary" type="submit">Login</button>
      <br>
      <a class="signup-link" href="/auth/register">
        Don't have an account? Join Crypto Planet now!
      </a>
    </form>
  </div>

  <!-- Overlay for switching between user and admin -->
  <div class="overlay-container">
    <div class="overlay">
      <h1>Crypto Planet</h1>
      <p>Enter your personal space to explore, trade, and manage your cryptocurrencies.</p>
      <button id="toggleLogin">Switch to Admin Mode</button>
    </div>
  </div>
</div>

<script>

  window.onload = function() {
    const errorMessage = '<%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>';
    if (errorMessage) {
      alert(errorMessage); // Affiche l'alerte si l'erreur est présente
    }
  }
  const toggleButton = document.getElementById('toggleLogin');
  const container = document.getElementById('container');

  let isUserLogin = true;

  toggleButton.addEventListener('click', () => {
    container.classList.toggle("right-panel-active");
    if (isUserLogin) {
      toggleButton.textContent = "Switch to User Mode";
    } else {
      toggleButton.textContent = "Switch to Admin Mode";
    }
    isUserLogin = !isUserLogin;
  });

</script>

</body>
</html>
