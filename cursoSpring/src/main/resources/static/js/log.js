// Call the dataTables jQuery plugin
$(document).ready(function () {
  //on ready
});

//-------------- REGISTRAR USUARIO--------------

//Datos de la tabla
async function iniciarSesion() {
  //Optenemos los datos
  let datos = {};
  datos.correo = document.getElementById("txtCorreo").value;
  datos.password = document.getElementById("txtPassword").value;

  //Enviamos
  const res = await fetch("api/usuario/login", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    //Enviar un JSON de strings con un objeto
    body: JSON.stringify(datos),
  });

  //Optenemos la respuesta en forma de texto
  const respuesta = await res.text();

  //Verificamos la respuesta
  if (respuesta !== "ERROR") {

    console.log(respuesta);

    //Guardamos el token
    localStorage.token = respuesta;
    //Guardamos el correo
    localStorage.email = datos.correo;

    //Lo mandamos a la tabla
    window.location.href = "usuarios.html";
  } else {
    alert("Usuario NO encontrado");
  }
}
