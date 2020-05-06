package com.fsun.domain.dto;

/**
 * 菜单树
 * @author sunxiaolei
 *
 */
public class MenuTreeDto extends BaseTreeDto<MenuTreeDto>{

	private String url;
	
	private String icon;
	
	//默认选中项
	private boolean isCurrent;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the isCurrent
	 */
	public boolean getIsCurrent() {
		return isCurrent;
	}

	/**
	 * @param isCurrent the isCurrent to set
	 */
	public void setIsCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	
	
}
