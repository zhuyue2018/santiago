package com.santiago.core.service.impl;

import com.santiago.core.entity.domain.SeqTable;
import com.santiago.core.mapper.SeqTableMapper;
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
