package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.LanguageRepository;
import isep.web.sakila.jpa.entities.Language;
import isep.web.sakila.webapi.model.LanguageWO;

@Service("languageService")
@Transactional
public class LanguageServiceImpl implements LanguageService
{
	@Autowired
	private LanguageRepository	languageRepository;

	private static final Log log = LogFactory.getLog(LanguageServiceImpl.class);

	public List<LanguageWO> findAllLanguages()
	{
		List<LanguageWO> languages = new LinkedList<LanguageWO>();

		for (Language language : languageRepository.findAll())
		{
			languages.add(new LanguageWO(language));
			log.debug("Adding " + language);
		}

		return languages;
	}

	public LanguageWO findById(byte id)
	{
		log.debug(String.format("Looking for user by Id %s", id));
		Language language = languageRepository.findOne(id);

		if (language != null)
		{
			return new LanguageWO(language);
		}
		return null;
	}

	public void saveLanguage(LanguageWO languageWO)
	{
		Language language = new Language();
		language.setName(languageWO.getlanguageName());
		language.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		languageRepository.save(language);
	}

	public void updateLanguage(LanguageWO languageWO)
	{
		Language language2update = languageRepository.findOne((byte)languageWO.getLanguageId());
		language2update.setName(languageWO.getlanguageName());
		language2update.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		languageRepository.save(language2update);
	}

	@Override
	public void deleteLanguageById(byte id)
	{
		languageRepository.delete(id);
	}

}
