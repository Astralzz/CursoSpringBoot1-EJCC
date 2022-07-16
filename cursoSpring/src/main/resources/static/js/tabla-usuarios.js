// Call the dataTables jQuery plugin
$(document).ready(function () {
  optenerUsuarios();
  $("#tablaUsuarios").DataTable();
});

//Arreglo de ususarios
let UsuariosL = [];

//-------------- OPTENER LISTA DE USUARIOS --------------

//Datos de la tabla
async function optenerUsuarios() {
  const resultado = await fetch("api/ListaDeUsuarios", {
    method: "GET",
    headers: getHeaders(),
  });

  const usuarios = await resultado.json();
  console.log("Usuarios vale --> ");
  console.log(usuarios);

  llenarTabla(usuarios);
}

//Llenar tabla
function llenarTabla(Usuarios) {
  let Listado;

  //Recorremos
  for (let usuario of Usuarios) {
    //Agregamos al rreglo
    crearObjeto(usuario);

    //Boton eliminar
    let botonElimnar =
      '<a href="#" onclick = "eliminarUsuario(' +
      usuario.id +
      ')"' +
      'class="btn btn-danger btn-circle btn-sm"> <i class="fas fa-trash"></i> </a>';

    let u = "<tr>"; //Filas de la tabla
    u += "<td>" + usuario.id + "</td>";
    u += "<td>" + usuario.nombre + "</td>";
    u += "<td>" + usuario.apellido_P + "</td>";
    u += "<td>" + usuario.apellido_M + "</td>";
    u += "<td>" + usuario.edad + "</td>";
    u += "<td>" + usuario.numero + "</td>";
    u += "<td>" + usuario.correo + "</td>";
    u += "<td>" + usuario.sexo + "</td>";
    u += "<td>" + botonElimnar + "</td>";
    u += "</tr>";

    Listado += u;
  }

  //Optenemos el elemento y ponemos las filas en las tablas
  document.querySelector("#tablaUsuarios tbody").outerHTML = Listado;
}

//Crear objeto
function crearObjeto(usuario) {
  //Creamos un objeto de ese usuario
  let Us = {
    id: usuario.id,
    nombre: usuario.nombre,
    apellido_P: usuario.apellido_P,
    apellido_M: usuario.apellido_M,
  };

  //Agregamos el objeto al arreglo
  UsuariosL.push(Us);
}

//Optener nombre por id
function optenerNombrePorId(id) {
  let n = UsuariosL.find((Us) => Us.id === id);
  return n.nombre;
}

//-------------- ELIMINAR USUARIO --------------

//Eliminar un usuario por id
async function eliminarUsuario(id) {
  //Pedimos confirmacion
  if (!confirm("¿Desea eliminar al usuario " + optenerNombrePorId(id) + "?:")) {
    alert("Operacion cancelada");
    return;
  }

  //eliminamos
  await fetch("api/usuario/Eliminar/" + id, {
    method: "DELETE",
    headers: getHeaders(),
  });

  alert("Se a eliminado el usuario " + optenerNombrePorId(id));

  //Actualizamos pagina
  location.reload();
}

//Autorizacion para alguna accion
function getHeaders() {
  return {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: localStorage.token, //Checamos el token
  };
}
