package xyz.wecloud.mybatis.spring.datasource;

public class DataSourceTypeHolder {
    private static final ThreadLocal<DataSourceType> DATA_BASE_TYPE = new ThreadLocal<>();

    public static void setDataBaseType(DataSourceType dataSourceType) {
        DATA_BASE_TYPE.set(dataSourceType);
    }

    public static DataSourceType getDataBaseType() {
        return DATA_BASE_TYPE.get();
    }
}
