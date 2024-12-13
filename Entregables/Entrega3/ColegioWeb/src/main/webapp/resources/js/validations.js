function validarRegistroMatricula() {
    var alumno = document.getElementById('matriculaDialogForm:alumno').value;
    var grado = document.getElementById('matriculaDialogForm:grado').value;
    var fechaRegistro = document.getElementById('matriculaDialogForm:fechaRegistro_input').value;
    var anio = document.getElementById('matriculaDialogForm:anio').value;
    var observaciones = document.getElementById('matriculaDialogForm:observaciones').value.trim();

    if (alumno === '' || grado === '') {
        alert('Por favor, complete todos los campos obligatorios.');
        return false;
    }

    if (fechaRegistro === '') {
        alert('Por favor, ingrese una fecha de registro.');
        return false;
    }

    var hoy = new Date();
    var fechaReg = new Date(fechaRegistro + "T00:00:00"); // Convertir a fecha

    if (fechaReg > hoy) {
        alert('Por favor, ingrese una fecha de registro válida (no puede ser futura).');
        return false;
    }

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

function validarResponsable() {
    var dni = document.getElementById('responsableDialogForm:dni').value.trim();
    var nombre = document.getElementById('responsableDialogForm:nombre').value.trim();
    var apellido = document.getElementById('responsableDialogForm:apellido').value.trim();
    var sexo = document.getElementById('responsableDialogForm:sexo').value;
    var telefono = document.getElementById('responsableDialogForm:telefono').value.trim();
    var email = document.getElementById('responsableDialogForm:email').value.trim();
    var direccion = document.getElementById('responsableDialogForm:direccion').value.trim();

    if (dni === '') {
        alert('Por favor, ingrese el DNI.');
        return false;
    }
    if (nombre === '') {
        alert('Por favor, ingrese el nombre.');
        return false;
    }
    if (apellido === '') {
        alert('Por favor, ingrese el apellido.');
        return false;
    }
    if (sexo === '') {
        alert('Por favor, seleccione el sexo.');
        return false;
    }

    var dniRegex = /^[0-9]{8}$/;  
    if (!dniRegex.test(dni)) {
        alert('Por favor, ingrese un DNI válido con 8 dígitos.');
        return false;
    }

    var telefonoRegex = /^[0-9]{9}$/;
    if (telefono !== '' && !telefonoRegex.test(telefono)) {
        alert('Por favor, ingrese un número de teléfono válido (9 dígitos).');
        return false;
    }

    var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (email !== '' && !emailRegex.test(email)) {
        alert('Por favor, ingrese un correo electrónico válido.');
        return false;
    }

    if (direccion === '') {
        alert('Por favor, ingrese una dirección.');
        return false;
    }

    return true;
}


function validarRegistroDocente() {
    var dni = document.getElementById("docenteDialogForm:dni").value.trim();
    var nombre = document.getElementById("docenteDialogForm:nombre").value.trim();
    var apellido = document.getElementById("docenteDialogForm:apellido").value.trim();
    var telefono = document.getElementById("docenteDialogForm:telefono").value.trim();
    var email = document.getElementById("docenteDialogForm:email").value.trim();

    if (!/^\d{8}$/.test(dni)) {
        alert("El DNI debe contener 8 dígitos.");
        return false;
    }

    if (nombre.length === 0) {
        alert("El campo Nombre es obligatorio.");
        return false;
    }

    if (apellido.length === 0) {
        alert("El campo Apellido es obligatorio.");
        return false;
    }

    if (!/^\d{9}$/.test(telefono)) {
        alert("El Teléfono debe contener 9 dígitos.");
        return false;
    }
    
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        alert("Por favor, ingrese un Email válido.");
        return false;
    }

    return true;
}

function validarRegistroAlumnos() {
    
    var codigoEstudiante = document.getElementById('alumnoDialogForm:codigoEstudiante').value;
    var dni = document.getElementById('alumnoDialogForm:dni').value;
    var nombre = document.getElementById('alumnoDialogForm:nombre').value;
    var apellido = document.getElementById('alumnoDialogForm:apellido').value;
     
    if (codigoEstudiante.trim() === "") {
        alert("El código de estudiante es obligatorio.");
        return false;
    }

    if (dni.trim() === "") {
        alert("El DNI es obligatorio.");
        return false;
    }

    if (nombre.trim() === "") {
        alert("El nombre es obligatorio.");
        return false;
    }

    if (apellido.trim() === "") {
        alert("El apellido es obligatorio.");
        return false;
    }
    
    var dniRegex = /^[0-9]{8}$/; 
    if (!dniRegex.test(dni)) {
        alert("El DNI debe tener exactamente 8 dígitos numéricos.");
        return false;
    }

    return true;
}
