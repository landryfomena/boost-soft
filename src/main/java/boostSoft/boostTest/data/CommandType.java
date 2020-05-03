package boostSoft.boostTest.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CommandType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long commandTypeId;
	private String title;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	List<CustomField> customfields;

	@JsonIgnore
	@OneToMany(mappedBy = "commandtype", cascade =  CascadeType.PERSIST, fetch = FetchType.EAGER)
	List<Command> commands;

	public CommandType() {
		super();
	}

	public CommandType(Long commandTypeId, String title, List<CustomField> customfields, List<Command> commands) {
		super();
		this.commandTypeId = commandTypeId;
		this.title = title;
		this.customfields = customfields;
		this.commands = commands;
	}

	public Long getCommandTypeId() {
		return commandTypeId;
	}

	public void setCommandTypeId(Long commandTypeId) {
		this.commandTypeId = commandTypeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<CustomField> getCustomfields() {
		return customfields;
	}

	public void setCustomfields(List<CustomField> customfields) {
		this.customfields = customfields;
	}

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commandTypeId == null) ? 0 : commandTypeId.hashCode());
		result = prime * result + ((commands == null) ? 0 : commands.hashCode());
		result = prime * result + ((customfields == null) ? 0 : customfields.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandType other = (CommandType) obj;
		if (commandTypeId == null) {
			if (other.commandTypeId != null)
				return false;
		} else if (!commandTypeId.equals(other.commandTypeId))
			return false;
		if (commands == null) {
			if (other.commands != null)
				return false;
		} else if (!commands.equals(other.commands))
			return false;
		if (customfields == null) {
			if (other.customfields != null)
				return false;
		} else if (!customfields.equals(other.customfields))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommandType [commandTypeId=" + commandTypeId + ", title=" + title + ", customfields=" + customfields
				+ ", commands=" + commands + "]";
	}

	
}
