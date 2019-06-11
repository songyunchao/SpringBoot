package com.fsun.web.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.fsun.domain.condition.SysUserCondition;
import com.fsun.domain.model.SysUser;
import com.fsun.exception.common.SCMException;
import com.fsun.exception.enums.SCMErrorEnum;
import com.fsun.exception.sys.UserException;
import com.fsun.service.SysUserApi;
import com.fsun.web.controller.base.BaseController;

@RestController
@RequestMapping("sys/user")
public class UserController extends BaseController {
	
	@Autowired
	private SysUserApi sysUserApi;

      
	/**
	 * 获取单个用户信息
	 * @Title: load 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param userId
	 * @param @return 
	 * @return HttpResult
	 */
    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult load(@PathVariable("userId") String userId) {
		try {
			SysUser sysUser = sysUserApi.load(userId);
			return success(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
   /**
    * 根据username码判断是否已存在，不允许相同
    * @param id
    * @param username
    * @return
    */
    @ResponseBody
    @RequestMapping(value = "/unique", method = {RequestMethod.GET})
    public HttpResult unique(SysUserCondition condition) {  
    	try {
    		boolean isUnique = sysUserApi.unique(condition);
    		return success(isUnique);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
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
	public HttpResult findPage(SysUserCondition condition) {
		try {
			PageModel pageModel = sysUserApi.findPage(condition);
			return success(pageModel);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 列表条件查询操作
	 * @Title: list 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param condition
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
	@RequiresPermissions(value={"USER_EDIT"})
	@ResponseBody
	public HttpResult list(SysUserCondition condition) {
		try {
			List<SysUser> list = sysUserApi.list(condition);
			return success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 新增或更新操作
	 * @Title: save 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param sysUser
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/save", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResult save(@RequestBody SysUser sysUser) {
		try {			
			String userId = sysUserApi.save(sysUser, getCurrentUser());
			return success(userId);
		} catch(UserException e){
			e.printStackTrace();
			return failure(SCMException.CODE_SAVE, e.getErrorMsg());
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}		
	}

	/**
	 * 启用禁用操作
	 * @Title: changeStatus 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param enabled
	 * @param @param userIds
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/status/{enabled}", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResult changeStatus(@PathVariable("enabled") Boolean enabled, 
		@RequestParam("userIds") String userIds) {
		try {
			if (!StringUtils.isEmpty(userIds)) {
				sysUserApi.changeStatus(userIds.split(","), enabled, getCurrentUser());
				return success(SCMErrorEnum.SUCCESS);
			}
			return failure(SCMErrorEnum.INVALID_PARAMS);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
	/**
	 * 批量删除操作
	 * @Title: delete 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param userIds
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/delete", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResult delete(@RequestParam("userIds") String userIds) {
		try {
			if (!StringUtils.isEmpty(userIds)) {
				sysUserApi.delete(userIds);
				return success(SCMErrorEnum.SUCCESS);
			}
			return failure(SCMErrorEnum.INVALID_PARAMS);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
}
