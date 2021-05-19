/*
 * Copyright (c) 2020-2021 Innominds inc. All Rights Reserved. This software is
 * confidential and proprietary information of Innominds inc. You shall not disclose
 * Confidential Information and shall use it only in accordance with the terms
 *
 */
package com.test.git.features.platform.web.service;

import com.test.git.commons.data.utils.PageUtils;
import com.test.git.commons.instrumentation.Instrument;
import com.test.git.features.platform.data.mapper.AbcdMapper;
import com.test.git.features.platform.data.model.experience.abcd.Abcd;
import com.test.git.features.platform.data.model.experience.abcd.CreateAbcdRequest;
import com.test.git.features.platform.data.model.experience.abcd.UpdateAbcdRequest;
import com.test.git.features.platform.data.model.persistence.AbcdEntity;
import com.test.git.features.platform.data.repository.AbcdRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * Service implementation that provides CRUD (Create, Read, Update, Delete) capabilities for
 * entities of type {@link AbcdEntity}.
 *
 * @author Admin
 */
@Slf4j
@Validated
@Service
public class AbcdService {
    /** Repository implementation of type {@link AbcdRepository}. */
    private final AbcdRepository abcdRepository;

    /** Mapper implementation of type {@link AbcdMapper} to transform between different types. */
    private final AbcdMapper abcdMapper;

    /**
     * Constructor.
     *
     * @param abcdRepository Repository instance of type {@link AbcdRepository}.
     * @param abcdMapper Mapper instance of type {@link AbcdMapper}.
     */
    public AbcdService(final AbcdRepository abcdRepository, final AbcdMapper abcdMapper) {
        this.abcdRepository = abcdRepository;
        this.abcdMapper = abcdMapper;
    }

    /**
     * This method attempts to create an instance of type {@link AbcdEntity} in the system based on
     * the provided payload.
     *
     * @param payload Payload containing the details required to create an instance of type {@link
     *     AbcdEntity}.
     * @return An experience model of type {@link Abcd} that represents the newly created entity of
     *     type {@link AbcdEntity}.
     */
    @Instrument
    @Transactional
    public Abcd createAbcd(@Valid final CreateAbcdRequest payload) {
        // 1. Transform the experience model to a persistence model.
        final AbcdEntity abcdEntity = abcdMapper.transform(payload);

        // 2. Save the entity.
        AbcdService.LOGGER.debug("Saving a new instance of type - AbcdEntity");
        final AbcdEntity newInstance = abcdRepository.save(abcdEntity);

        // 3. Transform the created entity to an experience model and return it.
        return abcdMapper.transform(newInstance);
    }

    /**
     * This method attempts to update an existing instance of type {@link AbcdEntity} using the
     * details from the provided input, which is an instance of type {@link UpdateAbcdRequest}.
     *
     * @param abcdId Unique identifier of Abcd in the system, which needs to be updated.
     * @param payload Request payload containing the details of an existing Abcd, which needs to be
     *     updated in the system.
     * @return A instance of type {@link Abcd} containing the updated details.
     */
    @Instrument
    @Transactional
    public Abcd updateAbcd(final Integer abcdId, @Valid final UpdateAbcdRequest payload) {
        // 1. Verify that the entity being updated truly exists.
        final AbcdEntity matchingInstance = abcdRepository.findByIdOrThrow(abcdId);

        // 2. Transform the experience model to a persistence model and delegate to the save()
        // method.
        abcdMapper.transform(payload, matchingInstance);

        // 3. Save the entity
        AbcdService.LOGGER.debug("Saving the updated entity - AbcdEntity");
        final AbcdEntity updatedInstance = abcdRepository.save(matchingInstance);

        // 4. Transform updated entity to output object
        return abcdMapper.transform(updatedInstance);
    }

    /**
     * This method attempts to find a {@link AbcdEntity} whose unique identifier matches the
     * provided identifier.
     *
     * @param abcdId Unique identifier of Abcd in the system, whose details have to be retrieved.
     * @return Matching entity of type {@link Abcd} if found, else returns null.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Abcd findAbcd(final Integer abcdId) {
        // 1. Find a matching entity and throw an exception if not found.
        final AbcdEntity matchingInstance = abcdRepository.findByIdOrThrow(abcdId);

        // 2. Transform the matching entity to the desired output.
        return abcdMapper.transform(matchingInstance);
    }

    /**
     * This method attempts to find instances of type AbcdEntity based on the provided page
     * definition. If the page definition is null or contains invalid values, this method attempts
     * to return the data for the first page (i.e. page index is 0) with a default page size as 20.
     *
     * @return Returns a page of objects based on the provided page definition. Each object in the
     *     returned page is an instance of type {@link Abcd}.
     */
    @Instrument
    @Transactional(readOnly = true)
    public Page<Abcd> findAllAbcds(final Pageable page) {
        // 1. Validate the provided pagination settings.
        final Pageable pageSettings = PageUtils.validateAndUpdatePaginationConfiguration(page);
        AbcdService.LOGGER.debug(
                "Page settings: page number {}, page size {}",
                pageSettings.getPageNumber(),
                pageSettings.getPageSize());

        // 2. Delegate to the super class method to find the data (page settings are verified in
        // that method).
        final Page<AbcdEntity> pageData = abcdRepository.findAll(pageSettings);

        // 3. If the page has data, transform each element into target type.
        if (pageData.hasContent()) {
            final List<Abcd> dataToReturn =
                    pageData.getContent().stream()
                            .map(abcdMapper::transform)
                            .collect(Collectors.toList());

            return PageUtils.createPage(dataToReturn, pageSettings, pageData.getTotalElements());
        }

        // Return empty page.
        return PageUtils.emptyPage(pageSettings);
    }

    /**
     * This method attempts to delete an existing instance of type {@link AbcdEntity} whose unique
     * identifier matches the provided identifier.
     *
     * @param abcdId Unique identifier of Abcd in the system, which needs to be deleted.
     * @return Unique identifier of the instance of type AbcdEntity that was deleted.
     */
    @Instrument
    @Transactional
    public Integer deleteAbcd(final Integer abcdId) {
        // 1. Delegate to our repository method to handle the deletion.
        return abcdRepository.deleteOne(abcdId);
    }
}
