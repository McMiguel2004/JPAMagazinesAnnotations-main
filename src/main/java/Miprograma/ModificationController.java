package Miprograma;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;
/**
 * Clase que proporciona métodos para modificar datos de monstruos y ubicaciones en la base de datos.
 */
public class ModificationController {


    /**
     * Modifica la descripción de un monstruo por su ID.
     *
     * @param entityManager EntityManager para realizar operaciones de persistencia.
     * @param entityId      ID del monstruo cuya descripción se va a modificar.
     * @param newDescription Nueva descripción del monstruo.
     */

    public static void modifyMonstruoDescriptionById(EntityManager entityManager, Long entityId, String newDescription) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Obtén la entidad Monstruo por su ID
            Monstruo monstruo = entityManager.find(Monstruo.class, entityId);

            if (monstruo != null) {
                // Actualiza la descripción
                monstruo.setDescripcion(newDescription);
                entityManager.merge(monstruo);

                transaction.commit();
                System.out.println("Descripción del monstruo actualizada correctamente.");
            } else {
                System.out.println("Monstruo no encontrado con ID: " + entityId);
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al ejecutar la actualización: " + e.getMessage());
        }
    }


    /**
     * Modifica el nombre de un monstruo por su ID.
     *
     * @param entityManager EntityManager para realizar operaciones de persistencia.
     * @param entityId      ID del monstruo a modificar.
     * @param newName       Nuevo nombre del monstruo.
     */
    public static void modifyMonstruoNameById(EntityManager entityManager, Long entityId, String newName) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Actualiza el nombre del monstruo
            String updateMonstruoNameQuery = "UPDATE Monstruo SET Nombre = :newName WHERE ID = :entityId";
            Query query = entityManager.createQuery(updateMonstruoNameQuery);
            query.setParameter("newName", newName);
            query.setParameter("entityId", entityId);
            query.executeUpdate();

            transaction.commit();

            System.out.println("Nombre del monstruo actualizado correctamente.");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al ejecutar la actualización: " + e.getMessage());
        }
    }

    /**
     * Modifica el nombre de la ubicación de un monstruo por su ID.
     *
     * @param entityManager EntityManager para realizar operaciones de persistencia.
     * @param entityId      ID del monstruo cuya ubicación se va a modificar.
     * @param newName       Nuevo nombre de la ubicación del monstruo.
     */
    public static void modifyLocationNameById(EntityManager entityManager, Long entityId, String newName) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Actualiza el nombre en la tabla Location
            String updateLocationQuery = "UPDATE Location SET Name = :newName WHERE ID = :entityId";
            Query query = entityManager.createQuery(updateLocationQuery);
            query.setParameter("newName", newName);
            query.setParameter("entityId", entityId);
            query.executeUpdate();

            transaction.commit();

            System.out.println("Nombre en la tabla Location actualizado correctamente.");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al ejecutar la actualización: " + e.getMessage());
        }
    }


    /**
     * Modifica el nombre, la descripción y la ubicación de un monstruo por su ID.
     *
     * @param entityManager   EntityManager para interactuar con la base de datos.
     * @param entityId        ID del monstruo a modificar.
     * @param newNombre       Nuevo nombre del monstruo.
     * @param newDescripcion  Nueva descripción del monstruo.
     * @param newLocationName Nuevo nombre de la ubicación del monstruo.
     */
    // Método en ModificationController.java
    public static void modifyMonstruoDetails(EntityManager entityManager, Long entityId, String newNombre, String newDescripcion, String newLocationName) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Actualizar el nombre y la descripción del monstruo
            Monstruo monstruo = entityManager.find(Monstruo.class, entityId);
            if (monstruo != null) {
                monstruo.setNombre(newNombre);
                monstruo.setDescripcion(newDescripcion);
                entityManager.persist(monstruo);
            }

            // Obtener la ubicación actual del monstruo
            Location location = entityManager.find(Location.class, entityId);

            // Actualizar el nombre de la ubicación del monstruo
            if (location != null) {
                location.setName(newLocationName);
                entityManager.persist(location);
            }

            transaction.commit();

            System.out.println("Detalles del monstruo actualizados correctamente.");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al ejecutar la actualización: " + e.getMessage());
        }
    }

    /**
     * Modifica las descripciones de múltiples monstruos según sus ID proporcionados por el usuario.
     *
     * @param entityManager EntityManager para interactuar con la base de datos.
     */
    public static void modifyMultipleMonstruoDescriptionsById(EntityManager entityManager) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("¿Cuántas descripciones deseas modificar? ");
            int numDescriptions = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            for (int i = 0; i < numDescriptions; i++) {
                System.out.print("ID del monstruo " + (i + 1) + ": ");
                Long entityId = scanner.nextLong();
                scanner.nextLine();  // Consumir el salto de línea

                System.out.print("Nueva descripción para el monstruo " + entityId + ": ");
                String newDescription = scanner.nextLine();

                EntityTransaction transaction = entityManager.getTransaction();

                try {
                    if (!transaction.isActive()) {
                        transaction.begin();
                    }

                    // Actualizar la descripción del monstruo
                    String updateDescriptionQuery = "UPDATE Monstruo SET Descripcion = :newDescription WHERE ID = :entityId";
                    Query query = entityManager.createQuery(updateDescriptionQuery);
                    query.setParameter("newDescription", newDescription);
                    query.setParameter("entityId", entityId);
                    query.executeUpdate();

                    transaction.commit();

                    System.out.println("Descripción del monstruo con ID " + entityId + " actualizada correctamente.");
                } catch (Exception e) {
                    if (transaction != null && transaction.isActive()) {
                        transaction.rollback();
                        System.out.println("Rollback de transacción para el monstruo con ID " + entityId);
                    }
                    System.err.println("Error al ejecutar la actualización para el monstruo con ID " + entityId + ": " + e.getMessage());
                } finally {
                    if (transaction != null && transaction.isActive()) {
                        transaction.rollback();
                        System.out.println("Rollback de transacción final para el monstruo con ID " + entityId);
                    }
                }
            }

            System.out.println("Todas las descripciones de monstruos se han actualizado correctamente.");

        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }
}

