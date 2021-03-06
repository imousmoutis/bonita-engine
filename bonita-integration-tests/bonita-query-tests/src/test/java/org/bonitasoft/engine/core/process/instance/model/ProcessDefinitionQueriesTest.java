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
package org.bonitasoft.engine.core.process.instance.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.engine.test.persistence.builder.ActorBuilder.anActor;
import static org.bonitasoft.engine.test.persistence.builder.ActorMemberBuilder.anActorMember;
import static org.bonitasoft.engine.test.persistence.builder.UserBuilder.aUser;
import static org.bonitasoft.engine.test.persistence.builder.UserMembershipBuilder.aUserMembership;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.TransactionManager;

import org.bonitasoft.engine.actor.mapping.model.SActor;
import org.bonitasoft.engine.actor.mapping.model.SActorMember;
import org.bonitasoft.engine.identity.model.SUser;
import org.bonitasoft.engine.identity.model.SUserMembership;
import org.bonitasoft.engine.test.persistence.repository.ProcessInstanceRepository;
import org.bonitasoft.engine.test.persistence.repository.UserMembershipRepository;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"/testContext.xml"})
@Transactional
public class ProcessDefinitionQueriesTest {


    private static final long PROCESS_ID = 45354312L;
    private static final long ROLE_ID = 222222L;
    @Inject
    private ProcessInstanceRepository repository;
    /*
        Tests for queries:
        getNumberOfSUserWhoCanStartProcess
        searchSUserWhoCanStartProcess
     */

    @Test
    public void searchSUserWhoCanStartProcess_should_return_users_having_the_right_user_membership() {
        long G1 = 333331L;
        long G2 = 333332L;
        long G3 = 333333L;
        final SUser john = repository.add(aUser().withUserName("john").withId(1L).build());
        final SUser paul = repository.add(aUser().withUserName("paul").withId(2L).build());
        final SUser walter = repository.add(aUser().withUserName("walter").withId(3L).build());
        final SUser marie = repository.add(aUser().withUserName("marie").withId(4L).build());
        final SActor actor = repository.add(anActor().withScopeId(PROCESS_ID).whoIsInitiator().build());
        repository.add(anActorMember().forActor(actor).withGroupId(G1).withRoleId(ROLE_ID).build());
        repository.add(aUserMembership().forUser(john).memberOf(G1, ROLE_ID).build());
        repository.add(aUserMembership().forUser(paul).memberOf(G1, ROLE_ID).build());
        repository.add(aUserMembership().forUser(walter).memberOf(G2, ROLE_ID).build());
        repository.add(aUserMembership().forUser(marie).memberOf(G3, ROLE_ID).build());


        final List<SUser> users = repository.searchSUserWhoCanStartProcess(PROCESS_ID);

        assertThat(users).hasSize(2).contains(john, paul);

        long numberOfSUserWhoCanStartProcess = repository.getNumberOfSUserWhoCanStartProcess(PROCESS_ID);

        assertThat(numberOfSUserWhoCanStartProcess).isEqualTo(2);
    }

    @Test
    public void searchSUserWhoCanStartProcess_should_return_users_mapped_on_initiator_actor_of_the_process() {
        SUser john = repository.add(aUser().withUserName("john").withId(1L).build());
        repository.add(aUser().withUserName("paul").withId(2L).build());
        SActor actor = repository.add(anActor().withScopeId(PROCESS_ID).whoIsInitiator().build());
        repository.add(anActorMember().forActor(actor).withUserId(john.getId()).build());

        List<SUser> users = repository.searchSUserWhoCanStartProcess(PROCESS_ID);

        assertThat(users).hasSize(1).contains(john);

        long numberOfSUserWhoCanStartProcess = repository.getNumberOfSUserWhoCanStartProcess(PROCESS_ID);

        assertThat(numberOfSUserWhoCanStartProcess).isEqualTo(1);
    }
}
