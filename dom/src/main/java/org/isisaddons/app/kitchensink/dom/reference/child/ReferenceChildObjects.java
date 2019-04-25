/*
 *  Copyright 2014 Dan Haywood
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.isisaddons.app.kitchensink.dom.reference.child;

import java.util.List;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.ParameterLayout;

import org.isisaddons.app.kitchensink.dom.RepositoryAbstract;
import org.isisaddons.app.kitchensink.dom.reference.ReferenceObject;

@DomainService(
        repositoryFor = ReferenceChildObject.class
)
public class ReferenceChildObjects extends RepositoryAbstract<ReferenceChildObject> {

    public ReferenceChildObjects() {
        super(ReferenceChildObject.class, Visibility.NOT_VISIBLE);
    }

    @MemberOrder(sequence = "30")
    public ReferenceChildObject create(
            @ParameterLayout(named="Name")
            final String name,
            final ReferenceObject parentObject) {
        final ReferenceChildObject obj = factoryService.instantiate(ReferenceChildObject.class);
        obj.setName(name);
        obj.setParent(parentObject);

        repositoryService.persist(obj);
        return obj;
    }

    @MemberOrder(sequence = "50")
    public List<ReferenceChildObject> findNamed(final @ParameterLayout(named = "Search") String search) {
        return Lists.newArrayList(
            FluentIterable.from(listAll()).filter(input -> input.getName().contains(search)).toList());
    }
}
