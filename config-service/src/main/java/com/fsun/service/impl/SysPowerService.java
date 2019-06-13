package com.fsun.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsun.common.utils.PKMapping;
import com.fsun.common.utils.StringUtils;
import com.fsun.domain.common.PageModel;
import com.fsun.domain.condition.SysPowerCondition;
import com.fsun.domain.model.SysPower;
import com.fsun.domain.model.SysUser;
import com.fsun.exception.enums.SCMErrorEnum;
import com.fsun.exception.sys.PowerException;
import com.fsun.mapper.SysPowerMapper;
import com.fsun.service.SysPowerApi;

/**
 * 
 * @ClassName: SysPowerService 
 * @Description: 权限操作接口
 * @author fsun 
 * @date 2019年5月20日 下午1:41:04 
 *
 */
@Service
public class SysPowerService implements SysPowerApi {

	@Autowired
    private SysPowerMapper sysPowerMapper;
	
	@Override
	public boolean unique(SysPowerCondition condition) {
		List<SysPower> list = sysPowerMapper.selectList(condition);
		if(list==null || list.size()==0){
			return true;
		}else if(list.size()==1){			
			if(list.get(0).getId().equals(condition.getId())){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public SysPower load(String id) {
		return sysPowerMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysPower> list(SysPowerCondition condition) {
		return sysPowerMapper.selectList(condition);
	}

	@Override
	public PageModel findPage(SysPowerCondition condition) {
		List<HashMap<String, Object>> list = sysPowerMapper.selectListMap(condition);		
		return new PageModel(list);
	}

	@Transactional
	@Override
	public String save(SysPower domain, SysUser currentUser) {
		SysPowerCondition condition = new SysPowerCondition();
		condition.setId(domain.getId());
		condition.setCode(domain.getCode());
		boolean hasUnique= this.unique(condition);
		if(!hasUnique){
			throw new PowerException(SCMErrorEnum.POWER_EXISTED);
		}
		//保存数据
		Date now = new Date();
		String id = domain.getId();
		if(StringUtils.isEmpty(id)){
			domain.setId(PKMapping.GUUID(PKMapping.sys_user));
			domain.setCreateManId(currentUser.getId());
			domain.setCreateTime(now);
			domain.setEnabled(true);
			sysPowerMapper.insert(domain);
			return domain.getId();
		}else{
			SysPower sysPower = this.load(id);
			if(sysPower==null){
				throw new PowerException(SCMErrorEnum.POWER_NOT_EXIST);
			}	
			sysPower.setName(domain.getName());
			sysPower.setMenuId(domain.getMenuId());
			sysPower.setUrl(domain.getUrl());
			sysPower.setPriority(domain.getPriority());			
			sysPower.setDescription(domain.getDescription());
			sysPower.setUpdateManId(currentUser.getId());
			sysPower.setUpdateTime(now);
			sysPowerMapper.updateByPrimaryKey(sysPower);
			return sysPower.getId();
		}		
	}

	@Transactional
	@Override
	public int delete(String ids) {
		if(ids!=null && !ids.equals("")){
			for (String id : ids.split(",")) {
				sysPowerMapper.deleteByPrimaryKey(id);
			}
			return ids.split(",").length;
		}
		return 0;
	}

	@Transactional
	@Override
	public void changeStatus(String[] powerIds, Boolean enabled, SysUser currentUser) {
		Date now = new Date();
		for (String powerId : powerIds) {
			SysPower sysPower = this.load(powerId);
			sysPower.setUpdateManId(currentUser.getId());
			sysPower.setUpdateTime(now);
			sysPower.setEnabled(enabled);
			sysPowerMapper.updateByPrimaryKeySelective(sysPower);
		}
	}

}
