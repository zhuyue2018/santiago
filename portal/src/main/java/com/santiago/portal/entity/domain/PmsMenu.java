package com.santiago.portal.entity.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "pms_menu")
public class PmsMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long version;

    /**
     * 创建人
     */
    private String creater;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 修改人
     */
    private String editor;

    /**
     * 修改时间
     */
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 账户状态：0：未激活，1：正常， 2：暂停使用， 3：关闭
     */
    private String status;

    private String remark;

    @Column(name = "is_leaf")
    private String isLeaf;

    /**
     * 1：系统级 2：菜单级 3：按钮级
     */
    private Short level;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "target_name")
    private String targetName;

    private String number;

    private String name;

    private String url;

    private List<PmsMenu> sonMenus;

    public List<PmsMenu> getSonMenus() {
        return sonMenus;
    }

    public void setSonMenus(List<PmsMenu> sonMenus) {
        this.sonMenus = sonMenus;
    }

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
     * 获取创建人
     *
     * @return creater - 创建人
     */
    public String getCreater() {
        return creater;
    }

    /**
     * 设置创建人
     *
     * @param creater 创建人
     */
    public void setCreater(String creater) {
        this.creater = creater;
    }

    /**
     * 获取创建时间
     *
     * @return gmt_create - 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取修改人
     *
     * @return editor - 修改人
     */
    public String getEditor() {
        return editor;
    }

    /**
     * 设置修改人
     *
     * @param editor 修改人
     */
    public void setEditor(String editor) {
        this.editor = editor;
    }

    /**
     * 获取修改时间
     *
     * @return gmt_modified - 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     *
     * @param gmtModified 修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * 获取账户状态：0：未激活，1：正常， 2：暂停使用， 3：关闭
     *
     * @return status - 账户状态：0：未激活，1：正常， 2：暂停使用， 3：关闭
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置账户状态：0：未激活，1：正常， 2：暂停使用， 3：关闭
     *
     * @param status 账户状态：0：未激活，1：正常， 2：暂停使用， 3：关闭
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return is_leaf
     */
    public String getIsLeaf() {
        return isLeaf;
    }

    /**
     * @param isLeaf
     */
    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * 获取1：系统级 2：菜单级 3：按钮级
     *
     * @return level - 1：系统级 2：菜单级 3：按钮级
     */
    public Short getLevel() {
        return level;
    }

    /**
     * 设置1：系统级 2：菜单级 3：按钮级
     *
     * @param level 1：系统级 2：菜单级 3：按钮级
     */
    public void setLevel(Short level) {
        this.level = level;
    }

    /**
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return target_name
     */
    public String getTargetName() {
        return targetName;
    }

    /**
     * @param targetName
     */
    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    /**
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
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
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}