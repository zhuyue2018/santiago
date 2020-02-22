package com.santiago.cg.entity.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pms_operator_log")
public class PmsOperatorLog {
    /**
     * 主键
     */
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

    private String status;

    private String remark;

    @Column(name = "operator_id")
    private Long operatorId;

    @Column(name = "operator_name")
    private String operatorName;

    /**
     * 操作类型（1:增加，2:修改，3:删除，4:查询）
     */
    @Column(name = "operate_type")
    private String operateType;

    private String ip;

    private String content;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
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
     * @return operator_id
     */
    public Long getOperatorId() {
        return operatorId;
    }

    /**
     * @param operatorId
     */
    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * @return operator_name
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * @param operatorName
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     * 获取操作类型（1:增加，2:修改，3:删除，4:查询）
     *
     * @return operate_type - 操作类型（1:增加，2:修改，3:删除，4:查询）
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置操作类型（1:增加，2:修改，3:删除，4:查询）
     *
     * @param operateType 操作类型（1:增加，2:修改，3:删除，4:查询）
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}