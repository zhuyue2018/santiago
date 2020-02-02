package com.santiago.rcs.service;

import com.santiago.rcs.entity.domain.ClearBookDTO;

public interface ClearBookService {
    void receiveClearBook(ClearBookDTO content);
}
