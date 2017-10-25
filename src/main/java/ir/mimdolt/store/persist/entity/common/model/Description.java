package ir.mimdolt.store.persist.entity.common.model;


import ir.mimdolt.store.persist.entity.common.model.audit.AuditListener;
import ir.mimdolt.store.persist.entity.common.model.audit.AuditSection;
import ir.mimdolt.store.persist.entity.common.model.audit.Auditable;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@EntityListeners(value = AuditListener.class)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Description implements Auditable, Serializable {
	private static final long serialVersionUID = -4335863941736710046L;
	
	@Id
	@Column(name = "DESCRIPTION_ID")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	@NotEmpty
	@Column(name="NAME", nullable = false, length=120)
	private String name;
	
	@Column(name="TITLE", length=100)
	private String title;
	
	@Column(name="DESCRIPTION")
	@Type(type = "org.hibernate.type.StringClobType")
	private String description;
	
	public Description() {
	}

	
	@Override
	public AuditSection getAuditSection() {
		return auditSection;
	}

	@Override
	public void setAuditSection(AuditSection auditSection) {
		this.auditSection = auditSection;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
