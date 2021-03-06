/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation
 * version 2.1 of the License.
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
 * Floor, Boston, MA 02110-1301, USA.
 **/
package org.bonitasoft.engine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.transaction.TransactionManager;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.managed.BasicManagedDataSource;

class BonitaDataSourceInitializer {

    private static final String H2 = "h2";
    private static final String MYSQL = "mysql";
    private static final String ORACLE = "oracle";
    private static final String POSTGRES = "postgres";
    private static final String SQLSERVER = "sqlserver";

    private static final Map<String, String> defaultDriver;

    static {
        HashMap<String, String> drivers = new HashMap<>();
        drivers.put(H2, "org.h2.Driver");
        drivers.put(MYSQL, "com.mysql.cj.jdbc.Driver");
        drivers.put(ORACLE, "oracle.jdbc.OracleDriver");
        drivers.put(POSTGRES, "org.postgresql.Driver");
        drivers.put(SQLSERVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        defaultDriver = Collections.unmodifiableMap(drivers);
    }

    BasicManagedDataSource createManagedDataSource(BonitaDatabaseConfiguration configuration, TransactionManager transactionManager) {
        validate(configuration);
        BasicManagedDataSource bonitaDataSource = new BasicManagedDataSource();
        bonitaDataSource.setDefaultAutoCommit(false);
        bonitaDataSource.setTransactionManager(transactionManager);
        configureDatasource(configuration.getXaDatasource(), bonitaDataSource);
        setCommonDataSourceConfiguration(configuration, bonitaDataSource);
        return bonitaDataSource;
    }

    private void setCommonDataSourceConfiguration(BonitaDatabaseConfiguration configuration, BasicDataSource bonitaDataSource) {
        bonitaDataSource.setInitialSize(1);
        bonitaDataSource.setDriverClassName(getDriverClassName(configuration, configuration.getDriverClassName()));
        bonitaDataSource.setUrl(configuration.getUrl());
        bonitaDataSource.setUsername(configuration.getUser());
        bonitaDataSource.setPassword(configuration.getPassword());
    }

    private String getDriverClassName(BonitaDatabaseConfiguration configuration, String driver) {
        String driverClassName;
        if (driver == null || driver.isEmpty()) {
            driverClassName = defaultDriver.get(configuration.getDbVendor());
        } else {
            driverClassName = driver;
        }
        return driverClassName;
    }

    private void validate(BonitaDatabaseConfiguration configuration) {
        checkNullOrEmpty(configuration.getDbVendor(), "dbVendor");
        if (!defaultDriver.keySet().contains(configuration.getDbVendor())) {
            throw new IllegalArgumentException(String.format("Database db vendor %s is invalid ( should be one of %s )", configuration.getDbVendor(), defaultDriver.keySet()));
        }
        checkNullOrEmpty(configuration.getUrl(), "url");
        checkNullOrEmpty(configuration.getUser(), "user");
    }

    private static void checkNullOrEmpty(String field, String fieldName) {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("Database " + fieldName + " not set");
        }
    }

    BasicDataSource createDataSource(BonitaDatabaseConfiguration configuration) {
        validate(configuration);
        BasicDataSource dataSource = new BasicDataSource();
        configureDatasource(configuration.getDatasource(), dataSource);
        setCommonDataSourceConfiguration(configuration, dataSource);
        return dataSource;
    }

    private void configureDatasource(DatasourceConfiguration configuration, BasicDataSource dataSource) {
        if (configuration != null && configuration.getMaxPoolSize() > 0) {
            dataSource.setMaxTotal(configuration.getMaxPoolSize());
        } else {
            dataSource.setMaxTotal(7);
        }
    }
}
