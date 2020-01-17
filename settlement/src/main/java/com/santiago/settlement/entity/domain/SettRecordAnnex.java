package com.santiago.settlement.entity.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sett_record_annex")
public class SettRecordAnnex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    private Long version;

    private String remark;

    @Column(name = "is_delete")
    private String isDelete;

    @Column(name = "annex_name")
    private String annexName;

    @Column(name = "annex_address")
    private String annexAddress;

    @Column(name = "settlement_id")
    private String settlementId;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return gmt_modified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * @param gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * @return version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * @return remark
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

    /**
     * @return is_delete
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return annex_name
     */
    public String getAnnexName() {
        return annexName;
    }

    /**
     * @param annexName
     */
    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }

    /**
     * @return annex_address
     */
    public String getAnnexAddress() {
        return annexAddress;
    }

    /**
     * @param annexAddress
     */
    public void setAnnexAddress(String annexAddress) {
        this.annexAddress = annexAddress;
    }

    /**
     * @return settlement_id
     */
    public String getSettlementId() {
        return settlementId;
    }

    /**
     * @param settlementId
     */
    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId;
    }
}