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
package org.bonitasoft.engine.dependency.model;

import org.bonitasoft.engine.persistence.PersistentObject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AbstractSDependency implements PersistentObject {

    public static final String DESCRIPTION = "description";
    public static final String FILE_NAME = "fileName";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String VALUE = "value_";

    private long tenantId;
    private long id;
    private String name;
    private String fileName;
    private String description;
    private byte[] value_;

    public AbstractSDependency(final String name, final String fileName, final byte[] value) {
        super();
        this.name = name;
        this.fileName = fileName;
        this.value_ = value;
    }

    public byte[] getValue() {
        return value_;
    }

}
