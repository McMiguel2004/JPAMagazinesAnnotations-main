package Miprograma;

import javax.persistence.*;

/**
 * Clase que representa los elementos asociados a un monstruo.
 */
@Entity
public class Elements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "element_name", nullable = false)
    private String elementName;

    @ManyToOne
    @JoinColumn(name = "monstruo_id", nullable = false)
    private Monstruo monstruo;

    /**
     * Obtiene el ID del elemento.
     *
     * @return El ID del elemento.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del elemento.
     *
     * @param id El ID del elemento.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del elemento.
     *
     * @return El nombre del elemento.
     */
    public String getElementName() {
        return elementName;
    }

    /**
     * Establece el nombre del elemento.
     *
     * @param elementName El nombre del elemento.
     */
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    /**
     * Obtiene el monstruo asociado a este elemento.
     *
     * @return El monstruo asociado a este elemento.
     */
    public Monstruo getMonstruo() {
        return monstruo;
    }

    /**
     * Establece el monstruo asociado a este elemento.
     *
     * @param monstruo El monstruo asociado a este elemento.
     */
    public void setMonstruo(Monstruo monstruo) {
        this.monstruo = monstruo;
    }
}
