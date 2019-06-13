package xyz.wecloud.mybatis.spring.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceType dataSourceType = DataSourceTypeHolder.getDataBaseType();
        return dataSourceType;
    }
}