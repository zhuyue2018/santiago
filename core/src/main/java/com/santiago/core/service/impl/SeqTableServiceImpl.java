package com.santiago.core.service.impl;

import com.zhuyue.pay0929.core.entity.domain.SeqTable;
import com.zhuyue.pay0929.core.mapper.SeqTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeqTableServiceImpl implements SeqTableService {
    @Autowired
    SeqTableMapper seqTableMapper;
    @Override
    public SeqTable getSeqNextValue(SeqTable seqTable) {
        SeqTable table = seqTableMapper.selectOne(seqTable);
        table.setCurrentValue(table.getCurrentValue() + table.getIncrement());
        seqTableMapper.updateByPrimaryKey(table);
        return table;
    }
}
