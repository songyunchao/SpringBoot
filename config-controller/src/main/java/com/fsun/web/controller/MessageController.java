package com.fsun.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fsun.common.utils.StringUtils;
import com.fsun.domain.common.BaseCondition;
import com.fsun.domain.common.HttpResult;
import com.fsun.domain.common.PageModel;
import com.fsun.domain.condition.SysMessageCondition;
import com.fsun.domain.model.SysMessage;
import com.fsun.domain.model.SysUser;
import com.fsun.exception.enums.SCMErrorEnum;
import com.fsun.service.SysMessageApi;
import com.fsun.web.controller.base.BaseController;

@RestController
@RequestMapping("sys/message")
public class MessageController extends BaseController {
	
	@Autowired
	private SysMessageApi sysMessageApi;

      
	/**
	 * 获取单个用户信息
	 * @Title: load 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param powerId
	 * @param @return 
	 * @return HttpResult
	 */
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult load(@PathVariable("id") String id) {
		try {
			SysMessage sysMessage = sysMessageApi.load(id);
			return success(sysMessage);
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
	public HttpResult findPage(@RequestBody SysMessageCondition condition) {
		try {
			SysUser currUser = getCurrentUser();
			if(!currUser.getIssys()){
				condition.setUsername(currUser.getUsername());
			}
			PageModel pageModel = sysMessageApi.findPage(condition);
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
	@ResponseBody
	public HttpResult list(SysMessageCondition condition) {
		try {
			SysUser currUser = getCurrentUser();
			if(!currUser.getIssys()){
				condition.setUsername(currUser.getUsername());
			}
			List<SysMessage> list = sysMessageApi.list(condition);
			return success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
	

	/**
	 * 取消操作
	 * @Title: changeStatus 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param enabled
	 * @param @param powerIds
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/cancel", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResult changeStatus(@RequestBody BaseCondition params) {
		try {
			if (!StringUtils.isEmpty(params.getIds())) {
				sysMessageApi.changeStatus(params.getIds().split(","), (short)90, getCurrentUser());
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
	 * @param @param powerIds
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/delete", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResult delete(@RequestBody BaseCondition params) {
		try {
			if (!StringUtils.isEmpty(params.getIds())) {
				sysMessageApi.delete(params.getIds());
				return success(SCMErrorEnum.SUCCESS);
			}
			return failure(SCMErrorEnum.INVALID_PARAMS);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
}
