package com.fsun.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;


@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "pageFilter1")
public class PageFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/**
		 * 
		 * 分页参数 pageNo:页码索引 pageSize:每页显示条数 sort:排序字段 order:排序类型(desc:倒序 or
		 * asc:为正序)
		 * 
		 */
		// String pageNo = request.getParameter("offset");
		// String pageSize = request.getParameter("limit");
		String pageNo = request.getParameter("page");
		if(pageNo!=null){
			System.out.println(pageNo);
		}
		
		String pageSize = request.getParameter("rows");
		String sort = request.getParameter("sort");
		String order = request.getParameter("order");
		String nopage = request.getParameter("nopage");
		String nofilterSort = request.getParameter("nofilterSort");
		String count = request.getParameter("count");
		if(StringUtils.isNotBlank(nofilterSort) && Boolean.parseBoolean(nofilterSort)){
			if ((StringUtils.isNotBlank(pageNo) && StringUtils.isNumeric(pageNo))
					&& (StringUtils.isNotBlank(pageSize) && StringUtils.isNumeric(pageSize))) {				
				if(StringUtils.isNotBlank(count)){
					PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize), Boolean.parseBoolean(count));
				}else{
					PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
				}			
			}
		}else{
			if(StringUtils.isNotBlank(nopage) && Boolean.parseBoolean(nopage)){
				//不做分页
			}else{
				if ((StringUtils.isNotBlank(pageNo) && StringUtils.isNumeric(pageNo))
						&& (StringUtils.isNotBlank(pageSize) && StringUtils
								.isNumeric(pageSize))) {
					if(StringUtils.isNotBlank(sort) && order!=null){
						PageHelper.startPage(Integer.parseInt(pageNo),
								Integer.parseInt(pageSize), sort + " " + order);
					}else{
						PageHelper.startPage(Integer.parseInt(pageNo),
								Integer.parseInt(pageSize));
					}
					
				}
			}
			
		}		
		chain.doFilter(request, response);
	}
}