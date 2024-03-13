package Miprograma;
import javax.persistence.*;
import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que contiene el método main y las funciones para interactuar con la base de datos.
 */

public class Main {

    public static EntityManagerFactory createEntityManagerFactory() {
        EntityManagerFactory emf = null;

        try {
            emf = Persistence.createEntityManagerFactory("JPAMagazines");
        } catch (PersistenceException ex) {
            System.err.println("Failed to create EntityManagerFactory. " + ex.getMessage());
            ex.printStackTrace();
            // Puedes manejar la excepción de manera específica aquí, si es necesario.
        } catch (Throwable ex) {
            System.err.println("Unexpected error while creating EntityManagerFactory. " + ex.getMessage());
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }

        return emf;
    }


    private static void consultarMonstruosPorEspecie(EntityManager entityManager) {
        System.out.println("");

        System.out.println("Posibles especies: Brute Wyverns - Elder Dragons - Flying Wyverns - Piscine Wyverns - Bird Wyverns - Fanged Wyverns");
        System.out.print("Introduce la especie para consultar monstruos: ");
        Scanner scanner = new Scanner(System.in);
        String especie = scanner.nextLine();

        // Llamar al método correspondiente en SelectController
        SelectController.selectMonstruosByEspecie(entityManager, especie);
    }

    public static void consultElementsContainingText(EntityManager entityManager) {
        System.out.println("");
        System.out.println("Posibles elementos: Dragonblight - Iceblight - Thunderblight - Fireblight - Waterblight");
        System.out.println("Posibles estados: Health decreases over time - Inflicts Poison Status on the target, slowly draining their health - Increased Stamina usage - Reduced Stamina recovery - Weapon Elemental and Status Damage nullified - Health decreases over time");
        System.out.println("");
        System.out.print("Introduce el texto para consultar en la tabla elements: ");
        Scanner scanner = new Scanner(System.in);
        String searchText = scanner.nextLine();

        // Llamar al método correspondiente en SelectController
        SelectController.selectElementsContainingText(entityManager, searchText);
    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        EntityManagerFactory entityManagerFactory = createEntityManagerFactory();

        // Utiliza la fábrica de entidades para crear un EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Resto del código...

        while (!exit) {
            System.out.println("1. Borrar tablas e información");
            System.out.println("2. Crear tablas");
            System.out.println("3. Poblar tablas desde archivos");
            System.out.println("");

            System.out.println("4. Consultar cualquier monstruo de la especie (CONDICIóN)");
            System.out.println("5. Consultar cualquier monstruo que contenga la  (CONDICIóN)");
            System.out.println("");

            System.out.println("6. Modificar descripción de un monstruo por ID");
            System.out.println("7. Modificar nombre de un monstruo por ID");
            System.out.println("8. Modificar el nombre de la localización de un monstruo por ID");
            System.out.println("9. Modificar por id el nombre, descripción y localización de un monstruo");
            System.out.println("10. Modificar varias descripciónes por id");
            System.out.println("");

            System.out.println("11. Eliminar un monstruo por id");
            System.out.println("12. Eliminar varios monstruos por id");

            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (choice) {
                case 1:
                    entityManager.getTransaction().begin();
                    TablaController.dropTables(entityManager);
                    entityManager.getTransaction().commit();
                    System.out.println("Tablas e información borradas.");
                    break;
                case 2:
                    entityManager.getTransaction().begin();
                    TablaController.createTables(entityManager);
                    entityManager.getTransaction().commit();
                    System.out.println("Tablas creadas.");
                    break;
                case 3:
                    entityManager.getTransaction().begin();
                    TablaController.populateFromXML(entityManager, "monstruos.xml");
                    entityManager.getTransaction().commit();
                    System.out.println("Tablas pobladas desde archivos.");
                    break;

                case 4:
                    entityManager.getTransaction().begin();
                    consultarMonstruosPorEspecie(entityManager);
                    entityManager.getTransaction().commit();
                    break;


                case 5:
                    entityManager.getTransaction().begin();
                    consultElementsContainingText(entityManager);
                    entityManager.getTransaction().commit();
                    break;
                case 6:
                    System.out.print("Introduce el ID del monstruo que quieres modificar: ");
                    Long entityId = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    System.out.print("Introduce la nueva descripción del monstruo: ");
                    String newDescription = scanner.nextLine();

                    ModificationController.modifyMonstruoDescriptionById(entityManager, entityId, newDescription);
                    break;


                case 7:
                    System.out.print("Introduce el ID del monstruo que quieres modificar: ");
                    Long entityIdForName = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    System.out.print("Introduce el nuevo nombre del monstruo: ");
                    String newName = scanner.nextLine();
                    ModificationController.modifyMonstruoNameById(entityManager, entityIdForName, newName);
                    break;


                case 8:
                    System.out.print("Introduce el ID del monstruo cuya ubicación quieres modificar: ");
                    Long entityIdForLocation = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    System.out.print("Introduce el nuevo nombre de la ubicación del monstruo: ");
                    String newLocationName = scanner.nextLine();

                    ModificationController.modifyLocationNameById(entityManager, entityIdForLocation, newLocationName);
                    break;

                case 9:
                    System.out.print("Introduce el ID del monstruo que quieres modificar: ");
                    Long entityIdForDetails = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    System.out.print("Introduce el nuevo nombre del monstruo: ");
                    String newMonsterName = scanner.nextLine();

                    System.out.print("Introduce la nueva descripción del monstruo: ");
                    String newMonsterDescription = scanner.nextLine();

                    System.out.print("Introduce el nuevo nombre de la ubicación del monstruo: ");
                    String newLocationNameForDetails = scanner.nextLine();

                    ModificationController.modifyMonstruoDetails(entityManager, entityIdForDetails, newMonsterName, newMonsterDescription, newLocationNameForDetails);
                    break;


                case 10:
                    try {
                        EntityTransaction transaction = entityManager.getTransaction();

                        if (transaction.isActive()) {
                            System.out.println("Rollback de transacción activa.");
                            transaction.rollback();
                        }

                        transaction.begin();
                        System.out.println("Transacción iniciada para modificar múltiples descripciones.");

                        ModificationController.modifyMultipleMonstruoDescriptionsById(entityManager);

                        if (transaction.isActive()) {
                            transaction.commit();
                            System.out.println("Transacción commit exitoso.");
                        }

                        System.out.println("Todas las descripciones de monstruos se han actualizado correctamente.");
                    } catch (Exception e) {
                        System.err.println("Error al procesar la opción 10: " + e.getMessage());
                        e.printStackTrace();

                        EntityTransaction transaction = entityManager.getTransaction();
                        if (transaction != null && transaction.isActive()) {
                            System.out.println("Rollback de transacción debido a excepción.");
                            transaction.rollback();
                        }
                    }
                    break;

                case 11:
                    System.out.print("Introduce el ID del monstruo que deseas eliminar: ");
                    Long monsterIdToDelete = scanner.nextLong();
                    scanner.nextLine(); // Consumir el salto de línea

                    DeleteController.deleteMonstruoById(entityManager, monsterIdToDelete);
                    break;

                case 12:
                    System.out.print("¿Cuántos monstruos deseas eliminar?: ");
                    int numMonstruos = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto d e línea

                    List<Long> entityIdsToDelete = new ArrayList<>();
                    for (int i = 0; i < numMonstruos; i++) {
                        System.out.print("Introduce la ID del monstruo " + (i + 1) + ": ");
                        long idToDelete = scanner.nextLong(); // Renombrar la variable
                        scanner.nextLine(); // Consumir el salto de línea
                        entityIdsToDelete.add(idToDelete);
                    }

                    DeleteController.deleteMonstruosByIds(entityManager, entityIdsToDelete); // Pasar el EntityManager como primer argumento
                    break;

                case 0:
                    exit = true;
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }


        entityManager.close();
        entityManagerFactory.close();
        scanner.close();
    }
}
