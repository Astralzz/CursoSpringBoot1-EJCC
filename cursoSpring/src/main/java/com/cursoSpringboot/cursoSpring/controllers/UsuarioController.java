package com.cursoSpringboot.cursoSpring.controllers;

import com.cursoSpringboot.cursoSpring.dao.UsuarioDao;
import com.cursoSpringboot.cursoSpring.models.User;
import com.cursoSpringboot.cursoSpring.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//Control de usuarios
@RestController
public class UsuarioController {

    //Variable dao (Conexxion)
    @Autowired //Crea un objeto de la implementacion (Inyeccion de dependecias)
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    //GET --> Optener
    //DELETE --> Eliminar
    //POST --> Insertar/Crear

    //Validar token
    private boolean validarToken(String token) {
        //Optenemos el id del usuario enviando el token
        String usuarioId = jwtUtil.getKey(token);
        //Verificamos que el id no sea vacio
        return usuarioId != null;
    }

    //Devolver Lista de usuarios (trae el token de autorizacion)
    @RequestMapping(value = "api/ListaDeUsuarios", method = RequestMethod.GET)
    public List<User> getListaUasuarios(@RequestHeader(value = "Authorization") String token) {

        //Validamos token
        if (!validarToken(token)) {
            //Retornamos null
            return null;
        }

        //Retornamos la lista de usuarios
        return usuarioDao.getListaUsuario();
    }

    //Devolver un usuario por id
    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.GET)
    public User getUsuario(@PathVariable long id) {
        //Creamos el usuario
        User user = new User();
        return user;
    }

    //Registrar un usuario
    @RequestMapping(value = "api/usuario/registrar", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody User user) {

        //Crear una contraseña con encriptada con argon 2
        Argon2 pass = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        //Convertimos la contraseña que puso el usuario a una encriptada
        //1 -> iteraciones, 1024 -> Uso en memoria, 1 -> Multihilos, user -> Contraseña a encriptar
        String newPass = pass.hash(1, 1024, 1, user.getPassword());

        //Ponemos la nueva contraseña
        user.setPassword(newPass);

        //Creamos el usuario
        usuarioDao.crearUsuario(user);
    }

    //Eliminar un usuario por id
    @RequestMapping(value = "api/usuario/Eliminar/{id}", method = RequestMethod.DELETE)
    public void deleteUserID(@RequestHeader(value = "Authorization") String token,
                             @PathVariable long id) {

        //Validamos token
        if (!validarToken(token)) {
            //Retornamos null
            return;
        }

        //Eliminamos
        usuarioDao.deleteUser(id);
    }


}
