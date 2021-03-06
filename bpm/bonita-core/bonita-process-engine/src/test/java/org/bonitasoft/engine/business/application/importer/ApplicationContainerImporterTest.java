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
package org.bonitasoft.engine.business.application.importer;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.engine.business.application.xml.ApplicationNodeContainer;
import org.bonitasoft.engine.exception.ImportException;
import org.junit.Before;
import org.junit.Test;

public class ApplicationContainerImporterTest {

    private ApplicationContainerImporter importer;

    @Before
    public void setUp() throws Exception {
        importer = new ApplicationContainerImporter();

    }

    @Test
    public void importXML_should_return_result_of_unmarshall() throws Exception {
        //when
        final ApplicationNodeContainer importedContainer = importer.importXML("<applications xmlns=\"http://documentation.bonitasoft.com/application-xml-schema/1.0\"></applications>".getBytes());

        //then
        assertThat(importedContainer.getApplications()).isEmpty();
    }

    @Test(expected = ImportException.class)
    public void importXML_should_throw_ExecutionException_when_unmarshall_throws_exception() throws Exception {
        //given

        //when
        importer.importXML("<applications/>".getBytes());

        //then exception
    }

}
