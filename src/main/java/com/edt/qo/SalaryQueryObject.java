package com.edt.qo;

import com.edt.domain.SystemDictionaryItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class SalaryQueryObject extends  QueryObject {
    private String keyword;

    private SystemDictionaryItem payout;

    private BigDecimal minMoney;

    private BigDecimal maxMoney;
}
