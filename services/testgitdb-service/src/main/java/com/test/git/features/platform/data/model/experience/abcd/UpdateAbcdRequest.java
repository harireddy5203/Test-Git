/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.git.features.platform.data.model.experience.abcd;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Implementation of an experience model that is meant to be used by the API Layer for communication
 * either with the front-end or to the service-layer.
 *
 * @author Admin
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class UpdateAbcdRequest {
    /** Reference to the id. */
    @NotNull(message = "abcd.id.not.null.message")
    private Integer id;

    /** Reference to the name. */
    @NotBlank(message = "abcd.name.not.blank.message")
    @Size(max = 20, message = "abcd.name.size.message")
    private String name;
}
