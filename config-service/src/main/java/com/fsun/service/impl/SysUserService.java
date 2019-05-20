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
import com.fsun.domain.condition.SysUserCondition;
import com.fsun.domain.model.SysUser;
import com.fsun.exception.enums.SCMErrorEnum;
import com.fsun.exception.sys.UserException;
import com.fsun.mapper.SysUserMapper;
import com.fsun.service.SysUserApi;

/**
 * 用户接口实现类
 * @ClassName: SysUserService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author fsun 
 * @date 2019年5月16日 下午4:06:16 
 *
 */

@Service
public class SysUserService implements SysUserApi {
   
	@Autowired
    private SysUserMapper sysUserMapper;
    
    @Override
	public boolean unique(SysUserCondition condition) {
		List<SysUser> list = sysUserMapper.selectList(condition);
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
	public SysUser load(String id) {
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysUser> list(SysUserCondition condition) {
		return sysUserMapper.selectList(condition);
	}

	@Override
	public PageModel findPage(SysUserCondition condition) {
		List<HashMap<String, Object>> list = sysUserMapper.selectListMap(condition);
		return new PageModel(list);
	}

	@Transactional
	@Override
	public String save(SysUser domain, SysUser currentUser) {
		
		SysUserCondition condition = new SysUserCondition();
		condition.setId(domain.getId());
		condition.setUsername(domain.getUsername());
		boolean hasUnique= this.unique(condition);
		if(!hasUnique){
			throw new UserException(SCMErrorEnum.USER_EXISTED);
		}
		//保存数据
		Date now = new Date();
		String id = domain.getId();
		if(StringUtils.isEmpty(id)){
			domain.setId(PKMapping.GUUID(PKMapping.sys_user));
			domain.setCreateManId(currentUser.getId());
			domain.setCreateTime(now);
			domain.setEnabled(true);
			domain.setIssys(false);
			sysUserMapper.insert(domain);
			return domain.getId();
		}else{
			SysUser sysUser = this.load(id);
			if(sysUser==null){
				throw new UserException(SCMErrorEnum.USER_NOT_EXIST);
			}				
			sysUser.setRealname(domain.getRealname());
			sysUser.setPriority(domain.getPriority());
			sysUser.setEmail(domain.getEmail());
			sysUser.setDescription(domain.getDescription());
			sysUser.setUpdateManId(currentUser.getId());
			sysUser.setUpdateTime(now);
			sysUserMapper.updateByPrimaryKey(sysUser);
			return sysUser.getId();
		}		
	}

	@Transactional
	@Override
	public int delete(String ids) {
		if(ids!=null && !ids.equals("")){
			for (String id : ids.split(",")) {
				sysUserMapper.deleteByPrimaryKey(id);
			}
			return ids.split(",").length;
		}
		return 0;
	}

	@Transactional
	@Override
	public void changeStatus(String[] userIds, Boolean enabled, SysUser user) {
		Date now = new Date();
		for (String userId : userIds) {
			SysUser sysUser = this.load(userId);
			sysUser.setUpdateManId(user.getId());
			sysUser.setUpdateTime(now);
			sysUser.setEnabled(enabled);
			sysUserMapper.updateByPrimaryKeySelective(sysUser);
		}
	}
}
