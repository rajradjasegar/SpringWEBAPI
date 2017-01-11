package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.FilmRepository;
import isep.web.sakila.dao.repositories.FilmRepository;
import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.webapi.model.FilmWO;
import isep.web.sakila.webapi.model.FilmWO;


@Service("filmService")
@Transactional
public class FilmServiceImpl implements FilmService {

	@Autowired
	private FilmRepository	filmRepository;

	private static final Log log = LogFactory.getLog(FilmServiceImpl.class);

	public List<FilmWO> findAllFilms()
	{
		List<FilmWO> films = new LinkedList<FilmWO>();

		for (Film film : filmRepository.findAll())
		{
			films.add(new FilmWO(film));
			log.debug("Adding " + film);
		}

		return films;
	}

	public FilmWO findById(int id)
	{
		log.debug(String.format("Looking for film by Id %s", id));
		Film film = filmRepository.findOne(id);

		if (film != null)
		{
			return new FilmWO(film);
		}
		return null;
	}

	public void saveFilm(FilmWO filmWO)
	{
		Film film = new Film();
		film.setTitle(filmWO.getTitle());
		film.setDescription(filmWO.getDescription());
		film.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		filmRepository.save(film);
	}

	public void updateFilm(FilmWO filmWO)
	{
		Film film2update = filmRepository.findOne(filmWO.getFilmId());
		film2update.setTitle(filmWO.getTitle());
		film2update.setDescription(filmWO.getDescription());
		film2update.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		filmRepository.save(film2update);
	}

	@Override
	public void deleteFilmById(int id)
	{
		filmRepository.delete(id);
	}


}
