package com.fsun.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsun.common.utils.PKMapping;
import com.fsun.common.utils.StringUtils;
import com.fsun.domain.common.PageModel;
import com.fsun.domain.condition.SysMenuCondition;
import com.fsun.domain.dto.MenuTreeDto;
import com.fsun.domain.model.SysMenu;
import com.fsun.domain.model.SysUser;
import com.fsun.exception.enums.SCMErrorEnum;
import com.fsun.exception.sys.PowerException;
import com.fsun.mapper.SysMenuMapper;
import com.fsun.service.SysMenuApi;

/***
 * 
 * @ClassName: SysMenuService 
 * @Description: 菜单模块接口实现
 * @author fsun 
 * @date 2019年6月23日 下午4:23:19 
 *
 */
@Service
public class SysMenuService implements SysMenuApi {

	@Autowired
    private SysMenuMapper sysMenuMapper;
	
	@Override
	public boolean unique(SysMenuCondition condition) {
		List<SysMenu> list = sysMenuMapper.selectList(condition);
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
	public SysMenu load(String id) {
		return sysMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysMenu> list(SysMenuCondition condition) {
		return sysMenuMapper.selectList(condition);
	}

	@Override
	public PageModel findPage(SysMenuCondition condition) {
		List<HashMap<String, Object>> list = sysMenuMapper.selectListMap(condition);		
		return new PageModel(list);
	}

	@Override
	public List<MenuTreeDto> findMenuTree(String pid) {	
		List<SysMenu> menus = sysMenuMapper.selectList(new SysMenuCondition());	
		List<MenuTreeDto> resultMenus = new ArrayList<>();		
		for(SysMenu menu : menus){	
			if(pid != null){
				if(pid.equals(menu.getPid())){
					MenuTreeDto parentNode = this.initParentNode(menu);
					this.addChildren(parentNode, menus);
					resultMenus.add(parentNode);
				}
			}else{
				if(menu.getPid()==null){
					MenuTreeDto parentNode = this.initParentNode(menu);
					this.addChildren(parentNode, menus);
					resultMenus.add(parentNode);
				}
			}						
		}		
		return resultMenus;
	}

	@Override
	public List<MenuTreeDto> findModulesByUser(String username) {
		List<SysMenu> menus = sysMenuMapper.findMenusByUsername(username);
		List<MenuTreeDto> resultMenus = new ArrayList<>();		
		for(SysMenu menu : menus){	
			if(menu.getPid()==null){
				MenuTreeDto parentNode = this.initParentNode(menu);
				this.addChildren(parentNode, menus);
				resultMenus.add(parentNode);
			}			
		}		
		return resultMenus;
	}
	
	@Transactional
	@Override
	public String save(SysMenu domain, SysUser currentUser) {
		SysMenuCondition condition = new SysMenuCondition();
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
			sysMenuMapper.insert(domain);
			return domain.getId();
		}else{
			SysMenu SysMenu = this.load(id);
			if(SysMenu==null){
				throw new PowerException(SCMErrorEnum.POWER_NOT_EXIST);
			}	
			SysMenu.setName(domain.getName());
			SysMenu.setId(domain.getId());
			SysMenu.setUrl(domain.getUrl());
			SysMenu.setPriority(domain.getPriority());			
			SysMenu.setDescription(domain.getDescription());
			SysMenu.setUpdateManId(currentUser.getId());
			SysMenu.setUpdateTime(now);
			sysMenuMapper.updateByPrimaryKey(SysMenu);
			return SysMenu.getId();
		}		
	}

	@Transactional
	@Override
	public int delete(String ids) {
		if(ids!=null && !ids.equals("")){
			for (String id : ids.split(",")) {
				sysMenuMapper.deleteByPrimaryKey(id);
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
			SysMenu sysMenu = this.load(powerId);
			sysMenu.setUpdateManId(currentUser.getId());
			sysMenu.setUpdateTime(now);
			sysMenu.setEnabled(enabled);
			sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
		}
	}
	

	/***********************************  内部方法     *************************************/
	
	/**
	 * 获取父节点
	 * @return
	 */
	private MenuTreeDto initParentNode(SysMenu sysMenu) {				
		MenuTreeDto rootNode = new MenuTreeDto();
		rootNode.setId(sysMenu.getId());
		rootNode.setText(sysMenu.getName());
		rootNode.setIsleaf(false);
		rootNode.setIconCls("icon-bricks");
		rootNode.setState("open");
		rootNode.setUrl(sysMenu.getUrl());
		rootNode.setIcon(sysMenu.getIconcls());
		return rootNode;		
	}
		
		
	/**
	 * 递归获取子节点
	 * @param parentNode
	 * @param list
	 */
	private void addChildren(MenuTreeDto parentNode, List<SysMenu> list) {
		List<MenuTreeDto> childrenNode = new ArrayList<>();
		for (SysMenu sysMenu : list) {
			if (sysMenu.getPid() != null && sysMenu.getPid().equals(parentNode.getId())) {
				MenuTreeDto childNode = new MenuTreeDto();
				childNode.setId(sysMenu.getId());
				childNode.setText(sysMenu.getName());				
				childNode.setIsleaf(true);
				childNode.setIconCls("icon-bricks");
				childNode.setState("open");
				childNode.setUrl(sysMenu.getUrl());
				childNode.setIcon(sysMenu.getIconcls());
				this.addChildren(childNode, list);
				childrenNode.add(childNode);
			}
		}		
		if(childrenNode.size()>0 && !"0".equals(parentNode.getId())){	
			parentNode.setIconCls("icon-rgb");
			parentNode.setState("closed");					
			parentNode.setIsleaf(false);			
		}	
		parentNode.setChildren(childrenNode);
	}


}
