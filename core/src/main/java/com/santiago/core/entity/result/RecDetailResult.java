package com.santiago.core.entity.result;

import java.util.List;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-25 14:19
 **/
public class RecDetailResult {
    List<DiffRecord> sysExceedList;
    List<DiffRecord> chnlExceedList;

    public RecDetailResult(List<DiffRecord> sysExceedList, List<DiffRecord> chnlExceedList) {
        this.sysExceedList = sysExceedList;
        this.chnlExceedList = chnlExceedList;
    }
}
