package io.github.bigbio.pgatk.utilities.mod.io.pridemod.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bigbio.pgatk.utilities.mod.io.pridemod.model.PrideMod;
import io.github.bigbio.pgatk.utilities.mod.io.pridemod.xml.unmarshaller.PrideModUnmarshallerFactory;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;


/**
 * Return the PRIDEMod Structure.
 */
public class PrideModReader {

    private static final Logger logger = LoggerFactory.getLogger(PrideModReader.class);

    private PrideMod prideMod_whole;

    public PrideModReader(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException("Xml file to be indexed must not be null");
        }

        try {
            Unmarshaller unmarshaller = PrideModUnmarshallerFactory.getInstance().initializeUnmarshaller();
            prideMod_whole = (PrideMod) unmarshaller.unmarshal(inputStream);
        } catch (JAXBException e) {
            throw new IllegalArgumentException("Error unmarshalling XML file: " + e.getMessage(), e);
        }
    }

    public PrideMod getPrideMod(){
        return prideMod_whole;
    }


}
