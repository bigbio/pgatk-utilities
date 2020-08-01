package io.github.bigbio.pgatk.utilities.obo;

import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * This code is licensed under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * ==Overview==
 *
 * @author ypriverol on 23/10/2018.
 */
public class OBOMapperTest {

    @Test
    public void getPSIMSInstance() {

        OBOMapper oboMapper = OBOMapper.getPSIMSInstance();
        Assert.assertTrue(oboMapper.getTerms().size() == 2708);
    }
}