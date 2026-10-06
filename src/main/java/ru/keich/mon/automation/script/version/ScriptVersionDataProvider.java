package ru.keich.mon.automation.script.version;

import java.util.Arrays;
import java.util.stream.Stream;

import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;

public class ScriptVersionDataProvider extends AbstractBackEndDataProvider<Integer, String> {

	private final ScriptVersionService _service;
	
	public ScriptVersionDataProvider(ScriptVersionService service) {
		this._service = service;
	}
	
	@Override
	protected Stream<Integer> fetchFromBackEnd(Query<Integer, String> q) {
		System.out.println("enter dataprovider");
		var r = this._service.getAllVersionByScriptName(q).get();
		System.out.println(r.size());
		return r.stream();
	}

	@Override
	protected int sizeInBackEnd(Query<Integer, String> query) {
		System.out.println("Enter size dataprovider");
		return this._service.getAllVersionCountByScriptName(query);
	}

}
