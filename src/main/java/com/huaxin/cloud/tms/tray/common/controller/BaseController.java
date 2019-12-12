package com.huaxin.cloud.tms.tray.common.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.huaxin.cloud.tms.tray.common.constant.HttpStatus;
import com.huaxin.cloud.tms.tray.common.page.PageDomain;
import com.huaxin.cloud.tms.tray.common.page.TableDataInfo;
import com.huaxin.cloud.tms.tray.common.page.TableSupport;
import com.huaxin.cloud.tms.tray.common.result.ResultInfo;
import com.huaxin.cloud.tms.tray.common.utils.DateUtils;
import com.huaxin.cloud.tms.tray.common.utils.StringUtils;
import com.huaxin.cloud.tms.tray.common.utils.sql.SqlUtil;

/**
 * web层通用数据处理
 */
public class BaseController {
	
	/**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

	/**
    * 响应请求分页数据
    */
   @SuppressWarnings({ "rawtypes", "unchecked" })
   protected TableDataInfo getDataTable(List<?> list)
   {
       TableDataInfo rspData = new TableDataInfo();
       rspData.setCode(HttpStatus.SUCCESS);
       rspData.setRows(list);
       rspData.setTotal(new PageInfo(list).getTotal());
       return rspData;
   }
   
   /**
    * 设置请求分页数据
    */
   protected void startPage()
   {
       PageDomain pageDomain = TableSupport.buildPageRequest();
       Integer pageNum = pageDomain.getPageNum();
       Integer pageSize = pageDomain.getPageSize();
       if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
       {
           String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
           PageHelper.startPage(pageNum, pageSize, orderBy);
       }
   }
   
   /**
    * 响应返回结果
    * 
    * @param rows 影响行数
    * @return 操作结果
    */
   protected ResultInfo toAjax(int rows)
   {
       return rows > 0 ? ResultInfo.success() : ResultInfo.error();
   }
}
