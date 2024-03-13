package Miprograma;

import javax.persistence.*;

/**
 * Clase que representa la ubicación de un monstruo.
 */
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "monstruo_id", nullable = false)
    private Monstruo monstruo;

    /**
     * Obtiene el ID de la ubicación.
     *
     * @return El ID de la ubicación.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la ubicación.
     *
     * @param id El ID de la ubicación.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la ubicación.
     *
     * @return El nombre de la ubicación.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la ubicación.
     *
     * @param name El nombre de la ubicación.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la descripción de la ubicación.
     *
     * @return La descripción de la ubicación.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la ubicación.
     *
     * @param descripcion La descripción de la ubicación.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el monstruo asociado a esta ubicación.
     *
     * @return El monstruo asociado a esta ubicación.
     */
    public Monstruo getMonstruo() {
        return monstruo;
    }

    /**
     * Establece el monstruo asociado a esta ubicación.
     *
     * @param monstruo El monstruo asociado a esta ubicación.
     */
    public void setMonstruo(Monstruo monstruo) {
        this.monstruo = monstruo;
    }
}
