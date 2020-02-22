package com.santiago.cg.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "trx_detail")
public class TrxDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trx_serial_no")
    private String trxSerialNo;

    @Column(name = "trx_type")
    private String trxType;

    private BigDecimal amount;

    @Column(name = "resc_account_no")
    private String rescAccountNo;

    @Column(name = "dest_account_no")
    private String destAccountNo;

    @Column(name = "accounting_state")
    private String accountingState;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return trx_serial_no
     */
    public String getTrxSerialNo() {
        return trxSerialNo;
    }

    /**
     * @param trxSerialNo
     */
    public void setTrxSerialNo(String trxSerialNo) {
        this.trxSerialNo = trxSerialNo;
    }

    /**
     * @return trx_type
     */
    public String getTrxType() {
        return trxType;
    }

    /**
     * @param trxType
     */
    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    /**
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return resc_account_no
     */
    public String getRescAccountNo() {
        return rescAccountNo;
    }

    /**
     * @param rescAccountNo
     */
    public void setRescAccountNo(String rescAccountNo) {
        this.rescAccountNo = rescAccountNo;
    }

    /**
     * @return dest_account_no
     */
    public String getDestAccountNo() {
        return destAccountNo;
    }

    /**
     * @param destAccountNo
     */
    public void setDestAccountNo(String destAccountNo) {
        this.destAccountNo = destAccountNo;
    }

    /**
     * @return accounting_state
     */
    public String getAccountingState() {
        return accountingState;
    }

    /**
     * @param accountingState
     */
    public void setAccountingState(String accountingState) {
        this.accountingState = accountingState;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}