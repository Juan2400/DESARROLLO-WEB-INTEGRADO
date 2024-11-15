function validarRegistroAlumno() {
    var nombre = document.getElementById('nombre').value.trim();
    var apellido = document.getElementById('apellido').value.trim();
    var fechaNacimiento = document.getElementById('fechaNacimiento').value;
    var direccion = document.getElementById('direccion').value.trim();
    var telefono = document.getElementById('telefono').value.trim();
    var email = document.getElementById('email').value.trim();
    var grado = document.getElementById('idGrado').value;

    if (nombre === '' || apellido === '' || fechaNacimiento === '' || direccion === '' || telefono === '' || email === '' || grado === '') {
        alert('Por favor, complete todos los campos.');
        return false;
    }

    var hoy = new Date();
    var fechaNac = new Date(fechaNacimiento);
    if (fechaNac >= hoy) {
        alert('La fecha de nacimiento debe ser anterior al día de hoy.');
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

    if (grado === '') {
        alert('Por favor, seleccione un grado.');
        return false;
    }

    return true;
}

function validarRegistroDocente() {
    var nombre = document.getElementById('nombre').value.trim();
    var apellido = document.getElementById('apellido').value.trim();
    var especialidad = document.getElementById('especialidad').value.trim();
    var telefono = document.getElementById('telefono').value.trim();
    var email = document.getElementById('email').value.trim();

    if (nombre === '' || apellido === '' || especialidad === '' || telefono === '' || email === '') {
        alert('Por favor, complete todos los campos.');
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
    var alumno = document.getElementById('idAlumno').value;
    var curso = document.getElementById('idCurso').value;
    var fechaMatricula = document.getElementById('fechaMatricula').value;

    if (alumno === '' || curso === '' || fechaMatricula === '') {
        alert('Por favor, complete todos los campos.');
        return false;
    }

    return true;
}

function validarRegistroMatricula() {
    var alumno = document.getElementById('idAlumno').value;
    var curso = document.getElementById('idCurso').value;
    var fechaMatricula = document.getElementById('fechaMatricula').value;
    var tipoMatricula = document.getElementById('tipoMatricula').value;
    var observaciones = document.getElementById('observaciones').value.trim();

    if (alumno === '' || curso === '' || tipoMatricula === '') {
        alert('Por favor, complete todos los campos.');
        return false;
    }

    var hoy = new Date();
    var fechaMat = new Date(fechaMatricula);
    if (fechaMatricula === '' || fechaMat > hoy) {
        alert('Por favor, ingrese una fecha de matrícula válida (no puede ser futura).');
        return false;
    }

    if (observaciones.length > 500) {
        alert('Las observaciones no pueden exceder los 500 caracteres.');
        return false;
    }

    return true; 
}

function validarRegistroCurso() {
    var nombreCurso = document.getElementById('nombreCurso').value.trim();
    var descripcionCurso = document.getElementById('descripcionCurso').value.trim();
    var docente = document.getElementById('idDocente').value;
    var fechaInicio = document.getElementById('fechaInicio').value;
    var fechaFin = document.getElementById('fechaFin').value;

    if (nombreCurso === '' || descripcionCurso === '' || docente === '' || fechaInicio === '' || fechaFin === '') {
        alert('Por favor, complete todos los campos.');
        return false;
    }

    var fechaInicioDate = new Date(fechaInicio);
    var fechaFinDate = new Date(fechaFin);
    if (fechaFinDate <= fechaInicioDate) {
        alert('La fecha de fin debe ser posterior a la fecha de inicio.');
        return false;
    }

    return true;
}
