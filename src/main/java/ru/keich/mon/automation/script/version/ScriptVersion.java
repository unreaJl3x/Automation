package ru.keich.mon.automation.script.version;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EmbeddedId;
@Entity
@Data
public class ScriptVersion {
	@EmbeddedId
	private ScriptVersionId id;
}
