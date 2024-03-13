package Miprograma;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Clase que proporciona métodos para realizar consultas select en la base de datos.
 */
public class SelectController {

    // Consulta para seleccionar la ID y el nombre de los monstruos por especie
    private static final String SELECT_MONSTRUOS_BY_ESPECIE =
            "SELECT m.id, m.nombre FROM Monstruo m WHERE m.speciesName = :especie";

    /**
     * Método para seleccionar la ID y el nombre de los monstruos por especie utilizando JPA.
     *
     * @param entityManager EntityManager para realizar operaciones de persistencia.
     * @param especie       Especie de monstruos a buscar.
     */
    public static void selectMonstruosByEspecie(EntityManager entityManager, String especie) {
        try {
            Query query = entityManager.createQuery(SELECT_MONSTRUOS_BY_ESPECIE, Object[].class);
            query.setParameter("especie", especie);

            List<Object[]> results = query.getResultList();

            for (Object[] result : results) {
                Long id = (Long) result[0];
                String nombre = (String) result[1];

                System.out.println("ID: " + id + ", Nombre: " + nombre);
            }
        } catch (Exception e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    // Consulta para seleccionar elementos de la tabla Elements que contienen un texto específico
    private static final String SELECT_ELEMENTS_CONTAINING_TEXT_WITH_MONSTER_NAME =
            "SELECT e.id, e.elementName, e.monstruo.id, e.monstruo.nombre " +
                    "FROM Elements e " +
                    "WHERE e.elementName LIKE :searchText";

    /**
     * Método para seleccionar elementos de la tabla Elements que contienen un texto específico utilizando JPA.
     *
     * @param entityManager Sesión de JPA.
     * @param searchText    Texto a buscar en los elementos.
     */
    public static void selectElementsContainingText(EntityManager entityManager, String searchText) {
        try {
            Query query = entityManager.createQuery(SELECT_ELEMENTS_CONTAINING_TEXT_WITH_MONSTER_NAME);
            query.setParameter("searchText", "%" + searchText + "%");

            List<Object[]> results = query.getResultList();

            for (Object[] result : results) {
                Long id = (Long) result[0];
                String elementName = (String) result[1];
                Long monstruoId = (Long) result[2];
                String monstruoNombre = (String) result[3];

                System.out.println("ID: " + id + ", Element Name: " + elementName +
                        ", Monstruo ID: " + monstruoId + ", Monstruo Nombre: " + monstruoNombre);
            }
        } catch (Exception e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }
}
