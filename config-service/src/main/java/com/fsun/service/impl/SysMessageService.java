package com.fsun.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsun.domain.common.PageModel;
import com.fsun.domain.condition.SysMessageCondition;
import com.fsun.domain.model.SysMessage;
import com.fsun.domain.model.SysUser;
import com.fsun.exception.enums.SCMErrorEnum;
import com.fsun.exception.sys.MessageException;
import com.fsun.mapper.SysMessageMapper;
import com.fsun.service.SysMessageApi;

@Service
public class SysMessageService implements SysMessageApi {
	
	@Autowired
    private SysMessageMapper sysMessageMapper;

	@Override
	public SysMessage load(String id) {
		return sysMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysMessage> list(SysMessageCondition condition) {
		return sysMessageMapper.selectList(condition);
	}

	@Override
	public PageModel findPage(SysMessageCondition condition) {
		List<HashMap<String, Object>> list = sysMessageMapper.selectListMap(condition);		
		return new PageModel(list);
	}

	@Override
	public String save(SysMessage domain, SysUser currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public int delete(String ids) {
		if(ids!=null && !ids.equals("")){
			for (String id : ids.split(",")) {
				sysMessageMapper.deleteByPrimaryKey(id);
			}
			return ids.split(",").length;
		}
		return 0;
	}

	@Transactional
	@Override
	public int changeStatus(String[] ids, Short status, SysUser currentUser) {
		if(ids!=null && !ids.equals("")){
			Date now = new Date();
			for (String id : ids) {
				SysMessage sysMessage = sysMessageMapper.selectByPrimaryKey(id);
				if(sysMessage == null){
					throw new MessageException(SCMErrorEnum.MESSAGE_NOT_EXIST);
				}
				//只有在排队的任务可以取消
				Short currStatus = sysMessage.getStatus();
				if(currStatus != 1){
					throw new MessageException(SCMErrorEnum.MESSAGE_STATUS_ILLEGAL);
				}	
				SysMessage currMessage = new SysMessage(); 
				currMessage.setId(id);
				currMessage.setUpdateName(currentUser.getRealname());
				currMessage.setUpdateTime(now);
				currMessage.setStatus(status);
				sysMessageMapper.updateByPrimaryKeySelective(currMessage);
			}
			return ids.length;
		}
		return 0;
	}

}
