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
package org.bonitasoft.platform.configuration.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.bonitasoft.platform.configuration.model.FullBonitaConfiguration;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

/**
 * @author Laurent Leseigneur
 */
public class BonitaAllConfigurationContentTypeCleaner implements BatchPreparedStatementSetter {

    public static final String DELETE_CONFIGURATION = "DELETE from configuration where tenant_id = ? and content_type = ? and resource_name = ? ";

    private final List<FullBonitaConfiguration> bonitaConfigurations;

    public BonitaAllConfigurationContentTypeCleaner(List<FullBonitaConfiguration> bonitaConfigurations) {
        this.bonitaConfigurations = bonitaConfigurations;

    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        final FullBonitaConfiguration bonitaConfiguration = bonitaConfigurations.get(i);
        ps.setLong(1, bonitaConfiguration.getTenantId());
        ps.setString(2, bonitaConfiguration.getConfigurationType());
        ps.setString(3, bonitaConfiguration.getResourceName());

    }

    @Override
    public int getBatchSize() {
        return bonitaConfigurations.size();
    }
}
