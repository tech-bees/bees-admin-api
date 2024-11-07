package org.bees.admin.service;

import org.bees.admin.dto.PermissionDto;

public interface PermissionService {

    public PermissionDto findPermissionByName(String name);
}
