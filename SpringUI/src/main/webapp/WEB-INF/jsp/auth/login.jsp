<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Page</title>
  <link rel="stylesheet" href="/assets/style.css">
</head>
<body>


  <div class="login-container">
    <div class="logo-container">
      <img src="/assets/img/cryptoz-favicon.png" alt="Logo" class="logo">
      <h1>CRYPTO</h1>
    </div>
    <h2>Login</h2>
    <form action="/login" method="post">
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
      </div>
      <button type="submit" class="login-button">
        <span>Login</span>
      </button>
    </form>
    <p>Don't have an account? <a href="/register">Sign Up</a></p>
  </div>
</body>
</html>
