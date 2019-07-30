package entities;

public class Periodical extends Print{

    // Уникальный номер периодического издания, включает в себя код страны и издательства
    private String issn;
    // Формат переодического издания - газета, журнал
    private PeriodicalFormat format;
    // Жанр переодического издания
    private PeriodicalGenre genre;

    public Periodical() {
        super(PrintType.PERIODICAL);
        this.setTakeaway(false);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Periodical print = (Periodical) obj;
        return super.equals(obj)
                && (issn == print.getIssn() || (issn != null && issn.equals(print.getIssn())))
                && (genre == print.getGenre() || (genre != null && genre.equals(print.getGenre())))
                && (format == print.getFormat() || (format != null && format.equals(print.getFormat())));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;

        res = prime * res + super.hashCode();
        if (format != null) res = prime*res + format.hashCode();
        if (issn != null) res = prime*res + issn.hashCode();

        return res;
    }

    @Override
    public String toString() {
        return "Periodical { " +
                super.toString() +
                ", '" + issn + '\'' +
                ", " + genre +
                " " + format +
                " }";
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public PeriodicalFormat getFormat() {
        return format;
    }

    public void setFormat(PeriodicalFormat format) {
        this.format = format;
    }

    public PeriodicalGenre getGenre() {
        return genre;
    }

    public void setGenre(PeriodicalGenre genre) {
        this.genre = genre;
    }

}
