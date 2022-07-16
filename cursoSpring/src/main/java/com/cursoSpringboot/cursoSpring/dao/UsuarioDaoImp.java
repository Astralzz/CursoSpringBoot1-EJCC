package com.cursoSpringboot.cursoSpring.dao;

import com.cursoSpringboot.cursoSpring.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jdk.jfr.TransitionTo;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getListaUsuario() {
        //Creamos una consulta
        String query = "FROM User";
        //Pasamos consulta y optenemos una lista
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void deleteUser(long id) {

        //Conectamos un usuario con la base de datos
        User user = entityManager.find(User.class, id);
        //Elimnamos
        entityManager.remove(user);

    }

    @Override
    public void crearUsuario(User user) {

        //Creamos el usuario
        entityManager.merge(user);

    }

    @Override
    public User optenerUsuarioPorCredenciales(User user) {

        //FORMA DE VERIFICAR LA CONTRASEÑA CON ARGON 2
        //Creamos una consulta de busqueda del correo
        String query = "FROM User WHERE correo = :correo";

        //Pasamos consulta con parametros y optenemos una lista
        List<User> lista = entityManager.createQuery(query)
                .setParameter("correo", user.getCorreo())
                .getResultList();

        //Si la lista esta vacia
        if (lista.isEmpty()) {
            return null;
        }

        //Obtenemos la contraseña de ese correo desde la BD
        String passUser = lista.get(0).getPassword();

        //Creamos un objeto de argon 2
        Argon2 comp = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        //Comparamos las 2 contraseñas
        if (comp.verify(passUser, user.getPassword())) {
            return lista.get(0);//Devolvemos el usuario
        }

        return null;

    }


}

      /* //FORMA TRADICIONAL DE VERIFICAR CONTRASEÑA Y CORREO
        //Creamos una consulta de busqueda
        String query = "FROM User WHERE correo = :correo AND password = :password";

        //Pasamos consulta con parametros y optenemos una lista
        List<User> lista = entityManager.createQuery(query)
                .setParameter("correo", user.getCorreo())
                .setParameter("password", user.getPassword())
                .getResultList();

        //Si la lista esta vacia (No encontrado)
        //Si lo encontro
        return !lista.isEmpty();
           */