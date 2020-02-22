package com.santiago.cg.entity.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_pri_menu")
public class SysPriMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "p_id")
    private Integer pId;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "manu_url")
    private String manuUrl;

    @Column(name = "pri_level")
    private String priLevel;

    @Column(name = "pri_path")
    private String priPath;

    private String status;

    @Column(name = "creator_id")
    private Integer creatorId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return p_id
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * @param pId
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }

    /**
     * @return menu_name
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * @param menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * @return manu_url
     */
    public String getManuUrl() {
        return manuUrl;
    }

    /**
     * @param manuUrl
     */
    public void setManuUrl(String manuUrl) {
        this.manuUrl = manuUrl;
    }

    /**
     * @return pri_level
     */
    public String getPriLevel() {
        return priLevel;
    }

    /**
     * @param priLevel
     */
    public void setPriLevel(String priLevel) {
        this.priLevel = priLevel;
    }

    /**
     * @return pri_path
     */
    public String getPriPath() {
        return priPath;
    }

    /**
     * @param priPath
     */
    public void setPriPath(String priPath) {
        this.priPath = priPath;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return creator_id
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * @param creatorId
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
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