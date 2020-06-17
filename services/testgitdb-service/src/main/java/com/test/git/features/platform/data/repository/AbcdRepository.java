/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.git.features.platform.data.repository;

import com.test.git.commons.data.jpa.repository.ExtendedJpaRepository;
import com.test.git.features.platform.data.model.persistence.AbcdEntity;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to handle the operations pertaining to domain models of type "AbcdEntity".
 *
 * @author Admin
 */
@Repository
public interface AbcdRepository extends ExtendedJpaRepository<AbcdEntity, Integer> {
    // Any custom methods can be added here.
}
