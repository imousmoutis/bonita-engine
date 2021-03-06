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
package org.bonitasoft.engine.identity.model.builder.impl;

import org.bonitasoft.engine.identity.model.SUser;
import org.bonitasoft.engine.identity.model.builder.SUserUpdateBuilder;
import org.bonitasoft.engine.recorder.model.EntityUpdateDescriptor;

/**
 * @author Baptiste Mesta
 * @author Yanyan Liu
 * @author Matthieu Chaffotte
 * @author Celine Souchet
 */
public class SUserUpdateBuilderImpl implements SUserUpdateBuilder {

    private final EntityUpdateDescriptor descriptor;

    public SUserUpdateBuilderImpl(final EntityUpdateDescriptor descriptor) {
        super();
        this.descriptor = descriptor;
    }
    public static SUserUpdateBuilder updateBuilder() {
        return new SUserUpdateBuilderImpl(new EntityUpdateDescriptor());
    }

    @Override
    public EntityUpdateDescriptor done() {
        return descriptor;
    }

    @Override
    public SUserUpdateBuilder updateUserName(final String username) {
        descriptor.addField(SUser.USER_NAME, username);
        return this;
    }

    @Override
    public SUserUpdateBuilder updatePassword(final String password) {
        descriptor.addField(SUser.PASSWORD, password);
        return this;
    }

    @Override
    public SUserUpdateBuilder updateFirstName(final String firstName) {
        descriptor.addField(SUser.FIRST_NAME, firstName);
        return this;
    }

    @Override
    public SUserUpdateBuilder updateLastName(final String lastName) {
        descriptor.addField(SUser.LAST_NAME, lastName);
        return this;
    }

    @Override
    public SUserUpdateBuilder updateTitle(final String title) {
        descriptor.addField(SUser.TITLE, title);
        return this;
    }

    @Override
    public SUserUpdateBuilder updateJobTitle(final String jobTitle) {
        descriptor.addField(SUser.JOB_TITLE, jobTitle);
        return this;
    }

    @Override
    public SUserUpdateBuilder updateManagerUserId(final long managerUserId) {
        descriptor.addField(SUser.MANAGER_USER_ID, managerUserId);
        return this;
    }

    @Override
    public SUserUpdateBuilder updateLastUpdate(final long lastUpdate) {
        descriptor.addField(SUser.LAST_UPDATE, lastUpdate);
        return this;
    }

    @Override
    public SUserUpdateBuilder updateLastConnection(final long lastConnection) {
        descriptor.addField(SUser.LAST_CONNECTION, lastConnection);
        return this;
    }

    @Override
    public SUserUpdateBuilder updateEnabled(final boolean enabled) {
        descriptor.addField(SUser.ENABLED, enabled);
        return this;
    }
}
