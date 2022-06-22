package com.my.excel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 描 述: <br/>
 * 作 者: liuliang14<br/>
 * 创 建:2022年05月29⽇<br/>
 * 版 本:v1.0.0<br> *
 * 历 史: (版本) 作者 时间 注释 <br/>
 */

public class CarsMappingData {
    /**
     * 瓜子车系Id
     */
    @ExcelProperty("瓜子车系Id")
    private  Integer guaziCarsId;
    /**
     * 业务商的车系Id
     */
    @ExcelProperty("业务商的车系Id")
    private String bizCarsId;
    /**
     * 业务商code，车300=300
     */
    @ExcelProperty("业务商编码")
    private String bizCode;

    public Integer getGuaziCarsId() {
        return guaziCarsId;
    }

    public void setGuaziCarsId(Integer guaziCarsId) {
        this.guaziCarsId = guaziCarsId;
    }

    public String getBizCarsId() {
        return bizCarsId;
    }

    public void setBizCarsId(String bizCarsId) {
        this.bizCarsId = bizCarsId;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }
}
