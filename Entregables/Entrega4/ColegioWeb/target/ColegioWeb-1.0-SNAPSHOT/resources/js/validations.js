function validarRegistroMatricula() {
    // Obtener valores de los campos del formulario
    var alumno = document.getElementById('matriculaDialogForm:alumno').value;
    var grado = document.getElementById('matriculaDialogForm:grado').value;
    var fechaRegistro = document.getElementById('matriculaDialogForm:fechaRegistro_input').value;
    var anio = document.getElementById('matriculaDialogForm:anio').value;
    var observaciones = document.getElementById('matriculaDialogForm:observaciones').value.trim();

    // Validar que los campos obligatorios no estén vacíos
    if (alumno === '' || grado === '') {
        alert('Por favor, complete todos los campos obligatorios.');
        return false;
    }

    // Validar que la fecha de registro no esté vacía
    if (fechaRegistro === '') {
        alert('Por favor, ingrese una fecha de registro.');
        return false;
    }

    // Validar que la fecha de registro no sea futura
    var hoy = new Date();
    var fechaReg = new Date(fechaRegistro + "T00:00:00"); // Convertir a fecha

    if (fechaReg > hoy) {
        alert('Por favor, ingrese una fecha de registro válida (no puede ser futura).');
        return false;
    }

    // Validar que el año no sea menor al actual
    var anioActual = new Date().getFullYear();
    if (anio === '' || anio < anioActual) {
        alert('Por favor, ingrese un año válido que no sea menor al año actual.');
        return false;
    }

    // Validar que las observaciones no excedan el límite de caracteres
    if (observaciones.length > 150) {
        alert('Las observaciones no pueden exceder los 150 caracteres.');
        return false;
    }

    // Si todas las validaciones son correctas
    return true;
}

function validarResponsable() {
    // Obtener los valores de los campos del formulario
    var dni = document.getElementById('responsableDialogForm:dni').value.trim();
    var nombre = document.getElementById('responsableDialogForm:nombre').value.trim();
    var apellido = document.getElementById('responsableDialogForm:apellido').value.trim();
    var sexo = document.getElementById('responsableDialogForm:sexo').value;
    var telefono = document.getElementById('responsableDialogForm:telefono').value.trim();
    var email = document.getElementById('responsableDialogForm:email').value.trim();
    var direccion = document.getElementById('responsableDialogForm:direccion').value.trim();

    // Validar que los campos obligatorios no estén vacíos
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

    // Validar el DNI (8 dígitos)
    var dniRegex = /^[0-9]{8}$/;  // Validación para 8 dígitos numéricos
    if (!dniRegex.test(dni)) {
        alert('Por favor, ingrese un DNI válido con 8 dígitos.');
        return false;
    }

    // Validar el formato del teléfono (opcional, adaptarlo según el formato necesario)
    var telefonoRegex = /^[0-9]{9}$/; // Suponiendo que el teléfono debe tener 9 dígitos
    if (telefono !== '' && !telefonoRegex.test(telefono)) {
        alert('Por favor, ingrese un número de teléfono válido (9 dígitos).');
        return false;
    }

    // Validar el formato del email
    var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (email !== '' && !emailRegex.test(email)) {
        alert('Por favor, ingrese un correo electrónico válido.');
        return false;
    }

    // Validar que la dirección no esté vacía (opcional)
    if (direccion === '') {
        alert('Por favor, ingrese una dirección.');
        return false;
    }

    // Si todas las validaciones son correctas
    return true;
}


function validarRegistroDocente() {
    
    // Obtener los valores de los campos
    var dni = document.getElementById("docenteDialogForm:dni").value.trim();
    var nombre = document.getElementById("docenteDialogForm:nombre").value.trim();
    var apellido = document.getElementById("docenteDialogForm:apellido").value.trim();
    var telefono = document.getElementById("docenteDialogForm:telefono").value.trim();
    var email = document.getElementById("docenteDialogForm:email").value.trim();


    // Validación de DNI
    if (!/^\d{8}$/.test(dni)) {
        alert("El DNI debe contener 8 dígitos.");
        return false;
    }

    // Validación de Nombre
    if (nombre.length === 0) {
        alert("El campo Nombre es obligatorio.");
        return false;
    }

    // Validación de Apellido
    if (apellido.length === 0) {
        alert("El campo Apellido es obligatorio.");
        return false;
    }

    // Validación de Teléfono
    if (!/^\d{9}$/.test(telefono)) {
        alert("El Teléfono debe contener 9 dígitos.");
        return false;
    }
    
    // Validación de Email
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        alert("Por favor, ingrese un Email válido.");
        return false;
    }

   
    // Si todas las validaciones son correctas
    return true;
}

function validarRegistroAlumnos() {
    
    var codigoEstudiante = document.getElementById('alumnoDialogForm:codigoEstudiante').value;
    var dni = document.getElementById('alumnoDialogForm:dni').value;
    var nombre = document.getElementById('alumnoDialogForm:nombre').value;
    var apellido = document.getElementById('alumnoDialogForm:apellido').value;
     
    // Validación de campos obligatorios
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
    
    // Validación del DNI para que tenga exactamente 8 dígitos
    var dniRegex = /^[0-9]{8}$/;  // Verifica que tenga exactamente 8 dígitos numéricos
    if (!dniRegex.test(dni)) {
        alert("El DNI debe tener exactamente 8 dígitos numéricos.");
        return false;
    }

    // Si todas las validaciones son correctas
    return true;
}
