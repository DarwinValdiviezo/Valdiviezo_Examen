# Evaluación Conjunta del Segundo Parcial efesita

Este proyecto forma parte de la **Evaluación Conjunta del Segundo Parcial** y consiste en la implementación de un sistema basado en microservicios para la **gestión de Doctores y Pacientes**. Incluye funcionalidades clave como la asignación, desasignación y listado de Dcotores junto a sus pacientes y listado de pacientes, además de persistencia en una base de datos MySQL alojada en Docker.

---

## 🔧 **Tecnologías Utilizadas**
- **Java** con **Spring Boot**: Framework para la construcción de microservicios.
- **Hibernate (JPA)**: Manejo de la capa de persistencia.
- **MySQL**: Base de datos relacional.
- **Docker**: Contenedor para la base de datos MySQL.
- **RestTemplate** y **Feign Client**: Comunicación entre microservicios.
- **Maven**: Gestión de dependencias.

---

## ⚙️ **Funcionalidades del Sistema**

### 🟢 Microservicio `micro-doctores`
- **CRUD de Doctor**: Crear, listar, actualizar y eliminar doctores.
- **Gestión de relaciones muchos a muchos**:
  - Asignar doctores a un paciente.
  - Desasignar doctores a un paciente.
  - Listar los pacientes de un doctor.
  - Listar los doctores.
- **Persistencia en MySQL**: Los datos se almacenan de forma estructurada y pueden ser consultados posteriormente.

### 🔵 Microservicio `micro-pacientes`
- **Gestión de pacientes**: CRUD básico para pacientes.
- **Comunicación entre microservicios**: Los pacientes se integran con el microservicio `micro-doctores`.

---

## 📁 **Estructura del Proyecto**

### `micro-doctores`
- `controller`: Controladores REST para manejar las operaciones de doctores, pacientes, y asignaciones.
- `service`: Lógica de negocio para la gestión de doctores y pacientes.
- `model.entity`: Entidades de la base de datos (`Doctor`, `Paciente`, `PacienteDoctor`).
- `repository`: Interfaces JPA para interactuar con MySQL.
- `clients`: Cliente Feign para la comunicación con `micro-pacientes`.

### `micro-pacientes`
- Estructura similar a `micro-doctores`, con funcionalidades específicas para la gestión de pacientes.

---


## 🚀 **Instrucciones de Ejecución**

### 1. **Configurar MySQL con Docker**
Se ejecuta el siguiente comando para crear un contenedor MySQL:

```bash
docker run -d \
--name mysql-container \
-e MYSQL_ROOT_PASSWORD=abcd \
-e MYSQL_DATABASE=sisdb_examen \
-e MYSQL_USER=root123 \
-e MYSQL_PASSWORD=abcd \
-p 3307:3306 \
mysql:8.0
```

La base de datos la puedes abrir en MySQL Workbench con los parámetros de conexión de arriba, y se verá así:
<div align="center">
<img width="600" alt="bd" src="https://github.com/DarwinValdiviezo/Valdiviezo_Examen/blob/main/Capturas/workbench.png" />
</div>

---


**micro-doctor, y pacientes define su *application.properties* de esta forma:**
<div align="center">
<img width="600" alt="micro-roles" src="https://github.com/DarwinValdiviezo/Valdiviezo_Examen/blob/main/Capturas/application.png" />
</div>

### 2. **Levantar los Microservicios**

1. Clona el repositorio y navega al directorio raíz.
2. En cada microservicio (`micro-doctores` y `micro-pacientes`), compila y ejecuta:
   ```bash
   mvn spring-boot:run
    ```

3. Los microservicios estarán disponibles en:
  - `micro-doctor`: http://localhost:8001/api/doctores
  - `micro-paciente`: http://localhost:8002/api/pacientes

### 3. **Pruebas de Funcionalidad**

### PACIENTES

- Listar los pacientes disponibles:
  - **GET:** http://localhost:8002/api/pacientes

<div align="center">
<img width="600" alt="ver_usuarios" src="https://github.com/DarwinValdiviezo/Valdiviezo_Examen/blob/main/Capturas/get-pac.png" />
</div>
<br>

- Crear un paciente:
  - **POST:** http://localhost:8002/api/pacientes
    ```json
    {
    "nombre": "Ana",
    "apellido": "Martinez",
    "email": "ana.martinez@ejemplo.com",
    "fechaNacimiento": "1990-05-15",
    "telefono": "0987654323"
    }
    ```
<div align="center">
<img width="600" alt="crear_user" src="https://github.com/DarwinValdiviezo/Valdiviezo_Examen/blob/main/Capturas/post-pac.png" />
</div>
<br>
  
- Eliminar un paciente:
  - **DELETE:** http://localhost:8002/api/pacientes/{pacienteId}

<div align="center">
<img width="600" alt="elim_user" src="https://github.com/DarwinValdiviezo/Valdiviezo_Examen/blob/main/Capturas/delete-pac.png" />
</div>
<br>

### DOCTORES

- Listar los doctores disponibles, y los pacientes que están asignados:
  - **GET:** http://localhost:8003/api/doctores

<div align="center">
<img width="600" alt="ver_rol" src="https://github.com/DarwinValdiviezo/Valdiviezo_Examen/blob/main/Capturas/get-doc.png" />
</div>
<br>

- Crear un doctor:
  - **POST:** http://localhost:8003/api/doctores
    ```json
    {
      "nombre": "Doctor Patricio",
      "apellido": "Real",
      "email": "doctorrealpatricio@ejemplo.com",
      "licencia": "98588",
      "especialidad": "Cardiología",
      "telefono": "0987654511"
    }

    ```

<div align="center">
<img width="600" alt="crear_rol" src="https://github.com/DarwinValdiviezo/Valdiviezo_Examen/blob/main/Capturas/1.png" />
</div>
<br>

- Eliminar un doctor:
  - **DELETE:** http://localhost:8001/api/doctores/{doctorId}
    
<div align="center">
<img width="600" alt="elim_rol" src="https://github.com/DarwinValdiviezo/Valdiviezo_Examen/blob/main/Capturas/delete-doc.png" />
</div>
<br>

- Asignar un paciente a un doctor:
  - **POST:** http://localhost:8001/api/doctores/{doctorId}/asignar-paciente/{pacienteId}
    ```json
    {
     "id": 3
    }
    ```

<div align="center">
<img width="600" alt="asign_rol" src="https://github.com/DarwinValdiviezo/Valdiviezo_Examen/blob/main/Capturas/post-asig.png" />
</div>
<br>

- Revocar paciente a un doctor:
  - **DELETE:** http://localhost:8001/api/doctores/{doctorId}/desmatricular-paciente/{pacienteId}

<div align="center">
<img width="600" alt="revocar_rol" src="https://github.com/DarwinValdiviezo/Valdiviezo_Examen/blob/main/Capturas/delete-asig.png" />
</div>
<br>

---

## 💻 Tecnologías Utilizadas
- Spring Boot: Para la creación de los microservicios.
- Spring Security: Para la gestión de autenticación y autorización.
- Spring Data JPA: Para la gestión de acceso a la base de datos.
- MySQL: Base de datos relacional.

## Licencia
Este proyecto está bajo la licencia MIT. 

---






