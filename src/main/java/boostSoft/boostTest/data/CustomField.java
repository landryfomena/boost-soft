package boostSoft.boostTest.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CustomField {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customFieldsId;
	private String title;
	private float value;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST)
	List<CommandType> commandtypes;

	public CustomField() {
		super();
	}

	public CustomField(Long customFieldsId, String title, float value, List<CommandType> commandtypes) {
		super();
		this.customFieldsId = customFieldsId;
		this.title = title;
		this.value = value;
		this.commandtypes = commandtypes;
	}

	public Long getCustomFieldsId() {
		return customFieldsId;
	}

	public void setCustomFieldsId(Long customFieldsId) {
		this.customFieldsId = customFieldsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public List<CommandType> getCommandtypes() {
		return commandtypes;
	}

	public void setCommandtypes(List<CommandType> commandtypes) {
		this.commandtypes = commandtypes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commandtypes == null) ? 0 : commandtypes.hashCode());
		result = prime * result + ((customFieldsId == null) ? 0 : customFieldsId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + Float.floatToIntBits(value);
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
		CustomField other = (CustomField) obj;
		if (commandtypes == null) {
			if (other.commandtypes != null)
				return false;
		} else if (!commandtypes.equals(other.commandtypes))
			return false;
		if (customFieldsId == null) {
			if (other.customFieldsId != null)
				return false;
		} else if (!customFieldsId.equals(other.customFieldsId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomField [customFieldsId=" + customFieldsId + ", title=" + title + ", value=" + value
				+ ", commandtypes=" + commandtypes + "]";
	}

	
}
