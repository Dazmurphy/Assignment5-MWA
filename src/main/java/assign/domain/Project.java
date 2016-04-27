package assign.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="project")
public class Project {
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String description;
	
	@XmlAttribute
	private Long id;
	
	@XmlElementWrapper(name ="meetings")
	private Set<Meeting> meeting;
	
	public Project(){
		
	}

	public Project(String name, String description){
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="project")
	@Cascade({CascadeType.DELETE})
	public Set<Meeting> getMeetings(){
		return this.meeting;
	}
	
	public void setMeetings(Set<Meeting> meetings){
		this.meeting = meetings;
	}
}
