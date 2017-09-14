package com.github.markzhl.admin.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "base_form")
public class Form {
    @Id
    private Integer id;

    /**
     * 表单名称
     */
    @Column(name = "form_name")
    private String formName;

    /**
     * 表单描述
     */
    @Column(name = "form_desc")
    private String formDesc;

    /**
     * 字段总数
     */
    private Short fields;

    @Column(name = "crt_time")
    private Date crtTime;

    @Column(name = "crt_user")
    private String crtUser;

    @Column(name = "crt_name")
    private String crtName;

    @Column(name = "crt_host")
    private String crtHost;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;

    private String attr5;

    private String attr6;

    private String attr7;

    private String attr8;

    /**
     * 表单原html模板未经处理的
     */
    private String content;

    /**
     * 表单替换的模板 经过处理
     */
    @Column(name = "content_parse")
    private String contentParse;

    /**
     * 表单中的字段数据
     */
    @Column(name = "content_data")
    private String contentData;

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
     * 获取表单名称
     *
     * @return form_name - 表单名称
     */
    public String getFormName() {
        return formName;
    }

    /**
     * 设置表单名称
     *
     * @param formName 表单名称
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }

    /**
     * 获取表单描述
     *
     * @return form_desc - 表单描述
     */
    public String getFormDesc() {
        return formDesc;
    }

    /**
     * 设置表单描述
     *
     * @param formDesc 表单描述
     */
    public void setFormDesc(String formDesc) {
        this.formDesc = formDesc;
    }

    /**
     * 获取字段总数
     *
     * @return fields - 字段总数
     */
    public Short getFields() {
        return fields;
    }

    /**
     * 设置字段总数
     *
     * @param fields 字段总数
     */
    public void setFields(Short fields) {
        this.fields = fields;
    }

    /**
     * @return crt_time
     */
    public Date getCrtTime() {
        return crtTime;
    }

    /**
     * @param crtTime
     */
    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * @return crt_user
     */
    public String getCrtUser() {
        return crtUser;
    }

    /**
     * @param crtUser
     */
    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    /**
     * @return crt_name
     */
    public String getCrtName() {
        return crtName;
    }

    /**
     * @param crtName
     */
    public void setCrtName(String crtName) {
        this.crtName = crtName;
    }

    /**
     * @return crt_host
     */
    public String getCrtHost() {
        return crtHost;
    }

    /**
     * @param crtHost
     */
    public void setCrtHost(String crtHost) {
        this.crtHost = crtHost;
    }

    /**
     * @return attr1
     */
    public String getAttr1() {
        return attr1;
    }

    /**
     * @param attr1
     */
    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    /**
     * @return attr2
     */
    public String getAttr2() {
        return attr2;
    }

    /**
     * @param attr2
     */
    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    /**
     * @return attr3
     */
    public String getAttr3() {
        return attr3;
    }

    /**
     * @param attr3
     */
    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    /**
     * @return attr4
     */
    public String getAttr4() {
        return attr4;
    }

    /**
     * @param attr4
     */
    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    /**
     * @return attr5
     */
    public String getAttr5() {
        return attr5;
    }

    /**
     * @param attr5
     */
    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    /**
     * @return attr6
     */
    public String getAttr6() {
        return attr6;
    }

    /**
     * @param attr6
     */
    public void setAttr6(String attr6) {
        this.attr6 = attr6;
    }

    /**
     * @return attr7
     */
    public String getAttr7() {
        return attr7;
    }

    /**
     * @param attr7
     */
    public void setAttr7(String attr7) {
        this.attr7 = attr7;
    }

    /**
     * @return attr8
     */
    public String getAttr8() {
        return attr8;
    }

    /**
     * @param attr8
     */
    public void setAttr8(String attr8) {
        this.attr8 = attr8;
    }

    /**
     * 获取表单原html模板未经处理的
     *
     * @return content - 表单原html模板未经处理的
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置表单原html模板未经处理的
     *
     * @param content 表单原html模板未经处理的
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取表单替换的模板 经过处理
     *
     * @return content_parse - 表单替换的模板 经过处理
     */
    public String getContentParse() {
        return contentParse;
    }

    /**
     * 设置表单替换的模板 经过处理
     *
     * @param contentParse 表单替换的模板 经过处理
     */
    public void setContentParse(String contentParse) {
        this.contentParse = contentParse;
    }

    /**
     * 获取表单中的字段数据
     *
     * @return content_data - 表单中的字段数据
     */
    public String getContentData() {
        return contentData;
    }

    /**
     * 设置表单中的字段数据
     *
     * @param contentData 表单中的字段数据
     */
    public void setContentData(String contentData) {
        this.contentData = contentData;
    }
}