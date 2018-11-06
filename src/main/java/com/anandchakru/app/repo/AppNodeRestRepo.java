package com.anandchakru.app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.anandchakru.app.entity.AppNode;

@RepositoryRestResource(path = "app-node")
public interface AppNodeRestRepo extends PagingAndSortingRepository<AppNode, Long> {
	/*
	@RestResource(path = "by", rel = "by") //	http://localhost:8078/repo/app-node/search/by?name=jrvite
	List<AppNode> findByName(@Param("name") String name);
	*/}