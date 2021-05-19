/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.git.features.platform.data.mapper;

import com.test.git.features.platform.data.model.experience.abcd.Abcd;
import com.test.git.features.platform.data.model.experience.abcd.CreateAbcdRequest;
import com.test.git.features.platform.data.model.experience.abcd.UpdateAbcdRequest;
import com.test.git.features.platform.data.model.persistence.AbcdEntity;
import java.util.Collection;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper contract that maps / transforms data from an instance of type {@link AbcdEntity to {@link Abcd and vice-versa.
 *
 * @author Admin
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AbcdMapper {

    /**
     * This method transforms an instance of type {@link CreateAbcdRequest} to an instance of type
     * {@link AbcdEntity}.
     *
     * @param source Instance of type {@link CreateAbcdRequest} that needs to be transformed to
     *     {@link AbcdEntity}.
     * @return Instance of type {@link AbcdEntity}.
     */
    AbcdEntity transform(CreateAbcdRequest source);

    /**
     * This method transforms an instance of type {@link AbcdEntity} to an instance of type {@link
     * Abcd}.
     *
     * @param source Instance of type {@link AbcdEntity} that needs to be transformed to {@link
     *     Abcd}.
     * @return Instance of type {@link Abcd}.
     */
    Abcd transform(AbcdEntity source);

    /**
     * This method converts / transforms the provided collection of {@link AbcdEntity} instances to
     * a collection of instances of type {@link Abcd}.
     *
     * @param source Instance of type {@link AbcdEntity} that needs to be transformed to {@link
     *     Abcd}.
     * @return Collection of instance of type {@link Abcd}.
     */
    default Collection<Abcd> transformListTo(Collection<AbcdEntity> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
    /**
     * This method transforms an instance of type {@link UpdateAbcdRequest} to an instance of type
     * {@link AbcdEntity}.
     *
     * <p>The provided instance ({@code target}) will be updated instead of creating a new instance.
     *
     * @param source Instance of type {@link UpdateAbcdRequest} that needs to be transformed to
     *     {@link AbcdEntity}.
     * @param target Instance of type {@link AbcdEntity} that will be updated instead of creating
     *     and returning a new instance.
     */
    void transform(UpdateAbcdRequest source, @MappingTarget AbcdEntity target);

    /**
     * This method transforms an instance of type {@link UpdateAbcdRequest} to an instance of type
     * {@link AbcdEntity}.
     *
     * @param source Instance of type {@link UpdateAbcdRequest} that needs to be transformed to
     *     {@link AbcdEntity}.
     * @return Instance of type {@link AbcdEntity}.
     */
    AbcdEntity transform(UpdateAbcdRequest source);

    /**
     * This method converts / transforms the provided collection of {@link UpdateAbcdRequest}
     * instances to a collection of instances of type {@link AbcdEntity}.
     *
     * @param source Instance of type {@link UpdateAbcdRequest} that needs to be transformed to
     *     {@link AbcdEntity}.
     * @return Instance of type {@link AbcdEntity}.
     */
    default Collection<AbcdEntity> transformList(Collection<UpdateAbcdRequest> source) {
        return source.stream().map(this::transform).collect(Collectors.toSet());
    }
}
