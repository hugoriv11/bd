/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.Metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Estudiante;

/**
 *Nombre de la base de datos: inscripciones
 * Nombre de la tabla: 
 * @author HugoJr. <Hugo Rivera at 00161417@uca.edu.sv>
 */
public class EstudianteDao implements Metodos<Estudiante>{
    private static final String SQL_INSERT = "INSERT INTO estudiante (carnet, nombres, apellidos, universidad, estado) VALUES(?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE estudiante SET estado = ? WHERE carnet = ?";
    private static final String SQL_DELETE = "DELETE FROM estudiante WHERE carnet = ?";
    private static final String SQL_READ = "SELECT * FROM estudiante WHERE carnet = ?";
    private static final String SQL_READALL = "SELECT * FROM estudiante";
    private static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Estudiante g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setInt(1, g.getCarnet());
            ps.setString(2, g.getNombres());
            ps.setString(3, g.getApellidos());
            ps.setString(4, g.getUniversidad());
            ps.setBoolean(5, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            con.cerrarConexion();
        }
        return false;    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Estudiante c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getCarnet());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setBoolean(1, c.getEstado());
            ps.setInt(2, c.getCarnet());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Estudiante read(Object key) {
        Estudiante estu = null;
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                estu = new Estudiante(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getBoolean(5));//constructor
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return estu;
    }

    @Override
    public ArrayList<Estudiante> readAll() {
        ArrayList<Estudiante> all = new ArrayList();
        Statement s;
        ResultSet rs;

        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);

            while (rs.next()) {
                all.add(new Estudiante(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getBoolean(5)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }

    
    
}
