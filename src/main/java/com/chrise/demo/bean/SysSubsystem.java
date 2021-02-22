package com.chrise.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 子系统管理表(SysSubsystem)实体类
 *
 * @author ioc
 * @since 2020-10-10 16:54:38
 */
@Data
@Table(name = "sys_subsystem")
public class SysSubsystem implements Serializable {
    private static final long serialVersionUID = 530963422154085679L;
    /**
     * 子系统ID，主键，自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 子系统名称
     */
    @Column(name="name")
    private String name;
    /**
     * 子系统访问路径
     */
    @Column(name="path")
    private String path;
    /**
     * 顺序
     */
    private Integer orderNum;
    /**
     * 子系统图标URL
     */
    @Column(name="icon_url")
    private String iconUrl;
    /**
     * 子系统类别，字典
     */
    @Column(name="category")
    private String category;
    /**
     * 子系统状态（0正常 1停用）
     */
    @Column(name="status")
    private String status;

    /** 创建者 */
    @Column(name="create_by")
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="create_time")
    private Date createTime;

    /**
     * 备注
     */
    @Column(name="remark")
    private String remark;

    /**
     * 删除标记
     */
    @Column(name="del_flag")
    private String delFlag;

    /**
     * 所有权
     */
    @Column(name="owner")
    private String owner;

    /**
     * 钥匙
     */
    @Column(name="topic")
    private String topic;

    /**
     * 钥匙
     */
    @Column(name="system_key")
    private String systemKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    @Override
    public String toString() {
        return "SysSubsystem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", orderNum=" + orderNum +
                ", iconUrl='" + iconUrl + '\'' +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", remark='" + remark + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", owner='" + owner + '\'' +
                ", topic='" + topic + '\'' +
                ", systemKey='" + systemKey + '\'' +
                '}';
    }
}