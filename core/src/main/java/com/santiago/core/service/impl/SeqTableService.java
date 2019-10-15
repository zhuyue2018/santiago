package com.santiago.core.service.impl;

import com.zhuyue.pay0929.core.entity.domain.SeqTable;

public interface SeqTableService {
    SeqTable getSeqNextValue(SeqTable seqTable);
}
