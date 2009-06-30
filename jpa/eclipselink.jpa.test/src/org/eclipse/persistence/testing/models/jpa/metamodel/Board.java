/*******************************************************************************
 * Copyright (c) 1998, 2009 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     06/30/2009-2.0  mobrien - finish JPA Metadata API modifications in support
 *       of the Metamodel implementation for EclipseLink 2.0 release involving
 *       Map, ElementCollection and Embeddable types on MappedSuperclass descriptors
 *       - 266912: JPA 2.0 Metamodel API (part of the JSR-317 EJB 3.1 Criteria API)  
 ******************************************************************************/  
package org.eclipse.persistence.testing.models.jpa.metamodel;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.TABLE;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

@Entity(name="BoardMetamodel")
@Table(name="CMP3_MM_BOARD")
public class Board implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy=TABLE, generator="BOARD_MM_TABLE_GENERATOR")
    @TableGenerator(
        name="BOARD_MM_TABLE_GENERATOR", 
        table="CMP3_MM_BOARD_SEQ", 
        pkColumnName="SEQ_MM_NAME", 
        valueColumnName="SEQ_MM_COUNT",
        pkColumnValue="CUST_MM_SEQ"
    )
    @Column(name="BOARD_ID")    
    private Integer id;
    
    @Version
    @Column(name="BOARD_VERSION")
    private int version;
    
    // If a JoinTable with a JoinColumn is used - then we need a mappedBy on the inverse side here
    @OneToMany(cascade=ALL, mappedBy="board")
    private Collection<VectorProcessor> processors = new HashSet<VectorProcessor>();

    // If a JoinTable with a JoinColumn is used - then we need a mappedBy on the inverse side here
    @OneToMany(cascade=ALL, mappedBy="board")
    private Collection<Memory> memories = new HashSet<Memory>();

    public Board() {}

    public int getVersion() { 
        return version; 
    }
    
    protected void setVersion(int version) {
        this.version = version;
    }

    public Collection<VectorProcessor> getProcessors() {
        return processors;
    }

    public void setProcessors(Collection<VectorProcessor> processors) {
        this.processors = processors;
    }

    public Collection<Memory> getMemories() {
        return memories;
    }

    public void setMemories(Collection<Memory> memories) {
        this.memories = memories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
}
