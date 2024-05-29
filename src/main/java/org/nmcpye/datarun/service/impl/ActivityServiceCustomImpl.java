package org.nmcpye.datarun.service.impl;

import org.nmcpye.datarun.domain.Activity;
import org.nmcpye.datarun.repository.ActivityRepositoryCustom;
import org.nmcpye.datarun.service.ActivityServiceCustom;
import org.nmcpye.datarun.utils.CodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional
public class ActivityServiceCustomImpl
    extends ActivityServiceImpl
    implements ActivityServiceCustom {

    private final Logger log = LoggerFactory.getLogger(ActivityServiceImpl.class);

    private final ActivityRepositoryCustom activityRepository;


    public ActivityServiceCustomImpl(ActivityRepositoryCustom activityRepository) {
        super(activityRepository);
        this.activityRepository = activityRepository;
    }

    @Override
    public Activity save(Activity activity) {
        if (activity.getUid() == null || activity.getUid().isEmpty()) {
            activity.setUid(CodeGenerator.generateUid());
        }
        return activityRepository.save(activity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Activity> findAll(Pageable pageable) {
        log.debug("Request to get all Activities");
        return activityRepository.findAllByUser(pageable);
    }

    public Page<Activity> findAllWithEagerRelationships(Pageable pageable) {
        return activityRepository.findAllWithEagerRelationshipsByUser(pageable);
    }
}
