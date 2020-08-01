package io.github.bigbio.pgatk.utilities.mod.controller.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.github.bigbio.pgatk.utilities.mod.controller.impl.PSIModDataAccessController;
import io.github.bigbio.pgatk.utilities.mod.model.PTM;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * DataAccessController Test
 * yperez
 */
public class PSIModDataAccessControllerTest {

    private PSIModDataAccessController psiModDataAccessController = null;

    @Before
    public void setUp() {
        InputStream inputStream = PSIModDataAccessControllerTest.class.getClassLoader().getResourceAsStream("PSI-MOD.obo");
        if (inputStream == null) {
            throw new IllegalStateException("no file for input found!");
        }
        psiModDataAccessController = new PSIModDataAccessController(inputStream);
    }


    @Test
    public void TestGetPTms() {
        List<PTM> ptms = psiModDataAccessController.getPTMListByPatternDescription("Phospho");
        assertTrue("Number of PTMs with Term 'Phospho' in name:", ptms.size() == 72);
    }

    @Test
    public void TestGetMod(){
        PTM ptm = psiModDataAccessController.getPTMbyAccession("MOD:00036");
        assertTrue("Difference mass for Average mass is:", ptm.getAveDeltaMass() == 16.0);
    }
}
