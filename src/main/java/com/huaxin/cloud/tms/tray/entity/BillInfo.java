package com.huaxin.cloud.tms.tray.entity;

import java.util.Date;

import io.swagger.annotations.ApiModel;

/**
 * 交货单对象 sys_tms_bill_info
 * 
 */
@ApiModel(description = "交货单")
public class BillInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String cid;

    /** 提单号 */
    private String lId;

    /** 磁卡号 */
    private String lCard;

    /** 类型(袋,散) */
    private String lType;

    /** 物料描述 */
    private String lStock;

    /** 物料编号 */
    private String lStockno;

    /** 车船号 */
    private String lTruck;

    /** 提货记录 */
    private String lTruckid;

    /** 交货量 */
    private Double lValue;

    /** 客户类型 */
    private String lCustype;

    /** 客户编号 */
    private String lCusid;

    /** 客户名称 */
    private String lCusname;

    /** 拼音简写 */
    private String lCuspy;

    /** 订单类型(销售,转储) */
    private String lOrder;

    /** 订单号 */
    private String lOrderid;

    /** 封签号 */
    private String lSeal;

    /** VIP单 */
    private String lIsvip;

    /** 操作人 */
    private String lMan;

    /** 创建时间 */
    private Date lDate;

    /** 拣配时间 */
    private Date lPickdate;

    /** 拣配人员 */
    private String lPickman;

    /** 过账日(周期) */
    private String lPostday;

    /** 过账时间 */
    private Date lPostdate;

    /** 过账人 */
    private String lPostman;

    /** 工厂编号 */
    private String lFactnum;

    /** 出厂编号 */
    private String lOutnum;

    /** 出厂日期 */
    private Date lOutfact;

    /** 当前状态 */
    private String lStatus;

    /** 上一动作 */
    private String lAction;

    /** 动作结果 */
    private String lResult;

    /** 动作备注 */
    private String lMemo;

    /** 电子委托单号  */
    private String lCrm;

    /** 电子委托单号返回标识 */
    private String lCrmflag;

    /** 交货单拉取 */
    private Integer lExtinfo;

    /** 拉取次数 */
    private Integer lExtres;

    /** 交货单删除人员 */
    private String lDelman;

    /** 交货单删除时间 */
    private Date lDeldate;

    /** $column.columnComment */
    private String lCrmitem;

    /** 销售部门编码 */
    private String lVkbur;

    /** 销售部门描述 */
    private String lBezeivk;

    /** 本地交货单(离线交货单) */
    private String lBillbak;

    /** $column.columnComment */
    private String lOrderbak;

    /** 离线标示(Y.离线、N.正常) */
    private String lOffline;

    /** 离线标示(Y.离线、N.正常) */
    private String lCrmtype;

    /** 空车过磅(Y.空车、N.正常) */
    private String lEpyflag;

    /** 空车放行时间 */
    private Date lEpytime;

    /** 空车放行人 */
    private String lEpyman;

    /** 删除状态 */
    private Integer deleteStatus;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCid(String cid) 
    {
        this.cid = cid;
    }

    public String getCid() 
    {
        return cid;
    }
    public void setLId(String lId) 
    {
        this.lId = lId;
    }

    public String getLId() 
    {
        return lId;
    }
    public void setLCard(String lCard) 
    {
        this.lCard = lCard;
    }

    public String getLCard() 
    {
        return lCard;
    }
    public void setLType(String lType) 
    {
        this.lType = lType;
    }

    public String getLType() 
    {
        return lType;
    }
    public void setLStock(String lStock) 
    {
        this.lStock = lStock;
    }

    public String getLStock() 
    {
        return lStock;
    }
    public void setLStockno(String lStockno) 
    {
        this.lStockno = lStockno;
    }

    public String getLStockno() 
    {
        return lStockno;
    }
    public void setLTruck(String lTruck) 
    {
        this.lTruck = lTruck;
    }

    public String getLTruck() 
    {
        return lTruck;
    }
    public void setLTruckid(String lTruckid) 
    {
        this.lTruckid = lTruckid;
    }

    public String getLTruckid() 
    {
        return lTruckid;
    }
    public void setLValue(Double lValue) 
    {
        this.lValue = lValue;
    }

    public Double getLValue() 
    {
        return lValue;
    }
    public void setLCustype(String lCustype) 
    {
        this.lCustype = lCustype;
    }

    public String getLCustype() 
    {
        return lCustype;
    }
    public void setLCusid(String lCusid) 
    {
        this.lCusid = lCusid;
    }

    public String getLCusid() 
    {
        return lCusid;
    }
    public void setLCusname(String lCusname) 
    {
        this.lCusname = lCusname;
    }

    public String getLCusname() 
    {
        return lCusname;
    }
    public void setLCuspy(String lCuspy) 
    {
        this.lCuspy = lCuspy;
    }

    public String getLCuspy() 
    {
        return lCuspy;
    }
    public void setLOrder(String lOrder) 
    {
        this.lOrder = lOrder;
    }

    public String getLOrder() 
    {
        return lOrder;
    }
    public void setLOrderid(String lOrderid) 
    {
        this.lOrderid = lOrderid;
    }

    public String getLOrderid() 
    {
        return lOrderid;
    }
    public void setLSeal(String lSeal) 
    {
        this.lSeal = lSeal;
    }

    public String getLSeal() 
    {
        return lSeal;
    }
    public void setLIsvip(String lIsvip) 
    {
        this.lIsvip = lIsvip;
    }

    public String getLIsvip() 
    {
        return lIsvip;
    }
    public void setLMan(String lMan) 
    {
        this.lMan = lMan;
    }

    public String getLMan() 
    {
        return lMan;
    }
    public void setLDate(Date lDate) 
    {
        this.lDate = lDate;
    }

    public Date getLDate() 
    {
        return lDate;
    }
    public void setLPickdate(Date lPickdate) 
    {
        this.lPickdate = lPickdate;
    }

    public Date getLPickdate() 
    {
        return lPickdate;
    }
    public void setLPickman(String lPickman) 
    {
        this.lPickman = lPickman;
    }

    public String getLPickman() 
    {
        return lPickman;
    }
    public void setLPostday(String lPostday) 
    {
        this.lPostday = lPostday;
    }

    public String getLPostday() 
    {
        return lPostday;
    }
    public void setLPostdate(Date lPostdate) 
    {
        this.lPostdate = lPostdate;
    }

    public Date getLPostdate() 
    {
        return lPostdate;
    }
    public void setLPostman(String lPostman) 
    {
        this.lPostman = lPostman;
    }

    public String getLPostman() 
    {
        return lPostman;
    }
    public void setLFactnum(String lFactnum) 
    {
        this.lFactnum = lFactnum;
    }

    public String getLFactnum() 
    {
        return lFactnum;
    }
    public void setLOutnum(String lOutnum) 
    {
        this.lOutnum = lOutnum;
    }

    public String getLOutnum() 
    {
        return lOutnum;
    }
    public void setLOutfact(Date lOutfact) 
    {
        this.lOutfact = lOutfact;
    }

    public Date getLOutfact() 
    {
        return lOutfact;
    }
    public void setLStatus(String lStatus) 
    {
        this.lStatus = lStatus;
    }

    public String getLStatus() 
    {
        return lStatus;
    }
    public void setLAction(String lAction) 
    {
        this.lAction = lAction;
    }

    public String getLAction() 
    {
        return lAction;
    }
    public void setLResult(String lResult) 
    {
        this.lResult = lResult;
    }

    public String getLResult() 
    {
        return lResult;
    }
    public void setLMemo(String lMemo) 
    {
        this.lMemo = lMemo;
    }

    public String getLMemo() 
    {
        return lMemo;
    }
    public void setLCrm(String lCrm) 
    {
        this.lCrm = lCrm;
    }

    public String getLCrm() 
    {
        return lCrm;
    }
    public void setLCrmflag(String lCrmflag) 
    {
        this.lCrmflag = lCrmflag;
    }

    public String getLCrmflag() 
    {
        return lCrmflag;
    }
    public void setLExtinfo(Integer lExtinfo) 
    {
        this.lExtinfo = lExtinfo;
    }

    public Integer getLExtinfo() 
    {
        return lExtinfo;
    }
    public void setLExtres(Integer lExtres) 
    {
        this.lExtres = lExtres;
    }

    public Integer getLExtres() 
    {
        return lExtres;
    }
    public void setLDelman(String lDelman) 
    {
        this.lDelman = lDelman;
    }

    public String getLDelman() 
    {
        return lDelman;
    }
    public void setLDeldate(Date lDeldate) 
    {
        this.lDeldate = lDeldate;
    }

    public Date getLDeldate() 
    {
        return lDeldate;
    }
    public void setLCrmitem(String lCrmitem) 
    {
        this.lCrmitem = lCrmitem;
    }

    public String getLCrmitem() 
    {
        return lCrmitem;
    }
    public void setLVkbur(String lVkbur) 
    {
        this.lVkbur = lVkbur;
    }

    public String getLVkbur() 
    {
        return lVkbur;
    }
    public void setLBezeivk(String lBezeivk) 
    {
        this.lBezeivk = lBezeivk;
    }

    public String getLBezeivk() 
    {
        return lBezeivk;
    }
    public void setLBillbak(String lBillbak) 
    {
        this.lBillbak = lBillbak;
    }

    public String getLBillbak() 
    {
        return lBillbak;
    }
    public void setLOrderbak(String lOrderbak) 
    {
        this.lOrderbak = lOrderbak;
    }

    public String getLOrderbak() 
    {
        return lOrderbak;
    }
    public void setLOffline(String lOffline) 
    {
        this.lOffline = lOffline;
    }

    public String getLOffline() 
    {
        return lOffline;
    }
    public void setLCrmtype(String lCrmtype) 
    {
        this.lCrmtype = lCrmtype;
    }

    public String getLCrmtype() 
    {
        return lCrmtype;
    }
    public void setLEpyflag(String lEpyflag) 
    {
        this.lEpyflag = lEpyflag;
    }

    public String getLEpyflag() 
    {
        return lEpyflag;
    }
    public void setLEpytime(Date lEpytime) 
    {
        this.lEpytime = lEpytime;
    }

    public Date getLEpytime() 
    {
        return lEpytime;
    }
    public void setLEpyman(String lEpyman) 
    {
        this.lEpyman = lEpyman;
    }

    public String getLEpyman() 
    {
        return lEpyman;
    }
    public void setDeleteStatus(Integer deleteStatus) 
    {
        this.deleteStatus = deleteStatus;
    }

    public Integer getDeleteStatus() 
    {
        return deleteStatus;
    }
}
