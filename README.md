# Monster Database Management System (MDMS)

## Descripción
MDMS es un sistema de gestión de base de datos para almacenar información relacionada con monstruos. El sistema permite realizar diversas operaciones como crear tablas, poblar la base de datos desde archivos XML, consultar información, modificar datos y eliminar registros.

## Funcionalidades
1. **Borrar tablas e información**: Elimina todas las tablas existentes junto con la información almacenada.
2. **Crear tablas**: Crea las tablas necesarias para almacenar datos de monstruos, elementos y ubicaciones.
3. **Poblar tablas desde archivos**: Permite cargar información sobre monstruos desde archivos XML y almacenarla en la base de datos.
4. **Consultar cualquier monstruo por especie**: Busca y muestra información de monstruos que pertenecen a una especie específica.
5. **Consultar cualquier monstruo que contenga un texto**: Busca y muestra información de monstruos cuyos nombres contienen un texto específico.
6. **Modificar descripción de un monstruo por ID**: Actualiza la descripción de un monstruo específico utilizando su identificador único.
7. **Modificar nombre de un monstruo por ID**: Modifica el nombre de un monstruo específico mediante su identificador único.
8. **Modificar el nombre de la localización de un monstruo por ID**: Cambia el nombre de la ubicación de un monstruo utilizando su identificador único.
9. **Modificar por ID el nombre, descripción y localización de un monstruo**: Realiza modificaciones simultáneas en el nombre, descripción y ubicación de un monstruo específico.
10. **Eliminar un monstruo por ID**: Elimina un monstruo de la base de datos según su identificador único.
11. **Eliminar varios monstruos por ID**: Permite la eliminación de múltiples monstruos proporcionando sus identificadores.

## Uso
1. **Ejecutar la aplicación**: Ejecutar la aplicación en un entorno Java compatible.
2. **Interactuar con el menú**: Se presenta un menú interactivo que permite seleccionar diversas operaciones. Ingrese el número correspondiente a la operación deseada.
3. **Seguir las instrucciones**: Siga las instrucciones en pantalla para proporcionar información adicional, como identificadores, nombres, descripciones, etc.
4. **Visualizar resultados**: Los resultados de las operaciones se mostrarán en la consola.
5. **Salir del programa**: Seleccione la opción '0' para salir del programa.

## Requisitos
- Java Runtime Environment (JRE)
- Conexión a una base de datos compatible

## Notas

El sistema está diseñado para interactuar con una base de datos que cumple con el esquema definido en el archivo de configuración de Hibernate.

La población de datos desde archivos XML sigue el formato esperado, incluyendo información sobre monstruos, especies, elementos y ubicaciones.


## Configuración de la conexión a la base de datos
Antes de ejecutar la aplicación, asegúrese de configurar la conexión a la base de datos en el archivo `persistence.xml`. Siga estos pasos:

1. Cree una base de datos en su servidor de base de datos (por ejemplo, PostgreSQL).
2. Abra el archivo `persistence.xml` ubicado en el directorio `src/main/resources/META-INF`.
3. Edite la URL de conexión, el nombre de usuario y la contraseña para que coincidan con su configuración de base de datos.

Ejemplo de configuración en `persistence.xml`:

```xml
<persistence-unit name="JPAMagazines" transaction-type="RESOURCE_LOCAL">
    <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
    <properties>
        <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/nombre_basedatos"/>
        <property name="javax.persistence.jdbc.user" value="usuario_basedatos"/>
        <property name="javax.persistence.jdbc.password" value="contraseña_basedatos"/>
        <!-- Otras propiedades de Hibernate -->
    </properties>
</persistence-unit>

