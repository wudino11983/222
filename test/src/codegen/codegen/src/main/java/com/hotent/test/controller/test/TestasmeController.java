

package com.hotent.test.controller.test;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.page.PageList;
import com.hotent.platform.model.system.SysUser;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.hotent.test.model.test.Testasme;
import com.hotent.test.service.test.TestasmeService;
import com.hotent.core.web.ResultMessage;
/**
 * 对象功能:test 控制器类
 */
@Controller
@RequestMapping("/test/test/testasme/")
public class TestasmeController extends BaseController
{
	@Resource
	private TestasmeService testasmeService;
	
	/**
	 * 添加或更新test。
	 * @param request
	 * @param response
	 * @param testasme 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新test")
	public void save(HttpServletRequest request, HttpServletResponse response,Testasme testasme) throws Exception
	{
		String resultMsg=null;		
		try{
			if(testasme.getId()==null){
				testasmeService.save(testasme);
				resultMsg=getText("添加","test");
			}else{
			    testasmeService.save(testasme);
				resultMsg=getText("更新","test");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得test分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看test分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Testasme> list=testasmeService.getAll(new QueryFilter(request,"testasmeItem"));
		ModelAndView mv=this.getAutoView().addObject("testasmeList",list);
		return mv;
	}
	
	/**
	 * 删除test
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除test")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			testasmeService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除test成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑test
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑test")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Testasme testasme=testasmeService.getById(id);
		
		return getAutoView().addObject("testasme",testasme)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得test明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看test明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Testasme testasme=testasmeService.getById(id);
		return getAutoView().addObject("testasme", testasme);
	}
	
}