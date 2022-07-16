package com.cursoSpringboot.cursoSpring.dao;

import com.cursoSpringboot.cursoSpring.models.User;

import java.util.List;

//Interface
public interface UsuarioDao {

    List<User> getListaUsuario();

    void deleteUser(long id);

    void crearUsuario(User user);

    User optenerUsuarioPorCredenciales(User user);
}
