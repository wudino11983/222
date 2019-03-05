package com.hotent.test.model.test;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:test Model对象
 */
public class Testasme extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *username
	 */
	protected String  username;
	/**
	 *sex
	 */
	protected Long  sex;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}
	/**
	 * 返回 username
	 * @return
	 */
	public String getUsername() 
	{
		return this.username;
	}
	public void setSex(Long sex) 
	{
		this.sex = sex;
	}
	/**
	 * 返回 sex
	 * @return
	 */
	public Long getSex() 
	{
		return this.sex;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Testasme)) 
		{
			return false;
		}
		Testasme rhs = (Testasme) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.username, rhs.username)
		.append(this.sex, rhs.sex)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.username) 
		.append(this.sex) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("username", this.username) 
		.append("sex", this.sex) 
		.toString();
	}

}