package com.colegio.bean;

import com.colegio.dao.AlumnoDAO;
import com.colegio.dao.AlumnoDAOImpl;
import com.colegio.dao.EstadoEstudianteDAO;
import com.colegio.dao.EstadoEstudianteDAOImpl;
import com.colegio.dao.GradoDAO;
import com.colegio.dao.GradoDAOImpl;
import com.colegio.dao.ResponsableDAO;
import com.colegio.dao.ResponsableDAOImpl;
import com.colegio.modelo.Alumno;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.*;
import java.util.List;

@Named
@ViewScoped
public class AlumnoBean implements Serializable {

    private List<Alumno> alumnos;
    private Alumno selectedAlumno = new Alumno();
    private AlumnoDAO alumnoDAO = new AlumnoDAOImpl();
    private ResponsableDAO  responsableDAO = new ResponsableDAOImpl();
    private GradoDAO gradoDAO = new GradoDAOImpl();
    private EstadoEstudianteDAO estadoEstudianteDAO = new EstadoEstudianteDAOImpl();

    @PostConstruct
    public void init() {
        alumnos = alumnoDAO.listarTodos();
    }

    public void prepareNewAlumno() {
        this.selectedAlumno = new Alumno();
    }

    public void saveAlumno() {
        try {
            if (this.selectedAlumno.getIdAlumno() == 0) {
                alumnoDAO.insertar(this.selectedAlumno);
                alumnos.add(this.selectedAlumno);
            } else {
                alumnoDAO.actualizar(this.selectedAlumno);
            }
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Éxito", "Alumno guardado correctamente"));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Error", "No se pudo guardar el alumno"));
        }
    }

    public void deleteAlumno(Alumno alumno) {
        alumnoDAO.eliminar(alumno.getIdAlumno());
        alumnos.remove(alumno);
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public Alumno getSelectedAlumno() {
        return selectedAlumno;
    }

    public void setSelectedAlumno(Alumno selectedAlumno) {
        this.selectedAlumno = selectedAlumno;
    }

    public AlumnoDAO getAlumnoDAO() {
        return alumnoDAO;
    }

    public void setAlumnoDAO(AlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }
}
