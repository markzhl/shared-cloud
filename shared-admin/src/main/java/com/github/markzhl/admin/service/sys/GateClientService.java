package com.github.markzhl.admin.service.sys;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.github.markzhl.admin.entity.sys.BaseResource;
import com.github.markzhl.admin.entity.sys.GateClient;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mark
 * @since 2017-09-20
 */
public interface GateClientService extends IService<GateClient> {
	public void modifyClientServices(Long id, String services);
	public List<BaseResource> getClientServices(Long id);
}
