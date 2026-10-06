package ru.keich.mon.automation.script.version;

import org.springframework.stereotype.Service;

import com.vaadin.flow.data.provider.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

@Service
public class ScriptVersionService {
	private final ScriptVersionRepository _scriptVersionRepo;
	private final Function<Query<?,?>, Pageable> getPageableFromQuery;
	
	private Map<String,String> _parseJson(String s) {
		System.out.println(s);
		if (!s.endsWith("}") || !s.startsWith("{")) return null;
		Map<String,String> map = new HashMap<>();
		Queue<String> queue = new LinkedList();
		
		queue.addAll(List.of(s.replace("{", "").replace("}","").strip().split(",")));
		while (!queue.isEmpty()) {
			var keyVal = queue.poll().split("=");
			if (keyVal.length!=2) break;
			System.out.println(keyVal[0]+" "+ keyVal[1]);
			map.put(keyVal[0], keyVal[1]);
		}
		return map;
	}
	
	public ScriptVersionService(ScriptVersionRepository scriptVersionRepo) {
		this._scriptVersionRepo = scriptVersionRepo;
		this.getPageableFromQuery = (q)->{
			return Pageable.ofSize(q.getPageSize()).withPage(q.getPage());
		};
	}
	
	public Optional<List<Integer>> getAllVersionByScriptName(Query<Integer, String> q) {	
		//var p = this.getPageableFromQuery.apply(q);
		//System.err.println("\n[DEBUG| getAllVersionByScriptName] %s %s".formatted(p.getPageNumber(), p.getOffset(), p.getPageSize()));
		var map = this._parseJson(q.getFilter().get());
		return this._scriptVersionRepo.getAllVersionByScriptName(map.get("field"));//, p);
	}
	public Integer getAllVersionCountByScriptName(Query<Integer, String> q) {
		var map = this._parseJson(q.getFilter().get());
		var i =this._scriptVersionRepo.getAllVersionCountByScriptName(map.get("field"));
		System.err.println("\n"+i);
		return i;
	}
	public ScriptVersion save(ScriptVersion ver) {
		return this._scriptVersionRepo.save(ver);
	}
}
