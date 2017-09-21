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
@TableName("base_user")
public class BaseUser extends Model<BaseUser> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String name;
	private String birthday;
	private String address;
	@TableField("mobile_phone")
	private String mobilePhone;
	@TableField("tel_phone")
	private String telPhone;
	private String email;
	private String sex;
	private String type;
	private String status;
	private String description;
	@TableField("crt_time")
	private Date crtTime;
	@TableField("crt_user")
	private String crtUser;
	@TableField("crt_name")
	private String crtName;
	@TableField("crt_host")
	private String crtHost;
	@TableField("upd_time")
	private Date updTime;
	@TableField("upd_user")
	private String updUser;
	@TableField("upd_name")
	private String updName;
	@TableField("upd_host")
	private String updHost;
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
