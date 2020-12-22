package com.edt.qo;

import com.edt.domain.SystemDictionaryItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class ConsumptionItemQueryObject extends QueryObject {

    private String keyword;
    /** 业务大类*/
    private SystemDictionaryItem category;

    /** 结算单流水号*/
    private String cno;

    private BigDecimal minPayAmount;

    private BigDecimal maxPayAmount;
}
