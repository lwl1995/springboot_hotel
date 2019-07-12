package cn.java.entity;

import java.util.List;

/**
 * 一级菜单
 * @author LWL
 *
 */
public class OneMenu {

	//一级菜单的主键
	private Long id;
	
	//一级菜单名
	private String oneName;
	
	//一级菜单跟二级菜单一对多的关系
	private List<TwoMenu> twoMenuList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOneName() {
		return oneName;
	}

	public void setOneName(String oneName) {
		this.oneName = oneName;
	}

	public List<TwoMenu> getTwoMenuList() {
		return twoMenuList;
	}

	public void setTwoMenuList(List<TwoMenu> twoMenuList) {
		this.twoMenuList = twoMenuList;
	}
	
	
	
}
