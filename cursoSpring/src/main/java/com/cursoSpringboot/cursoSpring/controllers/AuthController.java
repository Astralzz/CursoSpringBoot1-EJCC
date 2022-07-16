package com.cursoSpringboot.cursoSpring.controllers;

import com.cursoSpringboot.cursoSpring.dao.UsuarioDao;
import com.cursoSpringboot.cursoSpring.models.User;
import com.cursoSpringboot.cursoSpring.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Control de inicio de sesion
@RestController
public class AuthController {

    @Autowired //Crea un objeto de la implementacion (Inyeccion de dependecias)
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    //Registrar un usuario
    @RequestMapping(value = "api/usuario/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {

        //Obtenemos el usuario
        User uss = usuarioDao.optenerUsuarioPorCredenciales(user);

        //Si encontro al usuario
        if (uss != null) {

            //Convertimos id a str
            String id = String.valueOf(uss.getId());

            //Creamos un jwt (Token) con su id y el correo del usuario
            String token = jwtUtil.create(id, uss.getCorreo());

            //Retornamos el token
            return token;
        }

        return "ERROR";
    }

}
