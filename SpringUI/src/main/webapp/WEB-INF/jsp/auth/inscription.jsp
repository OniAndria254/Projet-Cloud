<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Registration Page</title>
  <link rel="stylesheet" href="/assets/style_inscription.css">
</head>
<body>
  <div class="registration-container">
    <div class="header">
      <img src="/assets/img/cryptoz-favicon.png" alt="Logo" class="logo">
      <p class="site-name">CRYPTO</p>
    </div>
    
    <h2>Let’s get you registered!</h2>
    <p>Already have an account? <a href="/login">Login</a></p>
    <form action="/register" method="post">
      <div class="form-group">
        <label for="partner-code">Username</label>
        <input type="text" id="partner-code" name="username" placeholder="Username">
      </div>

      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" placeholder="email" required>
      </div>

      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="password" required>
      </div>
      <button type="submit" class="register-button">Register</button>
    </form>


    <p class="legal-text">
      By registering, I declare that I have carefully read, understood and accepted the entire text of the Company's <a href="#">Legal Documents</a> and <a href="#">Privacy Policy</a>.
    </p>
  </div>
</body>
</html>
