package com.huaxin.cloud.tms.tray.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 磁卡-订单关系BO
 * 
 * @author Ken
 *
 */
public class CardBill implements Serializable {
	private static final long serialVersionUID = 8350841768304100224L;

	private Integer billId;

	private Integer cardId;

	private String cId;

	private String cCard;

	private String cCard2;

	private String cCard3;

	private String cOwner;

	private String cTruckno;

	private String cStatus;

	private String cFreeze;

	private String cUsed;

	private Integer cUsetime;

	private String cMan;

	private Date cDate;

	private String cMemo;

	private String ccreateBy;

	private Date ccreateTime;

	private String cupdateBy;

	private Date cupdateTime;

	private Integer cdeleteStatus;

	private String lId;

	private String lCard;

	private String lType;

	private String lStock;

	private String lStockno;

	private String lTruck;

	private String lTruckid;

	private BigDecimal lValue;

	private String lCustype;

	private String lCusid;

	private String lCusname;

	private String lCuspy;

	private String lOrder;

	private String lOrderid;

	private String lSeal;

	private String lIsvip;

	private String lMan;

	private Date lDate;

	private Date lPickdate;

	private String lPickman;

	private String lPostday;

	private Date lPostdate;

	private String lPostman;

	private String lFactnum;

	private String lOutnum;

	private Date lOutfact;

	private String lStatus;

	private String lAction;

	private String lResult;

	private String lMemo;

	private String lCrm;

	private String lCrmflag;

	private Integer lExtinfo;

	private Integer lExtres;

	private String lDelman;

	private Date lDeldate;

	private String lCrmitem;

	private String lVkbur;

	private String lBezeivk;

	private String lBillbak;

	private String lOrderbak;

	private String lOffline;

	private String lCrmtype;

	private String lEpyflag;

	private Date lEpytime;

	private String lEpyman;

	private String createBy;

	private String updateBy;

	private Date createTime;

	private Date updateTime;

	private Integer deleteStatus;

	private String lTime;

	private String firstDate;

	private String endDate;

	private String cQuery;

	public Integer getBillId() {
		return billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate == null ? null : firstDate.trim();
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate == null ? null : endDate.trim();
	}

	public String getlTime() {
		return lTime;
	}

	public void setlTime(String lTime) {
		this.lTime = lTime == null ? null : lTime.trim();
	}

	public String getlId() {
		return lId;
	}

	public void setlId(String lId) {
		this.lId = lId == null ? null : lId.trim();
	}

	public String getlCard() {
		return lCard;
	}

	public void setlCard(String lCard) {
		this.lCard = lCard == null ? null : lCard.trim();
	}

	public String getlType() {
		return lType;
	}

	public void setlType(String lType) {
		this.lType = lType == null ? null : lType.trim();
	}

	public String getlStock() {
		return lStock;
	}

	public void setlStock(String lStock) {
		this.lStock = lStock == null ? null : lStock.trim();
	}

	public String getlStockno() {
		return lStockno;
	}

	public void setlStockno(String lStockno) {
		this.lStockno = lStockno == null ? null : lStockno.trim();
	}

	public String getlTruck() {
		return lTruck;
	}

	public void setlTruck(String lTruck) {
		this.lTruck = lTruck == null ? null : lTruck.trim();
	}

	public String getlTruckid() {
		return lTruckid;
	}

	public void setlTruckid(String lTruckid) {
		this.lTruckid = lTruckid == null ? null : lTruckid.trim();
	}

	public BigDecimal getlValue() {
		return lValue;
	}

	public void setlValue(BigDecimal lValue) {
		this.lValue = lValue;
	}

	public String getlCustype() {
		return lCustype;
	}

	public void setlCustype(String lCustype) {
		this.lCustype = lCustype == null ? null : lCustype.trim();
	}

	public String getlCusid() {
		return lCusid;
	}

	public void setlCusid(String lCusid) {
		this.lCusid = lCusid == null ? null : lCusid.trim();
	}

	public String getlCusname() {
		return lCusname;
	}

	public void setlCusname(String lCusname) {
		this.lCusname = lCusname == null ? null : lCusname.trim();
	}

	public String getlCuspy() {
		return lCuspy;
	}

	public void setlCuspy(String lCuspy) {
		this.lCuspy = lCuspy == null ? null : lCuspy.trim();
	}

	public String getlOrder() {
		return lOrder;
	}

	public void setlOrder(String lOrder) {
		this.lOrder = lOrder == null ? null : lOrder.trim();
	}

	public String getlOrderid() {
		return lOrderid;
	}

	public void setlOrderid(String lOrderid) {
		this.lOrderid = lOrderid == null ? null : lOrderid.trim();
	}

	public String getlSeal() {
		return lSeal;
	}

	public void setlSeal(String lSeal) {
		this.lSeal = lSeal == null ? null : lSeal.trim();
	}

	public String getlIsvip() {
		return lIsvip;
	}

	public void setlIsvip(String lIsvip) {
		this.lIsvip = lIsvip == null ? null : lIsvip.trim();
	}

	public String getlMan() {
		return lMan;
	}

	public void setlMan(String lMan) {
		this.lMan = lMan == null ? null : lMan.trim();
	}

	public Date getlDate() {
		return lDate;
	}

	public void setlDate(Date lDate) {
		this.lDate = lDate;
	}

	public Date getlPickdate() {
		return lPickdate;
	}

	public void setlPickdate(Date lPickdate) {
		this.lPickdate = lPickdate;
	}

	public String getlPickman() {
		return lPickman;
	}

	public void setlPickman(String lPickman) {
		this.lPickman = lPickman == null ? null : lPickman.trim();
	}

	public String getlPostday() {
		return lPostday;
	}

	public void setlPostday(String lPostday) {
		this.lPostday = lPostday == null ? null : lPostday.trim();
	}

	public Date getlPostdate() {
		return lPostdate;
	}

	public void setlPostdate(Date lPostdate) {
		this.lPostdate = lPostdate;
	}

	public String getlPostman() {
		return lPostman;
	}

	public void setlPostman(String lPostman) {
		this.lPostman = lPostman == null ? null : lPostman.trim();
	}

	public String getlFactnum() {
		return lFactnum;
	}

	public void setlFactnum(String lFactnum) {
		this.lFactnum = lFactnum == null ? null : lFactnum.trim();
	}

	public String getlOutnum() {
		return lOutnum;
	}

	public void setlOutnum(String lOutnum) {
		this.lOutnum = lOutnum == null ? null : lOutnum.trim();
	}

	public Date getlOutfact() {
		return lOutfact;
	}

	public void setlOutfact(Date lOutfact) {
		this.lOutfact = lOutfact;
	}

	public String getlStatus() {
		return lStatus;
	}

	public void setlStatus(String lStatus) {
		this.lStatus = lStatus == null ? null : lStatus.trim();
	}

	public String getlAction() {
		return lAction;
	}

	public void setlAction(String lAction) {
		this.lAction = lAction == null ? null : lAction.trim();
	}

	public String getlResult() {
		return lResult;
	}

	public void setlResult(String lResult) {
		this.lResult = lResult == null ? null : lResult.trim();
	}

	public String getlMemo() {
		return lMemo;
	}

	public void setlMemo(String lMemo) {
		this.lMemo = lMemo == null ? null : lMemo.trim();
	}

	public String getlCrm() {
		return lCrm;
	}

	public void setlCrm(String lCrm) {
		this.lCrm = lCrm == null ? null : lCrm.trim();
	}

	public String getlCrmflag() {
		return lCrmflag;
	}

	public void setlCrmflag(String lCrmflag) {
		this.lCrmflag = lCrmflag == null ? null : lCrmflag.trim();
	}

	public Integer getlExtinfo() {
		return lExtinfo;
	}

	public void setlExtinfo(Integer lExtinfo) {
		this.lExtinfo = lExtinfo;
	}

	public Integer getlExtres() {
		return lExtres;
	}

	public void setlExtres(Integer lExtres) {
		this.lExtres = lExtres;
	}

	public String getlDelman() {
		return lDelman;
	}

	public void setlDelman(String lDelman) {
		this.lDelman = lDelman == null ? null : lDelman.trim();
	}

	public Date getlDeldate() {
		return lDeldate;
	}

	public void setlDeldate(Date lDeldate) {
		this.lDeldate = lDeldate;
	}

	public String getlCrmitem() {
		return lCrmitem;
	}

	public void setlCrmitem(String lCrmitem) {
		this.lCrmitem = lCrmitem == null ? null : lCrmitem.trim();
	}

	public String getlVkbur() {
		return lVkbur;
	}

	public void setlVkbur(String lVkbur) {
		this.lVkbur = lVkbur == null ? null : lVkbur.trim();
	}

	public String getlBezeivk() {
		return lBezeivk;
	}

	public void setlBezeivk(String lBezeivk) {
		this.lBezeivk = lBezeivk == null ? null : lBezeivk.trim();
	}

	public String getlBillbak() {
		return lBillbak;
	}

	public void setlBillbak(String lBillbak) {
		this.lBillbak = lBillbak == null ? null : lBillbak.trim();
	}

	public String getlOrderbak() {
		return lOrderbak;
	}

	public void setlOrderbak(String lOrderbak) {
		this.lOrderbak = lOrderbak == null ? null : lOrderbak.trim();
	}

	public String getlOffline() {
		return lOffline;
	}

	public void setlOffline(String lOffline) {
		this.lOffline = lOffline == null ? null : lOffline.trim();
	}

	public String getlCrmtype() {
		return lCrmtype;
	}

	public void setlCrmtype(String lCrmtype) {
		this.lCrmtype = lCrmtype == null ? null : lCrmtype.trim();
	}

	public String getlEpyflag() {
		return lEpyflag;
	}

	public void setlEpyflag(String lEpyflag) {
		this.lEpyflag = lEpyflag == null ? null : lEpyflag.trim();
	}

	public Date getlEpytime() {
		return lEpytime;
	}

	public void setlEpytime(Date lEpytime) {
		this.lEpytime = lEpytime;
	}

	public String getlEpyman() {
		return lEpyman;
	}

	public void setlEpyman(String lEpyman) {
		this.lEpyman = lEpyman == null ? null : lEpyman.trim();
	}

	public String getCcreateBy() {
		return ccreateBy;
	}

	public void setCcreateBy(String ccreateBy) {
		this.ccreateBy = ccreateBy == null ? null : ccreateBy.trim();
	}

	public String getCupdateBy() {
		return cupdateBy;
	}

	public void setCupdateBy(String cupdateBy) {
		this.cupdateBy = cupdateBy == null ? null : cupdateBy.trim();
	}

	public Date getCcreateTime() {
		return ccreateTime;
	}

	public void setCcreateTime(Date ccreateTime) {
		this.ccreateTime = ccreateTime;
	}

	public Date getCupdateTime() {
		return cupdateTime;
	}

	public void setCupdateTime(Date cupdateTime) {
		this.cupdateTime = cupdateTime;
	}

	public Integer getCdeleteStatus() {
		return cdeleteStatus;
	}

	public void setCdeleteStatus(Integer cdeleteStatus) {
		this.cdeleteStatus = cdeleteStatus;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId == null ? null : cId.trim();
	}

	public String getcCard() {
		return cCard;
	}

	public void setcCard(String cCard) {
		this.cCard = cCard == null ? null : cCard.trim();
	}

	public String getcCard2() {
		return cCard2;
	}

	public void setcCard2(String cCard2) {
		this.cCard2 = cCard2 == null ? null : cCard2.trim();
	}

	public String getcCard3() {
		return cCard3;
	}

	public void setcCard3(String cCard3) {
		this.cCard3 = cCard3 == null ? null : cCard3.trim();
	}

	public String getcOwner() {
		return cOwner;
	}

	public void setcOwner(String cOwner) {
		this.cOwner = cOwner == null ? null : cOwner.trim();
	}

	public String getcTruckno() {
		return cTruckno;
	}

	public void setcTruckno(String cTruckno) {
		this.cTruckno = cTruckno == null ? null : cTruckno.trim();
	}

	public String getcStatus() {
		return cStatus;
	}

	public void setcStatus(String cStatus) {
		this.cStatus = cStatus == null ? null : cStatus.trim();
	}

	public String getcFreeze() {
		return cFreeze;
	}

	public void setcFreeze(String cFreeze) {
		this.cFreeze = cFreeze == null ? null : cFreeze.trim();
	}

	public String getcUsed() {
		return cUsed;
	}

	public void setcUsed(String cUsed) {
		this.cUsed = cUsed == null ? null : cUsed.trim();
	}

	public Integer getcUsetime() {
		return cUsetime;
	}

	public void setcUsetime(Integer cUsetime) {
		this.cUsetime = cUsetime;
	}

	public String getcMan() {
		return cMan;
	}

	public void setcMan(String cMan) {
		this.cMan = cMan == null ? null : cMan.trim();
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public String getcMemo() {
		return cMemo;
	}

	public void setcMemo(String cMemo) {
		this.cMemo = cMemo == null ? null : cMemo.trim();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getcQuery() {
		return cQuery;
	}

	public void setcQuery(String cQuery){
		this.cQuery = cQuery;
	}
}
