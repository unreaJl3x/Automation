package ru.keich.mon.automation.script.version;

import com.github.difflib.text.DiffRowGenerator;
import java.util.List;
import com.github.difflib.text.DiffRow;

public class DiffRowGeneratorVersions {
	public static DiffRowGenerator build() {
		return DiffRowGenerator.create().showInlineDiffs(true).mergeOriginalRevised(true).inlineDiffByWord(true)
				.oldTag(f -> "-").newTag(f -> "+").build();
	}
}