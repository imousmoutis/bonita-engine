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

import org.bonitasoft.engine.identity.model.SUser;
import org.bonitasoft.engine.test.persistence.builder.PersistentObjectBuilder;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * @author Emmanuel Duchastenier
 */
@Repository
public class UserRepository extends TestRepository {

    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public SUser getUserByName(String userName) {
        getSession().enableFilter("tenantFilter").setParameter("tenantId", PersistentObjectBuilder.DEFAULT_TENANT_ID);
        Query namedQuery = getNamedQuery("getUserByUserName");
        namedQuery.setParameter("userName", userName);
        return ((SUser) namedQuery.uniqueResult());
    }

}