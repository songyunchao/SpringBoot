package com.fsun.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsun.common.utils.StringUtils;
import com.fsun.domain.common.HttpResult;
import com.fsun.domain.common.PageModel;
import com.fsun.domain.condition.SysMenuCondition;
import com.fsun.domain.dto.MenuTreeDto;
import com.fsun.domain.model.SysMenu;
import com.fsun.domain.model.SysUser;
import com.fsun.exception.common.SCMException;
import com.fsun.exception.enums.SCMErrorEnum;
import com.fsun.exception.sys.PowerException;
import com.fsun.service.SysMenuApi;
import com.fsun.web.controller.base.BaseController;

@RestController
@RequestMapping("sys/menu")
public class MenuController extends BaseController {
	
	@Autowired
	private SysMenuApi sysMenuApi;

      
	/**
	 * 
	 * @Title: load 
	 * @Description: 获取单个菜单信息 
	 * @param @param id
	 * @param @return 
	 * @return HttpResult
	 */
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult load(@PathVariable("id") String id) {
		try {
			SysMenu sysMenu = sysMenuApi.load(id);
			return success(sysMenu);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
   /**
    * 根据username码判断是否已存在，不允许相同
    * @param id
    * @param code
    * @return
    */
    @ResponseBody
    @RequestMapping(value = "/unique", method = {RequestMethod.GET})
    public HttpResult unique(SysMenuCondition condition) {  
    	try {
    		boolean isUnique = sysMenuApi.unique(condition);
    		return success(isUnique);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
    }

    /**
	 * 获取用户对应的模块列表
	 * @return
	 */
	@RequestMapping("/findMenuTree")
	@ResponseBody
	public HttpResult findMenuTree(String pid) {
		try {
			List<MenuTreeDto> list = sysMenuApi.findMenuTree(pid);
			return success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return success(new ArrayList<>());
		}
	}
	
	/**
	 * 获取当前用户对应的菜单列表
	 * @return
	 */
	@RequestMapping("/current/findMenuTree")
	@ResponseBody
	public HttpResult findMenuTreeByUser() {
		try {
			SysUser user = getCurrentUser();
			List<MenuTreeDto> list = sysMenuApi.findModulesByUser(user.getUsername());
			return success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return success(new ArrayList<>());
		}
	}
	
	/**
     * 分页条件查询操作
     * @Title: findPage 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param condition
     * @param @return 
     * @return HttpResult
     */
	@RequestMapping(value="/findPage", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public HttpResult findPage(@RequestBody SysMenuCondition condition) {
		try {
			PageModel pageModel = sysMenuApi.findPage(condition);
			return success(pageModel);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 
	 * @Title: list 
	 * @Description: 列表条件查询操作
	 * @param @param condition
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public HttpResult list(SysMenuCondition condition) {
		try {
			List<SysMenu> list = sysMenuApi.list(condition);
			return success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 
	 * @Title: save 
	 * @Description: 新增或更新操作
	 * @param @param sysUser
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/save", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResult save(@RequestBody SysMenu sysMenu) {
		try {			
			String id = sysMenuApi.save(sysMenu, getCurrentUser());
			return success(id);
		} catch(PowerException e){
			e.printStackTrace();
			return failure(SCMException.CODE_SAVE, e.getErrorMsg());
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}		
	}

	/**
	 * 
	 * @Title: changeStatus 
	 * @Description: 启用禁用操作
	 * @param @param enabled
	 * @param @param menuIds
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/status/{enabled}", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResult changeStatus(@PathVariable("enabled") Boolean enabled, 
		@RequestParam("menuIds") String menuIds) {
		try {
			if (!StringUtils.isEmpty(menuIds)) {
				sysMenuApi.changeStatus(menuIds.split(","), enabled, getCurrentUser());
				return success(SCMErrorEnum.SUCCESS);
			}
			return failure(SCMErrorEnum.INVALID_PARAMS);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 
	 * @Title: delete 
	 * @Description: 批量删除操作
	 * @param @param menuIds
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/delete", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResult delete(@RequestParam("menuIds") String menuIds) {
		try {
			if (!StringUtils.isEmpty(menuIds)) {
				sysMenuApi.delete(menuIds);
				return success(SCMErrorEnum.SUCCESS);
			}
			return failure(SCMErrorEnum.INVALID_PARAMS);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
}
