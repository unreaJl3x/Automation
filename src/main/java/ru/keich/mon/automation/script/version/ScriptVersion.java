package ru.keich.mon.automation.script.version;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Comparator;

@Entity
@Data
@NoArgsConstructor
public class ScriptVersion implements Comparable  {
	@Id
	private Instant id;
	@Column()
	private int version;
	@Column()
	private String note;
	@Column()
	private String name;
	@Column(columnDefinition = "TEXT")
	private String code;

	public ScriptVersion(int version, String note, String name, String code) {
		this.id = Instant.now();
		this.version = version;
		this.note = note;
		this.name = name;
		this.code = code;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(version);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ScriptVersion sc) {
			return (this.id == sc.getId()) && this.name.equals(sc.getName());
		} else return false;
	}

	@Override
	public int compareTo(Object o) {
		Objects.requireNonNull(o);
		if (o instanceof ScriptVersion sc) 
			return this.getId().compareTo(sc.getId());
		else
			throw new ClassCastException();
	}
}