## Proyecto CRUD - Gestión de Intentos, Reviews y Ejercicios

### Descripción General
Este proyecto es una aplicación CRUD desarrollada en Java utilizando **Java Swing** para la interfaz gráfica. La aplicación permite a instructores gestionar información sobre ejercicios y revisar intentos de los usuarios registrados. La interfaz incluye una **pantalla de inicio**, un **formulario de login** y una opción de **registro**. Solo los usuarios registrados como instructores tienen acceso al sistema, donde pueden gestionar intentos, reviews y ejercicios.

### Funcionalidades

1. **Pantalla de Inicio**: La pantalla principal ofrece un botón y un enlace para acceder al formulario de login. Es la primera vista del usuario al abrir la aplicación.
   
2. **Login y Registro**: 
   - **Login**: Acceso para usuarios registrados en el sistema.
   - **Registro**: Actualmente, solo los instructores tienen permisos de acceso. En esta vista, un nuevo usuario puede registrarse como instructor para obtener acceso completo a la aplicación.

3. **Gestión para Instructores**:
   - Los instructores pueden acceder a las secciones de intentos, reviews y ejercicios.
   - Cada sección permite al instructor realizar diferentes operaciones (ver, crear, modificar, y eliminar información), según los permisos de cada tipo de dato.

4. **Intentos**:
   - Vista de los intentos realizados por los usuarios.
   - Los instructores pueden ver todos los intentos y revisar los que aún no tienen review.
   - Acciones permitidas: **Modificar** y **Eliminar**.

5. **Reviews**:
   - Los instructores pueden ver todos los reviews y crear nuevos.
   - Cada review es una evaluación de un intento realizado por un usuario.

6. **Ejercicios**:
   - Los instructores pueden ver, crear, modificar y eliminar ejercicios.
   - Esta funcionalidad ayuda a los instructores a gestionar el contenido de ejercicios que los usuarios pueden intentar.

### Estructura de la Aplicación

- **Pantalla de Inicio**: Muestra un botón y un enlace que llevan al formulario de login.
- **Formulario de Login**: Permite a los usuarios registrados (actualmente solo instructores) ingresar al sistema.
- **Formulario de Registro**: Permite el registro de nuevos instructores.
- **Panel Principal del Instructor**: Aquí se encuentran las opciones de:
  - **Intentos**: Visualizar y revisar intentos de usuarios.
  - **Reviews**: Crear y ver reviews de intentos.
  - **Ejercicios**: Crear, ver, modificar y eliminar ejercicios.

### Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **Java Swing**: Librería para la interfaz gráfica de usuario (GUI).
- **JDBC**: Para la conexión con la base de datos.
- **MySQL/SQL Server**: Base de datos utilizada para almacenar los datos de usuarios, intentos, reviews y ejercicios.

### Requisitos Previos

1. **Java JDK**: Version 8 o superior.
2. **Base de datos**: MySQL o SQL Server configurada con tablas de usuarios, intentos, reviews y ejercicios.
3. **IDE recomendado**: IntelliJ IDEA, Eclipse o NetBeans.

### Instalación y Configuración

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/usuario/repo-crud-java.git
   ```

2. **Configurar la base de datos**:
   - Asegúrate de tener creada la base de datos necesaria y las tablas correspondientes.
   - Configura las credenciales de acceso a la base de datos en el archivo de configuración de la aplicación (normalmente `DataAccess.java` o un archivo de propiedades).

3. **Ejecutar el proyecto en el IDE**:
   - Importa el proyecto en tu IDE.
   - Configura la conexión a la base de datos.
   - Ejecuta el archivo principal (`Main.java`) para iniciar la aplicación.

### Uso de la Aplicación

1. **Pantalla de Inicio**: Haz clic en el botón de inicio para acceder al formulario de login.

2. **Login y Registro**:
   - **Registro**: Completa el formulario de registro para crear un nuevo instructor. Solo los instructores tienen acceso completo al sistema.
   - **Login**: Inicia sesión con las credenciales de instructor.

3. **Panel Principal del Instructor**:
   - **Intentos**: Visualiza todos los intentos realizados y pendientes de revisión.
   - **Reviews**: Crea nuevos reviews para los intentos realizados por los usuarios.
   - **Ejercicios**: Agrega nuevos ejercicios, edita los existentes o elimínalos según sea necesario.

### Funcionalidades CRUD para cada Entidad

- **Intentos**:
  - Modificar y eliminar intentos realizados por los usuarios.

- **Reviews**:
  - Crear nuevas reviews para los intentos sin revisar.

- **Ejercicios**:
  - Crear, ver, modificar y eliminar ejercicios de la base de datos.

### Ejemplo de Uso

1. Un usuario accede a la aplicación y selecciona **Iniciar Sesión**.
2. Si es la primera vez, se registra como **Instructor**.
3. Una vez autenticado, el instructor tiene acceso a gestionar **Intentos**, **Reviews**, y **Ejercicios**.
4. En la sección de **Intentos**, puede ver los intentos pendientes y crear reviews.
5. En **Ejercicios**, puede administrar los ejercicios disponibles en el sistema.

### Contribuciones

Las contribuciones son bienvenidas. Para contribuir, realiza un *fork* del repositorio, crea una nueva rama con tus mejoras, y abre un *pull request*.

### Licencia

Este proyecto está bajo la Licencia MIT. Puedes utilizarlo y modificarlo libremente según los términos de la licencia.

---

Este README te proporciona toda la información necesaria para comprender, instalar y utilizar el proyecto CRUD de gestión de intentos, reviews y ejercicios.
