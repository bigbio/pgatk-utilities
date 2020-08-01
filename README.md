# pgatk utilities library


Pgatk utilities library is a Java API to handle post-translational modifications (PTMs) in proteomics experiments. The library provide a common representation between different PTMs databases such as [UNIMOD](www.ebi.ac.uk/pride/archive/) , [PSI-MOD](http://www.psidev.info/MOD) and the PRIDE Modification Slim Ontology.

It also provide a the corresponding parsers for UNIMOD and PSI-MOD databases. It can be use to retrieve an specific modification using Accessions, Amino Acids, Delta Masses (monoisotopic or average). 

# How to cite it

Perez-Riverol, Yasset, Julian Uszkoreit, Aniel Sanchez, Tobias Ternent, Noemi del Toro, Henning Hermjakob, Juan Antonio Vizcaíno, and Rui Wang. "ms-data-core-api: an open-source, metadata-oriented library for computational proteomics."
Bioinformatics 31, no. 17 (2015): 2903-2905. [article](https://bioinformatics.oxfordjournals.org/content/31/17/2903.full)

# Main Features

* Support UNIMOD and PSI-MOD databases
* Common representation for all kind of modifications.
* Common API to retrieve modifications based on: Accessions, Amino Acids, Delta Masses, etc.
* Retrieve modifications by String patterns in Names or Descriptions.
* Retrieve Group of Modifications based on common Names or Specificity Groups.

# Availability and Version
* Current version is 2.0.0-SNAPSHOT
* The jara library can be download from the [EBI maven repository](http://www.ebi.ac.uk/Tools/maven/repos/content/groups/ebi-repo/).


How to use pratk-utilities
===============

* How to retrieve all modifications in PSI-Mod and UNIMOD with 'phospho' pattern in the Description:

```java
ModReader modReader = ModReader.getInstance();
List<PTM> ptms = modReader.getPTMListByPatternDescription("Phospho");
assertTrue("Number of PTMs with Term 'Phospho' in name:", ptms.size() == 102);
```
