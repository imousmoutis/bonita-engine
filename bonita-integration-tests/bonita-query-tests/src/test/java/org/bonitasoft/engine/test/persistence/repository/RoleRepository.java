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
package org.bonitasoft.engine.test.persistence.repository;

import java.util.List;

import org.bonitasoft.engine.identity.model.SGroup;
import org.bonitasoft.engine.identity.model.SRole;
import org.bonitasoft.engine.test.persistence.builder.PersistentObjectBuilder;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

/**
 * author Emmanuel Duchastenier
 */
public class RoleRepository extends TestRepository {

    public RoleRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public SRole getRoleByName(long tenantId, String name) {
        getSession().enableFilter("tenantFilter").setParameter("tenantId", tenantId);
        Query namedQuery = getNamedQuery("getRoleByName");
        namedQuery.setParameter("name", name);
        return ((SRole) namedQuery.uniqueResult());
    }


}
