package com.anandchakru.app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.anandchakru.app.entity.AppPipe;

@RepositoryRestResource(path = "app-pipe")
public interface AppPipeRestRepo extends PagingAndSortingRepository<AppPipe, Long> {
}