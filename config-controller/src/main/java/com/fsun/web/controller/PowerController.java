package com.fsun.web.controller;

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
import com.fsun.domain.condition.SysPowerCondition;
import com.fsun.domain.model.SysPower;
import com.fsun.exception.common.SCMException;
import com.fsun.exception.enums.SCMErrorEnum;
import com.fsun.exception.sys.PowerException;
import com.fsun.service.SysPowerApi;
import com.fsun.web.controller.base.BaseController;

@RestController
@RequestMapping("sys/power")
public class PowerController extends BaseController {
	
	@Autowired
	private SysPowerApi sysPowerApi;

      
	/**
	 * 获取单个用户信息
	 * @Title: load 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param powerId
	 * @param @return 
	 * @return HttpResult
	 */
    @RequestMapping(value="/{powerId}", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult load(@PathVariable("powerId") String powerId) {
		try {
			SysPower sysPower = sysPowerApi.load(powerId);
			return success(sysPower);
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
    public HttpResult unique(SysPowerCondition condition) {  
    	try {
    		boolean isUnique = sysPowerApi.unique(condition);
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
	public HttpResult findPage(SysPowerCondition condition) {
		try {
			PageModel pageModel = sysPowerApi.findPage(condition);
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
	public HttpResult list(SysPowerCondition condition) {
		try {
			List<SysPower> list = sysPowerApi.list(condition);
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
	public HttpResult save(@RequestBody SysPower sysPower) {
		try {			
			String powerId = sysPowerApi.save(sysPower, getCurrentUser());
			return success(powerId);
		} catch(PowerException e){
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
	 * @param @param powerIds
	 * @param @return 
	 * @return HttpResult
	 */
	@RequestMapping(value="/status/{enabled}", method = {RequestMethod.POST})
	@ResponseBody
	public HttpResult changeStatus(@PathVariable("enabled") Boolean enabled, 
		@RequestParam("powerIds") String powerIds) {
		try {
			if (!StringUtils.isEmpty(powerIds)) {
				sysPowerApi.changeStatus(powerIds.split(","), enabled, getCurrentUser());
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
	public HttpResult delete(@RequestParam("powerIds") String powerIds) {
		try {
			if (!StringUtils.isEmpty(powerIds)) {
				sysPowerApi.delete(powerIds);
				return success(SCMErrorEnum.SUCCESS);
			}
			return failure(SCMErrorEnum.INVALID_PARAMS);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(SCMErrorEnum.SYSTEM_ERROR);
		}
	}
	
}
