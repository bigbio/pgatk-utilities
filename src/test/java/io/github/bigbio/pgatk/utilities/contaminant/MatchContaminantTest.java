package io.github.bigbio.pgatk.utilities.contaminant;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests reading in and matching from the contaminates FASTA file.
 */
public class MatchContaminantTest {

  /**
   * Checks if a contaminant peptide is present, and if a non-contaminant peptide is not present.
   * @throws Exception assert failures from incorrect contaminant searching results.
   */
    @Test
    public void isPeptideContaminant() throws Exception {
        String peptide = "FNTGVQEGAKALYANLEPKAEQYAVI";
        Assert.assertTrue(MatchContaminant.instance().isPeptideContaminant(peptide));
        peptide = "KKKKKKKKKKKKKKRRRR";
        Assert.assertTrue(!MatchContaminant.instance().isPeptideContaminant(peptide));
    }

}