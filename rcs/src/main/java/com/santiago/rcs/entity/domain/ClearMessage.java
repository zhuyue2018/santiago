package com.santiago.rcs.entity.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ClearMessage {
    @NotNull(message = "商户订单号不能为空")
    @Size(min = 10, max = 30, message = "商户订单号长度为10到30之间")
    private String cusBillNo;
    @NotNull(message = "订单金额不能为空")
    private BigDecimal payAmount; // 订单金额
    @NotNull(message = "币种不能为空")
    @Size(min = 1, max = 5, message = "币种长度为1到5之间")
    private String payCurrency; // 币种（CNY）
    @Size(max = 30, message = "总订单号长度不超过30")
    private String totalBillNo; // 总订单号
    private BigDecimal totalPayAmount; // 总订单金额
    //    private String orgCardId; // 组织机构号
    @NotNull(message = "交易类型不能为空")
    @Pattern(regexp = "1302||1305||1308||1312||1315", message = "交易类型暂只支持1302||1305||1308||1312||1315")
    private String trxType; // 交易模式（1315：直接支付，1312，1308：担保支付）
    @NotNull(message = "交易状态不能为空")
    @Pattern(regexp = "S", message = "交易状态必须为S")
    private String trxState; // 交易状态
    @NotNull(message = "交易流水号不能为空")
    @Size(min = 10, max = 30, message = "交易流水号长度为10到30之间")
    private String trxSerialNo; // 交易流水号
    @Size(min = 10, max = 30, message = "原交易流水号长度为10到30之间")
    private String oTrxSerialNo; // 原交易流水号
    //    @Past(message = "订单创建时间晚于当前时间")
    private Date ordCreateTime; // 订单创建时间
    //    @Past(message = "订单成功时间晚于当前时间")
    private Date ordSuccTime; // 订单成功时间
    @NotNull(message = "是否包含分账不能为空")
    @Pattern(regexp = "1||0", message = "不能识别是否包分账（‘1’：是， ‘0’：否）")
    private String separateFlag; // 是否包分账（‘1’：是， ‘0’：否）
    @NotNull(message = "是否计费不能为空")
    @Pattern(regexp = "1||0", message = "不能识别是否计费（‘1’：是， ‘0’：否）")
    private String costFlag; // 是否计费（‘1’：是，‘ 0’：否）
    @Size(max = 100, message = "备注长度不超过100")
    private String memo; // 备注
    @Size(max = 500, message = "备用1长度不超过500")
    private List<ClearItem> separateInfo; // 分账信息
    @Size(max = 100, message = "备用1长度不超过100")
    private String sp1; // 备用1
    @Size(max = 100, message = "备用2长度不超过100")
    private String sp2; // 备用2
    @Size(max = 100, message = "备用3长度不超过100")
    private String sp3; // 备用3
    @NotNull(message = "一级商户名称不能为空")
    @Size(min = 1, max = 30, message = "一级商户名称长度为1到30之间")
    private String mainCusName;
    @NotNull(message = "一级商户编号不能为空")
    @Size(min = 1, max = 30, message = "一级商户编号长度为1到30之间")
    private String mainCusNo;
    @Size(min = 1, max = 30, message = "二级商户名称长度为1到30之间")
    private String generalCusName;
    @Size(min = 1, max = 30, message = "二级商户编号长度为1到30之间")
    private String generalCusNo;

    public String getMainCusName() {
        return mainCusName;
    }

    public void setMainCusName(String mainCusName) {
        this.mainCusName = mainCusName;
    }

    public String getMainCusNo() {
        return mainCusNo;
    }

    public void setMainCusNo(String mainCusNo) {
        this.mainCusNo = mainCusNo;
    }

    public String getGeneralCusName() {
        return generalCusName;
    }

    public void setGeneralCusName(String generalCusName) {
        this.generalCusName = generalCusName;
    }

    public String getGeneralCusNo() {
        return generalCusNo;
    }

    public void setGeneralCusNo(String generalCusNo) {
        this.generalCusNo = generalCusNo;
    }

    public String getoTrxSerialNo() {
        return oTrxSerialNo;
    }

    public void setoTrxSerialNo(String oTrxSerialNo) {
        this.oTrxSerialNo = oTrxSerialNo;
    }

    public String getCusBillNo() {
        return cusBillNo;
    }

    public void setCusBillNo(String cusBillNo) {
        this.cusBillNo = cusBillNo;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayCurrency() {
        return payCurrency;
    }

    public void setPayCurrency(String payCurrency) {
        this.payCurrency = payCurrency;
    }

    public String getTotalBillNo() {
        return totalBillNo;
    }

    public void setTotalBillNo(String totalBillNo) {
        this.totalBillNo = totalBillNo;
    }

    public BigDecimal getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(BigDecimal totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }



    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public String getTrxState() {
        return trxState;
    }

    public void setTrxState(String trxState) {
        this.trxState = trxState;
    }

    public String getTrxSerialNo() {
        return trxSerialNo;
    }

    public void setTrxSerialNo(String trxSerialNo) {
        this.trxSerialNo = trxSerialNo;
    }

    public Date getOrdCreateTime() {
        return ordCreateTime;
    }

    public void setOrdCreateTime(Date ordCreateTime) {
        this.ordCreateTime = ordCreateTime;
    }

    public Date getOrdSuccTime() {
        return ordSuccTime;
    }

    public void setOrdSuccTime(Date ordSuccTime) {
        this.ordSuccTime = ordSuccTime;
    }

    public String getSeparateFlag() {
        return separateFlag;
    }

    public void setSeparateFlag(String separateFlag) {
        this.separateFlag = separateFlag;
    }

    public String getCostFlag() {
        return costFlag;
    }

    public void setCostFlag(String costFlag) {
        this.costFlag = costFlag;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public List<ClearItem> getSeparateInfo() {
        return separateInfo;
    }

    public void setSeparateInfo(List<ClearItem> separateInfo) {
        this.separateInfo = separateInfo;
    }

    public String getSp1() {
        return sp1;
    }

    public void setSp1(String sp1) {
        this.sp1 = sp1;
    }

    public String getSp2() {
        return sp2;
    }

    public void setSp2(String sp2) {
        this.sp2 = sp2;
    }

    public String getSp3() {
        return sp3;
    }

    public void setSp3(String sp3) {
        this.sp3 = sp3;
    }
}
