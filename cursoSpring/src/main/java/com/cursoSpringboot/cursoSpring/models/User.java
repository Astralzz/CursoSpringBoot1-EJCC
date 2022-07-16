package com.cursoSpringboot.cursoSpring.models;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


//Nombre de la tabla en la base de datos
@Entity
@Table(name = "usuarios")
public class User {

    @Getter//Getter
    @Setter//Setter
    @Column(name = "nombre")//Columna
    private String Nombre;
    @Getter
    @Setter
    @Column(name = "apellido_P")
    private String Apellido_P;
    @Getter
    @Setter
    @Column(name = "apellido_M")
    private String Apellido_M;
    @Getter
    @Setter
    @Column(name = "correo")
    private String Correo;
    @Getter
    @Setter
    @Column(name = "numero")
    private String Numero;
    @Getter
    @Setter
    @Column(name = "password")
    private String Password;
    @Getter
    @Setter
    @Column(name = "edad")
    private int Edad;
    @Getter
    @Setter
    @Column(name = "sexo")
    private char Sexo;
    @Getter
    @Setter
    @Id//ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Generar id automaticamente
    @Column(name = "id")
    private Long id;


}
