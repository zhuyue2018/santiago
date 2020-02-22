package com.santiago.cg.entity.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "accounting_detail")
public class AccountingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trx_serial_no")
    private String trxSerialNo;

    @Column(name = "account_no")
    private String accountNo;

    private String direction;

    private BigDecimal amount;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return account_no
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * @return direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction
     */
    public void setDirection(String direction) {
        this.direction = direction;
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
}