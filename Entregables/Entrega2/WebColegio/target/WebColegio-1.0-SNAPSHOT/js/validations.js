function validarRegistroAlumno() {
    var dni = document.getElementById('dni').value.trim();
    var nombre = document.getElementById('nombre').value.trim();
    var apellido = document.getElementById('apellido').value.trim();
    var direccion = document.getElementById('direccion').value.trim();
    var telefono = document.getElementById('telefonoApoderado').value.trim();
    var email = document.getElementById('email').value.trim();

    if (dni === '' || nombre === '' || apellido === '' || direccion === '' || telefono === '' || email === '') {
        alert('Por favor, complete todos los campos obligatorios.');
        return false;
    }

    var dniRegex = /^\d{8}$/;
    if (!dniRegex.test(dni)) {
        alert('Por favor, ingrese un DNI válido (8 dígitos).');
        return false;
    }

    var telefonoRegex = /^\d{9}$/;
    if (!telefonoRegex.test(telefono)) {
        alert('Por favor, ingrese un número de teléfono válido (9 dígitos).');
        return false;
    }

    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert('Por favor, ingrese un email válido.');
        return false;
    }
   
    return true;
}


function validarRegistroDocente() {
    var dni = document.getElementById('dni').value.trim();
    var nombre = document.getElementById('nombre').value.trim();
    var apellido = document.getElementById('apellido').value.trim();
    var especialidad = document.getElementById('especialidad').value.trim();
    var telefono = document.getElementById('telefono').value.trim();
    var email = document.getElementById('email').value.trim();

    if (dni === '' || nombre === '' || apellido === '' || especialidad === '' || telefono === '' || email === '') {
        alert('Por favor, complete todos los campos obligatorios.');
        return false;
    }

    var dniRegex = /^\d{8}$/;
    if (!dniRegex.test(dni)) {
        alert('Por favor, ingrese un DNI válido (8 dígitos).');
        return false;
    }

    var telefonoRegex = /^\d{9}$/;
    if (!telefonoRegex.test(telefono)) {
        alert('Por favor, ingrese un número de teléfono válido (9 dígitos).');
        return false;
    }

    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        alert('Por favor, ingrese un email válido.');
        return false;
    }

    return true; 
}

function validarRegistroMatricula() {
    var alumno = document.getElementById('alumno').value;
    var curso = document.getElementById('grado').value;
    var tipoMatricula = document.getElementById('tipo_matricula').value;
    var fechaMatricula = document.getElementById('fecha_matricula').value;
    var anio = document.getElementById('anio').value;
    var observaciones = document.getElementById('observaciones').value.trim();

    if (alumno === '' || curso === '' || tipoMatricula === '') {
        alert('Por favor, complete todos los campos.');
        return false;
    }

    if (fechaMatricula === '') {
        alert('Por favor, ingrese una fecha de matrícula.');
        return false;
    }

    // Validar que la fecha de matrícula no sea futura
    var hoy = new Date();
    var fechaMat = new Date(fechaMatricula + "T00:00:00"); // Convertir a fecha con la hora a medianoche

    if (fechaMat > hoy) {
        alert('Por favor, ingrese una fecha de matrícula válida (no puede ser futura).');
        return false;
    }

    // Obtener el año actual
    var anioActual = new Date().getFullYear();
   
    if (anio === '' || anio < anioActual) {
        alert('Por favor, ingrese un año válido que no sea menor al año actual.');
        return false;
    }
    
    if (observaciones.length > 150) {
        alert('Las observaciones no pueden exceder los 150 caracteres.');
        return false;
    }

    return true;
}

function validarRegistroCurso() {
    var nombreCurso = document.getElementById('nombre_curso').value.trim();
    var descripcionCurso = document.getElementById('descripcion_curso').value.trim();

    if (nombreCurso === '' || descripcionCurso === '') {
        alert('Por favor, complete todos los campos obligatorios.');
        return false;
    }

    if (nombreCurso.length < 6) {
        alert('El nombre del curso debe tener al menos 5 caracteres.');
        return false;
    }
    
    if (descripcionCurso.length < 11) {
        alert('La descripcion del curso debe tener al menos 10 caracteres.');
        return false;
    }
    
    return true;
}

function validarRegistroCursoDocente() {
    var curso = document.getElementById('curso').value;
    var docente = document.getElementById('docente').value;
    var grado = document.getElementById('grado').value;

  
    if (curso === '' || docente === '' || grado === '') {
        alert('Por favor, complete todos los campos obligatorios.');
        return false;
    }

    return true; 
}