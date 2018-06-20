/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author HugoJr. <Hugo Rivera at 00161417@uca.edu.sv>
 */
public class Estudiante {
    private int carnet;
    private String nombres;
    private String apellidos;
    private String universidad;
    private boolean estado;
    
    public Estudiante(){}

    public Estudiante(int carnet, String nombres, String apellidos, String universidad, boolean estado) {
        this.carnet = carnet;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.universidad = universidad;
        this.estado = estado;
    }

    public Estudiante(String nombres, String apellidos, String universidad, boolean estado) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.universidad = universidad;
        this.estado = estado;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
