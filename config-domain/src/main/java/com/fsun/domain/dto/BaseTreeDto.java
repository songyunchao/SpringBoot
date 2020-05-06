package com.fsun.domain.dto;

import java.util.List;

/**
 * 基类树
 * @author sunxiaolei
 *
 */
public class BaseTreeDto<T extends BaseTreeDto<T>> {

	private String id;
	
	private String text;
	
	private String iconCls;
	
	private boolean isleaf;
	
	private String state;
	
	private List<T> children;
	
	private Object attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isIsleaf() {
		return isleaf;
	}

	public void setIsleaf(boolean isleaf) {
		this.isleaf = isleaf;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<T> getChildren() {
		return children;
	}

	public void setChildren(List<T> children) {
		this.children = children;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
	
}
