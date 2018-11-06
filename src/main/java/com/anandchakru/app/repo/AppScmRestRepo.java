package com.anandchakru.app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.anandchakru.app.entity.AppScm;

@RepositoryRestResource(path = "app-scm")
public interface AppScmRestRepo extends PagingAndSortingRepository<AppScm, Long> {
}