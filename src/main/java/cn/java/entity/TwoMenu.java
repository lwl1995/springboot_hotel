package cn.java.entity;
/**
 * 二级菜单
 * @author LWL
 *
 */
public class TwoMenu {
	
	
	private Long twoId;// 二级权限的iD
	
	//二级菜单名
	private String twoName;
	
	//二级菜单跳转的链接
	private String twoUrl;
	
	private Long parent;

	public String getTwoName() {
		return twoName;
	}

	public void setTwoName(String twoName) {
		this.twoName = twoName;
	}

	public String getTwoUrl() {
		return twoUrl;
	}

	public void setTwoUrl(String twoUrl) {
		this.twoUrl = twoUrl;
	}

	public Long getTwoId() {
		return twoId;
	}

	public void setTwoId(Long twoId) {
		this.twoId = twoId;
	}

	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}
	
	
}
