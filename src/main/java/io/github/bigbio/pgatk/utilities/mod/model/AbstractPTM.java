package io.github.bigbio.pgatk.utilities.mod.model;

import java.util.List;

/**
 * yperez
 */
public class AbstractPTM implements PTM {

    /**
     * Accession of the modification
     */
    String accession;

    /**
     * Modification Name
     */
    String name;

    /**
     * Modification Description
     */
    String description;

    /**
     * Mono delta mass
     */
    Double monoDeltaMass;

    /**
     * Average delta Mass
     */
    Double averageDeltaMass;

    /**
     * List of specificity. An specificity is an AminoAcid and a position term (N-Term, NONe, C-Term)
     */
    List<Specificity> specificityList;

    /**
     * Chemical formula of the modification
     */
    String formula;

    String cvLabel;

    /**
     * Contructor with all the fields for a general modification
     * @param accession          PTM accession
     * @param name               Name
     * @param description        Description
     * @param monoDeltaMass      MonoIsotopic Delta Mass
     * @param averageDeltaMass   Average Delta Mass
     * @param specificityList    List of Specificity
     * @param formula            Chemical Formula
     */
    public AbstractPTM(String accession, String name, String description, Double monoDeltaMass, Double averageDeltaMass, List<Specificity> specificityList, String formula) {
        this.accession = accession;
        this.name = name;
        this.description = description;
        this.monoDeltaMass = monoDeltaMass;
        this.averageDeltaMass = averageDeltaMass;
        this.specificityList = specificityList;
        this.formula = formula;
    }

    /**
     * Constructor when only an accession and name is known
     * @param accession
     */
    public AbstractPTM(String accession){
        this.accession = accession;
    }

    @Override
    public String getAccession() {
        return accession;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getMonoDeltaMass() {
        return monoDeltaMass;
    }

    @Override
    public Double getAveDeltaMass() {
        return averageDeltaMass;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<Specificity> getSpecificityCollection() {
        return specificityList;
    }

    @Override
    public String getFormula() {
        return formula;
    }

    @Override
    public String getCvLabel() {
        return cvLabel;
    }

    @Override
    public String getShortName() {
        String name = getName().trim();
        name = (name != null)? name.replaceAll("\\s+", "-").replaceAll("\\(", "").replaceAll("\\)" , "-"):name;
        if(name.endsWith("-"))
            name = name.substring(0, name.length() - 1);
        return name;
    }

    @Override
    public String toString() {
        return "AbstractPTM{" +
                "accession='" + accession + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", monoDeltaMass=" + monoDeltaMass +
                ", averageDeltaMass=" + averageDeltaMass +
                ", specificityList=" + specificityList +
                ", formula='" + formula + '\'' +
                '}';
    }
}