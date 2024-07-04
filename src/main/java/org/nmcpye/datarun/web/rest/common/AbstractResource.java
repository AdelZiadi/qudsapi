package org.nmcpye.datarun.web.rest.common;

import org.nmcpye.datarun.domain.common.IdentifiableObject;
import org.nmcpye.datarun.drun.service.IdentifiableService;
import org.nmcpye.datarun.web.rest.ChvRegisterResourceCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public abstract class AbstractResource<T extends IdentifiableObject> {

    private final Logger log = LoggerFactory.getLogger(ChvRegisterResourceCustom.class);

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

    //    /**
//     * {@code POST  /chv-registers} : Create a new chvRegister.
//     *
//     * @param object the object to create.
//     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new object, or with status {@code 400 (Bad Request)} if the object has already an ID.
//     * @throws URISyntaxException if the Location URI syntax is incorrect.
//     */
//    @PostMapping("")
//    public ResponseEntity<T> createChvRegister(@Valid @RequestBody T object) throws URISyntaxException {
//        log.debug("REST request to save Entities : {}", object);
//        if (object.getId() != null) {
//            throw new BadRequestAlertException("A new object cannot already have an ID", getName(), "idexists");
//        }
//        object = chvRegisterService.save(object);
//        return ResponseEntity.created(new URI("/api/chv-registers/" + object.getId()))
//            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, getName(), object.getId().toString()))
//            .body(object);
//    }
    protected abstract Page<T> getList(Pageable pageable, boolean eagerload);

    protected abstract String getName();
}
