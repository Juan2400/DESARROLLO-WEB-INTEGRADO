package com.colegio.bean;

import com.colegio.dao.CursoDAO;
import com.colegio.dao.CursoDAOImpl;
import com.colegio.dao.EspecialidadDAO;
import com.colegio.dao.EspecialidadDAOImpl;
import com.colegio.modelo.Curso;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CursoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private CursoDAO cursoDAO;  // Instancia manual del DAO
    private EspecialidadDAO especialidadDAO;  // Instancia manual del DAO
    
    private List<Curso> cursos;
    private Curso selectedCurso = new Curso();  // Curso seleccionado para edición o creación

    @PostConstruct
    public void init() {
        // Instanciamos los DAOs manualmente
        cursoDAO = new CursoDAOImpl();
        especialidadDAO = new EspecialidadDAOImpl();
        
        loadCursos();  // Cargar la lista de cursos al inicio
    }

    private void loadCursos() {
        cursos = cursoDAO.listarTodos();  // Obtener todos los cursos desde el DAO
    }

    public void prepareNewCurso() {
        this.selectedCurso = new Curso();  // Preparar un nuevo curso para creación
    }

    public void saveCurso() {
        try {
            if (selectedCurso.getIdCurso() == 0) {
                cursoDAO.insertar(selectedCurso);  // Insertar un nuevo curso
                cursos.add(this.selectedCurso);  // Agregar el curso a la lista
                showMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Curso registrado correctamente");
            } else {
                cursoDAO.actualizar(selectedCurso);  // Actualizar los datos del curso existente
                loadCursos();  // Recargar la lista de cursos
                showMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Curso actualizado correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el curso");
        }
    }

    public void deleteCurso(Curso curso) {
        try {
            cursoDAO.eliminar(curso.getIdCurso());  // Eliminar el curso
            cursos.remove(curso);  // Eliminar el curso de la lista
            showMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Curso eliminado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el curso");
        }
    }

    private void showMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    // Métodos Getter y Setter
    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getSelectedCurso() {
        return selectedCurso;
    }

    public void setSelectedCurso(Curso selectedCurso) {
        this.selectedCurso = selectedCurso;
    }

    public CursoDAO getCursoDAO() {
        return cursoDAO;
    }

    public void setCursoDAO(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    public EspecialidadDAO getEspecialidadDAO() {
        return especialidadDAO;
    }

    public void setEspecialidadDAO(EspecialidadDAO especialidadDAO) {
        this.especialidadDAO = especialidadDAO;
    }
}
