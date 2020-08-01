package io.github.bigbio.pgatk.utilities.iongen.model;

import io.github.bigbio.pgatk.utilities.mol.Peptide;

/**
 * When a peptide with charges, we call it peptide ion. {@link PrecursorIon} and {@link ProductIon}
 * are all belong to peptide ions. In the {@link io.github.bigbio.pgatk.utilities.iongen.impl.DefaultPeptideIon}
 * class, we provide the default implement, which pay main attention on mass and m/z calculate.
 *
 * @author Qingwei XU
 * @version 0.1-SNAPSHOT
 */
public interface PeptideIon {
    /**
     * @return the {@link Peptide} of ion.
     */
    Peptide getPeptide();

    /**
     * @return charge count of ion. The charge can be positron or negative electron. 0 means no charge on ion.
     */
    int getCharge();

    /**
     * @return the mass value of ion.
     */
    double getMass();

    /**
     *
     * @return the m/z value of ion.
     */
    double getMassOverCharge();
}
