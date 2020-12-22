package com.edt.qo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SystemDictionaryItemQueryObject extends QueryObject {
//        目录id
    private Long typeId;
//上级明细ID
    private Long parentId;
}
