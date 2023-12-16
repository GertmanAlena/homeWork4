package org.example.models;

import javax.persistence.*;
import java.util.Random;

@Entity
@Table(name = "Courses")
public class School {

    //region Поля

    private static final String[] titles = new String[] { "Право", "Бух", "Эконом", "Програм", "Полит", "Ветеринар"};
    private static final Double[] durations = new Double[] {1.2, 1.5, 2.0, 2.5};
    private static final Random random = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "duration")
    private double duration;

    //endregion

    //region Конструкторы
    public School() {
    }

    public School(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public School(int id, String title, double duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }
    //endregion

    //region вспомогательные методы create updateDuration updateTitle
    public static School create(){
        return new School(titles[random.nextInt(titles.length)], durations[random.nextInt(durations.length)]);
    }

    public void updateDuration(){
        duration = durations[random.nextInt(durations.length)];
    }

    public void updateTitle(){
        title = titles[random.nextInt(titles.length)];
    }
    //endregion


    //region Getter Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
    //endregion

    @Override
    public String toString() {
        return "School {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
