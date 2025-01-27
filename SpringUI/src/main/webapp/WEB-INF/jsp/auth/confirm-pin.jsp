<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>PIN Confirmation</title>
  <link rel="stylesheet" href="assets/stylepin.css">
</head>
<body>

  <div class="background-description">
  </div>

  <div class="login-container">
    <div class="logo-container">
      <img src="assets/img/cryptoz-favicon.png" alt="Logo" class="logo">
      <h1>CRYPTO</h1>
    </div>
    <h2>Confirm Your PIN</h2>
    <form action="/confirm-pin" method="post" id="pinForm">
      <input type="hidden" name="user_id" value="${user_id}">
      <div class="pin-group">
        <div class="pin-inputs">
          <input type="number" name="pin1" maxlength="1" required>
          <input type="number" name="pin2" maxlength="1" required>
          <input type="number" name="pin3" maxlength="1" required>
          <input type="number" name="pin4" maxlength="1" required>
          <input type="number" name="pin5" maxlength="1" required>
          <input type="number" name="pin6" maxlength="1" required>

        </div>
      </div>
      <button type="submit" class="login-button" id="submitButton">
        <span>Verify</span>
      </button>
    </form>
    <p>Don't have an account? <a href="inscription.jsp">Sign Up</a></p>
  </div>

  <script>
    const pinInputs = document.querySelectorAll('.pin-inputs input');
    const submitButton = document.getElementById('submitButton');

    pinInputs.forEach(input => {
      input.addEventListener('input', () => {
        moveFocus(input);
      });
    });

    function moveFocus(current) {
      const currentIndex = Array.from(pinInputs).indexOf(current);
 
      if (current.value.length >= current.maxLength) {
        if (currentIndex < pinInputs.length - 1) {
          pinInputs[currentIndex + 1].focus();
        }
      }

      if (current.value.length === 0 && currentIndex > 0) {
        pinInputs[currentIndex - 1].focus();
      }
    }

  </script>
</body>
</html>