package Miprograma;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Clase que proporciona métodos para eliminar monstruos de la base de datos.
 */
public class DeleteController {

    /**
     * Elimina un monstruo de la base de datos por su ID.
     *
     * @param entityManager El EntityManager utilizado para realizar operaciones de persistencia.
     * @param entityId      El ID del monstruo que se va a eliminar.
     */
    public static void deleteMonstruoById(EntityManager entityManager, Long entityId) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Encuentra el monstruo por su ID
            Monstruo monstruo = entityManager.find(Monstruo.class, entityId);

            // Verifica si el monstruo existe antes de eliminarlo
            if (monstruo != null) {
                // Elimina el monstruo de la base de datos
                entityManager.remove(monstruo);
                System.out.println("Monstruo con ID " + entityId + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró el monstruo con ID " + entityId);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al eliminar el monstruo con ID " + entityId + ": " + e.getMessage());
        }
    }

    /**
     * Elimina varios monstruos de la base de datos por sus IDs.
     *
     * @param entityManager El EntityManager utilizado para realizar operaciones de persistencia.
     * @param entityIds     La lista de IDs de los monstruos que se van a eliminar.
     */
    public static void deleteMonstruosByIds(EntityManager entityManager, List<Long> entityIds) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            for (Long entityId : entityIds) {
                // Encuentra el monstruo por su ID
                Monstruo monstruo = entityManager.find(Monstruo.class, entityId);

                // Verifica si el monstruo existe antes de eliminarlo
                if (monstruo != null) {
                    // Elimina el monstruo de la base de datos
                    entityManager.remove(monstruo);
                    System.out.println("Monstruo con ID " + entityId + " eliminado correctamente.");
                } else {
                    System.out.println("No se encontró el monstruo con ID " + entityId);
                }
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al eliminar los monstruos: " + e.getMessage());
        }
    }
}
