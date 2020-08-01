package io.github.bigbio.pgatk.utilities.iongen.model;

import io.github.bigbio.pgatk.utilities.mol.ProductIonType;

/**
 * A kind of {@link PeptideIon}, which have one {@link PrecursorIon}.
 *
 * @author Qingwei XU
 * @version 0.1-SNAPSHOT
 */
public interface ProductIon extends PeptideIon, Comparable<ProductIon> {
    /**
     *
     * @return {@link ProductIonType}
     */
    ProductIonType getType();

    /**
     *
     * @return {@link PrecursorIon}
     */
    PrecursorIon getPrecursorIon();

    /**
     *
     * @return the product ion charge.
     */
    int getCharge();

    /**
     *
     * @return the cleavage position of product ion.
     */
    int getPosition();
}
