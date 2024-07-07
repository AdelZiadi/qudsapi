package org.nmcpye.datarun.web.rest.common;

import org.nmcpye.datarun.domain.common.IdentifiableObject;
import org.nmcpye.datarun.drun.service.IdentifiableService;
import org.nmcpye.datarun.service.dto.drun.SaveSummary;
import org.nmcpye.datarun.service.dto.drun.SaveSummaryDTO;
import org.nmcpye.datarun.web.rest.ChvRegisterResourceCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/api/custom")
public abstract class AbstractResource<T extends IdentifiableObject> {

    private final Logger log = LoggerFactory.getLogger(ChvRegisterResourceCustom.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    final protected IdentifiableService<T> identifiableService;
    final protected JpaRepository<T, Long> repository;

    protected AbstractResource(IdentifiableService<T> identifiableService,
                               JpaRepository<T, Long> repository) {
        this.identifiableService = identifiableService;
        this.repository = repository;
    }

    /**
     * {@code GET  /Ts} : get all the entities.
     *
     * @param pageable  the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is for internal use only and should not be set by clients)
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assignments in body.
     */
    @GetMapping("")
    public ResponseEntity<PagedResponse<T>> getAllByCurrentUser(@ParameterObject Pageable pageable,
                                                                @RequestParam(name = "paging", required = false, defaultValue = "true") boolean paging,
                                                                @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload) {
        if (!paging) {
            pageable = Pageable.unpaged();
        }
        Page<T> page = getList(pageable, eagerload);
        PagedResponse<T> response = new PagedResponse<>();
        response.setPaging(paging);
        response.setPage(page.getNumber());
        response.setPageCount(page.getTotalPages());
        response.setTotal(page.getTotalElements());
        response.setPageSize(page.getSize());
        response.setItems(page.getContent());
        response.setNextPage(createNextPageLink(page));
        response.setEntityName(getName());
        return ResponseEntity.ok(response);
    }

    private String createNextPageLink(Page<?> page) {
        if (page.hasNext()) {
            return ServletUriComponentsBuilder.fromCurrentRequest()
                .queryParam("page", page.getNumber() + 2) // page is 0-based, but we display it 1-based
                .toUriString();
        } else {
            return null;
        }
    }

//    @PostMapping("/list")
//    public ResponseEntity<SaveSummaryDTO> createMany(@RequestBody List<T> entities) {
//        SaveSummaryDTO summaryDTO = new SaveSummaryDTO();
//        List<String> successfulUids = entities.stream()
//            .map(entity -> {
//                try {
//                    identifiableService.save(entity);
//                    return entity.getUid();
//                } catch (Exception e) {
//                    return null;
//                }
//            })
//            .filter(uid -> uid != null)
//            .collect(Collectors.toList());
//
//        Map<String, String> failedUids = entities.stream()
//            .filter(entity -> !successfulUids.contains(entity.getUid()))
//            .collect(Collectors.toMap(IdentifiableObject::getUid, entity -> "Failed to save entity"));
//
//        summaryDTO.setSuccessfulUids(successfulUids);
//        summaryDTO.setFailedUids(failedUids);
//
//        return ResponseEntity.ok(summaryDTO);
//    }

//    /**
//     * {@code POST  /chv-registers} : Create a new chvRegister.
//     *
//     * @param object the object to create.
//     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new object, or with status {@code 400 (Bad Request)} if the object has already an ID.
//     */
//    @PostMapping("")
//    public ResponseEntity<SaveSummaryDTO> createOne(@RequestBody T object) {
//        SaveSummaryDTO summaryDTO = new SaveSummaryDTO();
//        try {
//            identifiableService.save(object);
//            summaryDTO.setSuccessfulUids(List.of(object.getUid()));
//            summaryDTO.setFailedUids(new HashMap<>());
//        } catch (Exception e) {
//            summaryDTO.setSuccessfulUids(List.of());
//            Map<String, String> failedUids = new HashMap<>();
//            failedUids.put(object.getUid(), e.getMessage());
//            summaryDTO.setFailedUids(failedUids);
//        }
//
//        return ResponseEntity.ok(summaryDTO);
//    }

    @PostMapping("/bulk")
    public ResponseEntity<SaveSummary> saveAll(@RequestBody List<T> entities) {
        SaveSummary summary = new SaveSummary();
        for (T entity : entities) {
            try {
                if (identifiableService.existsByUid(entity.getUid())) {
                    identifiableService.update(entity);
                    summary.getUpdated().add(entity.getUid());
                } else {
                    identifiableService.save(entity);
                    summary.getCreated().add(entity.getUid());
                }
            } catch (Exception e) {
                summary.getFailed().put(entity.getUid(), e.getMessage());
                // Log the exception with stack trace
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok(summary);
    }


    @PostMapping
    public ResponseEntity<SaveSummary> saveOne(@RequestBody T entity) {
        SaveSummary summary = new SaveSummary();
        try {
            if (identifiableService.existsByUid(entity.getUid())) {
                identifiableService.update(entity);
                summary.getUpdated().add(entity.getUid());
            } else {
                identifiableService.save(entity);
                summary.getCreated().add(entity.getUid());
            }
        } catch (Exception e) {
            summary.getFailed().put(entity.getUid(), e.getMessage());
        }
        return ResponseEntity.ok(summary);
    }


    protected abstract Page<T> getList(Pageable pageable, boolean eagerload);

    protected abstract String getName();
}
