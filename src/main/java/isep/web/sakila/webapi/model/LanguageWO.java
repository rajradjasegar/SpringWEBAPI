package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Language;

public class LanguageWO extends WebObject
{

	private static final long	serialVersionUID	= -1377067679473844279L;

	protected int languageId;
	protected String languageName;

	public LanguageWO()
	{
		super();
	}

	public LanguageWO(int languageId, String languageName, String firstName)
	{
		super();
		this.languageId = languageId;
		this.languageName = languageName;
	}

	public LanguageWO(final Language language)
	{
		super();
		this.languageId = language.getLanguageId();
		this.languageName = language.getName();
	}

	public int getLanguageId()
	{
		return languageId;
	}

	public String getlanguageName()
	{
		return languageName;
	}

	public void setLanguageId(int languageId)
	{
		this.languageId = languageId;
	}

	public void setlanguageName(String languageName)
	{
		this.languageName = languageName;
	}

	@Override
	public String toString()
	{
		return "Language [id=" + this.languageId + ", LanguageNanem=" + this.languageName + "]";
	}
}
