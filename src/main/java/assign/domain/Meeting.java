package assign.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "meeting")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "meeting")
public class Meeting {

	@XmlAttribute
	private Long id;

	@XmlElement
	private String name;

	@XmlElement
	private Long year;

	@XmlTransient
	private Project project;

	public Meeting() {

	}

	public Meeting(String name, Long year) {
		this.name = name;
		this.year = year;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	@ManyToOne
	@JoinColumn(name = "projectId")
	public Project getProject() {
		return this.project;
	}

	public void setProject(Project p) {
		this.project = p;
	}
}
