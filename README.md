# API de Gestión de Eventos y Asistentes
## Contenedor de Docker
docker run -d --name mysql-examen -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=sisdb_examen -p 3306:3306 mysql:5.7                                                                                                                                   
## Asistentes

### 1. **POST /api/asistentes**
**Descripción**: Crear un nuevo asistente.
![image](https://github.com/user-attachments/assets/a7a8d183-430c-4b9d-a893-e06cd7eff89b)


### 2. **GET /api/asistentes**
**Descripción**: Obtener todos los asistentes.
![image](https://github.com/user-attachments/assets/4abdfba7-8774-451b-aab3-f623a80c5f0b)

### 3. **PUT /api/asistentes/{id}**
**Descripción**: Actualizar la información de un asistente.
![image](https://github.com/user-attachments/assets/77d4bbcd-13c7-45b3-b04f-4db30c25af17)


### 4. **DELETE /api/asistentes/{id}**
**Descripción**: Eliminar un asistente.
![image](https://github.com/user-attachments/assets/be200e7f-a104-4717-b2d0-302d88ba28f3)

---

## Eventos

### 1. **POST /api/eventos**
**Descripción**: Crear un nuevo evento.
![image](https://github.com/user-attachments/assets/29076bb9-ec7f-4e19-bfa7-76e6046cd2d1)

### 2. **GET /api/eventos**
**Descripción**: Obtener todos los eventos.
![image](https://github.com/user-attachments/assets/95331958-5367-4162-b4f4-da9895fe53e9)

### 3. **PUT /api/eventos/{id}**
**Descripción**: Actualizar la información de un evento.
![image](https://github.com/user-attachments/assets/a5af9419-83ae-42de-993f-471d545c9a84)

### 4. **DELETE /api/eventos/{id}**
**Descripción**: Eliminar un evento.
![image](https://github.com/user-attachments/assets/ba36ffe2-f91d-4680-a945-c327d545928c)
![image](https://github.com/user-attachments/assets/a00718f9-e2a8-46bf-8eb7-850c3f99b057)

---

## Asignar Eventos a Asistentes

### 1. **POST /api/eventos/{eventoId}/asistente/{usuarioId}**
**Descripción**: Asignar un asistente a un evento.
![image](https://github.com/user-attachments/assets/da36385d-5fc0-4c95-a606-a997b657a94f)


### 2. **GET /api/eventos/{id}/asistentes**
**Descripción**: Obtener la lista de asistentes para un evento.

### 3. **PUT /api/eventos/{eventoId}/asistente/{usuarioId}**
**Descripción**: Actualizar la asignación de un asistente a un evento.

### 4. **DELETE /api/eventos/{eventoId}/asistente/{usuarioId}**
**Descripción**: Eliminar la asignación de un asistente a un evento.

---
