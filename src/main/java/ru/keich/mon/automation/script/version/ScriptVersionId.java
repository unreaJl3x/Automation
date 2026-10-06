package ru.keich.mon.automation.script.version;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Embeddable
public record ScriptVersionId (
		@Column(name="name",nullable=false)
		String name,
		@Column(name="version",nullable=false)
		int version
) {}
