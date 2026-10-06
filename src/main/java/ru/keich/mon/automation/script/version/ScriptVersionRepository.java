package ru.keich.mon.automation.script.version;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ScriptVersionRepository extends JpaRepository<ScriptVersion, Integer> {
	@Query(value="Select version from where name like ?1",nativeQuery=true)
	public Optional<List<Integer>> getAllVersionByScriptName(String name, Pageable page);
	
	@Query(value="Select COUNT(*) where name like :name ",nativeQuery=true)
	public Integer getAllVersionCountByScriptName(String name);
}
