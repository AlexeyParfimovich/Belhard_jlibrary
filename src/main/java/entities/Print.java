package entities;

import java.util.Date;

/**
 * The {@code Pring} class is abstract class represents print publication.
 * @author  Alexey
 * @author  Evgeniy
 * @see     entities.PrintType
 * @since   1.0
 */

public abstract class Print {
    /**
     * Уникальный идентификаток издания в БД
     * если объект не добавлен в БД, идентификаток = NULL
     */
    private Long id;
    // Наименование печатного издания
    private String title;
    // Дата издания
    private Date date;

    /**
     * Тип издания (объекта)
     * задается один раз при создании объекта
     */
    final private PrintType type;

    private Boolean isAvailable;
    private Boolean isTakeaway;

    Print(PrintType type) {
        this.type = type;
        this.isAvailable = true;
        this.isTakeaway = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Print print = (Print)obj;
        return (id == print.getId() || (id != null && id.equals(print.getId())))
                && (title == print.getTitle() || (title != null && title.equals(print.getTitle())))
                && (type == print.getType() || (type != null && type.equals(print.getType())))
                && (date == print.getDate() || (date != null && date.equals(print.getDate())));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int res = 1;

        if (id != null) res = prime * res + id.intValue();
        if (title != null) res = prime*res + title.hashCode();

        return res;
    }

    @Override
    public String toString() {
        return "Print{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", date=" + date + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PrintType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Boolean getTakeaway() {
        return isTakeaway;
    }

    public void setTakeaway(Boolean takeaway) {
        isTakeaway = takeaway;
    }
}
