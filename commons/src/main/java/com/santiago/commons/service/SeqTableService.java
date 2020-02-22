package com.santiago.commons.service;


import com.santiago.commons.domain.SeqTable;

public interface SeqTableService {
    SeqTable getSeqNextValue(SeqTable seqTable);
}
