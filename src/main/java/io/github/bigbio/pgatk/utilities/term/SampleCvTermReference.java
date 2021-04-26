package io.github.bigbio.pgatk.utilities.term;


public enum SampleCvTermReference {

    EFO_ORGANISM_PART("EFO", "EFO:0000635", "organism part", null),
    MS_PROTEOMEXCHANGE_ACCESSION("MS", "MS:1001919", "ProteomeXchange accession number", null),
    EFO_ORGANISM("EFO", "OBI:0100026", "organism", null);


    /** End EFO Terms **/

    private final String cvLabel;
    private final String accession;
    private final String name;
    private final String parentAccession;

    SampleCvTermReference(String cvLabel, String accession, String name, String parentAccession) {
        this.cvLabel = cvLabel;
        this.accession = accession;
        this.name = name;
        this.parentAccession = parentAccession;
    }

    public String getCvLabel() {
        return cvLabel;
    }

    public String getAccession() {
        return accession;
    }

    public String getName() {
        return name;
    }

}
