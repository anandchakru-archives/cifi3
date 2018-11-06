package com.anandchakru.app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.anandchakru.app.entity.AppHistory;

@RepositoryRestResource(path = "app-history")
public interface AppHistoryRestRepo extends PagingAndSortingRepository<AppHistory, Long> {
	/*
	public AppHistory findByCommitId(String commitId);
	public AppHistory findByCommitIdAndApp_AppId(String commitId, Long appId);
	//
	@RestResource(path = "byAppName", rel = "byAppName") //eg:	http://localhost:8078/repo/history/search/byAppName?name=jrvite
	List<AppHistory> findByApp_AppNameOrderByTimeDesc(@Param("name") String appName);
	@RestResource(path = "byAppId", rel = "byAppId") //eg:	http://localhost:8078/repo/history/search/byAppId?id=1
	List<AppHistory> findByApp_AppId(@Param("id") Long appId);
	//
	@Modifying
	@Transactional
	@RestResource(exported = false)
	@Query("update AppHistory a set a.status=?2 where a.commitId = ?1")
	public void setFixedStatusFor(String commitId, HistoryStatus status);
	@Modifying
	@Transactional
	@RestResource(exported = false)
	@Query("update AppHistory a set a.assetId=?2, a.assetUrl=?3, a.status=?4, a.tag=?5, a.version=?6 where a.appHistoryId = ?1")
	public void setFixedAssetStatusTagVersionFor(Long appHistoryId, String assetId, String assetUrl,
	HistoryStatus status, String tag, String version);
	*/}