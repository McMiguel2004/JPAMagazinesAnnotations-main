package Miprograma;
import javax.persistence.*;
import java.util.List;

/**
 * Clase que representa un monstruo en el juego.
 */
@Entity
public class Monstruo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String imagen;

    @Column(nullable = false)
    private String descripcion;

    @Column(name = "SpeciesName")
    private String speciesName;

    @Column(name = "SpeciesDescripcion")
    private String speciesDescripcion;

    @OneToMany(mappedBy = "monstruo", cascade = CascadeType.ALL)
    private List<Elements> elements;

    @OneToMany(mappedBy = "monstruo", cascade = CascadeType.ALL)
    private List<Location> locations;

    /**
     * Obtiene el ID del monstruo.
     *
     * @return El ID del monstruo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del monstruo.
     *
     * @param id El ID del monstruo.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del monstruo.
     *
     * @return El nombre del monstruo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del monstruo.
     *
     * @param nombre El nombre del monstruo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la URL de la imagen del monstruo.
     *
     * @return La URL de la imagen del monstruo.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Establece la URL de la imagen del monstruo.
     *
     * @param imagen La URL de la imagen del monstruo.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene la descripción del monstruo.
     *
     * @return La descripción del monstruo.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del monstruo.
     *
     * @param descripcion La descripción del monstruo.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el nombre de la especie del monstruo.
     *
     * @return El nombre de la especie del monstruo.
     */
    public String getSpeciesName() {
        return speciesName;
    }

    /**
     * Establece el nombre de la especie del monstruo.
     *
     * @param speciesName El nombre de la especie del monstruo.
     */
    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    /**
     * Obtiene la descripción de la especie del monstruo.
     *
     * @return La descripción de la especie del monstruo.
     */
    public String getSpeciesDescripcion() {
        return speciesDescripcion;
    }

    /**
     * Establece la descripción de la especie del monstruo.
     *
     * @param speciesDescripcion La descripción de la especie del monstruo.
     */
    public void setSpeciesDescripcion(String speciesDescripcion) {
        this.speciesDescripcion = speciesDescripcion;
    }

    /**
     * Obtiene la lista de elementos asociados al monstruo.
     *
     * @return La lista de elementos asociados al monstruo.
     */
    public List<Elements> getElements() {
        return elements;
    }

    /**
     * Establece la lista de elementos asociados al monstruo.
     *
     * @param elements La lista de elementos asociados al monstruo.
     */
    public void setElements(List<Elements> elements) {
        this.elements = elements;
    }

    /**
     * Obtiene la lista de ubicaciones asociadas al monstruo.
     *
     * @return La lista de ubicaciones asociadas al monstruo.
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * Establece la lista de ubicaciones asociadas al monstruo.
     *
     * @param locations La lista de ubicaciones asociadas al monstruo.
     */
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
