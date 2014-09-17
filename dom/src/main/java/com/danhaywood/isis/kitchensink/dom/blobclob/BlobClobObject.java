package com.danhaywood.isis.kitchensink.dom.blobclob;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.VersionStrategy;
import com.danhaywood.isis.kitchensink.dom.other.OtherBoundedObjects;
import com.danhaywood.isis.kitchensink.dom.other.OtherObjects;
import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.util.ObjectContracts;
import org.apache.isis.applib.value.Blob;
import org.apache.isis.applib.value.Clob;

@javax.jdo.annotations.PersistenceCapable(identityType=IdentityType.DATASTORE)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
         column="id")
@javax.jdo.annotations.Version(
        strategy=VersionStrategy.VERSION_NUMBER, 
        column="version")
@ObjectType("BLOBCLOB")
@Bookmarkable
public class BlobClobObject implements Comparable<BlobClobObject> {

    //region > nameInCollection (property)

    public String getNameInCollection() {
        return getName();
    }

    //endregion

    //region > name (property)

    private String name;

    @Column(allowsNull="false")
    @Title(sequence="1")
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    //endregion

    //region > someBlob (property)
    @javax.jdo.annotations.Persistent(defaultFetchGroup="false", columns = {
            @javax.jdo.annotations.Column(name = "someBlob_name"),
            @javax.jdo.annotations.Column(name = "someBlob_mimetype"),
            @javax.jdo.annotations.Column(name = "someBlob_bytes", jdbcType = "BLOB", sqlType = "BLOB")
    })
    private Blob someBlob;

    @Optional
    public Blob getSomeBlob() {
        return someBlob;
    }

    public void setSomeBlob(final Blob someBlob) {
        this.someBlob = someBlob;
    }

    @ActionSemantics(ActionSemantics.Of.IDEMPOTENT)
    public BlobClobObject updateSomeBlob(final @Optional Blob i) {
        setSomeBlob(i);
        return this;
    }

    @ActionSemantics(ActionSemantics.Of.IDEMPOTENT)
    public BlobClobObject resetSomeBlob() {
        setSomeBlob(null);
        return this;
    }
    //endregion

    //region > someImage (property)
    @javax.jdo.annotations.Persistent(defaultFetchGroup="false", columns = {
            @javax.jdo.annotations.Column(name = "someImage_name"),
            @javax.jdo.annotations.Column(name = "someImage_mimetype"),
            @javax.jdo.annotations.Column(name = "someImage_bytes", jdbcType = "BLOB", sqlType = "BLOB")
    })
    private Blob someImage;

    @Optional
    public Blob getSomeImage() {
        return someImage;
    }

    public void setSomeImage(final Blob someImage) {
        this.someImage = someImage;
    }

    @ActionSemantics(ActionSemantics.Of.IDEMPOTENT)
    public BlobClobObject updateSomeImage(final @Optional Blob i) {
        setSomeImage(i);
        return this;
    }

    @ActionSemantics(ActionSemantics.Of.IDEMPOTENT)
    public BlobClobObject resetSomeImage() {
        setSomeImage(null);
        return this;
    }
    //endregion

    //region > someClob (property)
    @javax.jdo.annotations.Persistent(defaultFetchGroup="false", columns = {
            @javax.jdo.annotations.Column(name = "someClob_name"),
            @javax.jdo.annotations.Column(name = "someClob_mimetype"),
            @javax.jdo.annotations.Column(name = "someClob_chars", jdbcType = "CLOB", sqlType = "CLOB")
    })
    private Clob someClob;

    @Optional
    public Clob getSomeClob() {
        return someClob;
    }

    public void setSomeClob(final Clob someClob) {
        this.someClob = someClob;
    }

    @ActionSemantics(ActionSemantics.Of.IDEMPOTENT)
    public BlobClobObject updateSomeClob(final @Optional Clob i) {
        setSomeClob(i);
        return this;
    }

    @ActionSemantics(ActionSemantics.Of.IDEMPOTENT)
    public BlobClobObject resetSomeClob() {
        setSomeClob(null);
        return this;
    }
    //endregion

    //region > compareTo

    @Override
    public int compareTo(BlobClobObject other) {
        return ObjectContracts.compare(this, other, "name");
    }

    //endregion

    //region > injected services

    @javax.inject.Inject
    @SuppressWarnings("unused")
    private DomainObjectContainer container;

    @javax.inject.Inject
    private OtherObjects otherObjects;

    @javax.inject.Inject
    private OtherBoundedObjects otherBoundedObjects;

    //endregion

}