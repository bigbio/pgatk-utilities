package io.github.bigbio.pgatk.utilities.mod.controller.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.github.bigbio.pgatk.utilities.mod.model.PTM;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class UnimodDataAccessControllerTest {

    public UnimodDataAccessController unimodDataAccessController = null;

    @Before
    public void setUp() {
        InputStream inputStream = UnimodDataAccessControllerTest.class.getClassLoader().getResourceAsStream("unimod.xml");
        if (inputStream == null) {
            throw new IllegalStateException("no file for input found!");
        }
        unimodDataAccessController = new UnimodDataAccessController(inputStream);
    }

    @Test
    public void TestGetPTms() {
        List<PTM> ptms = unimodDataAccessController.getPTMListByPatternDescription("Phospho");
        assertTrue("Number of PTMs with Term 'Phospho' in name:", ptms.size() == 38);
    }

    @Test
    public void TestGetMod(){
        PTM ptm = unimodDataAccessController.getPTMbyAccession("UNIMOD:1");
        assertTrue("Difference mass for Average mass is:", ptm.getAveDeltaMass() == 42.0367);
    }
}
