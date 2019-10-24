package com.santiago.portal.service;

import com.santiago.portal.entity.domain.PmsOperatorRole;

public interface OperatorRoleService {
    PmsOperatorRole create(Long operatorId, Long roleId);
}
