package org.isisaddons.app.kitchensink.app.menu;

import java.util.List;

import javax.inject.Inject;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.DomainServiceLayout;
import org.apache.isis.applib.annotation.NatureOfService;
import org.apache.isis.applib.annotation.SemanticsOf;

import org.isisaddons.app.kitchensink.dom.reference.ReferenceChildObject;
import org.isisaddons.app.kitchensink.dom.reference.ReferenceChildObjects;
import org.isisaddons.app.kitchensink.dom.reference.ReferenceObject;
import org.isisaddons.app.kitchensink.dom.reference.ReferenceObjects;

@DomainService(nature = NatureOfService.VIEW)
@DomainServiceLayout(
        named = "TestMenu",
        menuOrder = ""
)
public class ReferenceObjectMenu {

    @Action(semantics = SemanticsOf.IDEMPOTENT)
    public ReferenceObject moveChildrenTest(
            final ReferenceObject object,
            final List<ReferenceChildObject> childObjects
            ){
        for (ReferenceChildObject childObject : childObjects) {
            childObject.setParent(object);
        }
        return object;
    }

    public List<ReferenceObject> choices0MoveChildrenTest() {
        return referenceObjects.listAll();
    }

    public List<ReferenceChildObject> choices1MoveChildrenTest(final ReferenceObject object) {
        return object!=null ? otherChildren(object) : null;
    }

    private List<ReferenceChildObject> otherChildren(final ReferenceObject object) {
        final List<ReferenceChildObject> referenceChildObjects = this.referenceChildObjects.listAll();
        referenceChildObjects.removeAll(object.getChildren());
        return referenceChildObjects;
    }

    @Inject
    ReferenceChildObjects referenceChildObjects;

    @Inject
    ReferenceObjects referenceObjects;

}