package io.github.bigbio.pgatk.utilities.util;

import org.junit.Assert;
import org.junit.Test;
import io.github.bigbio.pgatk.utilities.mol.GraviUtilities;
import io.github.bigbio.pgatk.utilities.mol.IsoelectricPointUtils;

/**
 * @author rwang
 * @author ypriverol
 */

public class NumberUtilitiesTest {
    @Test
    public void testIsNumber() {
        String str = "7.60056300136405E-4";
        Assert.assertTrue(NumberUtilities.isNumber(str));
        str = "7.60056300136405E--4";
        Assert.assertFalse(NumberUtilities.isNumber(str));
        str = "7.60056300e136405E-4";
        Assert.assertFalse(NumberUtilities.isNumber(str));
    }

    @Test
    public void testIsoPointcaculate(){
        String seq = "EAFQNAYLELGGLGER";
        double value = IsoelectricPointUtils.calculate(seq);
        System.out.println(value);
    }
    @Test
    public void testGravicalculate(){
        String seq = "MEKKPAAKKAGSDAAASRPRAAKVAKKVHPKGKKPKKAKPHCSRNPVLVRGIGRYSRSAMYSRKALYKRKYSAAKTKVEKKKKKEKVLATVTKTVGGDKNGGTRVVKLRKMPRYYPTEDVPRKLLSHGKKPFSQHVRRLRSSITPGTVLIILTGRHRGKRVVFLKQLDSGLLLVTGPLVI";
        double value = GraviUtilities.calculate(seq);
        System.out.println(value);
    }

}
