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
@TableName("gate_log")
public class GateLog extends Model<GateLog> {

    private static final long serialVersionUID = 1L;
    
    @TableId(value="id", type= IdType.ID_WORKER)
	private Long id;
	private String menu;
	private String opt;
	private String uri;
	@TableField("crt_time")
	private Date crtTime;
	@TableField("crt_user")
	private String crtUser;
	@TableField("crt_name")
	private String crtName;
	@TableField("crt_host")
	private String crtHost;
	
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
