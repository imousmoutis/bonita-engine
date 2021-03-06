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
package org.bonitasoft.engine.search.descriptor;

import org.bonitasoft.engine.core.form.SFormMapping;
import org.bonitasoft.engine.core.process.instance.model.event.handling.SMessageInstance;
import org.bonitasoft.engine.form.FormMappingSearchDescriptor;
import org.bonitasoft.engine.form.FormMappingType;
import org.bonitasoft.engine.page.SPageMapping;
import org.bonitasoft.engine.persistence.PersistentObject;
import org.bonitasoft.engine.profile.model.SProfile;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Baptiste Mesta
 */
public class SearchMessageInstanceDescriptor extends SearchEntityDescriptor {

    private final Map<String, FieldDescriptor> searchEntityKeys;

    private final Map<Class<? extends PersistentObject>, Set<String>> profileAllFields;

    public SearchMessageInstanceDescriptor() {
        searchEntityKeys = new HashMap<>(1);
        searchEntityKeys.put("messageName", new FieldDescriptor(SMessageInstance.class, "messageName"));

        profileAllFields = new HashMap<>(1);
        profileAllFields.put(SProfile.class, new HashSet<String>(0));
    }

    @Override
    protected Map<String, FieldDescriptor> getEntityKeys() {
        return searchEntityKeys;
    }

    @Override
    protected Map<Class<? extends PersistentObject>, Set<String>> getAllFields() {
        return profileAllFields;
    }

    @Override
    protected Serializable convertFilterValue(String filterField, Serializable filterValue) {
        if (filterValue instanceof FormMappingType) {
            return ((FormMappingType) filterValue).getId();
        }
        return super.convertFilterValue(filterField, filterValue);
    }
}
