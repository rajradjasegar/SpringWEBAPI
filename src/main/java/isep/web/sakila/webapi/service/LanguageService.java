package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.webapi.model.LanguageWO;

public interface LanguageService
{
	LanguageWO findById(int id);

	void saveLanguage(LanguageWO languageWO);

	void updateLanguage(LanguageWO languageWO);

	void deleteLanguageById(int id);

	List<LanguageWO> findAllLanguages();

}
