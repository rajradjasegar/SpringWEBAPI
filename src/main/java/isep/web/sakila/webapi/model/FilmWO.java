package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.FilmActor;
import isep.web.sakila.jpa.entities.FilmCategory;

public class FilmWO {
	private static final long	serialVersionUID	= -1377067679473844279L;

	protected int filmId;
	protected String title;
	protected String description;
	
	public FilmWO()
	{
		super();
	}

	public FilmWO(int filmId, String title, String description)
	{
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
	}

	public FilmWO(final Film film)
	{
		super();
		this.filmId = film.getFilmId();
		this.title = film.getTitle();
		this.description = film.getDescription();
	}

	public String getTitle()
	{
		return title;
	}

	public int getFilmId()
	{
		return filmId;
	}

	public String getDescription()
	{
		return description;
	}

	public void setTitle (String title)
	{
		this.title= title;
	}

	public void setFilmId(int filmId)
	{
		this.filmId = filmId;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String toString()
	{
		return "Film [id=" + this.filmId + ", Title =" + this.title+ ", Description =" + this.description+ "]";
	}
}
