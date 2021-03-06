package io.github.bigbio.pgatk.utilities.mod.controller;

import java.io.InputStream;
import java.util.List;

import io.github.bigbio.pgatk.utilities.mod.model.PTM;
import io.github.bigbio.pgatk.utilities.mod.model.Specificity;

/**
 * DataAccessController is an Interface for all the Modification Databases.
 * yperez.
 */
public interface DataAccessController {

	InputStream getSource();

	/**
	 * PTM accession
	 * 
	 * @param accession
	 * @return
	 */
	PTM getPTMbyAccession(String accession);

	/**
	 * String pattern present in the name.
	 * 
	 * @param namePattern
	 * @return
	 */
	List<PTM> getPTMListByPatternName(String namePattern);

	/**
	 * Specificity to filter all the identifications in the
	 * 
	 * @param specificity
	 * @return
	 */
	List<PTM> getPTMListBySpecificity(Specificity specificity);

	/**
	 * Description pattern to found PTMs with the pattern
	 * 
	 * @param descriptionPattern
	 * @return
	 */
	List<PTM> getPTMListByPatternDescription(String descriptionPattern);

	/**
	 * Return all PTMs with the same name. In case of PSI-Mod modifications
	 * different mofifications can have the same name.
	 * 
	 * @param name
	 * @return
	 */
	List<PTM> getPTMListByEqualName(String name);

	/**
	 * Get List of PTMs by Monoisotopic delta mass using a default precision of
	 * 0.00001
	 * 
	 * @param delta
	 * @return
	 */
	List<PTM> getPTMListByMonoDeltaMass(Double delta);

	/**
	 * Get List of PTMs by Monoisotopic delta mass using a custom precision
	 * 
	 * @param delta
	 * @return
	 */
	List<PTM> getPTMListByMonoDeltaMass(Double delta, Double precision);

	/**
	 * Get List PTMs by Average Delta Mass using a default precision of 0.00001
	 * 
	 * @param delta
	 * @return
	 */
	List<PTM> getPTMListByAvgDeltaMass(Double delta);

	/**
	 * Get List PTMs by Average Delta Mass using a custom precision
	 * 
	 * @param delta
	 * @return
	 */
	List<PTM> getPTMListByAvgDeltaMass(Double delta, Double precision);

}
