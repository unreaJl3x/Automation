package ru.keich.mon.automation.script.version;

import org.springframework.stereotype.Service;

import com.vaadin.flow.data.provider.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ScriptVersionService {
	private final ScriptVersionRepository _scriptVersionRepo;
	private final Function<Query<?,?>, Pageable> getPageableFromQuery;
	
	public ScriptVersionService(ScriptVersionRepository scriptVersionRepo) {
		this._scriptVersionRepo = scriptVersionRepo;
		this.getPageableFromQuery = (q)->{
			return Pageable.ofSize(q.getPageSize()).withPage(q.getPage());
		};
	}
	
	public Optional<List<Integer>> getAllVersionByScriptName(Query<Integer, String> q) {
		var p = this.getPageableFromQuery.apply(q);
		System.out.println("\n[DEBUG| getAllVersionByScriptName] %s %s".formatted(p.getPageNumber(), p.getOffset(), p.getPageSize()));
		return this._scriptVersionRepo.getAllVersionByScriptName(q.getFilter().orElse(""), p);
	}
	public Integer getAllVersionCountByScriptName(Query<Integer, String> q) {
		return this._scriptVersionRepo.getAllVersionCountByScriptName(q.getFilter().orElse(""));
	}
}
