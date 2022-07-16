// Call the dataTables jQuery plugin
$(document).ready(function () {
  //on ready
});

//-------------- REGISTRAR USUARIO--------------

//Datos de la tabla
async function registrarUsuarios() {
  //Optenemos los datos
  let datos = {};
  datos.nombre = document.getElementById("txtNombre").value;
  datos.apellido_P = document.getElementById("txtApellido_P").value;
  datos.apellido_M = document.getElementById("txtApellido_M").value;
  datos.edad = document.getElementById("txtEdad").value;
  datos.numero = document.getElementById("txtTelefono").value;
  datos.correo = document.getElementById("txtCorreo").value;
  datos.sexo = document.getElementById("txtSexo").value;
  datos.password = document.getElementById("txtPassword").value;

  //Comprobar la contraseña
  if (datos.password !== document.getElementById("txtPasswordC").value) {
    alert("las contraseñas No coinciden");
    return;
  }

  //Enviamos
  await fetch("api/usuario/registrar", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    //Enviar un JSON de strings con un objeto
    body: JSON.stringify(datos),
  });

  alert("Se a creado el ususario");
  //Lo mandamos al inicio de sesion
  window.location.href = "login.html";
}
