package net.rockey.core.hibernate;

import org.hibernate.stat.CollectionStatistics;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.NaturalIdCacheStatistics;
import org.hibernate.stat.QueryStatistics;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;

public class StatisticsWrapper implements Statistics {

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityStatistics getEntityStatistics(String entityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollectionStatistics getCollectionStatistics(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SecondLevelCacheStatistics getSecondLevelCacheStatistics(
			String regionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NaturalIdCacheStatistics getNaturalIdCacheStatistics(
			String regionName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryStatistics getQueryStatistics(String queryString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getEntityDeleteCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getEntityInsertCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getEntityLoadCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getEntityFetchCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getEntityUpdateCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getQueryExecutionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getQueryExecutionMaxTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getQueryExecutionMaxTimeQueryString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getQueryCacheHitCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getQueryCacheMissCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getQueryCachePutCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNaturalIdQueryExecutionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNaturalIdQueryExecutionMaxTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getNaturalIdQueryExecutionMaxTimeRegion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getNaturalIdCacheHitCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNaturalIdCacheMissCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNaturalIdCachePutCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getUpdateTimestampsCacheHitCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getUpdateTimestampsCacheMissCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getUpdateTimestampsCachePutCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getFlushCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getConnectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSecondLevelCacheHitCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSecondLevelCacheMissCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSecondLevelCachePutCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSessionCloseCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getSessionOpenCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionLoadCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionFetchCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionUpdateCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionRemoveCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionRecreateCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getStartTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void logSummary() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStatisticsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setStatisticsEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getQueries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getEntityNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getCollectionRoleNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getSecondLevelCacheRegionNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getSuccessfulTransactionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getTransactionCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getPrepareStatementCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCloseStatementCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getOptimisticFailureCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
