package com.santiago.core.service;


import com.santiago.core.entity.domain.SeqTable;

public interface SeqTableService {
    SeqTable getSeqNextValue(SeqTable seqTable);
}