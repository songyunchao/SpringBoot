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
import com.fsun.domain.condition.BusShopCondition;
import com.fsun.domain.model.BusShop;
import com.fsun.domain.model.SysUser;
import com.fsun.exception.enums.SCMErrorEnum;
import com.fsun.exception.sys.PowerException;
import com.fsun.mapper.BusShopMapper;
import com.fsun.service.BusShopApi;

@Service
public class BusShopService implements BusShopApi {
	
	@Autowired
    private BusShopMapper busShopMapper;

	@Override
	public BusShop load(String id) {
		return busShopMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BusShop> list(BusShopCondition condition) {
		return busShopMapper.selectList(condition);
	}

	@Override
	public PageModel findPage(BusShopCondition condition) {
		List<HashMap<String, Object>> list = busShopMapper.selectListMap(condition);		
		return new PageModel(list);
	}

	@Transactional
	@Override
	public String save(BusShop domain, SysUser currentUser) {
		BusShopCondition condition = new BusShopCondition();
		condition.setShopId(domain.getShopId());
		condition.setShopCode(domain.getShopCode());
		boolean hasUnique= this.unique(condition);
		if(!hasUnique){
			throw new PowerException(SCMErrorEnum.SHOP_EXISTED);
		}
		//保存数据
		Date now = new Date();
		String id = domain.getShopId();
		if(StringUtils.isEmpty(id)){
			domain.setShopId(PKMapping.GUUID(PKMapping.sys_user));
			domain.setCreatedId(currentUser.getId());
			domain.setCreatedTime(now);
			domain.setEnabled(true);
			busShopMapper.insert(domain);
			return domain.getShopId();
		}else{
			BusShop busShop = this.load(id);
			if(busShop==null){
				throw new PowerException(SCMErrorEnum.SHOP_NOT_EXIST);
			}	
			busShop.setShopCode(domain.getShopCode());
			busShop.setShopName(domain.getShopName());
			busShop.setAddress(domain.getAddress());
			busShop.setTel(domain.getTel());
			busShop.setContacts(domain.getContacts());
			busShop.setPosition(domain.getPosition());
			busShop.setMemo(domain.getMemo());
			busShop.setUpdatedId(currentUser.getId());
			busShop.setUpdatedTime(now);
			busShopMapper.updateByPrimaryKey(busShop);
			return busShop.getShopId();
		}		
	}

	@Transactional
	@Override
	public int delete(String ids) {
		if(ids!=null && !ids.equals("")){
			for (String id : ids.split(",")) {
				busShopMapper.deleteByPrimaryKey(id);
			}
			return ids.split(",").length;
		}
		return 0;
	}

	@Override
	public boolean unique(BusShopCondition condition) {
		List<BusShop> list = busShopMapper.selectList(condition);
		if(list==null || list.size()==0){
			return true;
		}else if(list.size()==1){			
			if(list.get(0).getShopId().equals(condition.getShopId())){
				return true;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public void changeStatus(String[] ids, Boolean enabled, SysUser currentUser) {
		Date now = new Date();
		for (String id : ids) {
			BusShop busShop = this.load(id);
			busShop.setUpdatedId(currentUser.getId());
			busShop.setUpdatedTime(now);
			busShop.setEnabled(enabled);
			busShopMapper.updateByPrimaryKeySelective(busShop);
		}
	}

}
