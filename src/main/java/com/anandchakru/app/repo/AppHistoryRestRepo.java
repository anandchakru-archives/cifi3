package com.anandchakru.app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import com.anandchakru.app.entity.AppHistory;

@RepositoryRestResource(path = "app-history")
public interface AppHistoryRestRepo extends PagingAndSortingRepository<AppHistory, Long> {
	@RestResource(path = "appIdAndCommitId", rel = "appIdAndCommitId") //	http://localhost:8078/repo/app-node/search/appIdAndCommitId?appId=1&commitId=c03ea15fbbbd7b886e744cf712b6e73434843570
	public AppHistory findByApp_AppIdAndCommitId(@Param("appId") Long appId, @Param("commitId") String commitId);
}