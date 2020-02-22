package com.santiago.cg.entity.domain;

import javax.persistence.*;

@Table(name = "recon_interface")
public class ReconInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private String desc;

    /**
     * 0：未激活，1：正常， 2：暂停使用， 3：关闭
     */
    private String status;

    /**
     * 对账周期，单位为天。比如1表示今天对昨天的账，2表示对前天的账
     */
    @Column(name = "recon_period")
    private Integer reconPeriod;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取0：未激活，1：正常， 2：暂停使用， 3：关闭
     *
     * @return status - 0：未激活，1：正常， 2：暂停使用， 3：关闭
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置0：未激活，1：正常， 2：暂停使用， 3：关闭
     *
     * @param status 0：未激活，1：正常， 2：暂停使用， 3：关闭
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取对账周期，单位为天。比如1表示今天对昨天的账，2表示对前天的账
     *
     * @return recon_period - 对账周期，单位为天。比如1表示今天对昨天的账，2表示对前天的账
     */
    public Integer getReconPeriod() {
        return reconPeriod;
    }

    /**
     * 设置对账周期，单位为天。比如1表示今天对昨天的账，2表示对前天的账
     *
     * @param reconPeriod 对账周期，单位为天。比如1表示今天对昨天的账，2表示对前天的账
     */
    public void setReconPeriod(Integer reconPeriod) {
        this.reconPeriod = reconPeriod;
    }
}