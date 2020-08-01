package io.github.bigbio.pgatk.utilities.mod.controller.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.github.bigbio.pgatk.utilities.mod.controller.DataAccessController;
import io.github.bigbio.pgatk.utilities.mod.controller.impl.PRIDEModDataAccessController;
import io.github.bigbio.pgatk.utilities.mod.controller.impl.PSIModDataAccessController;
import io.github.bigbio.pgatk.utilities.mod.controller.impl.UnimodDataAccessController;
import io.github.bigbio.pgatk.utilities.mod.model.PTM;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class PRIDEModDataAccessControllerTest {

    public PRIDEModDataAccessController prideModDataAccessController = null;

    @Before
    public void setUp() {
        InputStream inputStream = PRIDEModDataAccessController.class.getClassLoader().getResourceAsStream("pride_mods.xml");
        InputStream psiStream = PSIModDataAccessController.class.getClassLoader().getResourceAsStream("PSI-MOD.obo");
        InputStream uniModStream = PSIModDataAccessController.class.getClassLoader().getResourceAsStream("unimod.xml");

        if (psiStream == null || inputStream == null || uniModStream == null) {
            throw new IllegalStateException("Modification file not found!");
        }
        DataAccessController psiModDataAccessController = new PSIModDataAccessController(psiStream);
        DataAccessController unimodDataAccessController = new UnimodDataAccessController(uniModStream);
        prideModDataAccessController = new PRIDEModDataAccessController(inputStream,unimodDataAccessController,psiModDataAccessController);
    }

    @Test
    public void TestGetPTms() {
        List<PTM> ptms = prideModDataAccessController.getPTMListByPatternDescription("Phospho");
        assertTrue("Number of PTMs with Term 'Phospho' in name:", ptms.size() == 3);
    }

    @Test
    public void TestGetMod(){
        PTM ptm = prideModDataAccessController.getPTMbyAccession("UNIMOD:1");
        assertTrue("Difference mass for Average mass is:", ptm.getMonoDeltaMass() == 42.010565);
    }

}
