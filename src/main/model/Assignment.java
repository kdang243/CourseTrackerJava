package model;

public class Assignment {
    String name;
    double score;

    //REQUIRES: percentage > 0
    //EFFECTS: Contructs an assignment with given name and score in percentage
    public Assignment(String title, double percentage) {
        name = title;
        score = percentage;
    }


    //GETTERS AND SETTERS
    public double getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setScore(double percentage) {
        this.score = percentage;
    }

    public void setName(String title) {
        this.name = title;
    }
}
