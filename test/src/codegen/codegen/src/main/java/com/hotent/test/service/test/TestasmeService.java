package com.hotent.test.service.test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.GenericService;
import com.hotent.core.util.BeanUtils;
import com.hotent.test.model.test.Testasme;
import com.hotent.test.dao.test.TestasmeDao;
import com.hotent.core.util.UniqueIdUtil;

import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;


import com.hotent.core.service.BaseService;


@Service
public class TestasmeService extends BaseService<Testasme>
{
	@Resource
	private TestasmeDao dao;
	
	public TestasmeService()
	{
	}
	
	@Override
	protected IEntityDao<Testasme,Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 流程处理器方法 用于处理业务数据
	 * @param cmd
	 * @throws Exception
	 */
	public void processHandler(ProcessCmd cmd)throws Exception{
		Map data=cmd.getFormDataMap();
		if(BeanUtils.isNotEmpty(data)){
			String json=data.get("json").toString();
			Testasme testasme=getTestasme(json);
			if(StringUtil.isEmpty(cmd.getBusinessKey())){
				Long genId=UniqueIdUtil.genId();
				testasme.setId(genId);
				this.add(testasme);
			}else{
				testasme.setId(Long.parseLong(cmd.getBusinessKey()));
				this.update(testasme);
			}
			cmd.setBusinessKey(testasme.getId().toString());
		}
	}
	
	/**
	 * 根据json字符串获取Testasme对象
	 * @param json
	 * @return
	 */
	public Testasme getTestasme(String json){
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		Testasme testasme = (Testasme)JSONObject.toBean(obj, Testasme.class);
		return testasme;
	}
	/**
	 * 保存 test 信息
	 * @param testasme
	 */

	public void save(Testasme testasme) throws Exception{
		Long id=testasme.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			testasme.setId(id);
		    this.add(testasme);
		}
		else{
		   this.update(testasme);
		}
	}
}
