package io.github.bigbio.pgatk.utilities.mod.io.pridemod.model;

import io.github.bigbio.pgatk.utilities.mod.exception.DataAccessException;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigInteger;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}unimod_mappings"/>
 *         &lt;element ref="{}psi_modifications"/>
 *       &lt;/sequence>
 *       &lt;attribute name="biological_significance" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="diff_mono" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="accession" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="title" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "unimodMappings",
        "psiModifications"
})
@XmlRootElement(name = "pride_modification")
public class PrideModification
        implements Serializable, PrideModObject {

    private final static long serialVersionUID = 100L;
    @XmlElement(name = "unimod_mappings", required = true)
    protected UnimodMappings unimodMappings;
    @XmlElement(name = "psi_modifications", required = true)
    protected PsiModifications psiModifications;
    @XmlAttribute(name = "biological_significance", required = true)
    protected BigInteger biologicalSignificance;
    @XmlAttribute(required = true)
    protected BigInteger id;
    @XmlAttribute(name = "shortname")
    @XmlSchemaType(name = "anyURI")
    protected String shortname;


    /**
     * Gets the value of the unimodMapping property.
     *
     * @return possible object is
     *         {@link UnimodMapping }
     */
    public UnimodMappings getUnimodMappings() {
        return unimodMappings;
    }

    /**
     * Sets the value of the unimodMapping property.
     *
     * @param value allowed object is
     *              {@link UnimodMapping }
     */
    public void setUnimodMappings(UnimodMappings value) {
        this.unimodMappings = value;
    }

    /**
     * Gets the value of the psiModifications property.
     *
     * @return possible object is
     *         {@link PsiModifications }
     */
    public PsiModifications getPsiModifications() {
        return psiModifications;
    }

    /**
     * Sets the value of the psiModifications property.
     *
     * @param value allowed object is
     *              {@link PsiModifications }
     */
    public void setPsiModifications(PsiModifications value) {
        this.psiModifications = value;
    }

    /**
     * Gets the value of the biologicalSignificance property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getBiologicalSignificance() {
        return biologicalSignificance;
    }

    /**
     * Sets the value of the biologicalSignificance property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setBiologicalSignificance(BigInteger value) {
        this.biologicalSignificance = value;
    }

    /**
     * Gets the value of the accession property.
     *
     * @return possible object is
     *         {@link java.math.BigInteger }
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the accession property.
     *
     * @param value allowed object is
     *              {@link java.math.BigInteger }
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    public String getShortname() {
        return shortname;
    }

    public boolean compareId(int id) {
        return this.getId().intValue() == id;
    }

    public String getAccession() {
        if(this.getUnimodMappings() != null && this.getUnimodMappings().getUnimodMapping() != null && !this.getUnimodMappings().getUnimodMapping().isEmpty())
            for (UnimodMapping unimodMapping : this.getUnimodMappings().getUnimodMapping())
                if (unimodMapping.generalModification.intValue() == 1)
                    return unimodMapping.getAccession();
        if(this.getPsiModifications() != null && !this.getPsiModifications().getPsiModification().isEmpty())
            for (PsiModification psiModification : this.getPsiModifications().getPsiModification())
                if (psiModification.generalModification.intValue() == 1)
                    return psiModification.getAccession();

        throw new DataAccessException("Now Accession has been found for the Modification.");
    }


}
