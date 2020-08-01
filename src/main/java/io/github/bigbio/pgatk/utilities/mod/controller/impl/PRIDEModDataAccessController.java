package io.github.bigbio.pgatk.utilities.mod.controller.impl;

import lombok.extern.slf4j.Slf4j;
import io.github.bigbio.pgatk.utilities.mod.controller.DataAccessController;
import io.github.bigbio.pgatk.utilities.mod.io.pridemod.model.PsiModification;
import io.github.bigbio.pgatk.utilities.mod.io.pridemod.model.UnimodMapping;
import io.github.bigbio.pgatk.utilities.mod.model.*;
import io.github.bigbio.pgatk.utilities.mod.controller.AbstractDataAccessController;
import io.github.bigbio.pgatk.utilities.mod.io.pridemod.model.PrideMod;
import io.github.bigbio.pgatk.utilities.mod.io.pridemod.model.PrideModification;
import io.github.bigbio.pgatk.utilities.mod.io.pridemod.xml.PrideModReader;
import io.github.bigbio.pgatk.utilities.mod.utils.Utilities;

import java.io.InputStream;
import java.util.*;

/**
 * PRIDEMod Controller only provide metadata ang grouping options for each Modification.
 * Three main features are provided:
 *   - Relevance of the modification, if is biologically relevant taking into account PRIDE criteria.
 *   - Redundancy amount PSI-Mod modifications, for such modifications redundant it returns a unique Identifier
 *   - A shortname that can be use in Proteogenomics conversion and annotations.
 */
@Slf4j
public class PRIDEModDataAccessController extends AbstractDataAccessController {

    private static DataAccessController unimodController = null;
    private static DataAccessController psimodController = null;

    /**
     * This Constructor populates the information of each PTM in the Map from including all the information.
     * @param xml the PRIDE XML containing all the information about the PTMs
     * @param unimodController Unimod Controller.
     * @param psimodController PSI Controller.
     */
    public PRIDEModDataAccessController(InputStream xml, DataAccessController unimodController,
                                        DataAccessController psimodController){
        super(xml);

        PRIDEModDataAccessController.unimodController = unimodController;
        PRIDEModDataAccessController.psimodController = psimodController;

        PrideModReader reader = new PrideModReader(xml);
        initPTMMap(reader.getPrideMod());
    }

    /**
     * An initial Map containing all the information for the modification. In the PRIDE Case it contains
     * contains information about the each Modification.
     *
     * @param prideMod
     */
    private void initPTMMap(PrideMod prideMod) {

        if(prideMod != null) {
            ptmMap = new HashMap<>();
            for (PrideModification oldMod : prideMod.getPrideModifications().getPrideModification()) {
                log.debug("Accession -> " + oldMod.getId());
                String accession = oldMod.getAccession();
                String shortName = oldMod.getShortname();

                Map<Comparable, Map.Entry<UniModPTM, Boolean>> unimodChildren = new HashMap<>();
                if(oldMod.getUnimodMappings() != null && oldMod.getUnimodMappings().getUnimodMapping() != null){
                    for(UnimodMapping unimodMapping: oldMod.getUnimodMappings().getUnimodMapping()){
                        UniModPTM uniModPTM = (UniModPTM) unimodController.getPTMbyAccession(unimodMapping.getAccession());
                        unimodChildren.put(uniModPTM.getAccession(), new AbstractMap.SimpleEntry<>(uniModPTM, unimodMapping.getGeneralModification().intValue() == 1));
                    }
                }
                Map<Comparable, Map.Entry<PSIModPTM, Boolean>> children = new HashMap<>();
                if(oldMod.getPsiModifications() != null && !oldMod.getPsiModifications().getPsiModification().isEmpty()){
                    for(PsiModification psiModification: oldMod.getPsiModifications().getPsiModification()){
                        PSIModPTM psiModPTM = (PSIModPTM) psimodController.getPTMbyAccession(psiModification.getAccession());
                        children.put(psiModification.getAccession(), new AbstractMap.SimpleEntry<>(psiModPTM, psiModification.getGeneralModification().intValue() ==1));
                    }
                }
                PRIDEModPTM ptm = new PRIDEModPTM(accession, unimodChildren,shortName,(oldMod.getBiologicalSignificance().intValue() == 1),children);
                ptmMap.put(accession, ptm);
            }
        }
    }

    /**
     * This function retrieve the PRIDE PTM annotations if the accession can be found in the UNIMOD
     * Modifications of the PSIMOD references.
     * @param accession accession to be found
     * @return PRIDEModPTM
     */
    public PRIDEModPTM getPRIDEModByChildrenID(String accession) {
        for(PTM prideModPTMValue: ptmMap.values()){
            PRIDEModPTM prideModPTM = (PRIDEModPTM) prideModPTMValue;
            if(Utilities.isUniModAccession(accession) && prideModPTM.isUniModRef(accession) || Utilities.isPSIModAccession(accession) && prideModPTM.containsPSIMod(accession))
                return prideModPTM;
        }
        return null;
    }

    public UniModPTM getGeneralModificationUNIMOD(List<PTM> unimodList) {
        for(PTM ptmOldValue: ptmMap.values()){
            if(!((PRIDEModPTM) ptmOldValue).getChieldUnimodPTMSs().isEmpty()){
                boolean found = true;
                Collection<UniModPTM> listMods = ((PRIDEModPTM) ptmOldValue).getChieldUnimodPTMSs();
                for(PTM unimodQuery: unimodList){
                   if(!listMods.contains(unimodQuery))
                      found = false;
                }
                if(found)
                    return ((PRIDEModPTM) ptmOldValue).getUnimodGeneralModification();
            }
        }
        return null;
    }
}
