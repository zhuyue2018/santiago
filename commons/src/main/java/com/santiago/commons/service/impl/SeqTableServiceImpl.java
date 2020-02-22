package com.santiago.commons.service.impl;

import com.santiago.commons.domain.SeqTable;
import com.santiago.commons.mapper.SeqTableMapper;
import com.santiago.commons.service.SeqTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeqTableServiceImpl implements SeqTableService {
    @Autowired
    SeqTableMapper seqTableMapper;
    @Override
    public synchronized SeqTable getSeqNextValue(SeqTable seqTable) {
        SeqTable table = seqTableMapper.selectOne(seqTable);
        table.setCurrentValue(table.getCurrentValue() + table.getIncrement());
        seqTableMapper.updateByPrimaryKey(table);
        return table;
    }
}
