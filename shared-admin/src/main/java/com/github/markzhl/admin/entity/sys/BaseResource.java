package com.github.markzhl.admin.entity.sys;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

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
@TableName("base_resource")
public class BaseResource extends Model<BaseResource> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.ID_WORKER)
	private Long id;
	private String code;
	private String type;
	private String name;
	private String uri;
	@TableField("menu_id")
	private String menuId;
	@TableField("parent_id")
	private String parentId;
	private String path;
	private String method;
	private String description;
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
