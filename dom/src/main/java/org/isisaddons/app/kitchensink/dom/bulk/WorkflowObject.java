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
package org.isisaddons.app.kitchensink.dom.bulk;

import javax.inject.Inject;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.BookmarkPolicy;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.InvokeOn;
import org.apache.isis.applib.annotation.Title;
import org.apache.isis.applib.services.actinvoc.ActionInvocationContext;
import org.apache.isis.applib.util.ObjectContracts;

import org.isisaddons.app.kitchensink.dom.Entity;

import lombok.Getter;
import lombok.Setter;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@DomainObject(
        objectType = "WORKFLOW"
)
@DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
public class WorkflowObject implements Entity<WorkflowObject> {

    @Column(allowsNull="false")
    @Title(sequence="1")
    @Getter @Setter
    private String name;


    @javax.jdo.annotations.Column(allowsNull = "false")
    @Getter @Setter
    private State state;

    //region > previous (action)
    @Action(invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
    public WorkflowObject previous() {
        setState(getState().previous());
        return actionInvocationContext.getInvokedOn().isCollection()? null: this;
    }
    //endregion

    //region > next (action)
    @Action(invokeOn = InvokeOn.OBJECT_AND_COLLECTION)
    public WorkflowObject next() {
        setState(getState().next());
        return actionInvocationContext.getInvokedOn().isCollection()? null: this;
    }
    //endregion



    @Override
    public int compareTo(final WorkflowObject other) {
        return ObjectContracts.compare(this, other, "name");
    }


    @Inject
    ActionInvocationContext actionInvocationContext;

}
