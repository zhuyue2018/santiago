package com.santiago.core.entity.domain;

import javax.persistence.*;

@Table(name = "seq_table")
public class SeqTable {
    @Id
    @Column(name = "SEQ_NAME")
    private String seqName;

    @Column(name = "CURRENT_VALUE")
    private Long currentValue;

    @Column(name = "INCREMENT")
    private Short increment;

    @Column(name = "REMARK")
    private String remark;

    /**
     * @return SEQ_NAME
     */
    public String getSeqName() {
        return seqName;
    }

    /**
     * @param seqName
     */
    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    /**
     * @return CURRENT_VALUE
     */
    public Long getCurrentValue() {
        return currentValue;
    }

    /**
     * @param currentValue
     */
    public void setCurrentValue(Long currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * @return INCREMENT
     */
    public Short getIncrement() {
        return increment;
    }

    /**
     * @param increment
     */
    public void setIncrement(Short increment) {
        this.increment = increment;
    }

    /**
     * @return REMARK
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}