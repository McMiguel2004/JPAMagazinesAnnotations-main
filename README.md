Monster Hunter Database
Este proyecto es una aplicación de base de datos para gestionar información sobre monstruos del juego Monster Hunter. Permite crear, leer, actualizar y eliminar datos relacionados con monstruos, elementos y ubicaciones en el juego.

Requisitos
Para ejecutar este proyecto, necesitarás lo siguiente:

Java Development Kit (JDK) 8 o superior
Apache Maven
Base de datos PostgreSQL (u otro compatible con JPA)
IntelliJ IDEA (u otro IDE compatible con Java)
Configuración de la Base de Datos
Antes de ejecutar la aplicación, asegúrate de configurar la base de datos correctamente:

Crea una base de datos en tu servidor PostgreSQL.
Abre el archivo persistence.xml en el directorio src/main/resources/META-INF.
Edita la URL de conexión, el nombre de usuario y la contraseña para que coincidan con tu configuración de base de datos:
xml
Copy code
<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/nombre_base_de_datos"/>
<property name="javax.persistence.jdbc.user" value="usuario"/>
<property name="javax.persistence.jdbc.password" value="contraseña"/>
Ejecución
Para ejecutar la aplicación en IntelliJ IDEA, sigue estos pasos:

Clona este repositorio en tu máquina local.
Abre IntelliJ IDEA y selecciona "Open" desde el menú principal.
Navega hasta el directorio del proyecto y selecciona la carpeta raíz.
Espera a que IntelliJ importe el proyecto.
Haz clic derecho en el archivo Main.java en el directorio src/main/java/Miprograma y selecciona "Run Main.main()".
Uso
Una vez que la aplicación esté en funcionamiento, sigue las instrucciones en la consola para interactuar con la base de datos. Puedes crear, leer, actualizar y eliminar monstruos, elementos y ubicaciones según sea necesario.

Nota Importante
Recuerda que al descargar el proyecto y configurar la base de datos, debes cambiar los datos de conexión en el archivo persistence.xml para que coincidan con tu entorno local. Si no se configura correctamente, la aplicación no podrá conectarse a la base de datos.






