package com.wisdom.weixin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.wisdom.common.model.SheetIncomeDetail;
import com.wisdom.company.service.ISheetIncomeDetailService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.ICompanyFixedApi;
@Controller
public class InvoiceController {
    private static final Logger logger = LoggerFactory
            .getLogger(InvoiceController.class);
    @Autowired
    private ISheetIncomeDetailService sheetIncomeDetailService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICompanyFixedApi companyFixedapi;
    /** 
   * @param request
   * @return
   * @author XiaoMing
   * @date 2016年3月28日 上午10:13:43
   * Weixin SheetIncome Set Session
   *  Income Invoice
   */
  @RequestMapping("/getNewestSheetIncomeWithWeixinSetSession")
  public String getNewestSheetIncomeWithWeixinSetSession(HttpServletRequest request, HttpSession session) {
      logger.debug("enter set invoice weixin session");
      String dateNo=request.getParameter("type");
      String date=request.getParameter("date");
      String projectString=request.getParameter("project");
      if (dateNo == null && date == null && projectString == null) {
          return null;
      }
      session.setAttribute("type", dateNo);
      session.setAttribute("date", date);
      session.setAttribute("project", projectString);
      return "redirect:/views/weixinviews/invoice_income_info.html";
  }
  
  @RequestMapping("/getNewestSheetIncomeWithWeixinData")
  @ResponseBody
  public  List<SheetIncomeDetail> getNewestSheetIncomeWithWeixinData(HttpServletRequest request) {
      logger.debug("enter getAllSheetIncomeDetail");
      List<SheetIncomeDetail> list=new ArrayList<>();
      
      String userId = "gongjiaxii@bangbangzhang.com";
      long companyId = userService.getCompanyIdByUserId("gongjiaxii@bangbangzhang.com");
      String dateNo= (String) request.getSession().getAttribute("type");
      String date= (String) request.getSession().getAttribute("date");
      String projectString= (String) request.getSession().getAttribute("project");
      String[] projects=projectString.split(";");
      if(Integer.parseInt(dateNo)==0){
          list=sheetIncomeDetailService.InvokingOperateSqlByYear(date, projects,companyId);
      }else if(Integer.parseInt(dateNo)==1){
          list =sheetIncomeDetailService.InvokingOperateSqlByMonth(date, projects,companyId);
      }
      logger.debug("finish getAllSheetIncomeDetail");
      return list;
  }
  /**
 * @param request
 * @param session
 * @return
 * @author XiaoMing
 * @date 2016年3月28日 下午6:47:20
 *  Sheet Balance
 */
@RequestMapping("/getNewestSheetBalanceWithWeixinSetSession")
  public String getNewestSheetBalanceWithWeixinSetSession(HttpServletRequest request, HttpSession session) {
      logger.debug("enter set invoice weixin session");
      String date=request.getParameter("date");
      if ( date == null ) {
          return null;
      }
      session.setAttribute("date", date);
      return "redirect:/views/weixinviews/invoice_balance_info.html";
  }
    @RequestMapping("/getSheetBalanceWeixinData")
    @ResponseBody
    public List<Map<String, String>> getSheetBalanceWeixinData(
            HttpServletRequest request) {
//        String userId = (String) request.getSession().getAttribute("userId");
        String userId = "gongjiaxii@bangbangzhang.com";
        String month= (String) request.getSession().getAttribute("date");
        return  companyFixedapi.companyFixedInformation(userId,month);
    }
}
