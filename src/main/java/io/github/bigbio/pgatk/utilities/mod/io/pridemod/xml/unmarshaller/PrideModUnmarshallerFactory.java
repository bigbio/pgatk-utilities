package io.github.bigbio.pgatk.utilities.mod.io.pridemod.xml.unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.bigbio.pgatk.utilities.mod.io.pridemod.model.ModelConstants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Unmarshaller a specific PRIDE Modification File. It take an PRIDE XML file definition and
 * return the complete information of the file.
 *
 * @author ypriverol
 */
public class PrideModUnmarshallerFactory {

    private static final Logger logger = LoggerFactory.getLogger(PrideModUnmarshallerFactory.class);

    private static PrideModUnmarshallerFactory instance = new PrideModUnmarshallerFactory();

    private static JAXBContext jc = null;

    private PrideModUnmarshallerFactory() {
    }

    public static PrideModUnmarshallerFactory getInstance() {
        return instance;
    }

    public Unmarshaller initializeUnmarshaller() {

        try {
            // Lazy caching of the JAXB Context.
            if (jc == null) {
                jc = JAXBContext.newInstance(ModelConstants.MODEL_PKG);
            }

            //create unmarshaller
            Unmarshaller pum = jc.createUnmarshaller();
            logger.info("Unmarshaller Initialized");

            return pum;

        } catch (JAXBException e) {
            logger.error("UnmarshallerFactory.initializeUnmarshaller", e);
            throw new IllegalStateException("Could not initialize unmarshaller", e);
        }
    }
}