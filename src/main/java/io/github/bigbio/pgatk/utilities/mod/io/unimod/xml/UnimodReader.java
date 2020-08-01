package io.github.bigbio.pgatk.utilities.mod.io.unimod.xml;

import io.github.bigbio.pgatk.utilities.mod.io.unimod.model.Unimod;
import io.github.bigbio.pgatk.utilities.mod.io.unimod.xml.unmarshaller.UnimodUnmarshallerFactory;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;


/**
 * yperez
 */
@Slf4j
public class UnimodReader {

    private Unimod unimod_whole;

    /**
     *
     * @param xml
     * @throws JAXBException
     */
    public UnimodReader(InputStream xml) throws JAXBException {
        if (xml == null) {
            throw new IllegalArgumentException("Xml file to be indexed must not be null");
        }
        // create unmarshaller
        /*
      internal unmashaller
     */
        Unmarshaller unmarshaller = UnimodUnmarshallerFactory.getInstance().initializeUnmarshaller();
        unimod_whole = (Unimod) unmarshaller.unmarshal(xml);
    }

    public Unimod getUnimodObject(){
        return unimod_whole;
    }
}
