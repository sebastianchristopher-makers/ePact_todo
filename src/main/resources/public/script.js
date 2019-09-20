function validateForm() {
  let password = document.getElementById('password').value;
  let username = document.getElementById('username').value;
  if(!validPassword(password)) {
    document.getElementById('password-length').innerHTML = 'Password must be at least 6 characters';
    returnToPreviousPage();
    return false;
  }
  debugger;
  if(validUsername(username) == false) {
    document.getElementById('username-length').innerHTML = 'Username must not be empty';
    returnToPreviousPage();
    return false;
    }
  document.forms['sign-up'].submit()
}

function validPassword(password) {
  if(password.length < 6) {
    return false;
  }
  return true;
}

function validUsername(username) {
  if(username.length <= 0) {
    return false;
  }
}