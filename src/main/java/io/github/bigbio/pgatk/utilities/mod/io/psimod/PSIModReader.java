package io.github.bigbio.pgatk.utilities.mod.io.psimod;

import org.obolibrary.oboformat.model.Frame;
import org.obolibrary.oboformat.model.OBODoc;
import org.obolibrary.oboformat.parser.OBOFormatParser;

import java.io.*;
import java.util.Collection;
import java.util.Collections;

/**
 * yperez
 */
public class PSIModReader {

    /**
     * OboDoc that contains PSI-Mod PTms
     */
    private OBODoc oboDoc;

    public PSIModReader(InputStream inputStream) throws IOException {
        OBOFormatParser oboReader = new OBOFormatParser();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            oboDoc = oboReader.parse(bufferedReader);
            oboDoc.getInstanceFrames();
        }
    }

    public Collection<Frame> getTermCollection(){
        if(oboDoc != null)
            return oboDoc.getTermFrames();
        return Collections.emptyList();
    }

}
