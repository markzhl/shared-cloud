package com.github.markzhl.admin.entity.sys;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
@Data
@Accessors(chain = true)
@TableName("base_form")
public class BaseForm extends Model<BaseForm> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.ID_WORKER)
	private Long id;
    /**
     * 表单名称
     */
	@TableField("form_name")
	private String formName;
    /**
     * 表单描述
     */
	@TableField("form_desc")
	private String formDesc;
    /**
     * 表单原html模板未经处理的
     */
	private String content;
    /**
     * 表单替换的模板 经过处理
     */
	@TableField("content_parse")
	private String contentParse;
    /**
     * 表单中的字段数据
     */
	@TableField("content_data")
	private String contentData;
    /**
     * 字段总数
     */
	private Integer fields;
	@TableField("crt_time")
	private Date crtTime;
	@TableField("crt_user")
	private String crtUser;
	@TableField("crt_name")
	private String crtName;
	@TableField("crt_host")
	private String crtHost;
	private String attr1;
	private String attr2;
	private String attr3;
	private String attr4;
	private String attr5;
	private String attr6;
	private String attr7;
	private String attr8;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
