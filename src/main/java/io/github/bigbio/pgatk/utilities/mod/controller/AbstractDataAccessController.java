package io.github.bigbio.pgatk.utilities.mod.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.github.bigbio.pgatk.utilities.mod.model.PTM;
import io.github.bigbio.pgatk.utilities.mod.model.Specificity;

/**
 * The abstract data access controller contains the ptmMap a key->value pair
 * object that store all the information for the PTMs in the ontology. It also
 * contains the reference to the original source file that has been used to load
 * the ontology. The abstract controller contains default methods to retrieve,
 * and populate the ontology.
 *
 * @author ypriverol
 */
public class AbstractDataAccessController implements DataAccessController {

	public Map<Comparable, PTM> ptmMap;
	private final InputStream source;
	private static final double DEFAULT_PRECISION = 0.00001;

	/**
	 * Default constructor for Controllers
	 */
	public AbstractDataAccessController(InputStream inputStream) {
		ptmMap = new HashMap<>();
		source = inputStream;
	}

	@Override
	public InputStream getSource() {
		return source;
	}

	/**
	 * Return the PTM with an specific accession.
	 *
	 * @param accession
	 * @return
	 */
	@Override
	public PTM getPTMbyAccession(String accession) {
		return ptmMap.get(accession);
	}

	/**
	 * Return the ptm with an specific pattern in the name
	 *
	 * @param namePattern
	 * @return
	 */
	@Override
	public List<PTM> getPTMListByPatternName(String namePattern) {
		return ptmMap.values().stream()
				.filter(ptm -> ptm.getName().contains(namePattern)).collect(Collectors.toList());
	}

	@Override
	public List<PTM> getPTMListBySpecificity(Specificity specificity) {
		return ptmMap.values().stream()
				.filter(ptm->ptm.getSpecificityCollection().contains(specificity)).collect(Collectors.toList());
	}

	@Override
	public List<PTM> getPTMListByPatternDescription(String descriptionPattern) {
		return ptmMap.values().stream()
				.filter(ptm-> ptm.getDescription() != null
					&& ptm.getDescription().toUpperCase().contains(descriptionPattern.toUpperCase()))
				.collect(Collectors.toList());
	}

	@Override
	public List<PTM> getPTMListByEqualName(String name) {
		return ptmMap.values().stream()
				.filter(ptm-> ptm.getName().equalsIgnoreCase(name))
				.collect(Collectors.toList());
	}

	@Override
	public List<PTM> getPTMListByMonoDeltaMass(Double delta) {
		return getPTMListByMonoDeltaMass(delta, null);
	}

	@Override
	public List<PTM> getPTMListByMonoDeltaMass(Double delta, Double precision) {
		double precisionToUse = DEFAULT_PRECISION;
		if (precision != null) {
			precisionToUse = precision;
		}
		double finalPrecisionToUse = precisionToUse;
		return ptmMap.values().stream()
				.filter(ptm -> ptm.getMonoDeltaMass() != null && Math.abs(ptm.getMonoDeltaMass() - delta) < finalPrecisionToUse)
				.collect(Collectors.toList());
	}

	@Override
	public List<PTM> getPTMListByAvgDeltaMass(Double delta) {
		return getPTMListByAvgDeltaMass(delta, null);
	}

	@Override
	public List<PTM> getPTMListByAvgDeltaMass(Double delta, Double precision) {
		double precisionToUse = DEFAULT_PRECISION;
		if (precision != null) {
			precisionToUse = precision;
		}
		double finalPrecisionToUse = precisionToUse;
		return ptmMap.values().stream()
				.filter(ptm -> ptm.getAveDeltaMass() != null && Math.abs(ptm.getAveDeltaMass() - delta) < finalPrecisionToUse)
				.collect(Collectors.toList());
	}

	public List<PTM> getPTMListByMonoDeltaMassSpecificity(Double mass, String aa) {
		return getPTMListByMonoDeltaMassSpecificity(mass, aa, null);
	}

	private List<PTM> getPTMListByMonoDeltaMassSpecificity(Double mass, String aa, Double precision) {
		final List<PTM> ptms = getPTMListByMonoDeltaMass(mass, precision);
		final List<PTM> ptmsResults = new ArrayList<>();
		if (!ptms.isEmpty()) {
			ptms.stream()
					.filter(ptm -> ptm.getSpecificityCollection().stream()
							.anyMatch(specificity -> specificity.getName()
									.compareTo(Specificity.AminoAcid.valueOf(aa)) == 0))
					.collect(Collectors.toList());
		}
		return ptmsResults;
	}

}
