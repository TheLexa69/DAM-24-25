# Spring Boot Relaciones y CRUD

Este proyecto demuestra cómo manejar relaciones en Spring Boot usando JPA con una base de datos en memoria H2.

## 🔗 Relaciones implementadas:

- **@ManyToMany**: Relación entre `Estudiante` y `Curso`.
📚 Un estudiante puede inscribirse en varios cursos, y un curso puede tener varios estudiantes.
📌 Se mapea con @ManyToMany, creando una tabla intermedia estudiante_curso.
🛠 ¿Cómo funciona?
Si un estudiante se inscribe en un curso, se añade un registro en la tabla estudiante_curso.
####
- **@OneToMany - @ManyToOne**: Relación entre `Profesor` y `Curso`.
🎓 Un profesor puede impartir muchos cursos, pero un curso solo tiene un profesor.
📌 Se usa @OneToMany en Profesor y @ManyToOne en Curso.
🛠 ¿Cómo funciona?
Cada curso tiene una clave profesor_id que apunta a un único profesor.
####
- **@OneToOne**: Relación entre `Aula` y `Profesor`.
🏫 Un aula solo tiene un profesor asignado, y un profesor solo puede tener un aula específica.
📌 Se mapea con @OneToOne y @JoinColumn.
🛠 ¿Cómo funciona?
La tabla aula tendrá una columna profesor_id que apunta a un único profesor.
####
- **@OneToOne opcional (0:1)**: Relación entre `Estudiante` y `Direccion`.
🏠 Un estudiante puede tener una dirección, pero no es obligatorio.
📌 Se usa @OneToOne(mappedBy = "direccion") en Direccion.
Si un estudiante tiene una dirección, en la tabla direccion habrá una clave estudiante_id.
####
- 🌍 RestController: API para Estudiantes
El controlador EstudianteController expone las siguientes operaciones:

1️⃣ GET /estudiantes → Lista todos los estudiantes.
####
2️⃣ POST /estudiantes → Crea un nuevo estudiante.
####
3️⃣ PUT /estudiantes/{id} → Actualiza un estudiante.
####
4️⃣ DELETE /estudiantes/{id} → Elimina un estudiante.

##
## 🚀 Funcionalidad
- Conexión con **H2 Database**.
- Validación de campos con `@NotBlank`.
- API RESTful para manejar estudiantes con CRUD (`GET`, `POST`, `PUT`, `DELETE`).

