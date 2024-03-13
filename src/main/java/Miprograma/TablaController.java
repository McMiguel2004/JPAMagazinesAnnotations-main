package Miprograma;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase que proporciona métodos para la creación, eliminación y población de tablas en la base de datos.
 */
public class TablaController {

    private static EntityManager entityManager = Persistence.createEntityManagerFactory("JPAMagazines").createEntityManager();

    /**
     * Crea las tablas Monstruo, Elements y Location en la base de datos.
     *
     * @param entityManager EntityManager para realizar operaciones de persistencia.
     */
    public static void createTables(EntityManager entityManager) {
        EntityTransaction transaction = null;

        try {
            transaction = TablaController.entityManager.getTransaction();
            transaction.begin();

            TablaController.entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS Monstruo ("
                    + "id SERIAL PRIMARY KEY,"
                    + "Nombre VARCHAR(255) NOT NULL,"
                    + "Imagen VARCHAR(255) NOT NULL,"
                    + "Descripcion TEXT NOT NULL,"
                    + "SpeciesName VARCHAR(255),"
                    + "SpeciesDescripcion TEXT)").executeUpdate();

            // Crear tabla Elements
            TablaController.entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS Elements ("
                    + "id SERIAL PRIMARY KEY,"
                    + "element_name VARCHAR(255) NOT NULL,"
                    + "monstruo_id INT REFERENCES Monstruo(id))").executeUpdate();

            // Crear tabla Location
            TablaController.entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS Location ("
                    + "id SERIAL PRIMARY KEY,"
                    + "Name VARCHAR(255) NOT NULL,"
                    + "descripcion TEXT NOT NULL,"
                    + "monstruo_id INT REFERENCES Monstruo(id))").executeUpdate();

            transaction.commit();
            System.out.println("Tablas creadas exitosamente.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al crear tablas: " + e.getMessage());
        }
    }

    /**
     * Elimina las tablas Elements, Location y Monstruo de la base de datos.
     *
     * @param entityManager EntityManager para realizar operaciones de persistencia.
     */


    public static void dropTables(EntityManager entityManager) {
        EntityTransaction transaction = null;

        try {
            transaction = TablaController.entityManager.getTransaction();
            transaction.begin();

            TablaController.entityManager.createNativeQuery("DROP TABLE IF EXISTS Elements").executeUpdate();
            TablaController.entityManager.createNativeQuery("DROP TABLE IF EXISTS Location").executeUpdate();
            TablaController.entityManager.createNativeQuery("DROP TABLE IF EXISTS Monstruo CASCADE").executeUpdate();

            transaction.commit();
            System.out.println("Todas las tablas han sido eliminadas exitosamente.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Error al eliminar tablas: " + e.getMessage());
        }
    }


    /**
     * Puebla la base de datos con datos de un archivo XML.
     *
     * @param entityManager EntityManager para realizar operaciones de persistencia.
     * @param xmlFilePath   Ruta del archivo XML que contiene los datos.
     */
    public static void populateFromXML(EntityManager entityManager, String xmlFilePath) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }

            File xmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList monstruoList = doc.getElementsByTagName("Monstruo");

            for (int i = 0; i < monstruoList.getLength(); i++) {
                Node monstruoNode = monstruoList.item(i);

                if (monstruoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element monstruoElement = (Element) monstruoNode;

                    Monstruo monstruo = createMonstruoFromXML(monstruoElement);
                    entityManager.persist(monstruo);
                }
            }

            if (transaction != null && transaction.isActive() && !transaction.getRollbackOnly()) {
                transaction.commit();
            } else if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }

            System.out.println("Datos insertados desde XML exitosamente.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.err.println("Error al poblar desde XML: " + e.getMessage());
        }

    }



    private static Monstruo createMonstruoFromXML(Element monstruoElement) {
        Monstruo monstruo = new Monstruo();
        monstruo.setNombre(getElementTextContent(monstruoElement, "Nombre"));
        monstruo.setImagen(getElementTextContent(monstruoElement, "Imagen"));
        monstruo.setDescripcion(getElementTextContent(monstruoElement, "Descripcion"));

        Element speciesElement = (Element) monstruoElement.getElementsByTagName("Species").item(0);
        if (speciesElement != null) {
            monstruo.setSpeciesName(getElementTextContent(speciesElement, "name"));
            monstruo.setSpeciesDescripcion(getElementTextContent(speciesElement, "descripcion"));
        }

        List<Elements> elementsList = createElementsFromXML(monstruoElement, monstruo);
        monstruo.setElements(elementsList);

        List<Location> locationsList = createLocationsFromXML(monstruoElement, monstruo);
        monstruo.setLocations(locationsList);

        return monstruo;
    }

    private static List<Elements> createElementsFromXML(Element monstruoElement, Monstruo monstruo) {
        List<Elements> elementsList = new ArrayList<>();
        Element elementsElement = (Element) monstruoElement.getElementsByTagName("Elements").item(0);

        if (elementsElement != null) {
            NodeList elementNodes = elementsElement.getElementsByTagName("*");

            for (int i = 0; i < elementNodes.getLength(); i++) {
                Node elementNode = elementNodes.item(i);

                if (elementNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementElement = (Element) elementNode;
                    Elements elements = new Elements();
                    elements.setElementName(elementElement.getTextContent());
                    elements.setMonstruo(monstruo);
                    elementsList.add(elements);
                }
            }
        }

        return elementsList;
    }

    private static List<Location> createLocationsFromXML(Element monstruoElement, Monstruo monstruo) {
        List<Location> locationsList = new ArrayList<>();
        NodeList locationNodes = monstruoElement.getElementsByTagName("location");

        for (int i = 0; i < locationNodes.getLength(); i++) {
            Node locationNode = locationNodes.item(i);

            if (locationNode.getNodeType() == Node.ELEMENT_NODE) {
                Element locationElement = (Element) locationNode;
                Location location = createLocationFromXML(locationElement);
                location.setMonstruo(monstruo);
                locationsList.add(location);
            }
        }

        return locationsList;
    }

    private static String getElementTextContent(Element parentElement, String tagName) {
        if (parentElement != null) {
            NodeList nodeList = parentElement.getElementsByTagName(tagName);
            if (nodeList.getLength() > 0) {
                return nodeList.item(0).getTextContent();
            }
        }
        return null;
    }

    private static Location createLocationFromXML(Element locationElement) {
        Location location = new Location();
        location.setName(getElementTextContent(locationElement, "name"));
        location.setDescripcion(getElementTextContent(locationElement, "description"));
        return location;
    }
}